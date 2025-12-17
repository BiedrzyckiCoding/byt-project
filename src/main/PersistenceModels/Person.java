package main.PersistenceModels;

import main.Utils.ValidationUtil;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static List<Person> extent = new ArrayList<>();

    private String name;
    private List<String> address;
    private String surname;
    private String email;
    private LocalDate birthDate;

    private Customer customerRole;
    private Employee employeeRole;

    private Person(String name, List<String> address, String surname, String email, LocalDate birthDate) {
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

        addToExtent(this);
    }

    public Person(String name, List<String> address, String surname, String email, LocalDate birthDate, Customer.CustomerData customerData) {
        this(name, address, surname, email, birthDate);
        ValidationUtil.notNull(customerData, "customerData");
        this.customerRole = new Customer(this, customerData);
    }

    public Person(String name, List<String> address, String surname, String email, LocalDate birthDate, Employee.EmployeeData employeeData) {
        this(name, address, surname, email, birthDate);
        ValidationUtil.notNull(employeeData, "employeeData");
        this.employeeRole = new Employee(this, employeeData);
    }

    public Person(String name, List<String> address, String surname, String email, LocalDate birthDate, Customer.CustomerData customerData, Employee.EmployeeData employeeData) {
        this(name, address, surname, email, birthDate);
        ValidationUtil.notNull(customerData, "customerData");
        ValidationUtil.notNull(employeeData, "employeeData");
        this.customerRole = new Customer(this, customerData);
        this.employeeRole = new Employee(this, employeeData);
    }

    public boolean isCustomer() { return customerRole != null; }
    public boolean isEmployee() { return employeeRole != null; }

    public Customer asCustomer() {
        if (customerRole == null) {
            throw new IllegalStateException("This person is not a Customer");
        }
        return customerRole;
    }

    public Employee asEmployee() {
        if (employeeRole == null) {
            throw new IllegalStateException("This person is not an Employee");
        }
        return employeeRole;
    }

    public void attachCustomer(Customer.CustomerData customerData) {
        ValidationUtil.notNull(customerData, "customerData");
        if (customerRole != null) {
            throw new IllegalStateException("Already a Customer");
        }
        customerRole = new Customer(this, customerData);
    }

    public void attachEmployee(Employee.EmployeeData employeeData) {
        ValidationUtil.notNull(employeeData, "employeeData");
        if (employeeRole != null) {
            throw new IllegalStateException("Already an Employee");
        }
        employeeRole = new Employee(this, employeeData);
    }

    public void detachCustomer() {
        if (customerRole == null) return;
        if (employeeRole == null) {
            throw new IllegalStateException("At least one role must be assigned");
        }
        customerRole.onDetach();
        customerRole = null;
    }

    public void detachEmployee() {
        if (employeeRole == null) return;
        if (customerRole == null) {
            throw new IllegalStateException("At least one role must be assigned");
        }
        employeeRole.onDetach();
        employeeRole = null;
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

    private static void addToExtent(Person p) {
        if (p == null) throw new IllegalArgumentException("Person cannot be null");
        extent.add(p);
    }

    public static List<Person> getExtent() { return new ArrayList<>(extent); }
    static void setExtent(List<Person> loaded) { extent = new ArrayList<>(loaded); }
}