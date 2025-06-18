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

public class ColumnNameFilterAction {

    public static void columnNameFilterAction(Button headerNameFilterButton, DataRun dataRun) {

        if (headerNameFilterButton.getUserData() instanceof ContextMenu) {
                ContextMenu currentDropdown = (ContextMenu) headerNameFilterButton.getUserData();
                if (currentDropdown.isShowing()) {
                    return;
                }
            }
            
            ContextMenu dropdown = new ContextMenu();
            VBox checkBoxContainer = new VBox();
            checkBoxContainer.setSpacing(5);

            for (String item : DataRun.uniqueNameElementsSet) {
                CheckBox checkBox = new CheckBox(item);
                if (DataSheet.filteredNameElementsSet.contains(item)) {
                    checkBox.setSelected(true);
                } else {
                    checkBox.setSelected(false);
                }

                checkBox.setOnAction(e -> {
                    if (checkBox.isSelected()) {
                        DataSheet.filteredNameElementsSet.add(item);
                    } else {
                        DataSheet.filteredNameElementsSet.remove(item);
                    }
                });
                checkBoxContainer.getChildren().add(checkBox);
            }
            
            ScrollPane scrollPane = new ScrollPane(checkBoxContainer);
            scrollPane.setPrefSize(200, 150);
            CustomMenuItem scrollableItem = new CustomMenuItem(scrollPane);
            scrollableItem.setHideOnClick(false);
            dropdown.getItems().add(scrollableItem);

            headerNameFilterButton.setUserData(dropdown);
            dropdown.setOnHidden(e -> headerNameFilterButton.setUserData(null));
            dropdown.show(headerNameFilterButton, Side.BOTTOM, 0, 0);

    }
    
}
