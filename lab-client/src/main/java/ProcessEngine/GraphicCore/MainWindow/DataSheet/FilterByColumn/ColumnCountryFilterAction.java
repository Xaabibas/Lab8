package ProcessEngine.GraphicCore.MainWindow.DataSheet.FilterByColumn;

import ProcessEngine.DataCore.DataRun;
import ProcessEngine.GraphicCore.MainWindow.DataSheet.DataSheet;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ColumnCountryFilterAction {

    public static void columnCountryFilterAction(Button headerCountryFilterButton, DataRun dataRun) {

        if (headerCountryFilterButton.getUserData() instanceof ContextMenu) {
                ContextMenu currentDropdown = (ContextMenu) headerCountryFilterButton.getUserData();
                if (currentDropdown.isShowing()) {
                    return;
                }
            }
            
            ContextMenu dropdown = new ContextMenu();
            VBox checkBoxContainer = new VBox();
            checkBoxContainer.setSpacing(5);

            for (String item : DataRun.uniqueCountryElementsSet) {
                CheckBox checkBox = new CheckBox(item);
                if (DataSheet.filteredCountryElementsSet.contains(item)) {
                    checkBox.setSelected(true);
                } else {
                    checkBox.setSelected(false);
                }

                checkBox.setOnAction(e -> {
                    if (checkBox.isSelected()) {
                        DataSheet.filteredCountryElementsSet.add(item);
                    } else {
                        DataSheet.filteredCountryElementsSet.remove(item);
                    }
                });
                checkBoxContainer.getChildren().add(checkBox);
            }
            
            ScrollPane scrollPane = new ScrollPane(checkBoxContainer);
            scrollPane.setPrefSize(200, 150);
            CustomMenuItem scrollableItem = new CustomMenuItem(scrollPane);
            scrollableItem.setHideOnClick(false);
            dropdown.getItems().add(scrollableItem);

            headerCountryFilterButton.setUserData(dropdown);
            dropdown.setOnHidden(e -> headerCountryFilterButton.setUserData(null));
            dropdown.show(headerCountryFilterButton, Side.BOTTOM, 0, 0);

    }
    
}
