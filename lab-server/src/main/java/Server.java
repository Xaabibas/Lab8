import managers.Invoker;

import commands.*;
import managers.CollectionManager;

import java.util.Scanner;

public final class Server {


    private Server() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CollectionManager cm = new CollectionManager();

        Invoker invoker = new Invoker(scanner);
        invoker.registerCommand("clear", new ClearCommand(cm));
        invoker.registerCommand("help", new HelpCommand(cm, invoker));
        invoker.registerCommand("info", new InfoCommand(cm));
        invoker.registerCommand("show", new ShowCommand(cm));
        invoker.registerCommand("insert", new InsertCommand(cm, invoker.getScanner()));
        invoker.registerCommand("update", new UpdateCommand(cm, invoker.getScanner()));
        invoker.registerCommand("remove_key", new RemoveByKeyCommand(cm));
        invoker.registerCommand("clear", new ClearCommand(cm));
        invoker.registerCommand("save", new SaveCommand(cm));
        invoker.registerCommand("execute_script", new ExecuteScriptCommand(cm, invoker));
        invoker.registerCommand("exit", new ExitCommand(cm));
        invoker.registerCommand("remove_greater", new RemoveGreaterCommand(cm, invoker.getScanner()));
        invoker.registerCommand("remove_lower", new RemoveLowerCommand(cm, invoker.getScanner()));
        invoker.registerCommand("remove_lower_key", new RemoveLowerByKeyCommand(cm));
        invoker.registerCommand("sum_of_price", new SumOfPriceCommand(cm));
        invoker.registerCommand("count_by_type", new CountByTypeCommand(cm));
        invoker.registerCommand("print_ascending", new PrintAscendingCommand(cm));

        invoker.interactiveMode();
    }
}
