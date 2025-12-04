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
    private HashSet<Employee> subordinates = new HashSet<>();
    private final HashSet<Contract> contractSet = new HashSet<>();


    public Employee(String name, List<String> address, String surname, String email,
                    LocalDate birthDate, double salary, int itemsSold, Employee manager, HashSet<Employee> subordinates) {
        super(name, address, surname, email, birthDate);

        ValidationUtil.nonNegative(salary, "salary");
        ValidationUtil.nonNegative(itemsSold, "itemsSold");
        ValidationUtil.notNull(subordinates, "subordinates");

        this.salary = salary;
        this.itemsSold = itemsSold;
        this.manager = manager;
        this.subordinates = subordinates;

        addToExtent(this);
    }

    public Employee(String name, List<String> address, String surname, String email,
                    LocalDate birthDate, double salary, int itemsSold, HashSet<Employee> subordinates) {
        super(name, address, surname, email, birthDate);

        ValidationUtil.nonNegative(salary, "salary");
        ValidationUtil.nonNegative(itemsSold, "itemsSold");
        ValidationUtil.notNull(subordinates, "subordinates");

        this.salary = salary;
        this.itemsSold = itemsSold;
        this.manager = null;
        this.subordinates = subordinates;

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

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public HashSet<Employee> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(HashSet<Employee> subordinates) {
        ValidationUtil.notNull(subordinates, "subordinates");
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

    void addContract(Contract contract) {
        if (contractSet.contains(contract)) {
            throw new IllegalArgumentException("This contract is already own by this employee");
        }
        if (!contractSet.isEmpty()) {
            throw new IllegalArgumentException("Employee can have only one contract");
        }
        contractSet.add(contract);
    }

    void removeContract() {
        contractSet.clear();
    }

    public void deleteEmployee() {
        for(Contract contract : contractSet) {
            contract.deleteContract();
        }
        removeContract();
        extent.remove(this);
    }

    public void assignManager(Employee manager) {
        if (this.manager == manager){
            throw new IllegalArgumentException("Manager cannot be the same");
        }

//        if(manager == this.employee){
//            throw new IllegalArgumentException("Manager cannot be the same as the employee");
//        }

        if (this.manager != null) {
            this.manager.removeSubordinateInternal(this);
        }
        manager.addSubordinateInternal(this);
    }

    public void addSubordinate(Employee subordinate) {
        ValidationUtil.notNull(subordinate, "subordinate");
        subordinate.assignManager(this);
    }

    private void addSubordinateInternal(Employee subordinate) {
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

