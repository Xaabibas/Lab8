package ProcessEngine.ProcessCore.validatorModule.fieldValidators;

import ProcessEngine.GraphicCore.GraphicRun;
import ProcessEngine.ProcessCore.validatorModule.Validator;
import moduls.TicketType;

import java.util.Arrays;

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

    @Override
    public String message() {
        StringBuilder message = new StringBuilder();
        message.append(GraphicRun.localizator.getString("correct values"));
        Arrays.stream(TicketType.values()).forEach(a -> message.append(a.toString()).append(" "));
        message.append(GraphicRun.localizator.getString("can be null"));
        return message.toString();
    }
}
