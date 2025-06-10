package ProcessEngine.GraphicCore.MainWindow;

import ProcessEngine.ProcessCore.validatorModule.Validator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainWindow {

    private static class ButtonFactory {
        protected static Button getCommandButton(String name) {
            Button button = new Button(name);
            button.setPrefWidth(150);

            return button;
        }
        protected static Button getCommitButton() {
            Button button = new Button("Commit");
            button.setPrefWidth(150);

            return button;
        }
    }

    private static class BoxFactory {
        protected static HBox getBoxWithButtons(Button... buttons) {
            HBox box = new HBox();
            box.setSpacing(17);
            box.setAlignment(Pos.CENTER);

            box.getChildren().addAll(buttons);

            return box;
        }

        protected static VBox getPopUpBox(Node... es) {
            VBox box = new VBox();
            box.setBackground(new Background(new BackgroundFill(Color.KHAKI, new CornerRadii(5), Insets.EMPTY)));
            box.setAlignment(Pos.CENTER);
            box.getChildren().addAll(es);

            return box;
        }
    }

    private static class TextFieldFactory {
        protected static TextField getFieldWithValidator(String text, Validator validator) {
            TextField field = new TextField();
            field.setPromptText(text);
            field.setPrefWidth(180);

            field.focusedProperty().addListener(
                    (arg0, oldValue, newValue) -> {
                        if (!newValue) {
                            if (!validator.validate(field.getText())) {
                                field.setText("");
                            }
                        }
                    }
            );

            return field;
        }
    }

    public void window(Stage stage) {
        BorderPane root = new BorderPane();

        root.setBackground(new Background(new BackgroundFill(Color.MINTCREAM, new CornerRadii(5), Insets.EMPTY)));

        VBox commands = commands();

        HBox upLine = upLine();

        GridPane sheet = sheet();

        root.setTop(upLine);
        root.setLeft(commands);
        root.setCenter(sheet);

        Scene scene = new Scene(root, 1000, 700);

        stage.setScene(scene);
        stage.show();
    }

    private VBox commands() { // Блок команд
        VBox commands = new VBox();
        commands.setAlignment(Pos.CENTER);
        commands.setSpacing(17);
        commands.setPadding(new Insets(30, 30, 30, 30));

        Button update = ButtonFactory.getCommandButton("update"); // Написать setOnAction
        Button insert = ButtonFactory.getCommandButton("insert"); // Написать setOnAction
        insert.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Stage stage = insertWindow();

                        stage.show();
                    }
                }
        );

        HBox first = BoxFactory.getBoxWithButtons(update, insert);

        Button removeGreater = ButtonFactory.getCommandButton("remove greater"); // Написать setOnAction
        Button removeLower = ButtonFactory.getCommandButton("remove lower"); // Написать setOnAction
        HBox second = BoxFactory.getBoxWithButtons(removeGreater, removeLower);
        second.setPadding(new Insets(17, 0, 0, 0));

        Button removeLowerKey = ButtonFactory.getCommandButton("remove lower by key"); // Написать setOnAction
        Button removeKey = ButtonFactory.getCommandButton("remove by key"); // Написать setOnAction
        HBox third = BoxFactory.getBoxWithButtons(removeLowerKey, removeKey);
        third.setPadding(new Insets(0, 0, 17, 0));

        Button sum = ButtonFactory.getCommandButton("sum of price"); // Написать setOnAction
        Button count = ButtonFactory.getCommandButton("count by type"); // Написать setOnAction
        HBox fourth = BoxFactory.getBoxWithButtons(sum, count);

        Button printAscending = ButtonFactory.getCommandButton("print ascending"); // Написать setOnAction
        Button clear = ButtonFactory.getCommandButton("clear"); // Написать setOnAction
        HBox fifth = BoxFactory.getBoxWithButtons(printAscending, clear);

        commands.getChildren().addAll(first, second, third, fourth, fifth);

        return commands;
    }

    private GridPane sheet() { // Должно отвечать за отображение таблички
        GridPane sheet = new GridPane();
        sheet.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(1))));

        return sheet;
    }

    private HBox upLine() {
        HBox upLine = new HBox();
        upLine.setSpacing(30);
        upLine.setPadding(new Insets(1, 15, 1, 0));
        upLine.setAlignment(Pos.CENTER_RIGHT);
        upLine.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(0), Insets.EMPTY)));

        Button language = new Button("language"); // setOnAction? Возможность смены языка / или выбирать язык сначала и потом его просто отображать
        language.setFont(new Font(14));
        Label name = new Label("name"); // Как-то отображать имя
        name.setFont(new Font(14));
        Hyperlink logOut = new Hyperlink("log out"); // setOnAction Выход в стартовое окно
        logOut.setTextFill(Color.RED);
        logOut.setFont(new Font(14));

        upLine.getChildren().addAll(language, name, logOut);

        return upLine;
    }

    private Stage insertWindow() {
        Stage stage = new Stage();

        Label mainLabel = new Label("Insert your data");
        TextField key = TextFieldFactory.getFieldWithValidator("key", (line) -> {
            try {
                Long.parseLong(line);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        });
        TextField name = TextFieldFactory.getFieldWithValidator("name", new NameValidator());
        TextField x = TextFieldFactory.getFieldWithValidator("x", new XValidator());
        TextField y = TextFieldFactory.getFieldWithValidator("y", new YValidator());
        TextField price = TextFieldFactory.getFieldWithValidator("price", new PriceValidator());
        TextField type = TextFieldFactory.getFieldWithValidator("type", new TypeValidator());
        Label personData = new Label("Person Data");
        TextField birthday = TextFieldFactory.getFieldWithValidator("birthday", new TypeValidator());
        TextField country = TextFieldFactory.getFieldWithValidator("country", new TypeValidator());
        TextField eye = TextFieldFactory.getFieldWithValidator("eye color", new TypeValidator());
        TextField hair = TextFieldFactory.getFieldWithValidator("hair color", new TypeValidator());

        Button commit = ButtonFactory.getCommitButton(); // Написать setOnAction

        VBox box = BoxFactory.getPopUpBox();
        box.setSpacing(1);
        box.getChildren().addAll(mainLabel, key, name, x, y, price, type, personData, birthday, country, eye, hair, commit);
        Scene scene = new Scene(box, 500, 600);

        stage.setScene(scene);

        return stage;
    }


}
