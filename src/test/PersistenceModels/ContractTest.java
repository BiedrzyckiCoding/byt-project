package test.PersistenceModels;

import main.Enums.ContractType;
import main.PersistenceModels.Contract;
import main.PersistenceModels.Employee;
import main.PersistenceModels.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContractTest {

    private Employee validEmployee;
    private ContractType validType;
    private LocalDate validStart;
    private LocalDate validEnd;

    @BeforeEach
    void setUp() {
//        Contract.setExtent(Collections.emptyList());

        validStart = LocalDate.now().minusDays(10);
        validEnd = LocalDate.now().plusDays(365);
        validType = ContractType.COMMISSION;

        Employee.EmployeeData data = new Employee.EmployeeData(2000.0, 0);
        Person person = new Person("John", List.of("Street"), "Doe", "john@test.com", LocalDate.of(1990, 1, 1), data);
        validEmployee = person.asEmployee();
    }

    @AfterEach
    void tearDown() {
//        Contract.setExtent(Collections.emptyList());
    }

    @Test
    void constructor_shouldCreateContract_whenAllDataValid() {
        Contract contract = new Contract(validType, validStart, validEnd, validEmployee);
        assertNotNull(contract);
    }

    @Test
    void constructor_shouldCreateContract_whenEndDateIsNull() {
        assertDoesNotThrow(() -> new Contract(validType, validStart, null, validEmployee));
    }

    @Test
    void constructor_shouldCreateContract_whenUsingOverloadWithoutEndDate() {
        assertDoesNotThrow(() -> new Contract(validType, validStart, validEmployee));
    }

    @Test
    void constructor_shouldThrowException_whenTypeIsNull() {
        assertThrows(Exception.class, () -> new Contract(null, validStart, validEnd, validEmployee));
    }

    @Test
    void constructor_shouldThrowException_whenEmploymentDateIsFuture() {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        assertThrows(Exception.class, () -> new Contract(validType, futureDate, validEnd, validEmployee));
    }

    @Test
    void constructor_shouldThrowException_whenEmployeeIsNull() {
        assertThrows(Exception.class, () -> new Contract(validType, validStart, validEnd, null));
    }

    @Test
    void constructor_shouldThrowException_whenEndDateBeforeStartDate() {
        LocalDate earlyEnd = validStart.minusDays(1);
        assertThrows(Exception.class, () -> new Contract(validType, validStart, earlyEnd, validEmployee));
    }

    @Test
    void constructor_shouldAddContractToExtent_whenValid() {
        Contract contract = new Contract(validType, validStart, validEnd, validEmployee);
        assertTrue(Contract.getExtent().contains(contract));
    }

    @Test
    void getType_shouldReturnCorrectType() {
        Contract contract = new Contract(validType, validStart, validEnd, validEmployee);
        assertEquals(validType, contract.getType());
    }

    @Test
    void setType_shouldUpdateType_whenValid() {
        Contract contract = new Contract(validType, validStart, validEnd, validEmployee);
        contract.setType(ContractType.EMPLOYMENT);
        assertEquals(ContractType.EMPLOYMENT, contract.getType());
    }

    @Test
    void setType_shouldThrowException_whenNull() {
        Contract contract = new Contract(validType, validStart, validEnd, validEmployee);
        assertThrows(Exception.class, () -> contract.setType(null));
    }

    @Test
    void setEmploymentDates_shouldUpdateDates_whenValid() {
        Contract contract = new Contract(validType, validStart, validEnd, validEmployee);
        LocalDate newStart = LocalDate.now().minusDays(20);
        LocalDate newEnd = LocalDate.now().plusDays(100);

        contract.setEmploymentDates(newStart, newEnd);

        assertEquals(newStart, contract.getEmploymentDate());
    }

    @Test
    void setEmploymentDates_shouldThrowException_whenStartDateNull() {
        Contract contract = new Contract(validType, validStart, validEnd, validEmployee);
        assertThrows(Exception.class, () -> contract.setEmploymentDates(null, validEnd));
    }

    @Test
    void setEmploymentDates_shouldThrowException_whenStartDateIsFuture() {
        Contract contract = new Contract(validType, validStart, validEnd, validEmployee);
        assertThrows(Exception.class, () -> contract.setEmploymentDates(LocalDate.now().plusDays(1), validEnd));
    }

    @Test
    void setEmploymentDates_shouldThrowException_whenEndDateBeforeStart() {
        Contract contract = new Contract(validType, validStart, validEnd, validEmployee);
        assertThrows(Exception.class, () -> contract.setEmploymentDates(validStart, validStart.minusDays(1)));
    }

    @Test
    void deleteContract_shouldRemoveFromExtent() {
        Contract contract = new Contract(validType, validStart, validEnd, validEmployee);

        contract.deleteContract();

        assertFalse(Contract.getExtent().contains(contract));
    }
}