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

public class ColumnIdFilterAction {

    public static void columnIdFilterAction(Button headerIdFilterButton, DataRun dataRun) {

        if (headerIdFilterButton.getUserData() instanceof ContextMenu) {
                ContextMenu currentDropdown = (ContextMenu) headerIdFilterButton.getUserData();
                if (currentDropdown.isShowing()) {
                    return;
                }
            }
            
            ContextMenu dropdown = new ContextMenu();
            VBox checkBoxContainer = new VBox();
            checkBoxContainer.setSpacing(5);

            for (String item : DataRun.uniqueIdElementsSet) {
                CheckBox checkBox = new CheckBox(item);
                if (DataSheet.filteredIdElementsSet.contains(item)) {
                    checkBox.setSelected(true);
                } else {
                    checkBox.setSelected(false);
                }

                checkBox.setOnAction(e -> {
                    if (checkBox.isSelected()) {
                        DataSheet.filteredIdElementsSet.add(item);
                    } else {
                        DataSheet.filteredIdElementsSet.remove(item);
                    }
                });
                checkBoxContainer.getChildren().add(checkBox);
            }
            
            ScrollPane scrollPane = new ScrollPane(checkBoxContainer);
            scrollPane.setPrefSize(200, 150);
            CustomMenuItem scrollableItem = new CustomMenuItem(scrollPane);
            scrollableItem.setHideOnClick(false);
            dropdown.getItems().add(scrollableItem);

            headerIdFilterButton.setUserData(dropdown);
            dropdown.setOnHidden(e -> headerIdFilterButton.setUserData(null));
            dropdown.show(headerIdFilterButton, Side.BOTTOM, 0, 0);

    }

}
