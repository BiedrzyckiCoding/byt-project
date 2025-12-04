package test.PersistenceModels;

import main.Enums.ContractType;
import main.PersistenceModels.Contract;
import main.PersistenceModels.Employee;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContractTest {

    private Employee createDummyEmployee() {
        return new Employee("Bob", List.of("Address"), "Jones", "mail",
                LocalDate.of(1990, 1, 1), 500, 5, new HashSet<>());
    }


    @Test
    void constructor_ShouldRejectNullType() {
        Employee e = createDummyEmployee();

        assertThrows(IllegalArgumentException.class, () ->
                new Contract(null, LocalDate.now(), LocalDate.now().plusYears(1), e)
        );
    }

    @Test
    void constructor_ShouldRejectFutureEmploymentDate() {
        Employee e = createDummyEmployee();

        LocalDate futureDate = LocalDate.now().plusDays(1);

        assertThrows(IllegalArgumentException.class, () ->
                new Contract(ContractType.EMPLOYMENT, futureDate, futureDate.plusDays(1), e)
        );
    }

    @Test
    void constructor_ShouldRejectInvalidDateOrder() {
        Employee e = createDummyEmployee();

        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2023, 1, 1);

        assertThrows(IllegalArgumentException.class, () ->
                new Contract(ContractType.EMPLOYMENT, start, end, e)
        );
    }

    @Test
    void constructor_ShouldRejectNullEmployee() {
        Employee nullEmployee = null;

        assertThrows(IllegalArgumentException.class, () ->
                new Contract(ContractType.EMPLOYMENT, LocalDate.now(), nullEmployee)
        );
    }

    //1 to 1 with employee

    @Test
    void assignEmployee_ShouldRejectIfEmployeeAlreadyHasContract() {
        Employee e = createDummyEmployee();
        //first contract assigned to e
        new Contract(ContractType.EMPLOYMENT, LocalDate.now(), e);

        //creating a second contract for the same employee should fail
        assertThrows(IllegalArgumentException.class, () ->
                new Contract(ContractType.COMMISSION, LocalDate.now(), e)
        );
    }

    @Test
    void assignEmployee_ShouldRejectIfContractAlreadyHasEmployee() {
        Employee e1 = createDummyEmployee();
        Employee e2 = createDummyEmployee();
        Contract c = new Contract(ContractType.EMPLOYMENT, LocalDate.now(), e1);

        // Attempting to manually assign a second employee to this contract instance
        assertThrows(IllegalArgumentException.class, () ->
                c.assignEmployee(e2)
        );
    }


    @Test
    void setType_ShouldUpdateValue() {
        Employee e = createDummyEmployee();
        Contract c = new Contract(ContractType.EMPLOYMENT, LocalDate.now(), e);

        c.setType(ContractType.COMMISSION);

        assertEquals(ContractType.COMMISSION, c.getType());
    }

    @Test
    void setEmploymentDates_ShouldUpdateStartDate() {
        Employee e = createDummyEmployee();
        Contract c = new Contract(ContractType.EMPLOYMENT, LocalDate.of(2020, 1, 1), e);
        LocalDate newStart = LocalDate.of(2021, 1, 1);

        c.setEmploymentDates(newStart, LocalDate.of(2022, 1, 1));

        assertEquals(newStart, c.getEmploymentDate());
    }

    @Test
    void deleteContract_ShouldRemoveFromExtent() {
        Employee e = createDummyEmployee();
        Contract c = new Contract(ContractType.EMPLOYMENT, LocalDate.now(), e);

        c.deleteContract();

        assertFalse(Contract.getExtent().contains(c));
    }
}