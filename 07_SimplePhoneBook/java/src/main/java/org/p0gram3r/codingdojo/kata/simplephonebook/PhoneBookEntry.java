package org.p0gram3r.codingdojo.kata.simplephonebook;

import java.io.Serializable;

public class PhoneBookEntry implements Serializable {

    private static final long serialVersionUID = 1337L;

    private String name;
    private String number;
    private transient Integer age;

    public PhoneBookEntry(String name, String number) {
        this(name, number, null);
    }

    public PhoneBookEntry(String name, String number, Integer age) {
        this.name = name;
        this.number = number;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public Integer getAge() {
        return age;
    }

}
