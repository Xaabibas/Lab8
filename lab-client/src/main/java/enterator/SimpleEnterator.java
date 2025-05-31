package enterator;

import validator.Validator;

import java.util.Scanner;

public interface SimpleEnterator<T> {
    T enter(Scanner scanner, Validator validator);
}
