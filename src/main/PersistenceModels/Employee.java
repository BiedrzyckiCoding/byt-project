package main.PersistenceModels;

import main.Person.Person;
import main.Utils.ValidationUtil;

import java.io.Serial;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Employee extends Person {

    @Serial
    private static final long serialVersionUID = 1L;

    private static List<Employee> extent = new ArrayList<>();

    private double salary;
    private int itemsSold;
    private Employee manager;
    private HashSet<Employee> subordinates;
    private HashSet<Contract> contracts;


    public Employee(String name, List<String> address, String surname, String email,
                    LocalDate birthDate, double salary, int itemsSold) {
        super(name, address, surname, email, birthDate);

        ValidationUtil.nonNegative(salary, "salary");
        ValidationUtil.nonNegative(itemsSold, "itemsSold");
        ValidationUtil.notNull(subordinates, "subordinates");

        this.salary = salary;
        this.itemsSold = itemsSold;
        this.manager = null;
        this.subordinates = null;
        this.contracts = null;


        addToExtent(this);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        ValidationUtil.nonNegative(salary, "salary");
        this.salary = salary;
    }

    public int getItemsSold() {
        return itemsSold;
    }

    public void setItemsSold(int itemsSold) {
        ValidationUtil.nonNegative(itemsSold, "itemsSold");
        this.itemsSold = itemsSold;
    }

    public Employee getManager() {
        return manager;
    }

    public HashSet<Employee> getSubordinates() {
        return subordinates;
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

    void addContract(Contract contract) {
        ValidationUtil.notNull(contract, "contract");
        if (contracts == null) {contracts = new HashSet<>();}
        if (contracts.contains(contract)) {
            throw new IllegalArgumentException("This contract is already own by this employee");
        }
        contracts.add(contract);
    }

    void removeContract(Contract contract) {
        ValidationUtil.notNull(contract, "contract");
        if(!contracts.contains(contract)) {throw new IllegalArgumentException("This contract is not own by this employee");}
        if (contracts.remove(contract)) {
            Contract.removeFromExtent(contract);
        }
    }


    public void deleteEmployee() {
        for(Contract contract : contracts) {
            contract.deleteContract();
        }
        extent.remove(this);
    }

    public void assignManager(Employee manager) {
        if (this.manager == manager){
            throw new IllegalArgumentException("Manager cannot be the same");
        }

        if (this.manager != null) {
            this.manager.removeSubordinateInternal(this);
        }
        manager.addSubordinateInternal(this);
    }

    public void addSubordinate(Employee subordinate) {
        ValidationUtil.notNull(subordinate, "subordinate");
        if (this == subordinate) {throw new IllegalArgumentException("Employee cannot be cannot be its own subordinate");}
        subordinate.assignManager(this);
    }

    public void removeSubordinate(Employee subordinate) {
        ValidationUtil.notNull(subordinate, "subordinate");
        if(!this.subordinates.contains(subordinate)) {throw new IllegalArgumentException("This employee doesnt have a this subordinate");}
        this.removeSubordinateInternal(subordinate);
    }

    private void addSubordinateInternal(Employee subordinate) {
        if (subordinates == null) subordinates = new HashSet<>();
        if (subordinates.add(subordinate)) {
            subordinate.setManagerInternal(this);
        }
    }

    private void removeSubordinateInternal(Employee subordinate) {
        if (subordinates.remove(subordinate)) {
            subordinate.setManagerInternal(null);
        }
    }

    private void setManagerInternal(Employee manager) {
        this.manager = manager;
    }

    public void addNewEmployee() {
        /* TODO */
    }

    public void checkEmployeeList() {
        /* TODO */
    }

    public void viewDashboard() {
        /* TODO */
    }

    public void checkFinancialReport() {
        /* TODO */
    }
}

