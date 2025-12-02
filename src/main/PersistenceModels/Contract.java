package main.PersistenceModels;

import main.Enums.ContractType;
import main.Utils.ValidationUtil;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Contract implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static List<Contract> extent = new ArrayList<>();

    private ContractType type;
    private LocalDate employmentDate;
    private LocalDate employmentDueDate;

    public Contract(ContractType type, LocalDate employmentDate, LocalDate employmentDueDate) {
        ValidationUtil.notNull(type, "type");
        ValidationUtil.notFuture(employmentDate, "employmentDate");
        ValidationUtil.dateOrder(employmentDate, employmentDueDate);

        this.type = type;
        this.employmentDate = employmentDate;
        this.employmentDueDate = employmentDueDate;

        addToExtent(this);
    }

    public Contract(ContractType type, LocalDate employmentDate) {
        this(type,employmentDate,null);
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
}
