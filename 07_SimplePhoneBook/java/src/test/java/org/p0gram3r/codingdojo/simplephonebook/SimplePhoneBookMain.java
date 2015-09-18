package org.p0gram3r.codingdojo.simplephonebook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SimplePhoneBookMain {

    static String FILE_NAME = "sample_entry.ser";

    public static void main(String[] args) throws Exception {
        serialize();
        deserialize();
    }

    private static void serialize() throws FileNotFoundException, IOException {
        PhoneBookEntry entryOut = new PhoneBookEntry("Andre", "1234", 31);
        System.out.println(entryOut);
        System.out.println();

        FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(entryOut);
        out.close();
        System.out.println("Serialized data is saved in " + FILE_NAME);
        System.out.println();
    }

    private static void deserialize() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(FILE_NAME);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        PhoneBookEntry entryIn = (PhoneBookEntry) in.readObject();
        in.close();
        System.out.println(entryIn);
    }

}

class PhoneBookEntry implements Serializable {

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

    @Override
    public String toString() {
        return "PhoneBookEntry [name=" + name + ", number=" + number + ", age=" + age + "]";
    }

}