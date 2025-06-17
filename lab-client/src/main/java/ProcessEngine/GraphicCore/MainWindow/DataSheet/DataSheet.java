package ProcessEngine.GraphicCore.MainWindow.DataSheet;

import ProcessEngine.DataCore.DataRun;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.BoxFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.ButtonFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.LabelFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.UpdatePopUpWindow.UpdatePopUpWindow;
import ProcessEngine.ProcessCore.networkModule.NetworkManager;
import ProcessEngine.GraphicCore.MainWindow.DataSheet.SorteByColumn.ColumnSortFlag;
import moduls.Country;
import moduls.EyeColor;
import moduls.HairColor;
import moduls.TicketType;
import network.Request;

import java.util.Arrays;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class DataSheet {

    protected DataRun dataRun;
    private static NetworkManager networkManager;
    private static String login;
    private static String password;

    public static Boolean keyFlagSort = null;
    public static Boolean idFlagSort = null;
    public static Boolean nameFlagSort = null;
    public static Boolean xFlagSort = null;
    public static Boolean yFlagSort = null;
    public static Boolean creationDateFlagSort = null;
    public static Boolean priceFlagSort = null;
    public static Boolean typeFlagSort = null;
    public static Boolean birthdayFlagSort = null;
    public static Boolean eyeFlagSort = null;
    public static Boolean hairFlagSort = null;
    public static Boolean countryFlagSort = null;

    public static Button headerKeyButton = new Button("⇅");
    public static Button headerIdButton = new Button("⇅");
    public static Button headerNameButton = new Button("⇅");
    public static Button headerXButton = new Button("⇅");
    public static Button headerYButton = new Button("⇅");
    public static Button headerCreationDateButton = new Button("⇅");
    public static Button headerPriceButton = new Button("⇅");
    public static Button headerTypeButton = new Button("⇅");
    public static Button headerBirthdayButton = new Button("⇅");
    public static Button headerEyeButton = new Button("⇅");
    public static Button headerHairButton = new Button("⇅");
    public static Button headerCountryButton = new Button("⇅");


    public DataSheet(DataRun dataRun, NetworkManager networkManager, String login, String password) {
        this.dataRun = dataRun;
        DataSheet.networkManager = networkManager;
        DataSheet.login = login;
        DataSheet.password = password;
    }

    public StackPane getDataSheet(Stage stage) {
        ObservableList<String[]> tickets = FXCollections.observableArrayList(dataRun.getCollectionData());
        TableView<String[]> table = new TableView<>();
        table.setItems(tickets);


        TableColumn<String[], Long> keyColumn = new TableColumn<>(null);
        keyColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(Long.parseLong(s.getValue()[0])));
        table.getColumns().add(keyColumn);
        HBox headerKeyBox = new HBox();
        headerKeyBox.setSpacing(15);
        Label keyLabel = new Label("key");
        headerKeyButton.setOnAction(e -> ColumnSortFlag.setColumnSortFlag(
                "keyFlagSort",
                headerKeyButton,
                headerIdButton,
                headerNameButton,
                headerXButton,
                headerYButton,
                headerCreationDateButton,
                headerPriceButton,
                headerTypeButton,
                headerBirthdayButton,
                headerEyeButton,
                headerHairButton,
                headerCountryButton
        ));
        headerKeyBox.getChildren().addAll(keyLabel, headerKeyButton);
        keyColumn.setGraphic(headerKeyBox);


        TableColumn<String[], Long> idColumn = new TableColumn<>(null);
        idColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(Long.parseLong(s.getValue()[1])));
        table.getColumns().add(idColumn);
        HBox headerIdBox = new HBox();
        headerIdBox.setSpacing(15);
        Label idLabel = new Label("id");
        headerIdButton.setOnAction(e -> ColumnSortFlag.setColumnSortFlag(
                "idFlagSort",
                headerKeyButton,
                headerIdButton,
                headerNameButton,
                headerXButton,
                headerYButton,
                headerCreationDateButton,
                headerPriceButton,
                headerTypeButton,
                headerBirthdayButton,
                headerEyeButton,
                headerHairButton,
                headerCountryButton
        ));
        headerIdBox.getChildren().addAll(idLabel, headerIdButton);
        idColumn.setGraphic(headerIdBox);


        TableColumn<String[], String> nameColumn = new TableColumn<>(null);
        nameColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[2]));
        table.getColumns().add(nameColumn);
        HBox headerNameBox = new HBox();
        headerNameBox.setSpacing(15);
        Label nameLabel = new Label("name");
        headerNameButton.setOnAction(e -> ColumnSortFlag.setColumnSortFlag(
                "nameFlagSort",
                headerKeyButton,
                headerIdButton,
                headerNameButton,
                headerXButton,
                headerYButton,
                headerCreationDateButton,
                headerPriceButton,
                headerTypeButton,
                headerBirthdayButton,
                headerEyeButton,
                headerHairButton,
                headerCountryButton
        ));
        headerNameBox.getChildren().addAll(nameLabel, headerNameButton);
        nameColumn.setGraphic(headerNameBox);


        TableColumn<String[], Float> xColumn = new TableColumn<>(null);
        xColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(Float.parseFloat(s.getValue()[3])));
        table.getColumns().add(xColumn);
        HBox headerXBox = new HBox();
        headerXBox.setSpacing(15);
        Label xLabel = new Label("x");
        headerXButton.setOnAction(e -> ColumnSortFlag.setColumnSortFlag(
                "xFlagSort",
                headerKeyButton,
                headerIdButton,
                headerNameButton,
                headerXButton,
                headerYButton,
                headerCreationDateButton,
                headerPriceButton,
                headerTypeButton,
                headerBirthdayButton,
                headerEyeButton,
                headerHairButton,
                headerCountryButton
        ));
        headerXBox.getChildren().addAll(xLabel, headerXButton);
        xColumn.setGraphic(headerXBox);


        TableColumn<String[], Long> yColumn = new TableColumn<>(null);
        yColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(Long.parseLong(s.getValue()[4])));
        table.getColumns().add(yColumn);
        HBox headerYBox = new HBox();
        headerYBox.setSpacing(15);
        Label yLabel = new Label("y");
        headerYButton.setOnAction(e -> ColumnSortFlag.setColumnSortFlag(
                "yFlagSort",
                headerKeyButton,
                headerIdButton,
                headerNameButton,
                headerXButton,
                headerYButton,
                headerCreationDateButton,
                headerPriceButton,
                headerTypeButton,
                headerBirthdayButton,
                headerEyeButton,
                headerHairButton,
                headerCountryButton
        ));
        headerYBox.getChildren().addAll(yLabel, headerYButton);
        yColumn.setGraphic(headerYBox);


        TableColumn<String[], String> creationDateColumn = new TableColumn<>(null);
        creationDateColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[5]));
        table.getColumns().add(creationDateColumn);
        HBox headerCreationDateBox = new HBox();
        headerCreationDateBox.setSpacing(15);
        Label creationDateLabel = new Label("creation date");
        headerCreationDateButton.setOnAction(e -> ColumnSortFlag.setColumnSortFlag(
                "creationDateFlagSort",
                headerKeyButton,
                headerIdButton,
                headerNameButton,
                headerXButton,
                headerYButton,
                headerCreationDateButton,
                headerPriceButton,
                headerTypeButton,
                headerBirthdayButton,
                headerEyeButton,
                headerHairButton,
                headerCountryButton
        ));
        headerCreationDateBox.getChildren().addAll(creationDateLabel, headerCreationDateButton);
        creationDateColumn.setGraphic(headerCreationDateBox);


        TableColumn<String[], Float> priceColumn = new TableColumn<>(null);
        priceColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(Float.parseFloat(s.getValue()[6])));
        table.getColumns().add(priceColumn);
        HBox headerPriceBox = new HBox();
        headerPriceBox.setSpacing(15);
        Label priceLabel = new Label("price");
        headerPriceButton.setOnAction(e -> ColumnSortFlag.setColumnSortFlag(
                "priceFlagSort",
                headerKeyButton,
                headerIdButton,
                headerNameButton,
                headerXButton,
                headerYButton,
                headerCreationDateButton,
                headerPriceButton,
                headerTypeButton,
                headerBirthdayButton,
                headerEyeButton,
                headerHairButton,
                headerCountryButton
        ));
        headerPriceBox.getChildren().addAll(priceLabel, headerPriceButton);
        priceColumn.setGraphic(headerPriceBox);


        TableColumn<String[], TicketType> typeColumn = new TableColumn<>(null);
        typeColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[7].equals("null") ? null : TicketType.valueOf(s.getValue()[7])));
        table.getColumns().add(typeColumn);
        HBox headerTypeBox = new HBox();
        headerTypeBox.setSpacing(15);
        Label typeLabel = new Label("type");
        headerTypeButton.setOnAction(e -> ColumnSortFlag.setColumnSortFlag(
                "typeFlagSort",
                headerKeyButton,
                headerIdButton,
                headerNameButton,
                headerXButton,
                headerYButton,
                headerCreationDateButton,
                headerPriceButton,
                headerTypeButton,
                headerBirthdayButton,
                headerEyeButton,
                headerHairButton,
                headerCountryButton
        ));
        headerTypeBox.getChildren().addAll(typeLabel, headerTypeButton);
        typeColumn.setGraphic(headerTypeBox);


        TableColumn<String[], String> birthdayColumn = new TableColumn<>(null);
        birthdayColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[8].equals("null") ? null : s.getValue()[8]));
        table.getColumns().add(birthdayColumn);
        HBox headerBirthdayBox = new HBox();
        headerBirthdayBox.setSpacing(15);
        Label birthdayLabel = new Label("birthday");
        headerBirthdayButton.setOnAction(e -> ColumnSortFlag.setColumnSortFlag(
                "birthdayFlagSort",
                headerKeyButton,
                headerIdButton,
                headerNameButton,
                headerXButton,
                headerYButton,
                headerCreationDateButton,
                headerPriceButton,
                headerTypeButton,
                headerBirthdayButton,
                headerEyeButton,
                headerHairButton,
                headerCountryButton
        ));
        headerBirthdayBox.getChildren().addAll(birthdayLabel, headerBirthdayButton);
        birthdayColumn.setGraphic(headerBirthdayBox);


        TableColumn<String[], EyeColor> eyeColumn = new TableColumn<>(null);
        eyeColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[9].equals("null") ? null : EyeColor.valueOf(s.getValue()[9])));
        table.getColumns().add(eyeColumn);
        HBox headerEyeBox = new HBox();
        headerEyeBox.setSpacing(15);
        Label eyeLabel = new Label("eye");
        headerEyeButton.setOnAction(e -> ColumnSortFlag.setColumnSortFlag(
                "eyeFlagSort",
                headerKeyButton,
                headerIdButton,
                headerNameButton,
                headerXButton,
                headerYButton,
                headerCreationDateButton,
                headerPriceButton,
                headerTypeButton,
                headerBirthdayButton,
                headerEyeButton,
                headerHairButton,
                headerCountryButton
        ));
        headerEyeBox.getChildren().addAll(eyeLabel, headerEyeButton);
        eyeColumn.setGraphic(headerEyeBox);


        TableColumn<String[], HairColor> hairColumn = new TableColumn<>(null);
        hairColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[10].equals("null") ? null : HairColor.valueOf(s.getValue()[10])));
        table.getColumns().add(hairColumn);
        HBox headerHairBox = new HBox();
        headerHairBox.setSpacing(15);
        Label hairLabel = new Label("hair");
        headerHairButton.setOnAction(e -> ColumnSortFlag.setColumnSortFlag(
                "hairFlagSort",
                headerKeyButton,
                headerIdButton,
                headerNameButton,
                headerXButton,
                headerYButton,
                headerCreationDateButton,
                headerPriceButton,
                headerTypeButton,
                headerBirthdayButton,
                headerEyeButton,
                headerHairButton,
                headerCountryButton
        ));
        headerHairBox.getChildren().addAll(hairLabel, headerHairButton);
        hairColumn.setGraphic(headerHairBox);


        TableColumn<String[], Country> countryColumn = new TableColumn<>(null);
        countryColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[11].equals("null") ? null : Country.valueOf(s.getValue()[11])));
        table.getColumns().add(countryColumn);
        HBox headerCountryBox = new HBox();
        headerCountryBox.setSpacing(15);
        Label countryLabel = new Label("country");
        headerCountryButton.setOnAction(e -> ColumnSortFlag.setColumnSortFlag(
                "countryFlagSort",
                headerKeyButton,
                headerIdButton,
                headerNameButton,
                headerXButton,
                headerYButton,
                headerCreationDateButton,
                headerPriceButton,
                headerTypeButton,
                headerBirthdayButton,
                headerEyeButton,
                headerHairButton,
                headerCountryButton
        ));
        headerCountryBox.getChildren().addAll(countryLabel, headerCountryButton);
        countryColumn.setGraphic(headerCountryBox);


        startAutoParallelUpdateDataSheet(table);
        table.getColumns().forEach(column -> column.setReorderable(false));

        table.setRowFactory(
                tableView -> {
                    TableRow<String[]> row = new TableRow<>();
                    row.setOnMouseClicked(
                            event -> {
                                if (event.getButton() == MouseButton.SECONDARY) {
                                    ContextMenu menu = new ContextMenu();
                                    MenuItem update = getUpdate(row.getItem());
                                    MenuItem remove = getRemove(row.getItem());


                                    menu.getItems().addAll(update, remove);
                                    menu.setX(event.getScreenX());
                                    menu.setY(event.getScreenY());
                                    menu.show(stage);
                                }
                            }
                    );
                    return row;
                }
        );

        return new StackPane(table);
    }

    protected void startAutoParallelUpdateDataSheet(TableView<String[]> table) {
        Task<Void> updateTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                while (!isCancelled()) {
                    ObservableList<String[]> tickets = FXCollections.observableArrayList(dataRun.getCollectionData());
                    Platform.runLater(() -> table.setItems(tickets));
                    Thread.sleep(50);
                }
                return null;
            }
        };

        Thread newThread = new Thread(updateTask);
        newThread.setDaemon(true);
        newThread.start();
    }

    private MenuItem getUpdate(String[] i) {
        MenuItem update = new MenuItem("update");
        update.setOnAction(
                event1 -> {
                    Stage stage = UpdatePopUpWindow.secondWindow(networkManager, login, password, i, Long.parseLong(i[0]));
                    stage.show();
                }
        );
        return update;
    }

    private MenuItem getRemove(String[] i) {
        MenuItem remove = new MenuItem("remove");
        remove.setOnAction(
                event1 -> {
                    Stage stage = new Stage();
                    VBox box = BoxFactory.getPopUpBox();
                    Label label = LabelFactory.getMainLabel("Удалить данный элемент? (ключ - " + i[0] + ")");
                    Button yes = ButtonFactory.getCommitButton();
                    Label error = LabelFactory.getErrorLabel("");
                    yes.setText("yes");

                    yes.setOnAction(
                            event2 -> {
                                Request request = new Request();
                                request.setUser(login);
                                request.setPassword(Arrays.toString(password
                                        .chars()
                                        .mapToObj(c -> String.valueOf((char) c))
                                        .toArray(String[]::new))
                                );
                                request.setCommandName("remove_key");
                                request.setTokens("remove_key " + i[0]);
                                String answer = networkManager.sendAndReceive(request);

                                if (answer.equals("Элемент успешно удален")) {
                                    error.setText("Элемент успешно удален");
                                    LabelFactory.toResultLabel(error);
                                } else if (answer.equals("[ERROR] Не достаточно прав для взаимодействия с данным элементом")) {
                                    error.setText("Нет прав доступа");
                                    LabelFactory.toErrorLabel(error);
                                } else {
                                    error.setText("Элемент уже удален");
                                    LabelFactory.toErrorLabel(error);
                                }
                            });

                    box.getChildren().addAll(label, yes, error);
                    stage.setScene(new Scene(box, 500, 300));

                    stage.show();
                }
        );
        return remove;
    }
}
