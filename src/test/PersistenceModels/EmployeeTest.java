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

class EmployeeTest {

    private List<String> address() {
        return List.of("Street 1", "City", "00000");
    }

    private LocalDate birthDate() {
        return LocalDate.of(1990, 1, 1);
    }

    private HashSet<Employee> emptySubordinates() {
        return new HashSet<>();
    }

    private Employee createEmployee() {
        return new Employee("Bob", address(), "Jones", "bob@mail.com", birthDate(),
                500, 5, emptySubordinates());
    }

    @Test
    void getSalary_ShouldReturnCorrectValue() {
        Employee e = createEmployee();

        assertEquals(500, e.getSalary());
    }

    @Test
    void setSalary_ShouldUpdateValue() {
        Employee e = createEmployee();

        e.setSalary(600);

        assertEquals(600, e.getSalary());
    }

    @Test
    void setSalary_ShouldRejectNegative() {
        Employee e = createEmployee();

        assertThrows(IllegalArgumentException.class, () -> e.setSalary(-1));
    }

    @Test
    void getItemsSold_ShouldReturnCorrectValue() {
        Employee e = createEmployee();

        assertEquals(5, e.getItemsSold());
    }

    @Test
    void setItemsSold_ShouldUpdateValue() {
        Employee e = createEmployee();

        e.setItemsSold(10);

        assertEquals(10, e.getItemsSold());
    }

    @Test
    void assignManager_ShouldSetManagerFieldOnSubordinate() {
        Employee manager = createEmployee();
        Employee subordinate = createEmployee();

        subordinate.assignManager(manager);

        assertEquals(manager, subordinate.getManager());
    }

    @Test
    void assignManager_ShouldAddSubordinateToManagerSet() {
        Employee manager = createEmployee();
        Employee subordinate = createEmployee();

        subordinate.assignManager(manager);

        assertTrue(manager.getSubordinates().contains(subordinate));
    }

    @Test
    void assignManager_ShouldRemoveSubordinateFromOldManager() {
        Employee oldManager = createEmployee();
        Employee newManager = createEmployee();
        Employee subordinate = createEmployee();
        subordinate.assignManager(oldManager);

        subordinate.assignManager(newManager);

        assertFalse(oldManager.getSubordinates().contains(subordinate));
    }

    @Test
    void assignManager_ShouldRejectSelfAssignment() {
        Employee e = createEmployee();

        assertThrows(IllegalArgumentException.class, () -> e.assignManager(e));
    }

    @Test
    void addSubordinate_ShouldSetManagerOnSubordinate() {
        Employee manager = createEmployee();
        Employee subordinate = createEmployee();

        manager.addSubordinate(subordinate);

        assertEquals(manager, subordinate.getManager());
    }

    @Test
    void addSubordinate_ShouldUpdateManagerSubordinateList() {
        Employee manager = createEmployee();
        Employee subordinate = createEmployee();

        manager.addSubordinate(subordinate);

        assertTrue(manager.getSubordinates().contains(subordinate));
    }

    @Test
    void addSubordinate_ShouldRejectNull() {
        Employee manager = createEmployee();

        assertThrows(IllegalArgumentException.class, () -> manager.addSubordinate(null));
    }


    @Test
    void contractAssociation_ShouldRejectSecondContractForEmployee() {
        Employee e = createEmployee();
        // First contract (implicit association via Contract constructor)
        new Contract(ContractType.EMPLOYMENT, LocalDate.now(), e);

        // Trying to create a second contract for the same employee should trigger the "Employee can have only one contract" check
        assertThrows(IllegalArgumentException.class, () ->
                new Contract(ContractType.COMMISSION, LocalDate.now(), e)
        );
    }

    @Test
    void deleteEmployee_ShouldRemoveAssociatedContractFromExtent() {
        Employee e = createEmployee();
        Contract c = new Contract(ContractType.EMPLOYMENT, LocalDate.now(), e);

        e.deleteEmployee();

        assertFalse(Contract.getExtent().contains(c));
    }

    @Test
    void extent_ShouldReturnCopyAndNotReference() {
        createEmployee();

        List<Employee> firstCall = Employee.getExtent();
        List<Employee> secondCall = Employee.getExtent();

        assertNotSame(firstCall, secondCall);
    }
}