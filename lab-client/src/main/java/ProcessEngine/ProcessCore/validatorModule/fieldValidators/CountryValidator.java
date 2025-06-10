package ProcessEngine.ProcessCore.validatorModule.fieldValidators;

import ProcessEngine.ProcessCore.validatorModule.Validator;
import moduls.Country;

import java.util.Arrays;

public class CountryValidator implements Validator {
    @Override
    public boolean validate(String line) {
        try {
            if (line.isEmpty()) {
                return true;
            }
            Country.valueOf(line);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] Введите корректное значение");
        }
        return false;
    }

    @Override
    public String message() {
        StringBuilder message = new StringBuilder();
        message.append("Корректные значения: ");
        Arrays.stream(Country.values()).forEach(a -> message.append(a.toString()).append(" "));
        message.append(". Может быть null");
        return message.toString();
    }
}
