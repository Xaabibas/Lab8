package managers;

import commands.abstraction.Command;
import network.Request;
import network.Response;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private final Map<String, Command> commands;

    public CommandManager() {
        this.commands = new HashMap<>();
    }

    public Response processRequest(Request request) {
        Response response;
        try {
            Command command = commands.get(request.getCommandName()); // Получаем команду
            response = command.execute(request); // Формируем ответ
        } catch (NullPointerException e) {
            response = new Response("[ERROR] Такая команда не зарегистрирована. Для справки введите help");
        }
        return response;
    }

    public void registerCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
