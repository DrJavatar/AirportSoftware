package org.airport;

import java.util.HashMap;
import java.util.Map;

public class Airport {

    private int code;
    private Map<Integer, Airplane> planes;

    public Airport(int code) {
        this.code = code;
        this.planes = new HashMap<>();
    }

    public void registerAircraft(Airplane airplane) {
        this.planes.put(airplane.getPlaneId(), airplane);
    }



}
