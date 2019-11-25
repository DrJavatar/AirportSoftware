package org.sql;

import java.sql.SQLException;
import java.util.List;

@FunctionalInterface
public interface SQLCallback<T> {

    List<T> call() throws SQLException;

}
