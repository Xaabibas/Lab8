package ProcessEngine.GraphicCore.MainWindow;

import ProcessEngine.ProcessCore.validatorModule.Validator;
import ProcessEngine.ProcessCore.validatorModule.fieldValidators.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    private static class LabelFactory {
        protected static Label getMainLabel(String name) {
            Label label = new Label(name);
            label.setFont(new Font(20));
            label.setTextFill(Color.FORESTGREEN);

            return label;
        }

        protected static Label fetUsualLabel(String name) {
            Label label = new Label(name);
            label.setFont(new Font(16));
            label.setTextFill(Color.FORESTGREEN);

            return label;
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
            box.setBackground(new Background(new BackgroundFill(Color.PALEGREEN, new CornerRadii(5), Insets.EMPTY)));
            box.setAlignment(Pos.CENTER);
            box.getChildren().addAll(es);
            box.setSpacing(10);
            box.setPadding(new Insets(40, 30, 40,30));

            return box;
        }
    }

    private static class TextFieldFactory {
        protected static TextField getFieldWithValidator(String text, Validator validator) {
            TextField field = new TextField();
            field.setPromptText(text);
            field.setPrefWidth(180);
            Tooltip tooltip = new Tooltip(validator.message());
            tooltip.setFont(new Font(10));
            tooltip.setShowDelay(new Duration(2));
            field.setTooltip(tooltip);

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
        update.setOnAction(
                event -> {
                    Stage stage = updateWindow();

                    stage.show();
                }
        );
        Button insert = ButtonFactory.getCommandButton("insert"); // Написать setOnAction
        insert.setOnAction(
                event -> {
                    Stage stage = insertWindow();

                    stage.show();
                }
        );

        HBox first = BoxFactory.getBoxWithButtons(update, insert);

        Button removeGreater = ButtonFactory.getCommandButton("remove greater"); // Написать setOnAction
        removeGreater.setOnAction(
                event -> {
                    Stage stage = removeGreaterWindow();

                    stage.show();
                }
        );
        Button removeLower = ButtonFactory.getCommandButton("remove lower"); // Написать setOnAction
        removeLower.setOnAction(
                event -> {
                    Stage stage = removeLowerWindow();

                    stage.show();
                }
        );
        HBox second = BoxFactory.getBoxWithButtons(removeGreater, removeLower);
        second.setPadding(new Insets(17, 0, 0, 0));

        Button removeLowerKey = ButtonFactory.getCommandButton("remove lower by key"); // Написать setOnAction
        removeLowerKey.setOnAction(
                event -> {
                    Stage stage = removeLowerKeyWindow();

                    stage.show();
                }
        );
        Button removeKey = ButtonFactory.getCommandButton("remove by key"); // Написать setOnAction
        removeKey.setOnAction(
                event -> {
                    Stage stage = removeKeyWindow();

                    stage.show();
                }
        );
        HBox third = BoxFactory.getBoxWithButtons(removeLowerKey, removeKey);
        third.setPadding(new Insets(0, 0, 17, 0));

        Button sum = ButtonFactory.getCommandButton("sum of price"); // Написать setOnAction
        sum.setOnAction(
                event -> {
                    Stage stage = sumWindow();

                    stage.show();
                }
        );
        Button count = ButtonFactory.getCommandButton("count by type"); // Написать setOnAction
        count.setOnAction(
                event -> {
                    Stage stage = countWindow();

                    stage.show();
                }
        );
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

    private Stage updateWindow() {
        Stage stage = new Stage();
        Label mainLabel = LabelFactory.getMainLabel("Update");
        TextField key = TextFieldFactory.getFieldWithValidator("key", new KeyValidator());
        Button commit = ButtonFactory.getCommitButton(); // Надо проверить валидность ключа и перейти к обновлению данных
        commit.setOnAction(
                event -> {
                    Stage subStage = insertWindow();

                    subStage.show();
                }
        );
        VBox box = BoxFactory.getPopUpBox(mainLabel, key, commit);

        stage.setScene(new Scene(box, 500, 600));

        return stage;
    }

    private Stage insertWindow() {
        Stage stage = new Stage();

        Label mainLabel = LabelFactory.getMainLabel("Insert your data");
        TextField key = TextFieldFactory.getFieldWithValidator("key", new KeyValidator());
        TextField name = TextFieldFactory.getFieldWithValidator("name", new NameValidator());
        TextField x = TextFieldFactory.getFieldWithValidator("x", new XValidator());
        TextField y = TextFieldFactory.getFieldWithValidator("y", new YValidator());
        TextField price = TextFieldFactory.getFieldWithValidator("price", new PriceValidator());
        TextField type = TextFieldFactory.getFieldWithValidator("type", new TypeValidator());
        Label personData = LabelFactory.fetUsualLabel("Person Data");
        TextField birthday = TextFieldFactory.getFieldWithValidator("birthday", new DateValidator());
        TextField country = TextFieldFactory.getFieldWithValidator("country", new CountryValidator());
        TextField eye = TextFieldFactory.getFieldWithValidator("eye color", new EyeValidator());
        TextField hair = TextFieldFactory.getFieldWithValidator("hair color", new HairValidator());

        Button commit = ButtonFactory.getCommitButton(); // Написать setOnAction

        VBox box = BoxFactory.getPopUpBox();
        box.getChildren().addAll(mainLabel, key, name, x, y, price, type, personData, birthday, country, eye, hair, commit);
        Scene scene = new Scene(box, 500, 600);

        stage.setScene(scene);

        return stage;
    }

    private Stage removeGreaterWindow() {
        Stage stage = new Stage();
        Label mainLabel = LabelFactory.getMainLabel("Insert price"); // Сравнение идет по цене
        TextField price = TextFieldFactory.getFieldWithValidator("price", new PriceValidator());
        Button commit = ButtonFactory.getCommitButton(); // Написать setOnAction

        VBox box = BoxFactory.getPopUpBox(mainLabel, price, commit);
        Scene scene = new Scene(box, 300, 400);
        stage.setScene(scene);

        return stage;
    }

    private Stage removeLowerWindow() {
        Stage stage = new Stage();
        Label mainLabel = LabelFactory.getMainLabel("Insert price"); // Сравнение идет по цене
        TextField price = TextFieldFactory.getFieldWithValidator("price", new PriceValidator());
        Button commit = ButtonFactory.getCommitButton(); // Написать setOnAction

        VBox box = BoxFactory.getPopUpBox(mainLabel, price, commit);
        Scene scene = new Scene(box, 300, 400);
        stage.setScene(scene);

        return stage;
    }

    private Stage removeLowerKeyWindow() {
        Stage stage = new Stage();
        Label mainLabel = LabelFactory.getMainLabel("Insert Key");
        TextField key = TextFieldFactory.getFieldWithValidator("key", new KeyValidator());
        Button commit = ButtonFactory.getCommitButton(); // Написать setOnAction

        VBox box = BoxFactory.getPopUpBox(mainLabel, key, commit);
        Scene scene = new Scene(box, 300, 400);
        stage.setScene(scene);

        return stage;
    }

    private Stage removeKeyWindow() {
        Stage stage = new Stage();
        Label mainLabel = LabelFactory.getMainLabel("Insert Key");
        TextField key = TextFieldFactory.getFieldWithValidator("key", new KeyValidator());
        Button commit = ButtonFactory.getCommitButton(); // Написать setOnAction

        VBox box = BoxFactory.getPopUpBox(mainLabel, key, commit);
        Scene scene = new Scene(box, 300, 400);
        stage.setScene(scene);

        return stage;
    }

    private Stage sumWindow() {
        Stage stage = new Stage();
        // Необходимо сделать запрос/подсчет и вывести
        Label mainLabel = LabelFactory.getMainLabel("Sum of price");

        VBox box = BoxFactory.getPopUpBox(mainLabel);

        stage.setScene(new Scene(box, 500, 600));

        return stage;
    }

    private Stage countWindow() {
        Stage stage = new Stage();
        Label mainLabel = LabelFactory.getMainLabel("Count by type");
        TextField type = TextFieldFactory.getFieldWithValidator("type", new TypeValidator());
        Button count = ButtonFactory.getCommitButton(); // Написать setOnAction
        count.setTooltip(new Tooltip("Enter the type of ticket you want to count"));
        count.setText("Count!");
        // Необходимо сделать запрос/подсчет и вывести
        VBox box = BoxFactory.getPopUpBox(mainLabel, type, count);
        stage.setScene(new Scene(box, 500, 400));
        return stage;
    }
}