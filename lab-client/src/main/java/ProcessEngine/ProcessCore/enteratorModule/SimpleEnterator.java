package ProcessEngine.ProcessCore.enteratorModule;

import java.util.Scanner;

import ProcessEngine.ProcessCore.validatorModule.Validator;

public interface SimpleEnterator<T> {
    T enter(Scanner scanner, Validator validator);
}
