package Person;

import Enums.ContractType;
import Utils.ValidationUtil;

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

    public ContractType getType() {
        return type;
    }

    public void setType(ContractType type) {
        this.type = type;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public LocalDate getEmploymentDueDate() {
        return employmentDueDate;
    }

    public void setEmploymentDueDate(LocalDate employmentDueDate) {
        this.employmentDueDate = employmentDueDate;
    }

    private static void addToExtent(Contract c) {
        if (c == null) throw new IllegalArgumentException("Contract cannot be null");
        extent.add(c);
    }

    public static List<Contract> getExtent() {
        return new ArrayList<>(extent);
    }

    public static void setExtent(List<Contract> loaded) {
        extent = new ArrayList<>(loaded);
    }
}
