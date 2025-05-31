package ProcessEngine.ProcessCore.enteratorModule;

import moduls.Person;

import java.util.Scanner;

import ProcessEngine.ProcessCore.validatorModule.fieldValidators.CountryValidator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.DateValidator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.EyeValidator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.HairValidator;

public class EnterPerson implements ComplexEnterator<Person> {
    public Person enter(Scanner scanner) {
        System.out.println("Ведите значения для поля person:");
        return new Person(new EnterLocalDateTime().enter(scanner, new DateValidator()),
                new EnterEyeColor().enter(scanner, new EyeValidator()),
                new EnterHairColor().enter(scanner, new HairValidator()),
                new EnterCountry().enter(scanner, new CountryValidator()));
    }
}
