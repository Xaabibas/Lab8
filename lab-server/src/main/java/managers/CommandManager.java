package managers;

import commands.abstraction.Command;
import network.Request;
import network.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

public class CommandManager {
    public static final Logger logger = Logger.getLogger("CommandLogger");
    private final Map<String, Command> commands;
    private final Object lock = new Object();

    public CommandManager() {
        this.commands = new HashMap<>();
    }

    public synchronized Response processRequest(Request request) {
        AtomicReference<Response> response = new AtomicReference<>(new Response());
        Runnable runnable = () -> {
            synchronized (lock) {
                logger.info("Beginning of request processing");
                try {
                    Command command = commands.get(request.getCommandName()); // Получаем команду

                    response.set(command.execute(request)); // Формируем ответ
                    logger.info("The request was successfully processed and a response was generated");
                } catch (NullPointerException e) {
                    logger.info("An unregistered command was transferred");
                    response.set(new Response("[ERROR] Такая команда не зарегистрирована. Для справки введите help"));
                }
                lock.notify();
            }
        };
        new Thread(runnable).start();

        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return response.get();
    }

    public void registerCommand(String commandName, Command command) {
        commands.put(commandName, command);
        logger.info("Command " + commandName + " was registered");
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
