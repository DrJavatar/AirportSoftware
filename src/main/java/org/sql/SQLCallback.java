package org.sql;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

@FunctionalInterface
public interface SQLCallback<T> {

    ObservableList<T> call() throws SQLException;

}
