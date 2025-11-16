package Person;

import Enums.ContractType;
import Validation.ValidationUtil;

import java.time.LocalDate;

public class Contract {
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
}
