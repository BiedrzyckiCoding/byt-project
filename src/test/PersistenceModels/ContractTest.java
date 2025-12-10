package test.PersistenceModels;

import main.Enums.ContractType;
import main.PersistenceModels.Contract;
import main.PersistenceModels.Employee;
import main.PersistenceModels.PersistenceUtil;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContractTest {

    private Employee mockEmployee = new Employee(
            "jarvis",
            List.of("black lane 1"),
            "cameron", "jcameron@gmail.com",
            LocalDate.of(1990, 1, 1),
            67,
            1);

    @BeforeEach
    void setUp() {
        PersistenceUtil.loadAll();
    }

    @Test
    void constructorAddsToExtent() {
        Contract c = new Contract(
                ContractType.EMPLOYMENT,
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2024, 1, 1),
                mockEmployee // added employee
        );

        assertEquals(1, Contract.getExtent().size());
    }

    @Test
    void extentReturnsCopy() {
        Contract c = new Contract(
                ContractType.EMPLOYMENT,
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2024, 1, 1),
                mockEmployee // Added employee
        );

        // This checks if getExtent returns a safe copy (modifying it doesn't affect the real extent)
        List<Contract> copy = Contract.getExtent();
        copy.clear();

        assertEquals(1, Contract.getExtent().size());
    }

    @Test
    void constructorRejectsNullType() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contract(
                        null,
                        LocalDate.of(2023, 1, 1),
                        LocalDate.of(2024, 1, 1),
                        mockEmployee // Added employee
                )
        );
    }

    @Test
    void constructorRejectsFutureEmploymentDate() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contract(
                        ContractType.EMPLOYMENT,
                        LocalDate.now().plusDays(1),
                        LocalDate.now().plusDays(2),
                        mockEmployee // Added employee
                )
        );
    }

    @Test
    void constructorRejectsInvalidDateOrder() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contract(
                        ContractType.EMPLOYMENT,
                        LocalDate.of(2024, 1, 1),
                        LocalDate.of(2023, 1, 1),
                        mockEmployee // Added employee
                )
        );
    }

    @Test
    void constructorWithoutDueDateSetsDueDateNull() {
        Contract c = new Contract(
                ContractType.COMMISSION,
                LocalDate.of(2023, 1, 1),
                mockEmployee // Added employee
        );

        assertNull(c.getEmploymentDueDate());
    }

    @Test
    void constructorWithoutDueDateRejectsNullType() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contract(
                        null,
                        LocalDate.of(2023, 1, 1),
                        mockEmployee // Added employee
                )
        );
    }

    @Test
    void constructorWithoutDueDateRejectsFutureEmploymentDate() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contract(
                        ContractType.COMMISSION,
                        LocalDate.now().plusDays(1),
                        mockEmployee // Added employee
                )
        );
    }

    @Test
    void getTypeReturnsCorrectValue() {
        Contract c = new Contract(
                ContractType.EMPLOYMENT,
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2024, 1, 1),
                mockEmployee // Added employee
        );

        assertEquals(ContractType.EMPLOYMENT, c.getType());
    }

    @Test
    void getEmploymentDateReturnsCorrectValue() {
        LocalDate d = LocalDate.of(2023, 1, 1);
        Contract c = new Contract(
                ContractType.EMPLOYMENT,
                d,
                LocalDate.of(2024, 1, 1),
                mockEmployee // Added employee
        );

        assertEquals(d, c.getEmploymentDate());
    }

    @Test
    void getEmploymentDueDateReturnsCorrectValue() {
        LocalDate due = LocalDate.of(2024, 1, 1);
        Contract c = new Contract(
                ContractType.EMPLOYMENT,
                LocalDate.of(2023, 1, 1),
                due,
                mockEmployee // Added employee
        );

        assertEquals(due, c.getEmploymentDueDate());
    }


    @Test
    void setTypeChangesValue() {
        Contract c = new Contract(
                ContractType.EMPLOYMENT,
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2024, 1, 1),
                mockEmployee // Added employee
        );

        c.setType(ContractType.COMMISSION);

        assertEquals(ContractType.COMMISSION, c.getType());
    }

    @Test
    void setTypeRejectsNull() {
        Contract c = new Contract(
                ContractType.EMPLOYMENT,
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2024, 1, 1),
                mockEmployee // Added employee
        );

        assertThrows(IllegalArgumentException.class, () ->
                c.setType(null)
        );
    }


    @Test
    void setEmploymentDatesUpdatesValues() {
        Contract c = new Contract(
                ContractType.EMPLOYMENT,
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2021, 1, 1),
                mockEmployee // Added employee
        );

        LocalDate newStart = LocalDate.of(2022, 2, 2);
        LocalDate newDue = LocalDate.of(2023, 3, 3);

        c.setEmploymentDates(newStart, newDue);

        assertEquals(newStart, c.getEmploymentDate());
    }

    @Test
    void setEmploymentDatesRejectsNullStart() {
        Contract c = new Contract(
                ContractType.EMPLOYMENT,
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2021, 1, 1),
                mockEmployee // Added employee
        );

        assertThrows(IllegalArgumentException.class, () ->
                c.setEmploymentDates(null, LocalDate.of(2023, 1, 1))
        );
    }

    @Test
    void setEmploymentDatesRejectsFutureStart() {
        Contract c = new Contract(
                ContractType.EMPLOYMENT,
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2021, 1, 1),
                mockEmployee // Added employee
        );

        assertThrows(IllegalArgumentException.class, () ->
                c.setEmploymentDates(LocalDate.now().plusDays(2), LocalDate.now().plusDays(3))
        );
    }

    @Test
    void setEmploymentDatesRejectsInvalidOrder() {
        Contract c = new Contract(
                ContractType.EMPLOYMENT,
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2021, 1, 1),
                mockEmployee // Added employee
        );

        assertThrows(IllegalArgumentException.class, () ->
                c.setEmploymentDates(
                        LocalDate.of(2024, 1, 1),
                        LocalDate.of(2023, 1, 1)
                )
        );
    }
}
