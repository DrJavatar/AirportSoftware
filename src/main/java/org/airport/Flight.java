package org.airport;

public final class Flight {

    private int flightId;
    private Airplane airplane;
    private String airport_code;
    private String airport_code_dest;

    public Flight(int flightId, Airplane airplane, String airport_code, String airport_code_dest) {
        this.flightId = flightId;
        this.airplane = airplane;
        this.airport_code = airport_code;
        this.airport_code_dest = airport_code_dest;
    }

    public Flight(Airplane airplane, String airport_code, String airport_code_dest) {
        this(-1, airplane, airport_code, airport_code_dest);
    }

    public int getFlightId() {
        return flightId;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public String getAirport_code() {
        return airport_code;
    }

    public String getAirport_code_dest() {
        return airport_code_dest;
    }
}
