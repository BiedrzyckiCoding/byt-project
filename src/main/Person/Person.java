package main.Person;

import main.Utils.ValidationUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public abstract class Person implements Serializable {
    private String name;
    private List<String> address;
    private String surname;
    private String email;
    private LocalDate birthDate;

    public Person(String name, List<String> address, String surname, String email, LocalDate birthDate) {
        ValidationUtil.notEmptyString(name, "name");
        ValidationUtil.nonEmptyList(address, "address");
        ValidationUtil.notEmptyString(surname, "surname");
        ValidationUtil.notEmptyString(email, "email");
        ValidationUtil.notFuture(birthDate, "birthDate");

        this.name = name;
        this.address = address;
        this.surname = surname;
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        ValidationUtil.notEmptyString(name, "name");
        this.name = name;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        ValidationUtil.nonEmptyList(address, "address");
        this.address = address;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        ValidationUtil.notEmptyString(surname, "surname");
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        ValidationUtil.notEmptyString(email, "email");
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        ValidationUtil.notFuture(birthDate, "birthDate");
        this.birthDate = birthDate;
    }

    public int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }
}