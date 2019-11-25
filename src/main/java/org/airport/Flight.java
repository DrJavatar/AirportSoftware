package org.airport;

public final class Flight {

    private Airplane airplane;
    private String airport_code;
    private String airport_code_dest;

    public Flight(Airplane airplane, String airport_code, String airport_code_dest) {
        this.airplane = airplane;
        this.airport_code = airport_code;
        this.airport_code_dest = airport_code_dest;
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
