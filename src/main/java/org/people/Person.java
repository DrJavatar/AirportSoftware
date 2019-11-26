package org.people;

import java.util.Objects;

public abstract class Person {

    private String firstName;
    private String lastName;
    private int flightNumber;
    private int socialNumber;

    public Person(String firstName, String lastName, int flightNumber, int socialNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.flightNumber = flightNumber;
        this.socialNumber = socialNumber;
    }

    public Person(String firstName, String lastName, int socialNumber) {
        this(firstName, lastName, -1, socialNumber);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getSocialNumber() {
        return socialNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSocialNumber(int socialNumber) {
        this.socialNumber = socialNumber;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return socialNumber == person.socialNumber &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, flightNumber, socialNumber);
    }
}
