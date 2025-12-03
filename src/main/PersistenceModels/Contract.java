package main.PersistenceModels;

import main.Enums.ContractType;
import main.Utils.ValidationUtil;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Contract implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static List<Contract> extent = new ArrayList<>();

    private ContractType type;
    private LocalDate employmentDate;
    private LocalDate employmentDueDate;
    private final HashSet<Employee> employeeSet = new HashSet<>();

    public Contract(ContractType type, LocalDate employmentDate, LocalDate employmentDueDate, Employee employee) {
        ValidationUtil.notNull(type, "type");
        ValidationUtil.notFuture(employmentDate, "employmentDate");
        ValidationUtil.dateOrder(employmentDate, employmentDueDate);

        this.type = type;
        this.employmentDate = employmentDate;
        this.employmentDueDate = employmentDueDate;
        assignEmployee(employee);

        addToExtent(this);
    }

    public Contract(ContractType type, LocalDate employmentDate, Employee employee) {
        ValidationUtil.notNull(type, "type");
        ValidationUtil.notFuture(employmentDate, "employmentDate");

        this.type = type;
        this.employmentDate = employmentDate;
        this.employmentDueDate = null;
        assignEmployee(employee);

        addToExtent(this);
    }

    public ContractType getType() {
        return type;
    }

    public void setType(ContractType type) {
        ValidationUtil.notNull(type, "type");
        this.type = type;
    }

    public void setEmploymentDates(LocalDate employmentDate, LocalDate employmentDueDate) {
        ValidationUtil.notNull(employmentDate, "employmentDate");
        ValidationUtil.notFuture(employmentDate, "employmentDate");
        ValidationUtil.dateOrder(employmentDate, employmentDueDate);
        this.employmentDate = employmentDate;
        this.employmentDueDate = employmentDueDate;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public LocalDate getEmploymentDueDate() {
        return employmentDueDate;
    }

    private static void addToExtent(Contract c) {
        if (c == null) throw new IllegalArgumentException("Contract cannot be null");
        extent.add(c);
    }

    public static List<Contract> getExtent() {
        return new ArrayList<>(extent);
    }

    static void setExtent(List<Contract> loaded) {
        extent = new ArrayList<>(loaded);
    }

    public void assignEmployee(Employee employee) {
        ValidationUtil.notNull(employee, "employee");
        employee.addContract(this);
        addEmployee(employee);
    }

    void addEmployee(Employee employee) {
        if (employeeSet.contains(employee)) {
            throw new IllegalArgumentException("This employee already has this contract");
        }
        if (!employeeSet.isEmpty()) {
            throw new IllegalArgumentException("Contract can be assigned to only one employee");
        }
        employeeSet.add(employee);
    }

    private void removeEmployee() {
        employeeSet.clear();
    }

    public void deleteContract() {
        for (Employee employee : employeeSet) {
            employee.removeContract();
        }
        removeEmployee();
        extent.remove(this);
    }
}

