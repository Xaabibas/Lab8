package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import managers.NetworkManager;
import network.Request;
import network.Response;

public class ExitCommand extends Command {
    public ExitCommand(CollectionManager cm) {
        super(cm);
    }

    @Override
    public String describe() {
        return "exit - завершение программы";
    }

    @Override
    public String rightFormat() {
        return "exit";
    }

    @Override
    public Response execute(Request request) {
        NetworkManager.logger.info("Работа клиентского приложения завершена");
        return new Response("");
    }
}
