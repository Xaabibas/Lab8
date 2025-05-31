import managers.*;
import network.Pack;
import network.Request;

import network.Response;

import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public final class Server extends Thread {
    private final RequestManager requestManager;
    private final ResponseManager responseManager;
    private final NetworkManager networkManager;
    private final CommandManager commandManager;
    private final ExecutorService pool = Executors.newCachedThreadPool();
    private final ConcurrentLinkedQueue<Pack> queue = new ConcurrentLinkedQueue<>();

    public Server(RequestManager requestManager, ResponseManager responseManager,
                  NetworkManager networkManager, CommandManager commandManager) {
        this.requestManager = requestManager;
        this.responseManager = responseManager;
        this.networkManager = networkManager;
        this.commandManager = commandManager;
    }

    @Override
    public void run() {
        new Thread(() -> {
            while (true) {
                if (!queue.isEmpty()) {
                    Pack pack = queue.poll();
                    Request request = pack.getRequest();
                    Socket client = pack.getClient();

                    AtomicReference<Response> response = new AtomicReference<>(new Response());

                    Thread processThread = new Thread(() -> {
                        response.set(commandManager.processRequest(request));
                    });
                    processThread.start();

                    Thread sendThread = new Thread(() -> {
                        try {
                            processThread.join();
                            responseManager.sendToClient(response.get(), client);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    sendThread.start();
                }
            }
        }).start();

        while (true) {
            Socket client = networkManager.connectToClient();

            pool.submit(() -> {
                Request request = requestManager.readRequest(client);
                queue.add(new Pack(request, client));
            });
        }
    }
}