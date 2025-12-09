package test.PersistenceModels;

import main.Enums.ContractType;
import main.PersistenceModels.Contract;
import main.PersistenceModels.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private List<String> createAddress() {
        return List.of("Street 1", "City", "00000");
    }

    private LocalDate createBirthDate() {
        return LocalDate.of(1990, 1, 1);
    }

    private Employee createEmployee(String name, double salary) {
        return new Employee(name, createAddress(), "Jones", "mail@mail.com", createBirthDate(), salary, 5);
    }


    @Test
    void constructor_ShouldSetSalary() {
        Employee e = createEmployee("Bob", 500.0);
        assertEquals(500.0, e.getSalary());
    }

    @Test
    void constructor_ShouldSetItemsSold() {
        Employee e = createEmployee("Bob", 500.0);
        assertEquals(5, e.getItemsSold());
    }

    @Test
    void constructor_ShouldAddToExtent() {
        Employee e = createEmployee("Bob", 500.0);
        assertTrue(Employee.getExtent().contains(e));
    }

    @Test
    void setSalary_ShouldUpdateValue() {
        Employee e = createEmployee("Bob", 500.0);
        e.setSalary(600.0);
        assertEquals(600.0, e.getSalary());
    }

    @Test
    void setSalary_ShouldRejectNegative() {
        Employee e = createEmployee("Bob", 500.0);
        assertThrows(IllegalArgumentException.class, () -> e.setSalary(-1));
    }

    @Test
    void setItemsSold_ShouldUpdateValue() {
        Employee e = createEmployee("Bob", 500.0);
        e.setItemsSold(10);
        assertEquals(10, e.getItemsSold());
    }

    @Test
    void setItemsSold_ShouldRejectNegative() {
        Employee e = createEmployee("Bob", 500.0);
        assertThrows(IllegalArgumentException.class, () -> e.setItemsSold(-5));
    }

    @Test
    void getSubordinates_ShouldBeNullInitially() {
        Employee e = createEmployee("Bob", 500.0);
        assertNull(e.getSubordinates());
    }

    @Test
    void assignManager_ShouldSetManagerField() {
        Employee manager = createEmployee("Alice", 1000.0);
        Employee worker = createEmployee("Bob", 500.0);

        worker.assignManager(manager);

        assertEquals(manager, worker.getManager());
    }

    @Test
    void assignManager_ShouldAddSubordinateToManagerList() {
        Employee manager = createEmployee("Alice", 1000.0);
        Employee worker = createEmployee("Bob", 500.0);

        worker.assignManager(manager);

        assertTrue(manager.getSubordinates().contains(worker));
    }

    @Test
    void assignManager_ShouldPreventSelfAssignment() {
        Employee e = createEmployee("Bob", 500.0);
        assertThrows(IllegalArgumentException.class, () -> e.assignManager(e));
    }

    @Test
    void assignManager_Change_ShouldUpdateManagerField() {
        Employee manager1 = createEmployee("Alice", 1000.0);
        Employee manager2 = createEmployee("Carol", 1100.0);
        Employee worker = createEmployee("Bob", 500.0);

        worker.assignManager(manager1);
        worker.assignManager(manager2);

        assertEquals(manager2, worker.getManager());
    }

    @Test
    void assignManager_Change_ShouldRemoveFromOldManager() {
        Employee manager1 = createEmployee("Alice", 1000.0);
        Employee manager2 = createEmployee("Carol", 1100.0);
        Employee worker = createEmployee("Bob", 500.0);

        worker.assignManager(manager1);
        worker.assignManager(manager2);

        assertFalse(manager1.getSubordinates().contains(worker));
    }

    @Test
    void assignManager_Change_ShouldAddToNewManager() {
        Employee manager1 = createEmployee("Alice", 1000.0);
        Employee manager2 = createEmployee("Carol", 1100.0);
        Employee worker = createEmployee("Bob", 500.0);

        worker.assignManager(manager1);
        worker.assignManager(manager2);

        assertTrue(manager2.getSubordinates().contains(worker));
    }

    @Test
    void addSubordinate_ShouldSetManagerFieldOnWorker() {
        Employee manager = createEmployee("Alice", 1000.0);
        Employee worker = createEmployee("Bob", 500.0);

        manager.addSubordinate(worker);

        assertEquals(manager, worker.getManager());
    }

    @Test
    void addSubordinate_ShouldAddWorkerToManagerList() {
        Employee manager = createEmployee("Alice", 1000.0);
        Employee worker = createEmployee("Bob", 500.0);

        manager.addSubordinate(worker);

        assertTrue(manager.getSubordinates().contains(worker));
    }

    @Test
    void addSubordinate_ShouldRejectNull() {
        Employee manager = createEmployee("Alice", 1000.0);
        assertThrows(IllegalArgumentException.class, () -> manager.addSubordinate(null));
    }

    @Test
    void addSubordinate_ShouldRejectSelf() {
        Employee manager = createEmployee("Alice", 1000.0);
        assertThrows(IllegalArgumentException.class, () -> manager.addSubordinate(manager));
    }

    @Test
    void removeSubordinate_ShouldRemoveFromSubordinatesList() {
        Employee manager = createEmployee("Alice", 1000.0);
        Employee worker = createEmployee("Bob", 500.0);
        manager.addSubordinate(worker);

        manager.removeSubordinate(worker);

        assertFalse(manager.getSubordinates().contains(worker));
    }

    @Test
    void removeSubordinate_ShouldSetManagerToNull() {
        Employee manager = createEmployee("Alice", 1000.0);
        Employee worker = createEmployee("Bob", 500.0);
        manager.addSubordinate(worker);

        manager.removeSubordinate(worker);

        assertNull(worker.getManager());
    }
}