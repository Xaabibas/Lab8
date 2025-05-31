package ProcessEngine.ProcessCore.enteratorModule;

import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;

public class EnterPassword implements ComplexEnterator<String> {
    @Override
    public String enter(Scanner scanner) {
        String password = "";
        Console console = System.console();
        while (password.isEmpty()) {
            System.out.print("Введите пароль > ");
            password = Arrays.toString(console.readPassword());
        }
        return password;
    }
}
