package ProcessEngine.GraphicCore.MainWindow.DataSheet.FilterByColumn;

import ProcessEngine.DataCore.DataRun;
import ProcessEngine.GraphicCore.MainWindow.DataSheet.DataSheet;

import javafx.geometry.Side;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

public class ColumnKeyFilterAction {

    public static void columnKeyFilterAction(Button headerKeyFilterButton, DataRun dataRun) {

        if (headerKeyFilterButton.getUserData() instanceof ContextMenu) {
                ContextMenu currentDropdown = (ContextMenu) headerKeyFilterButton.getUserData();
                if (currentDropdown.isShowing()) {
                    return;
                }
            }
            
            ContextMenu dropdown = new ContextMenu();
            VBox checkBoxContainer = new VBox();
            checkBoxContainer.setSpacing(5);
            
            for (String item : DataRun.uniqueKeyElementsSet) {
                CheckBox checkBox = new CheckBox(item);
                if (DataSheet.filteredKeyElementsSet.contains(item)) {
                    checkBox.setSelected(true);
                } else {
                    checkBox.setSelected(false);
                }

                checkBox.setOnAction(e -> {
                    if (checkBox.isSelected()) {
                        DataSheet.filteredKeyElementsSet.add(item);
                    } else {
                        DataSheet.filteredKeyElementsSet.remove(item);
                    }
                });
                checkBoxContainer.getChildren().add(checkBox);
            }
            
            ScrollPane scrollPane = new ScrollPane(checkBoxContainer);
            scrollPane.setPrefSize(200, 150);
            CustomMenuItem scrollableItem = new CustomMenuItem(scrollPane);
            scrollableItem.setHideOnClick(false);
            dropdown.getItems().add(scrollableItem);
            
            headerKeyFilterButton.setUserData(dropdown);
            dropdown.setOnHidden(e -> headerKeyFilterButton.setUserData(null));
            dropdown.show(headerKeyFilterButton, Side.BOTTOM, 0, 0);

    }
    
}
