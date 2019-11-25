package org.controller.table;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.airport.Airplane;

public class StringCellFactory implements Callback<TableColumn<Airplane, String>, TableCell<Airplane, String>> {
    @Override
    public TableCell<Airplane, String> call(TableColumn<Airplane, String> airplaneStringTableColumn) {
        return new TableCell<Airplane, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if(!empty && item != null) {
                    super.setText(item);
                } else {
                    super.setText(null);
                }
            }
        };
    }

}
