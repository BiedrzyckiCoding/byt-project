package main.PersistenceModels;

import main.Person.Person;
import main.Utils.ValidationUtil;

import java.io.Serial;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Employee extends Person {

    @Serial
    private static final long serialVersionUID = 1L;

    private static List<Employee> extent = new ArrayList<>();

    private double salary;
    private int itemsSold;
    private Contract contract;
    private List<Employee> subordinates;

    public Employee(String name, List<String> address, String surname, String email,
                    LocalDate birthDate, double salary, int itemsSold, Contract contract, List<Employee> subordinates) {
        super(name, address, surname, email, birthDate);

        ValidationUtil.nonNegative(salary, "salary");
        ValidationUtil.nonNegative(itemsSold, "itemsSold");
        ValidationUtil.notNull(contract, "contract");
        ValidationUtil.notNull(subordinates, "subordinates");

        this.salary = salary;
        this.itemsSold = itemsSold;
        this.contract = contract;
        this.subordinates = subordinates;

        addToExtent(this);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getItemsSold() {
        return itemsSold;
    }

    public void setItemsSold(int itemsSold) {
        this.itemsSold = itemsSold;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<Employee> subordinates) {
        this.subordinates = subordinates;
    }

    private static void addToExtent(Employee e) {
        if (e == null) throw new IllegalArgumentException("Employee cannot be null");
        extent.add(e);
    }

    public static List<Employee> getExtent() {
        return new ArrayList<>(extent);
    }

    static void setExtent(List<Employee> loaded) {
        extent = new ArrayList<>(loaded);
    }

    public void addNewEmployee() {
        /* TODO */ }

    public void deleteEmployee() {
        /* TODO */ }

    public void checkEmployeeList() {
        /* TODO */ }

    public void viewDashboard() {
        /* TODO */ }

    public void checkFinancialReport() {
        /* TODO */ }
}
