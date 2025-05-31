package ProcessEngine.ProcessCore.enteratorModule;

import java.util.Scanner;

import ProcessEngine.ProcessCore.validatorModule.Validator;

public class EnterName implements SimpleEnterator<String> {
    public String enter(Scanner scanner, Validator validator) {
        while (true) {
            System.out.print("Введите значение поля name > ");
            String line = scanner.nextLine();
            if (validator.validate(line)) {
                return line;
            }
        }
    }
}
