package ProcessEngine.GraphicCore.MainWindow.DataSheet;

import ProcessEngine.DataCore.DataRun;
import ProcessEngine.GraphicCore.GraphicRun;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.BoxFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.ButtonFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.Factories.LabelFactory;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.InfoColumnPopUpWindow.InfoColumnPopUpWindow;
import ProcessEngine.GraphicCore.MainWindow.AdditionalWindows.UpdatePopUpWindow.UpdatePopUpWindow;
import ProcessEngine.ProcessCore.networkModule.NetworkManager;
import ProcessEngine.GraphicCore.MainWindow.DataSheet.SorteByColumn.ColumnSortFlag;
import ProcessEngine.GraphicCore.MainWindow.DataSheet.FilterByColumn.ColumnKeyFilterAction;
import ProcessEngine.GraphicCore.MainWindow.DataSheet.FilterByColumn.ColumnIdFilterAction;
import ProcessEngine.GraphicCore.MainWindow.DataSheet.FilterByColumn.ColumnNameFilterAction;
import ProcessEngine.GraphicCore.MainWindow.DataSheet.FilterByColumn.ColumnXFilterAction;
import ProcessEngine.GraphicCore.MainWindow.DataSheet.FilterByColumn.ColumnYFilterAction;
import ProcessEngine.GraphicCore.MainWindow.DataSheet.FilterByColumn.ColumnCreationDateFilterAction;
import ProcessEngine.GraphicCore.MainWindow.DataSheet.FilterByColumn.ColumnPriceFilterAction;
import ProcessEngine.GraphicCore.MainWindow.DataSheet.FilterByColumn.ColumnTypeFilterAction;
import ProcessEngine.GraphicCore.MainWindow.DataSheet.FilterByColumn.ColumnBirthdayFilterAction;
import ProcessEngine.GraphicCore.MainWindow.DataSheet.FilterByColumn.ColumnEyeFilterAction;
import ProcessEngine.GraphicCore.MainWindow.DataSheet.FilterByColumn.ColumnHairFilterAction;
import ProcessEngine.GraphicCore.MainWindow.DataSheet.FilterByColumn.ColumnCountryFilterAction;
import ProcessEngine.GraphicCore.MainWindow.DataSheet.FilterByColumn.FilterActualCollectionElements;
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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.TreeSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DataSheet {

    protected DataRun dataRun;
    private static NetworkManager networkManager;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
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

    public static final TreeSet<String> filteredKeyElementsSet = new TreeSet<String>();
    public static final TreeSet<String> filteredIdElementsSet = new TreeSet<String>();
    public static final TreeSet<String> filteredNameElementsSet = new TreeSet<String>();
    public static final TreeSet<String> filteredXElementsSet = new TreeSet<String>();
    public static final TreeSet<String> filteredYElementsSet = new TreeSet<String>();
    public static final TreeSet<String> filteredCreationDateElementsSet = new TreeSet<String>();
    public static final TreeSet<String> filteredPriceElementsSet = new TreeSet<String>();
    public static final TreeSet<String> filteredTypeElementsSet = new TreeSet<String>();
    public static final TreeSet<String> filteredBirthdayElementsSet = new TreeSet<String>();
    public static final TreeSet<String> filteredEyeElementsSet = new TreeSet<String>();
    public static final TreeSet<String> filteredHairElementsSet = new TreeSet<String>();
    public static final TreeSet<String> filteredCountryElementsSet = new TreeSet<String>();


    public DataSheet(DataRun dataRun, NetworkManager networkManager, String login, String password) {
        this.dataRun = dataRun;
        DataSheet.networkManager = networkManager;
        DataSheet.login = login;
        DataSheet.password = password;
    }

    public StackPane getDataSheet(Stage stage) {
        ObservableList<String[]> tickets = FXCollections.observableArrayList(FilterActualCollectionElements.filterActualCollectionElements(dataRun.getCollectionData()));
        TableView<String[]> table = new TableView<>();
        table.setItems(tickets);


        TableColumn<String[], Long> keyColumn = new TableColumn<>(null);
        keyColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(Long.parseLong(s.getValue()[0])));
        keyColumn.setPrefWidth(115);
        keyColumn.setResizable(false);
        keyColumn.setSortable(false);
        keyColumn.setStyle("-fx-alignment: CENTER;");
        table.getColumns().add(keyColumn);
        HBox headerKeyBox = new HBox();
        headerKeyBox.setSpacing(7);
        Label keyLabel = new Label(GraphicRun.localizator.getString("key"));
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
        Button headerKeyFilterButton = new Button("🔍");
        headerKeyFilterButton.setOnAction(event -> {
            ColumnKeyFilterAction.columnKeyFilterAction(headerKeyFilterButton, dataRun);
        });
        headerKeyBox.getChildren().addAll(keyLabel, headerKeyButton, headerKeyFilterButton);
        keyColumn.setGraphic(headerKeyBox);


        TableColumn<String[], Long> idColumn = new TableColumn<>(null);
        idColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(Long.parseLong(s.getValue()[1])));
        table.getColumns().add(idColumn);
        idColumn.setPrefWidth(100);
        idColumn.setResizable(false);
        idColumn.setSortable(false);
        HBox headerIdBox = new HBox();
        headerIdBox.setSpacing(7);
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
        Button headerIdFilterButton = new Button("🔍");
        headerIdFilterButton.setOnAction(event -> {
            ColumnIdFilterAction.columnIdFilterAction(headerIdFilterButton, dataRun);
        });
        headerIdBox.getChildren().addAll(idLabel, headerIdButton, headerIdFilterButton);
        idColumn.setGraphic(headerIdBox);


        TableColumn<String[], String> nameColumn = new TableColumn<>(null);
        nameColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[2]));
        table.getColumns().add(nameColumn);
        nameColumn.setPrefWidth(110);
        nameColumn.setResizable(false);
        nameColumn.setSortable(false);
        HBox headerNameBox = new HBox();
        headerNameBox.setSpacing(7);
        Label nameLabel = new Label(GraphicRun.localizator.getString("name"));
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
        Button headerNameFilterButton = new Button("🔍");
        headerNameFilterButton.setOnAction(event -> {
            ColumnNameFilterAction.columnNameFilterAction(headerNameFilterButton, dataRun);
        });
        headerNameBox.getChildren().addAll(nameLabel, headerNameButton, headerNameFilterButton);
        nameColumn.setGraphic(headerNameBox);


        TableColumn<String[], Float> xColumn = new TableColumn<>(null);
        xColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(Float.parseFloat(s.getValue()[3])));
        table.getColumns().add(xColumn);
        xColumn.setPrefWidth(90);
        xColumn.setResizable(false);
        xColumn.setSortable(false);
        HBox headerXBox = new HBox();
        headerXBox.setSpacing(7);
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
        Button headerXFilterButton = new Button("🔍");
        headerXFilterButton.setOnAction(event -> {
            ColumnXFilterAction.columnXFilterAction(headerXFilterButton, dataRun);
        });
        headerXBox.getChildren().addAll(xLabel, headerXButton, headerXFilterButton);
        xColumn.setGraphic(headerXBox);


        TableColumn<String[], Long> yColumn = new TableColumn<>(null);
        yColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(Long.parseLong(s.getValue()[4])));
        table.getColumns().add(yColumn);
        yColumn.setPrefWidth(90);
        yColumn.setResizable(false);
        yColumn.setSortable(false);
        HBox headerYBox = new HBox();
        headerYBox.setSpacing(7);
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
        Button headerYFilterButton = new Button("🔍");
        headerYFilterButton.setOnAction(event -> {
            ColumnYFilterAction.columnYFilterAction(headerYFilterButton, dataRun);
        });
        headerYBox.getChildren().addAll(yLabel, headerYButton, headerYFilterButton);
        yColumn.setGraphic(headerYBox);


        TableColumn<String[], String> creationDateColumn = new TableColumn<>(null);
        creationDateColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(GraphicRun.localizator.getDate(s.getValue()[5])));
        table.getColumns().add(creationDateColumn);
        creationDateColumn.setPrefWidth(135);
        creationDateColumn.setResizable(false);
        creationDateColumn.setSortable(false);
        HBox headerCreationDateBox = new HBox();
        headerCreationDateBox.setSpacing(5);
        Label creationDateLabel = new Label(GraphicRun.localizator.getString("creation date"));
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
        Button headerCreationDateFilterButton = new Button("🔍");
        headerCreationDateFilterButton.setOnAction(event -> {
            ColumnCreationDateFilterAction.columnCreationDateFilterAction(headerCreationDateFilterButton, dataRun);
        });
        headerCreationDateBox.getChildren().addAll(creationDateLabel, headerCreationDateButton, headerCreationDateFilterButton);
        creationDateColumn.setGraphic(headerCreationDateBox);


        TableColumn<String[], Float> priceColumn = new TableColumn<>(null);
        priceColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(Float.parseFloat(s.getValue()[6])));
        table.getColumns().add(priceColumn);
        priceColumn.setPrefWidth(115);
        priceColumn.setResizable(false);
        priceColumn.setSortable(false);
        HBox headerPriceBox = new HBox();
        headerPriceBox.setSpacing(7);
        Label priceLabel = new Label(GraphicRun.localizator.getString("price"));
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
        Button headerPriceFilterButton = new Button("🔍");
        headerPriceFilterButton.setOnAction(event -> {
            ColumnPriceFilterAction.columnPriceFilterAction(headerPriceFilterButton, dataRun);
        });
        headerPriceBox.getChildren().addAll(priceLabel, headerPriceButton, headerPriceFilterButton);
        priceColumn.setGraphic(headerPriceBox);


        TableColumn<String[], TicketType> typeColumn = new TableColumn<>(null);
        typeColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[7].equals("null") ? null : TicketType.valueOf(s.getValue()[7])));
        table.getColumns().add(typeColumn);
        typeColumn.setPrefWidth(110);
        typeColumn.setResizable(false);
        typeColumn.setSortable(false);
        HBox headerTypeBox = new HBox();
        headerTypeBox.setSpacing(7);
        Label typeLabel = new Label(GraphicRun.localizator.getString("type"));
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
        Button headerTypeFilterButton = new Button("🔍");
        headerTypeFilterButton.setOnAction(event -> {
            ColumnTypeFilterAction.columnTypeFilterAction(headerTypeFilterButton, dataRun);
        });
        headerTypeBox.getChildren().addAll(typeLabel, headerTypeButton, headerTypeFilterButton);
        typeColumn.setGraphic(headerTypeBox);


        TableColumn<String[], String> birthdayColumn = new TableColumn<>(null);
        birthdayColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[8].equals("null") ? null : GraphicRun.localizator.getDate(s.getValue()[8])));
        table.getColumns().add(birthdayColumn);
        birthdayColumn.setPrefWidth(135);
        birthdayColumn.setResizable(false);
        birthdayColumn.setSortable(false);
        HBox headerBirthdayBox = new HBox();
        headerBirthdayBox.setSpacing(7);
        Label birthdayLabel = new Label(GraphicRun.localizator.getString("birthday"));
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
        Button headerBirthdayFilterButton = new Button("🔍");
        headerBirthdayFilterButton.setOnAction(event -> {
            ColumnBirthdayFilterAction.columnBirthdayFilterAction(headerBirthdayFilterButton, dataRun);
        });
        headerBirthdayBox.getChildren().addAll(birthdayLabel, headerBirthdayButton, headerBirthdayFilterButton);
        birthdayColumn.setGraphic(headerBirthdayBox);


        TableColumn<String[], EyeColor> eyeColumn = new TableColumn<>(null);
        eyeColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[9].equals("null") ? null : EyeColor.valueOf(s.getValue()[9])));
        table.getColumns().add(eyeColumn);
        eyeColumn.setPrefWidth(120);
        eyeColumn.setResizable(false);
        eyeColumn.setSortable(false);
        HBox headerEyeBox = new HBox();
        headerEyeBox.setSpacing(7);
        Label eyeLabel = new Label(GraphicRun.localizator.getString("eye color"));
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
        Button headerEyeFilterButton = new Button("🔍");
        headerEyeFilterButton.setOnAction(event -> {
            ColumnEyeFilterAction.columnEyeFilterAction(headerEyeFilterButton, dataRun);
        });
        headerEyeBox.getChildren().addAll(eyeLabel, headerEyeButton, headerEyeFilterButton);
        eyeColumn.setGraphic(headerEyeBox);


        TableColumn<String[], HairColor> hairColumn = new TableColumn<>(null);
        hairColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[10].equals("null") ? null : HairColor.valueOf(s.getValue()[10])));
        table.getColumns().add(hairColumn);
        hairColumn.setPrefWidth(125);
        hairColumn.setResizable(false);
        hairColumn.setSortable(false);
        HBox headerHairBox = new HBox();
        headerHairBox.setSpacing(7);
        Label hairLabel = new Label(GraphicRun.localizator.getString("hair color"));
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
        Button headerHairFilterButton = new Button("🔍");
        headerHairFilterButton.setOnAction(event -> {
            ColumnHairFilterAction.columnHairFilterAction(headerHairFilterButton, dataRun);
        });
        headerHairBox.getChildren().addAll(hairLabel, headerHairButton, headerHairFilterButton);
        hairColumn.setGraphic(headerHairBox);


        TableColumn<String[], Country> countryColumn = new TableColumn<>(null);
        countryColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[11].equals("null") ? null : Country.valueOf(s.getValue()[11])));
        table.getColumns().add(countryColumn);
        countryColumn.setPrefWidth(130);
        countryColumn.setResizable(false);
        countryColumn.setSortable(false);
        HBox headerCountryBox = new HBox();
        headerCountryBox.setSpacing(7);
        Label countryLabel = new Label(GraphicRun.localizator.getString("country"));
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
        Button headerCountryFilterButton = new Button("🔍");
        headerCountryFilterButton.setOnAction(event -> {
            ColumnCountryFilterAction.columnCountryFilterAction(headerCountryFilterButton, dataRun);
        });
        headerCountryBox.getChildren().addAll(countryLabel, headerCountryButton, headerCountryFilterButton);
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
                                } else {
                                    Stage subStage = InfoColumnPopUpWindow.InfoWindow(row.getItem());
                                    subStage.show();
                                }
                            }
                    );
                    return row;
                }
        );

        return new StackPane(table);
    }

    protected void startAutoParallelUpdateDataSheet(TableView<String[]> table) {
        scheduler.scheduleAtFixedRate(() -> {
            ObservableList<String[]> tickets = FXCollections.observableArrayList(
                FilterActualCollectionElements.filterActualCollectionElements(dataRun.getCollectionData())
            );
            Platform.runLater(() -> table.setItems(tickets));
        }, 0, 100, TimeUnit.MILLISECONDS);
    }

    private MenuItem getUpdate(String[] i) {
        MenuItem update = new MenuItem(GraphicRun.localizator.getString("update"));
        update.setOnAction(
                event1 -> {
                    Stage stage = UpdatePopUpWindow.secondWindow(networkManager, login, password, i, Long.parseLong(i[0]));
                    stage.show();
                }
        );
        return update;
    }

    private MenuItem getRemove(String[] i) {
        MenuItem remove = new MenuItem(GraphicRun.localizator.getString("remove"));
        remove.setOnAction(
                event1 -> {
                    Stage stage = new Stage();
                    VBox box = BoxFactory.getPopUpBox();
                    Label label = LabelFactory.getMainLabel(GraphicRun.localizator.getString("remove this?") + "(" + GraphicRun.localizator.getString("key") + i[0] + ")");
                    Button yes = ButtonFactory.getCommitButton();
                    Label error = LabelFactory.getErrorLabel("");
                    yes.setText(GraphicRun.localizator.getString("yes"));

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
                                    error.setText(GraphicRun.localizator.getString("success remove"));
                                    LabelFactory.toResultLabel(error);
                                    stage.close();
                                } else if (answer.equals("[ERROR] Не достаточно прав для взаимодействия с данным элементом")) {
                                    error.setText(GraphicRun.localizator.getString("not enough rights"));
                                    LabelFactory.toErrorLabel(error);
                                } else {
                                    error.setText(GraphicRun.localizator.getString("no key"));
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
