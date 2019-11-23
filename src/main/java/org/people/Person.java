package org.people;

public abstract class Person {

    private String firstName;
    private String lastName;
    private int socialNumber;

    public Person(String firstName, String lastName, int socialNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialNumber = socialNumber;
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

}
