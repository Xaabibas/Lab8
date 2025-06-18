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

public class ColumnCreationDateFilterAction {

    public static void columnCreationDateFilterAction(Button headerCreationDateFilterButton, DataRun dataRun) {

        if (headerCreationDateFilterButton.getUserData() instanceof ContextMenu) {
                ContextMenu currentDropdown = (ContextMenu) headerCreationDateFilterButton.getUserData();
                if (currentDropdown.isShowing()) {
                    return;
                }
            }
            
            ContextMenu dropdown = new ContextMenu();
            VBox checkBoxContainer = new VBox();
            checkBoxContainer.setSpacing(5);

            for (String item : DataRun.uniqueCreationDateElementsSet) {
                CheckBox checkBox = new CheckBox(item);
                if (DataSheet.filteredCreationDateElementsSet.contains(item)) {
                    checkBox.setSelected(true);
                } else {
                    checkBox.setSelected(false);
                }

                checkBox.setOnAction(e -> {
                    if (checkBox.isSelected()) {
                        DataSheet.filteredCreationDateElementsSet.add(item);
                    } else {
                        DataSheet.filteredCreationDateElementsSet.remove(item);
                    }
                });
                checkBoxContainer.getChildren().add(checkBox);
            }
            
            ScrollPane scrollPane = new ScrollPane(checkBoxContainer);
            scrollPane.setPrefSize(200, 150);
            CustomMenuItem scrollableItem = new CustomMenuItem(scrollPane);
            scrollableItem.setHideOnClick(false);
            dropdown.getItems().add(scrollableItem);

            headerCreationDateFilterButton.setUserData(dropdown);
            dropdown.setOnHidden(e -> headerCreationDateFilterButton.setUserData(null));
            dropdown.show(headerCreationDateFilterButton, Side.BOTTOM, 0, 0);

    }
    
}
