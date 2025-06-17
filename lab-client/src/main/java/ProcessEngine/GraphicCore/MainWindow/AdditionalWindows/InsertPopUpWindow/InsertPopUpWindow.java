package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.InsertPopUpWindow;

import ProcessEngine.GraphicCore.GraphicRun;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.BoxFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.ButtonFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.LabelFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.TextFieldFactory;
import ProcessEngine.GraphicCore.SignWindow.SignWindow;
import ProcessEngine.ProcessCore.networkModule.NetworkManager;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import moduls.*;
import network.Request;

import java.time.LocalDateTime;
import java.util.Arrays;

public class InsertPopUpWindow {

    public static Stage insertWindow(NetworkManager networkManager, String login, String password) {
        Stage stage = new Stage();

        Label mainLabel = LabelFactory.getMainLabel(GraphicRun.localizator.getString("insert data"));
        VBox textBox = BoxFactory.getTextBox();
        TextField keyField = TextFieldFactory.getFieldWithValidator(GraphicRun.localizator.getString("key"), new KeyValidator());
        TextField nameField = TextFieldFactory.getFieldWithValidator(GraphicRun.localizator.getString("name"), new NameValidator());
        TextField xField = TextFieldFactory.getFieldWithValidator("x", new XValidator());
        TextField yField = TextFieldFactory.getFieldWithValidator("y", new YValidator());
        TextField priceField = TextFieldFactory.getFieldWithValidator(GraphicRun.localizator.getString("price"), new PriceValidator());
        TextField typeField = TextFieldFactory.getFieldWithValidator(GraphicRun.localizator.getString("type"), new TypeValidator());
        Label personData = LabelFactory.getUsualLabel(GraphicRun.localizator.getString("person data"));
        TextField birthdayField = TextFieldFactory.getFieldWithValidator(GraphicRun.localizator.getString("birthday"), new DateValidator());
        TextField countryField = TextFieldFactory.getFieldWithValidator(GraphicRun.localizator.getString("country"), new CountryValidator());
        TextField eyeField = TextFieldFactory.getFieldWithValidator(GraphicRun.localizator.getString("eye color"), new EyeValidator());
        TextField hairField = TextFieldFactory.getFieldWithValidator(GraphicRun.localizator.getString("hair color"), new HairValidator());
        Label label = LabelFactory.getErrorLabel("");

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
                hairField,
                label
        );
        Button commit = ButtonFactory.getCommitButton();


        commit.setOnAction(
                event -> {
                    try {
                        long key = Long.parseLong(keyField.getText());
                        String name = nameField.getText();
                        Coordinates coord = new Coordinates(Float.parseFloat(xField.getText()), Long.parseLong(yField.getText()));
                        float price = Float.parseFloat(priceField.getText());
                        TicketType type = typeField.getText().isEmpty() ? null : TicketType.valueOf(typeField.getText());
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
                        Country country = countryField.getText().isEmpty() ? null : Country.valueOf(countryField.getText());
                        EyeColor eye = eyeField.getText().isEmpty() ? null : EyeColor.valueOf(eyeField.getText());
                        HairColor hair = hairField.getText().isEmpty() ? null : HairColor.valueOf(hairField.getText());

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
                            label.setText("Уже существуюет элемент с таким ключом");
                            LabelFactory.toErrorLabel(label);
                        } else {
                            label.setText(GraphicRun.localizator.getString("success insert"));
                            LabelFactory.toResultLabel(label);
                        }
                    } catch (Exception e) {
                        label.setText(GraphicRun.localizator.getString("insert correct values"));
                        LabelFactory.toErrorLabel(label);
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
