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

public class ColumnPriceFilterAction {

    public static void columnPriceFilterAction(Button headerPriceFilterButton, DataRun dataRun) {

        if (headerPriceFilterButton.getUserData() instanceof ContextMenu) {
                ContextMenu currentDropdown = (ContextMenu) headerPriceFilterButton.getUserData();
                if (currentDropdown.isShowing()) {
                    return;
                }
            }
            
            ContextMenu dropdown = new ContextMenu();
            VBox checkBoxContainer = new VBox();
            checkBoxContainer.setSpacing(5);

            for (String item : DataRun.uniquePriceElementsSet) {
                CheckBox checkBox = new CheckBox(item);
                if (DataSheet.filteredPriceElementsSet.contains(item)) {
                    checkBox.setSelected(true);
                } else {
                    checkBox.setSelected(false);
                }

                checkBox.setOnAction(e -> {
                    if (checkBox.isSelected()) {
                        DataSheet.filteredPriceElementsSet.add(item);
                    } else {
                        DataSheet.filteredPriceElementsSet.remove(item);
                    }
                });
                checkBoxContainer.getChildren().add(checkBox);
            }
            
            ScrollPane scrollPane = new ScrollPane(checkBoxContainer);
            scrollPane.setPrefSize(200, 150);
            CustomMenuItem scrollableItem = new CustomMenuItem(scrollPane);
            scrollableItem.setHideOnClick(false);
            dropdown.getItems().add(scrollableItem);

            headerPriceFilterButton.setUserData(dropdown);
            dropdown.setOnHidden(e -> headerPriceFilterButton.setUserData(null));
            dropdown.show(headerPriceFilterButton, Side.BOTTOM, 0, 0);

    }
    
}
