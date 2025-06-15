package ProcessEngine.GraphicCore.MainWindow.DataSheet;

import ProcessEngine.DataCore.DataRun;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.concurrent.Task;
import javafx.application.Platform;
import moduls.Country;
import moduls.EyeColor;
import moduls.HairColor;
import moduls.TicketType;

public class DataSheet {

    protected DataRun dataRun; // dataRun.getCollectionData() - даст коллекцию (уже потокобезопасно)

    public DataSheet(DataRun dataRun) {
        this.dataRun = dataRun;
    }

    public TableView<String[]> getDataSheet() {
        ObservableList<String[]> tickets = FXCollections.observableArrayList(
                dataRun.getCollectionData()
        );
        TableView<String[]> table = new TableView<>();
        table.setItems(tickets);

        TableColumn<String[], Long> keyColumn = new TableColumn<>("key");
        keyColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(Long.parseLong(s.getValue()[0])));
        table.getColumns().add(keyColumn);

        TableColumn<String[], Long> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(Long.parseLong(s.getValue()[1])));
        table.getColumns().add(idColumn);

        TableColumn<String[], String> nameColumn = new TableColumn<>("name");
        nameColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[2]));
        table.getColumns().add(nameColumn);

        TableColumn<String[], Float> xColumn = new TableColumn<>("x");
        xColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(Float.parseFloat(s.getValue()[3])));
        table.getColumns().add(xColumn);

        TableColumn<String[], Long> yColumn = new TableColumn<>("y");
        yColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(Long.parseLong(s.getValue()[4])));
        table.getColumns().add(yColumn);

        TableColumn<String[], String> creationDateColumn = new TableColumn<>("creation date");
        creationDateColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[5]));
        table.getColumns().add(creationDateColumn);

        TableColumn<String[], Float> priceColumn = new TableColumn<>("price");
        priceColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(Float.parseFloat(s.getValue()[6])));
        table.getColumns().add(priceColumn);

        TableColumn<String[], TicketType> typeColumn = new TableColumn<>("type");
        typeColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[7].equals("null") ? null : TicketType.valueOf(s.getValue()[7])));
        table.getColumns().add(typeColumn);

        TableColumn<String[], String> birthdayColumn = new TableColumn<>("birthday");
        birthdayColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[8].equals("null") ? null : s.getValue()[8]));
        table.getColumns().add(birthdayColumn);

        TableColumn<String[], EyeColor> eyeColumn = new TableColumn<>("eye");
        eyeColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[9].equals("null") ? null : EyeColor.valueOf(s.getValue()[9])));
        table.getColumns().add(eyeColumn);

        TableColumn<String[], HairColor> hairColumn = new TableColumn<>("hair");
        hairColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[10].equals("null") ? null : HairColor.valueOf(s.getValue()[10])));
        table.getColumns().add(hairColumn);

        TableColumn<String[], Country> countryColumn = new TableColumn<>("country");
        countryColumn.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue()[11].equals("null") ? null : Country.valueOf(s.getValue()[11])));
        table.getColumns().add(countryColumn);

        startAutoParallelUpdateDataSheet(table);

        return table;
    }

    protected void startAutoParallelUpdateDataSheet(TableView<String[]> table) {
        Task<Void> updateTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (!isCancelled()) {
                    // запрашиваешь новые данные из dataRun
                    // че то обновляешь для графических элементов
                    ObservableList<String[]> tickets = FXCollections.observableArrayList(
                            dataRun.getCollectionData()
                    );
                    Platform.runLater(() -> {
                        table.setItems(tickets);
                    });
                    Thread.sleep(3000);
                }
                return null;
            }
        };

        Thread newThread = new Thread(updateTask);
        newThread.setDaemon(true);
        newThread.start();
    }
    
}
