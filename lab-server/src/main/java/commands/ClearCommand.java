package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import network.Request;
import network.Response;

import java.sql.SQLException;

public class ClearCommand extends Command {
    public ClearCommand(CollectionManager cm) {
        super(cm);
    }

    @Override
    public String describe() {
        return "clear - очистка коллекции (удаление всех элементов, принадлежащих данному пользователю)";
    }

    @Override
    public String rightFormat() {
        return "clear";
    }

    @Override
    public Response execute(Request request) {
        if (request.getTokens().length > 1) {
            return Response.wrongCount();
        }
        try {
            if (!this.getCm().getDbManager().checkUserPassword(request.getUser(), request.getPassword())) {
                return Response.wrongPassword();
            }
            this.getCm().clear(request.getUser());
            return new Response("Коллекция успешно очищена");
        } catch (SQLException e) {
            return new Response("Не удалось очистить коллекцию");
        }
    }
}
