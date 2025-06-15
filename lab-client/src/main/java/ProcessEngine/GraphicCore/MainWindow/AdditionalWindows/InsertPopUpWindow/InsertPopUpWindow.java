package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.InsertPopUpWindow;

import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.BoxFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.ButtonFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.LabelFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.TextFieldFactory;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.CountryValidator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.DateValidator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.EyeValidator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.HairValidator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.KeyValidator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.NameValidator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.PriceValidator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.TypeValidator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.XValidator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.YValidator;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import moduls.*;

import java.security.spec.ECField;
import java.time.LocalDateTime;

public class InsertPopUpWindow {

    public static Stage insertWindow() {
        Stage stage = new Stage();

        Label mainLabel = LabelFactory.getMainLabel("Insert your data");
        VBox textBox = BoxFactory.getTextBox();
        TextField keyField = TextFieldFactory.getFieldWithValidator("key", new KeyValidator());
        TextField nameField = TextFieldFactory.getFieldWithValidator("name", new NameValidator());
        TextField xField = TextFieldFactory.getFieldWithValidator("x", new XValidator());
        TextField yField = TextFieldFactory.getFieldWithValidator("y", new YValidator());
        TextField priceField = TextFieldFactory.getFieldWithValidator("price", new PriceValidator());
        TextField typeField = TextFieldFactory.getFieldWithValidator("type", new TypeValidator());
        Label personData = LabelFactory.fetUsualLabel("Person Data");
        TextField birthdayField = TextFieldFactory.getFieldWithValidator("birthday", new DateValidator());
        TextField countryField = TextFieldFactory.getFieldWithValidator("country", new CountryValidator());
        TextField eyeField = TextFieldFactory.getFieldWithValidator("eye color", new EyeValidator());
        TextField hairField = TextFieldFactory.getFieldWithValidator("hair color", new HairValidator());

        textBox.getChildren().addAll(keyField, nameField, xField, yField, priceField, typeField,
                personData, birthdayField, countryField, eyeField, hairField);
        Button commit = ButtonFactory.getCommitButton();

        Label error = new Label("Введите корректные данные!");
        error.setTextFill(Color.RED);

        commit.setOnAction(
                event -> {
                    try {
                        // Надо будет еще раз перепроверить все ли данные корректны, если нет кидать exception
                        Long key = Long.parseLong(keyField.getText());
                        String name = nameField.getText();
                        Coordinates coord = new Coordinates(Float.parseFloat(xField.getText()), Long.parseLong(yField.getText()));
                        float price = Float.parseFloat(priceField.getText());
                        TicketType type = typeField.getText() == null ? null : TicketType.valueOf(typeField.getText());
                        LocalDateTime birthday;
                        String line = birthdayField.getText();
                        if (!line.isEmpty()) {
                            String[] data = line.split("\\s*;\\s*");
                            int[] date = new int[6];

                            for (int i = 0; i < 6; i++) {
                                if (data[i].isEmpty()) {
                                    continue;
                                }
                                date[i] = Integer.parseInt(data[i]);
                            }
                            birthday = LocalDateTime.of(date[0], date[1], date[2], date[3], date[4], date[5]);
                        } else {
                            birthday = null;
                        }
                        Country country = countryField.getText() == null ? null : Country.valueOf(countryField.getText());
                        EyeColor eye = eyeField.getText() == null ? null : EyeColor.valueOf(eyeField.getText());
                        HairColor hair = hairField.getText() == null ? null : HairColor.valueOf(hairField.getText());

                        Ticket ticket = new Ticket();
                        ticket.setName(name);
                        ticket.setCoordinates(coord);
                        ticket.setType(type);
                        ticket.setPrice(price);
                        Person person = new Person();
                        person.setBirthday(birthday);
                        person.setNationality(country);
                        person.setEyeColor(eye);
                        person.setHairColor(hair);
                        ticket.setPerson(person);

                        // отправить ticket в коллекцию по key
                    } catch (Exception e) {
                        // Наверное стоит подумать какой exception может вылететь, но попозже
                        if (!textBox.getChildren().contains(error)) {
                            textBox.getChildren().add(error);
                        }
                    }
                }
        );

        VBox box = BoxFactory.getPopUpBox();
        box.getChildren().addAll(mainLabel, textBox, commit);
        Scene scene = new Scene(box, 500, 600);

        stage.setScene(scene);

        return stage;
    }

}
