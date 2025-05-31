package ProcessEngine.ProcessCore.validatorModule.fieldValidators;

import ProcessEngine.ProcessCore.validatorModule.Validator;
import moduls.TicketType;

public class TypeValidator implements Validator {
    @Override
    public boolean validate(String line){
        try {
            if (line.isEmpty()) {
                return true;
            }

            TicketType.valueOf(line);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] Значение поля введено неверно");
        }
        return false;
    }
}
