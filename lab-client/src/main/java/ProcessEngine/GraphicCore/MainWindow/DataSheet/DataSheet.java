package ProcessEngine.GraphicCore.MainWindow.DataSheet;

import ProcessEngine.DataCore.DataRun;
import moduls.Country;
import moduls.EyeColor;
import moduls.HairColor;
import moduls.TicketType;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.concurrent.Task;
import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class DataSheet {

    protected DataRun dataRun;

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


    public DataSheet(DataRun dataRun) {
        this.dataRun = dataRun;
    }

    public StackPane getDataSheet() {
        ObservableList<String[]> tickets = FXCollections.observableArrayList(dataRun.getCollectionData());
        TableView<String[]> table = new TableView<>();
        table.setItems(tickets);


        TableColumn<String[], Long> keyColumn = new TableColumn<>(null);
        keyColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(Long.parseLong(s.getValue()[0])));
        table.getColumns().add(keyColumn);
        HBox headerKeyBox = new HBox();
        headerKeyBox.setSpacing(15);
        Label keyLabel = new Label("key");
        headerKeyButton.setOnAction(e -> {
            setColumnSortFlag(
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
                );
        });
        headerKeyBox.getChildren().addAll(keyLabel, headerKeyButton);
        keyColumn.setGraphic(headerKeyBox);
        

        TableColumn<String[], Long> idColumn = new TableColumn<>(null);
        idColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(Long.parseLong(s.getValue()[1])));
        table.getColumns().add(idColumn);
        HBox headerIdBox = new HBox();
        headerIdBox.setSpacing(15);
        Label idLabel = new Label("id");
        headerIdButton.setOnAction(e -> {
            setColumnSortFlag(
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
                );
        });
        headerIdBox.getChildren().addAll(idLabel, headerIdButton);
        idColumn.setGraphic(headerIdBox);


        TableColumn<String[], String> nameColumn = new TableColumn<>(null);
        nameColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[2]));
        table.getColumns().add(nameColumn);
        HBox headerNameBox = new HBox();
        headerNameBox.setSpacing(15);
        Label nameLabel = new Label("name");
        headerNameButton.setOnAction(e -> {
            setColumnSortFlag(
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
                );
        });
        headerNameBox.getChildren().addAll(nameLabel, headerNameButton);
        nameColumn.setGraphic(headerNameBox);


        TableColumn<String[], Float> xColumn = new TableColumn<>(null);
        xColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(Float.parseFloat(s.getValue()[3])));
        table.getColumns().add(xColumn);
        HBox headerXBox = new HBox();
        headerXBox.setSpacing(15);
        Label xLabel = new Label("x");
        headerXButton.setOnAction(e -> {
            setColumnSortFlag(
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
                );
        });
        headerXBox.getChildren().addAll(xLabel, headerXButton);
        xColumn.setGraphic(headerXBox);


        TableColumn<String[], Long> yColumn = new TableColumn<>(null);
        yColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(Long.parseLong(s.getValue()[4])));
        table.getColumns().add(yColumn);
        HBox headerYBox = new HBox();
        headerYBox.setSpacing(15);
        Label yLabel = new Label("y");
        headerYButton.setOnAction(e -> {
            setColumnSortFlag(
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
                );
        });
        headerYBox.getChildren().addAll(yLabel, headerYButton);
        yColumn.setGraphic(headerYBox);


        TableColumn<String[], String> creationDateColumn = new TableColumn<>(null);
        creationDateColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[5]));
        table.getColumns().add(creationDateColumn);
        HBox headerCreationDateBox = new HBox();
        headerCreationDateBox.setSpacing(15);
        Label creationDateLabel = new Label("creation date");
        headerCreationDateButton.setOnAction(e -> {
            setColumnSortFlag(
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
                );
        });
        headerCreationDateBox.getChildren().addAll(creationDateLabel, headerCreationDateButton);
        creationDateColumn.setGraphic(headerCreationDateBox);


        TableColumn<String[], Float> priceColumn = new TableColumn<>(null);
        priceColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(Float.parseFloat(s.getValue()[6])));
        table.getColumns().add(priceColumn);
        HBox headerPriceBox = new HBox();
        headerPriceBox.setSpacing(15);
        Label priceLabel = new Label("price");
        headerPriceButton.setOnAction(e -> {
            setColumnSortFlag(
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
                );
        });
        headerPriceBox.getChildren().addAll(priceLabel, headerPriceButton);
        priceColumn.setGraphic(headerPriceBox);


        TableColumn<String[], TicketType> typeColumn = new TableColumn<>(null);
        typeColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[7].equals("null") ? null : TicketType.valueOf(s.getValue()[7])));
        table.getColumns().add(typeColumn);
        HBox headerTypeBox = new HBox();
        headerTypeBox.setSpacing(15);
        Label typeLabel = new Label("type");
        headerTypeButton.setOnAction(e -> {
            setColumnSortFlag(
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
                );
        });
        headerTypeBox.getChildren().addAll(typeLabel, headerTypeButton);
        typeColumn.setGraphic(headerTypeBox);


        TableColumn<String[], String> birthdayColumn = new TableColumn<>(null);
        birthdayColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[8].equals("null") ? null : s.getValue()[8]));
        table.getColumns().add(birthdayColumn);
        HBox headerBirthdayBox = new HBox();
        headerBirthdayBox.setSpacing(15);
        Label birthdayLabel = new Label("birthday");
        headerBirthdayButton.setOnAction(e -> {
            setColumnSortFlag(
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
                );
        });
        headerBirthdayBox.getChildren().addAll(birthdayLabel, headerBirthdayButton);
        birthdayColumn.setGraphic(headerBirthdayBox);


        TableColumn<String[], EyeColor> eyeColumn = new TableColumn<>(null);
        eyeColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[9].equals("null") ? null : EyeColor.valueOf(s.getValue()[9])));
        table.getColumns().add(eyeColumn);
        HBox headerEyeBox = new HBox();
        headerEyeBox.setSpacing(15);
        Label eyeLabel = new Label("eye");
        headerEyeButton.setOnAction(e -> {
            setColumnSortFlag(
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
                );
        });
        headerEyeBox.getChildren().addAll(eyeLabel, headerEyeButton);
        eyeColumn.setGraphic(headerEyeBox);


        TableColumn<String[], HairColor> hairColumn = new TableColumn<>(null);
        hairColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[10].equals("null") ? null : HairColor.valueOf(s.getValue()[10])));
        table.getColumns().add(hairColumn);
        HBox headerHairBox = new HBox();
        headerHairBox.setSpacing(15);
        Label hairLabel = new Label("hair");
        headerHairButton.setOnAction(e -> {
            setColumnSortFlag(
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
                );
        });
        headerHairBox.getChildren().addAll(hairLabel, headerHairButton);
        hairColumn.setGraphic(headerHairBox);


        TableColumn<String[], Country> countryColumn = new TableColumn<>(null);
        countryColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[11].equals("null") ? null : Country.valueOf(s.getValue()[11])));
        table.getColumns().add(countryColumn);
        HBox headerCountryBox = new HBox();
        headerCountryBox.setSpacing(15);
        Label countryLabel = new Label("country");
        headerCountryButton.setOnAction(e -> {
            setColumnSortFlag(
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
                );
        });
        headerCountryBox.getChildren().addAll(countryLabel, headerCountryButton);
        countryColumn.setGraphic(headerCountryBox);


        startAutoParallelUpdateDataSheet(table);
        table.getColumns().forEach(column -> column.setReorderable(false));

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

    public static void setColumnSortFlag(
        String columnFlag, 
        Button headerKeyButton, 
        Button headerIdButton, 
        Button headerNameButton, 
        Button headerXButton, 
        Button headerYButton, 
        Button headerCreationDateButton, 
        Button headerPriceButton, 
        Button headerTypeButton, 
        Button headerBirthdayButton, 
        Button headerEyeButton, 
        Button headerHairButton, 
        Button headerCountryButton
        ) {
            if (columnFlag.equals("keyFlagSort")) {
                if (keyFlagSort == null) {
                    keyFlagSort = true;
                    headerKeyButton.setText("↓");
                } else if (keyFlagSort == true) {
                    headerKeyButton.setText("↑");
                    keyFlagSort = false;
                } else if (keyFlagSort == false) {
                    headerKeyButton.setText("⇅");
                    keyFlagSort = null;
                }
                headerIdButton.setText("⇅");
                headerNameButton.setText("⇅");
                headerXButton.setText("⇅");
                headerYButton.setText("⇅");
                headerCreationDateButton.setText("⇅");
                headerPriceButton.setText("⇅");
                headerTypeButton.setText("⇅");
                headerBirthdayButton.setText("⇅");
                headerEyeButton.setText("⇅");
                headerHairButton.setText("⇅");
                headerCountryButton.setText("⇅");
                idFlagSort = null;
                nameFlagSort = null;
                xFlagSort = null;
                yFlagSort = null;
                creationDateFlagSort = null;
                priceFlagSort = null;
                typeFlagSort = null;
                birthdayFlagSort = null;
                eyeFlagSort = null;
                hairFlagSort = null;
                countryFlagSort = null;
            } else if (columnFlag.equals("idFlagSort")) {
                if (idFlagSort == null) {
                    idFlagSort = true;
                    headerIdButton.setText("↓");
                } else if (idFlagSort == true) {
                    headerIdButton.setText("↑");
                    idFlagSort = false;
                } else if (idFlagSort == false) {
                    headerIdButton.setText("⇅");
                    idFlagSort = null;
                }
                headerKeyButton.setText("⇅");
                headerNameButton.setText("⇅");
                headerXButton.setText("⇅");
                headerYButton.setText("⇅");
                headerCreationDateButton.setText("⇅");
                headerPriceButton.setText("⇅");
                headerTypeButton.setText("⇅");
                headerBirthdayButton.setText("⇅");
                headerEyeButton.setText("⇅");
                headerHairButton.setText("⇅");
                headerCountryButton.setText("⇅");
                keyFlagSort = null;
                nameFlagSort = null;
                xFlagSort = null;
                yFlagSort = null;
                creationDateFlagSort = null;
                priceFlagSort = null;
                typeFlagSort = null;
                birthdayFlagSort = null;
                eyeFlagSort = null;
                hairFlagSort = null;
                countryFlagSort = null;
            } else if (columnFlag.equals("nameFlagSort")) {
                if (nameFlagSort == null) {
                    nameFlagSort = true;
                    headerNameButton.setText("↓");
                } else if (nameFlagSort == true) {
                    headerNameButton.setText("↑");
                    nameFlagSort = false;
                } else if (nameFlagSort == false) {
                    headerNameButton.setText("⇅");
                    nameFlagSort = null;
                }
                headerKeyButton.setText("⇅");
                headerIdButton.setText("⇅");
                headerXButton.setText("⇅");
                headerYButton.setText("⇅");
                headerCreationDateButton.setText("⇅");
                headerPriceButton.setText("⇅");
                headerTypeButton.setText("⇅");
                headerBirthdayButton.setText("⇅");
                headerEyeButton.setText("⇅");
                headerHairButton.setText("⇅");
                headerCountryButton.setText("⇅");
                keyFlagSort = null;
                idFlagSort = null;
                xFlagSort = null;
                yFlagSort = null;
                creationDateFlagSort = null;
                priceFlagSort = null;
                typeFlagSort = null;
                birthdayFlagSort = null;
                eyeFlagSort = null;
                hairFlagSort = null;
                countryFlagSort = null;
            } else if (columnFlag.equals("xFlagSort")) {
                if (xFlagSort == null) {
                    xFlagSort = true;
                    headerXButton.setText("↓");
                } else if (xFlagSort == true) {
                    headerXButton.setText("↑");
                    xFlagSort = false;
                } else if (xFlagSort == false) {
                    headerXButton.setText("⇅");
                    xFlagSort = null;
                }
                headerKeyButton.setText("⇅");
                headerIdButton.setText("⇅");
                headerNameButton.setText("⇅");
                headerYButton.setText("⇅");
                headerCreationDateButton.setText("⇅");
                headerPriceButton.setText("⇅");
                headerTypeButton.setText("⇅");
                headerBirthdayButton.setText("⇅");
                headerEyeButton.setText("⇅");
                headerHairButton.setText("⇅");
                headerCountryButton.setText("⇅");
                keyFlagSort = null;
                idFlagSort = null;
                nameFlagSort = null;
                yFlagSort = null;
                creationDateFlagSort = null;
                priceFlagSort = null;
                typeFlagSort = null;
                birthdayFlagSort = null;
                eyeFlagSort = null;
                hairFlagSort = null;
                countryFlagSort = null;
            } else if (columnFlag.equals("yFlagSort")) {
                if (yFlagSort == null) {
                    yFlagSort = true;
                    headerYButton.setText("↓");
                } else if (yFlagSort == true) {
                    headerYButton.setText("↑");
                    yFlagSort = false;
                } else if (yFlagSort == false) {
                    headerYButton.setText("⇅");
                    yFlagSort = null;
                }
                headerKeyButton.setText("⇅");
                headerIdButton.setText("⇅");
                headerNameButton.setText("⇅");
                headerXButton.setText("⇅");
                headerCreationDateButton.setText("⇅");
                headerPriceButton.setText("⇅");
                headerTypeButton.setText("⇅");
                headerBirthdayButton.setText("⇅");
                headerEyeButton.setText("⇅");
                headerHairButton.setText("⇅");
                headerCountryButton.setText("⇅");
                keyFlagSort = null;
                idFlagSort = null;
                nameFlagSort = null;
                xFlagSort = null;
                creationDateFlagSort = null;
                priceFlagSort = null;
                typeFlagSort = null;
                birthdayFlagSort = null;
                eyeFlagSort = null;
                hairFlagSort = null;
                countryFlagSort = null;
            } else if (columnFlag.equals("creationDateFlagSort")) {
                if (creationDateFlagSort == null) {
                    creationDateFlagSort = true;
                    headerCreationDateButton.setText("↓");
                } else if (creationDateFlagSort == true) {
                    headerCreationDateButton.setText("↑");
                    creationDateFlagSort = false;
                } else if (creationDateFlagSort == false) {
                    headerCreationDateButton.setText("⇅");
                    creationDateFlagSort = null;
                }
                headerKeyButton.setText("⇅");
                headerIdButton.setText("⇅");
                headerNameButton.setText("⇅");
                headerXButton.setText("⇅");
                headerYButton.setText("⇅");
                headerPriceButton.setText("⇅");
                headerTypeButton.setText("⇅");
                headerBirthdayButton.setText("⇅");
                headerEyeButton.setText("⇅");
                headerHairButton.setText("⇅");
                headerCountryButton.setText("⇅");
                keyFlagSort = null;
                idFlagSort = null;
                nameFlagSort = null;
                xFlagSort = null;
                yFlagSort = null;
                priceFlagSort = null;
                typeFlagSort = null;
                birthdayFlagSort = null;
                eyeFlagSort = null;
                hairFlagSort = null;
                countryFlagSort = null;
            } else if (columnFlag.equals("priceFlagSort")) {
                if (priceFlagSort == null) {
                    priceFlagSort = true;
                    headerPriceButton.setText("↓");
                } else if (priceFlagSort == true) {
                    headerPriceButton.setText("↑");
                    priceFlagSort = false;
                } else if (priceFlagSort == false) {
                    headerPriceButton.setText("⇅");
                    priceFlagSort = null;
                }
                headerKeyButton.setText("⇅");
                headerIdButton.setText("⇅");
                headerNameButton.setText("⇅");
                headerXButton.setText("⇅");
                headerYButton.setText("⇅");
                headerCreationDateButton.setText("⇅");
                headerTypeButton.setText("⇅");
                headerBirthdayButton.setText("⇅");
                headerEyeButton.setText("⇅");
                headerHairButton.setText("⇅");
                headerCountryButton.setText("⇅");
                keyFlagSort = null;
                idFlagSort = null;
                nameFlagSort = null;
                xFlagSort = null;
                yFlagSort = null;
                creationDateFlagSort = null;
                typeFlagSort = null;
                birthdayFlagSort = null;
                eyeFlagSort = null;
                hairFlagSort = null;
                countryFlagSort = null;
            } else if (columnFlag.equals("typeFlagSort")) {
                if (typeFlagSort == null) {
                    typeFlagSort = true;
                    headerTypeButton.setText("↓");
                } else if (typeFlagSort == true) {
                    headerTypeButton.setText("↑");
                    typeFlagSort = false;
                } else if (typeFlagSort == false) {
                    headerTypeButton.setText("⇅");
                    typeFlagSort = null;
                }
                headerKeyButton.setText("⇅");
                headerIdButton.setText("⇅");
                headerNameButton.setText("⇅");
                headerXButton.setText("⇅");
                headerYButton.setText("⇅");
                headerCreationDateButton.setText("⇅");
                headerPriceButton.setText("⇅");
                headerBirthdayButton.setText("⇅");
                headerEyeButton.setText("⇅");
                headerHairButton.setText("⇅");
                headerCountryButton.setText("⇅");
                keyFlagSort = null;
                idFlagSort = null;
                nameFlagSort = null;
                xFlagSort = null;
                yFlagSort = null;
                creationDateFlagSort = null;
                priceFlagSort = null;
                birthdayFlagSort = null;
                eyeFlagSort = null;
                hairFlagSort = null;
                countryFlagSort = null;
            } else if (columnFlag.equals("birthdayFlagSort")) {
                if (birthdayFlagSort == null) {
                    birthdayFlagSort = true;
                    headerBirthdayButton.setText("↓");
                } else if (birthdayFlagSort == true) {
                    headerBirthdayButton.setText("↑");
                    birthdayFlagSort = false;
                } else if (birthdayFlagSort == false) {
                    headerBirthdayButton.setText("⇅");
                    birthdayFlagSort = null;
                }
                headerKeyButton.setText("⇅");
                headerIdButton.setText("⇅");
                headerNameButton.setText("⇅");
                headerXButton.setText("⇅");
                headerYButton.setText("⇅");
                headerCreationDateButton.setText("⇅");
                headerPriceButton.setText("⇅");
                headerTypeButton.setText("⇅");
                headerEyeButton.setText("⇅");
                headerHairButton.setText("⇅");
                headerCountryButton.setText("⇅");
                keyFlagSort = null;
                idFlagSort = null;
                nameFlagSort = null;
                xFlagSort = null;
                yFlagSort = null;
                creationDateFlagSort = null;
                priceFlagSort = null;
                typeFlagSort = null;
                eyeFlagSort = null;
                hairFlagSort = null;
                countryFlagSort = null;
            } else if (columnFlag.equals("eyeFlagSort")) {
                if (eyeFlagSort == null) {
                    eyeFlagSort = true;
                    headerEyeButton.setText("↓");
                } else if (eyeFlagSort == true) {
                    headerEyeButton.setText("↑");
                    eyeFlagSort = false;
                } else if (eyeFlagSort == false) {
                    headerEyeButton.setText("⇅");
                    eyeFlagSort = null;
                }
                headerKeyButton.setText("⇅");
                headerIdButton.setText("⇅");
                headerNameButton.setText("⇅");
                headerXButton.setText("⇅");
                headerYButton.setText("⇅");
                headerCreationDateButton.setText("⇅");
                headerPriceButton.setText("⇅");
                headerTypeButton.setText("⇅");
                headerBirthdayButton.setText("⇅");
                headerHairButton.setText("⇅");
                headerCountryButton.setText("⇅");
                keyFlagSort = null;
                idFlagSort = null;
                nameFlagSort = null;
                xFlagSort = null;
                yFlagSort = null;
                creationDateFlagSort = null;
                priceFlagSort = null;
                typeFlagSort = null;
                birthdayFlagSort = null;
                hairFlagSort = null;
                countryFlagSort = null;
            } else if (columnFlag.equals("hairFlagSort")) {
                if (hairFlagSort == null) {
                    hairFlagSort = true;
                    headerHairButton.setText("↓");
                } else if (hairFlagSort == true) {
                    headerHairButton.setText("↑");
                    hairFlagSort = false;
                } else if (hairFlagSort == false) {
                    headerHairButton.setText("⇅");
                    hairFlagSort = null;
                }
                headerKeyButton.setText("⇅");
                headerIdButton.setText("⇅");
                headerNameButton.setText("⇅");
                headerXButton.setText("⇅");
                headerYButton.setText("⇅");
                headerCreationDateButton.setText("⇅");
                headerPriceButton.setText("⇅");
                headerTypeButton.setText("⇅");
                headerBirthdayButton.setText("⇅");
                headerEyeButton.setText("⇅");
                headerCountryButton.setText("⇅");
                keyFlagSort = null;
                idFlagSort = null;
                nameFlagSort = null;
                xFlagSort = null;
                yFlagSort = null;
                creationDateFlagSort = null;
                priceFlagSort = null;
                typeFlagSort = null;
                birthdayFlagSort = null;
                eyeFlagSort = null;
                countryFlagSort = null;
            } else if (columnFlag.equals("countryFlagSort")) {
                if (countryFlagSort == null) {
                    countryFlagSort = true;
                    headerCountryButton.setText("↓");
                } else if (countryFlagSort == true) {
                    headerCountryButton.setText("↑");
                    countryFlagSort = false;
                } else if (countryFlagSort == false) {
                    headerCountryButton.setText("⇅");
                    countryFlagSort = null;
                }
                headerKeyButton.setText("⇅");
                headerIdButton.setText("⇅");
                headerNameButton.setText("⇅");
                headerXButton.setText("⇅");
                headerYButton.setText("⇅");
                headerCreationDateButton.setText("⇅");
                headerPriceButton.setText("⇅");
                headerTypeButton.setText("⇅");
                headerBirthdayButton.setText("⇅");
                headerEyeButton.setText("⇅");
                headerHairButton.setText("⇅");
                keyFlagSort = null;
                idFlagSort = null;
                nameFlagSort = null;
                xFlagSort = null;
                yFlagSort = null;
                creationDateFlagSort = null;
                priceFlagSort = null;
                typeFlagSort = null;
                birthdayFlagSort = null;
                eyeFlagSort = null;
                hairFlagSort = null;
            }
        }

}
