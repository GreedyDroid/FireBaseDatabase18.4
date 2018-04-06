package com.example.sayed.firebasedatabase184;

public class Person {
    private String personName;
    private String personEmail;
    private String databaseKey;

    public Person(String personName, String personEmail, String databaseKey) {
        this.personName = personName;
        this.personEmail = personEmail;
        this.databaseKey = databaseKey;
    }

    public Person() {
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public String getDatabaseKey() {
        return databaseKey;
    }
}
