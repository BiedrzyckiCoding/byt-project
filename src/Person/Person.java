package Person;

import Utils.ValidationUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public abstract class Person implements Serializable {
    private String name;
    private List<String> address;
    private String surname;
    private String email;
    private LocalDate birthDate;
    private int age;

    public Person(String name, List<String> address, String surname, String email, LocalDate birthDate) {
        ValidationUtil.notNull(name, "name");
        ValidationUtil.emptyList(address, "address");
        ValidationUtil.notNull(surname, "surname");
        ValidationUtil.notNull(email, "email");
        ValidationUtil.notFuture(birthDate, "birthDate");

        this.name = name;
        this.address = address;
        this.surname = surname;
        this.email = email;
        this.birthDate = birthDate;
        this.age = LocalDate.now().getYear() - birthDate.getYear();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }
}