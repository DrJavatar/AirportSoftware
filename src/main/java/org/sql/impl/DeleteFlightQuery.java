package org.sql.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.airport.Airplane;
import org.airport.Flight;
import org.sql.SQLCallback;
import org.sql.SQLManager;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteFlightQuery implements SQLCallback<Flight> {

    private Airplane airplane;

    public DeleteFlightQuery(Airplane airplane) {
        this.airplane = airplane;
    }

    @Override
    public ObservableList<Flight> call() throws SQLException {
        Connection con = SQLManager.getSqlManager().createConnection();
        con.prepareStatement("DELETE FROM Flights WHERE aircraft_type = '" + airplane.getPlaneId() + "';").executeUpdate();
        con.close();
        return FXCollections.emptyObservableList();
    }
}
