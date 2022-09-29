package com.ncst.collection;

import java.util.Objects;

public class Person {
    private String name;
    private String chest;

    public Person() {

    }

    public Person(String name, String chest) {
        this.name = name;
        this.chest = chest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(chest, person.chest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, chest);
    }

    public void setChest(String chest) {
        this.chest = chest;
    }

    public String setChest() {
        return chest;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{" +
                "name: " + name +
                ", chest: " + chest +
                '}';
    }
}