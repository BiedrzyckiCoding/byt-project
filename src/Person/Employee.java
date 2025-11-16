package Person;

import java.time.LocalDate;
import java.util.List;

public class Employee extends Person {
    private double salary;
    private int itemsSold;
    private Contract contract;
    private List<Employee> subordinates;

    public Employee(String name, List<String> address, String surname, String email,
                    LocalDate birthDate, double salary, int itemsSold, Contract contract, List<Employee> subordinates) {
        super(name, address, surname, email, birthDate);
        this.salary = salary;
        this.itemsSold = itemsSold;
        this.contract = contract;
        this.subordinates = subordinates;
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
