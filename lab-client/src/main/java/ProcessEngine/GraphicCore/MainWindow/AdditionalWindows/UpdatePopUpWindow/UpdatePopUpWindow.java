package ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.UpdatePopUpWindow;

import ProcessEngine.ProcessCore.networkModule.NetworkManager;
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
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.*;
import moduls.Coordinates;
import moduls.Country;
import moduls.EyeColor;
import moduls.HairColor;
import moduls.Person;
import moduls.Ticket;
import moduls.TicketType;
import network.Request;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UpdatePopUpWindow {

    public static Stage updateWindow(NetworkManager networkManager, String login, String password, Vector<String[]> collection) {
        Stage stage = new Stage();
        Label mainLabel = LabelFactory.getMainLabel("Insert key");

        VBox textBox = BoxFactory.getTextBox();
        TextField keyField = TextFieldFactory.getFieldWithValidator("key", new KeyValidator());
        Label label = new Label("");
        LabelFactory.toErrorLabel(label);
        textBox.getChildren().addAll(keyField, label);

        Button button = ButtonFactory.getCommitButton();
        button.setOnAction(
                event -> {
                    try {
                        long key = Long.parseLong(keyField.getText());
                        Set<Long> keys = collection.stream().map(strings -> Long.parseLong(strings[0])).collect(Collectors.toSet());
                        if (keys.contains(key)) {
                            String[] values = collection.stream().filter(strings -> key == Long.parseLong(strings[0])).collect(toSingleton());
                            Stage second = secondWindow(networkManager, login, password, values, key);
                            second.show();
                            stage.close();
                        } else {
                            label.setText("Нет элемента с заданным key");
                        }
                    } catch (IllegalArgumentException e) {
                        label.setText("Введите корректный key");
                    }
                }
        );

        VBox box = BoxFactory.getPopUpBox();
        box.getChildren().addAll(mainLabel, textBox, button);

        Scene scene = new Scene(box, 400, 400);
        stage.setScene(scene);
        return stage;
    }

    private static Stage secondWindow(NetworkManager networkManager, String login, String password, String[] values, long key) {
        Stage stage = new Stage();
        // [ [key, id, name, Coordinates.x, Coordinates.y, creationDate, price, type, Person.birthday, Person.eyeColor, Person.hairColor, Person.nationality] ]

        Label mainLabel = LabelFactory.getMainLabel("Insert your data");
        VBox textBox = BoxFactory.getTextBox();
        TextField nameField = TextFieldFactory.getFieldWithValidator("name", new NameValidator());
        nameField.setText(values[2]);
        TextField xField = TextFieldFactory.getFieldWithValidator("x", new XValidator());
        xField.setText(values[3]);
        TextField yField = TextFieldFactory.getFieldWithValidator("y", new YValidator());
        yField.setText(values[4]);
        TextField priceField = TextFieldFactory.getFieldWithValidator("price", new PriceValidator());
        priceField.setText(values[6]);
        TextField typeField = TextFieldFactory.getFieldWithValidator("type", new TypeValidator());
        typeField.setText(values[7].equals("null") ? "" : values[7]);
        Label personData = LabelFactory.getUsualLabel("Person Data");
        TextField birthdayField = TextFieldFactory.getFieldWithValidator("birthday [ year.month.day.hour.minute.second ]", new DateValidator());
        birthdayField.setText(values[8].equals("null") ? "" : format(values[8]));
        TextField countryField = TextFieldFactory.getFieldWithValidator("country", new CountryValidator());
        countryField.setText(values[11].equals("null") ? "" : values[11]);
        TextField eyeField = TextFieldFactory.getFieldWithValidator("eye color", new EyeValidator());
        eyeField.setText(values[9].equals("null") ? "" : values[9]);
        TextField hairField = TextFieldFactory.getFieldWithValidator("hair color", new HairValidator());
        hairField.setText(values[10].equals("null") ? "" : values[10]);
        Label label = new Label();

        textBox.getChildren().addAll(
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
                        insertRequest.setCommandName("update");
                        insertRequest.setTokens("update" + " " + key);
                        String netAnswer = networkManager.sendAndReceive(insertRequest);

                        if (!netAnswer.equals("Элемент был успешно обновлен")) {
                            label.setText("Нет прав доступа");
                            LabelFactory.toErrorLabel(label);
                        } else {
                            label.setText(netAnswer);
                            LabelFactory.toResultLabel(label);
                        }

                    } catch (Exception e) {
                        label.setText("Введите корректные данные");
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

    public static <T> Collector<T, ?, T> toSingleton() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() != 1) {
                        throw new IllegalStateException();
                    }
                    return list.get(0);
                }
        );
    }

    private static String format(String date) {
        String regex = "\\d+";
        StringBuilder result = new StringBuilder();
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(date);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            result.append(date, start, end).append(".");
        }
        String out = result.toString();

        return out.substring(0, out.length() - 1);
    }
    
}
