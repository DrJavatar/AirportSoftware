package org.sql;

@FunctionalInterface
public interface SQLCallback<T> {

    T call();

}
