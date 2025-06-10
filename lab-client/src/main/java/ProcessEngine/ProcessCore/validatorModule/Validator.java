package ProcessEngine.ProcessCore.validatorModule;

public interface Validator {
    boolean validate(String line);

    String message();
}
