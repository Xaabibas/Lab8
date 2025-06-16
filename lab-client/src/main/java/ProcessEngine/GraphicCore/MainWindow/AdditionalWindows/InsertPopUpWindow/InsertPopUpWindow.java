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
import ProcessEngine.ProcessCore.networkModule.NetworkManager;
import network.Request;
import moduls.*;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.util.Arrays;

public class InsertPopUpWindow {

    public static Stage insertWindow(NetworkManager networkManager, String login, String password) {
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
        TextField birthdayField = TextFieldFactory.getFieldWithValidator("birthday [ year.month.day.hour.minute.second ]", new DateValidator());
        TextField countryField = TextFieldFactory.getFieldWithValidator("country", new CountryValidator());
        TextField eyeField = TextFieldFactory.getFieldWithValidator("eye color", new EyeValidator());
        TextField hairField = TextFieldFactory.getFieldWithValidator("hair color", new HairValidator());

        textBox.getChildren().addAll(
            keyField, 
            nameField, 
            xField, 
            yField, 
            priceField, 
            typeField,
            personData, 
            birthdayField, 
            countryField, 
            eyeField, 
            hairField
        );
        Button commit = ButtonFactory.getCommitButton();

        Label error = new Label("Введите корректные данные!");
        error.setTextFill(Color.RED);

        commit.setOnAction(
                event -> {
                    try {
                        long key = Long.parseLong(keyField.getText());
                        String name = nameField.getText();
                        Coordinates coord = new Coordinates(Float.parseFloat(xField.getText()), Long.parseLong(yField.getText()));
                        float price = Float.parseFloat(priceField.getText());
                        TicketType type = typeField.getText() == null ? null : TicketType.valueOf(typeField.getText());
                        LocalDateTime birthday;
                        String line = birthdayField.getText();
                        if (!line.isEmpty()) {
                            String[] data = line.split("\\.");
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

                        Request insertRequest = new Request();
                        insertRequest.setUser(login);
                        insertRequest.setPassword(Arrays.toString(password
                            .chars()
                            .mapToObj(c -> String.valueOf((char) c))
                            .toArray(String[]::new))
                        );
                        insertRequest.setObj(ticket);
                        insertRequest.setCommandName("insert");
                        insertRequest.setTokens("insert" + " " + key);
                        String netAnswer = networkManager.sendAndReceive(insertRequest);

                        if (netAnswer.equals("[ERROR] Данное значение уже является ключом")) {
                            mainLabel.setFont(Font.font("System", FontWeight.BOLD, 19));
                            mainLabel.setTextFill(javafx.scene.paint.Color.RED);
                            mainLabel.setText("Уже существуюет элемент с таким ключом");
                        } else {
                            mainLabel.setFont(Font.font("System", FontWeight.BOLD, 19));
                            mainLabel.setTextFill(javafx.scene.paint.Color.FORESTGREEN);
                            mainLabel.setText("Insert your data");
                        }

                    } catch (Exception e) {
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
