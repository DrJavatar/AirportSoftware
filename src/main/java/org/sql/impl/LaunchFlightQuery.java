package org.sql.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.airport.Airplane;
import org.airport.Flight;
import org.sql.SQLCallback;
import org.sql.SQLManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LaunchFlightQuery implements SQLCallback<Flight> {

    private Flight flight;

    public LaunchFlightQuery(Flight flight) {
        this.flight = flight;
    }

    @Override
    public ObservableList<Flight> call() throws SQLException {
        Connection con = SQLManager.getSqlManager().createConnection();
        Airplane airplane = flight.getAirplane();
        PreparedStatement stat = con.prepareStatement("INSERT INTO Flights (aircraft_type, airport_code, airport_code_dest, pilot_id, co_pilot_id) VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE flight_id = ?, aircraft_type = ?, airport_code = ?, airport_code_dest = ?, pilot_id = ?, co_pilot_id = ?;");
        stat.setString(1, airplane.getPlaneId());
        stat.setString(2, flight.getAirport_code());
        stat.setString(3, flight.getAirport_code_dest());
        stat.setInt(4, airplane.getPilot().getSocialNumber());
        stat.setInt(5, airplane.getCoPilot().getSocialNumber());
        stat.setInt(6, flight.getFlightId());
        stat.setString(7, airplane.getPlaneId());
        stat.setString(8, flight.getAirport_code());
        stat.setString(9, flight.getAirport_code_dest());
        stat.setInt(10, airplane.getPilot().getSocialNumber());
        stat.setInt(11, airplane.getCoPilot().getSocialNumber());
        stat.executeUpdate();
        return FXCollections.emptyObservableList();
    }
}
