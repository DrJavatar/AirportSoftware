package org.sql;

import java.sql.SQLException;

@FunctionalInterface
public interface SQLCallback<T> {

    T call() throws SQLException;

}
