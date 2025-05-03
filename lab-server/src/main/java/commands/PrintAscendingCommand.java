package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import network.Request;
import network.Response;

import java.sql.SQLException;
import java.util.stream.Collectors;

public class PrintAscendingCommand extends Command {
    public PrintAscendingCommand(CollectionManager cm) {
        super(cm);
    }

    @Override
    public String describe() {
        return "print_ascending - вывод элементов коллекции по возрастанию ";
    }

    @Override
    public String rightFormat() {
        return "print_ascending";
    }

    @Override
    public Response execute(Request request) {
        if (request.getTokens().length!=1) {
            return Response.wrongCount();
        }
        try {
            if (!this.getCm().getDbManager().checkUserPassword(request.getUser(), request.getPassword())) {
                return Response.wrongPassword();
            }
            this.getCm().sortByPrice();

            String answer = this.getCm().getCollection().keySet().stream().map(
                    k -> k + " - " + this.getCm().getCollection().get(k).toString() + "\n"
            ).collect(Collectors.joining());
            return new Response(answer);
        } catch (SQLException e) {
            return new Response("[ERROR} Не удалось обратиться к БД");
        }
    }
}