package org.airport;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Airport {

    private String code;
    private Map<String, Airplane> planes;
    private Map<Airplane, Flight> flights;

    public Airport(String code) {
        this.code = code;
        this.planes = new HashMap<>();
        this.flights = new HashMap<>();
    }

    public void registerAircraft(Airplane airplane) {
        this.planes.put(airplane.getPlaneId(), airplane);
    }

    public Optional<Flight> launchFlight(Airplane airplane, String airport_code, String airport_code_dest) {
        if(this.planes.containsKey(airplane.getPlaneId())) {
            this.flights.put(airplane, new Flight(airplane, airport_code, airport_code_dest));
            return Optional.of(this.flights.get(airplane));
        }
        return Optional.empty();
    }

    public Optional<Flight> getActiveFlight(Airplane airplane) {
        return Optional.ofNullable(this.flights.get(airplane));
    }

    public String getCode() {
        return code;
    }
}
