package test.PersistenceModels;

import main.Enums.ContractType;
import main.PersistenceModels.Contract;
import main.PersistenceModels.DebitCard;
import main.PersistenceModels.Customer;
import main.PersistenceModels.Employee;
import main.PersistenceModels.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private Person validPerson;
    private Employee employee;
    private Employee.EmployeeData validData;

    @BeforeEach
    void setUp() {
        validData = new Employee.EmployeeData(5000.0, 10);
        validPerson = new Person("John", List.of("Address"), "Doe", "john@test.com", LocalDate.of(1990, 1, 1), validData);
        employee = validPerson.asEmployee();
    }

    @Test
    void constructor_shouldThrowException_whenSalaryNegative() {
        Employee.EmployeeData invalidData = new Employee.EmployeeData(-100.0, 10);
        assertThrows(Exception.class, () ->
                new Person("Jane", List.of("Addr"), "Doe", "jane@test.com", LocalDate.now(), invalidData)
        );
    }

    @Test
    void constructor_shouldThrowException_whenItemsSoldNegative() {
        Employee.EmployeeData invalidData = new Employee.EmployeeData(5000.0, -1);
        assertThrows(Exception.class, () ->
                new Person("Jane", List.of("Addr"), "Doe", "jane@test.com", LocalDate.now(), invalidData)
        );
    }

    @Test
    void getSalary_shouldReturnCorrectValue() {
        assertEquals(5000.0, employee.getSalary());
    }

    @Test
    void setSalary_shouldUpdateValue_whenValid() {
        employee.setSalary(6000.0);
        assertEquals(6000.0, employee.getSalary());
    }

    @Test
    void setSalary_shouldThrowException_whenNegative() {
        assertThrows(Exception.class, () -> employee.setSalary(-1.0));
    }

    @Test
    void setItemsSold_shouldUpdateValue_whenValid() {
        employee.setItemsSold(20);
        assertEquals(20, employee.getItemsSold());
    }

    @Test
    void setItemsSold_shouldThrowException_whenNegative() {
        assertThrows(Exception.class, () -> employee.setItemsSold(-1));
    }

    @Test
    void assignManager_shouldSetManagerField() {
        Person managerPerson = new Person("Boss", List.of("Addr"), "Big", "boss@test.com", LocalDate.of(1980, 1, 1), validData);
        Employee manager = managerPerson.asEmployee();

        employee.assignManager(manager);

        assertEquals(manager, employee.getManager());
    }

    @Test
    void assignManager_shouldAddSubordinateToManager() {
        Person managerPerson = new Person("Boss", List.of("Addr"), "Big", "boss@test.com", LocalDate.of(1980, 1, 1), validData);
        Employee manager = managerPerson.asEmployee();

        employee.assignManager(manager);

        assertTrue(manager.getSubordinates().contains(employee));
    }

    @Test
    void assignManager_shouldThrowException_whenSelfAssignment() {
        assertThrows(IllegalArgumentException.class, () -> employee.assignManager(employee));
    }

    @Test
    void assignManager_shouldSetNewManager_whenReassigned() {
        Person boss1Person = new Person("Boss1", List.of("Addr"), "One", "b1@test.com", LocalDate.of(1980, 1, 1), validData);
        Employee boss1 = boss1Person.asEmployee();
        Person boss2Person = new Person("Boss2", List.of("Addr"), "Two", "b2@test.com", LocalDate.of(1980, 1, 1), validData);
        Employee boss2 = boss2Person.asEmployee();

        employee.assignManager(boss1);
        employee.assignManager(boss2);

        assertEquals(boss2, employee.getManager());
    }

    @Test
    void assignManager_shouldAddToNewManagerSubordinates_whenReassigned() {
        Person boss1Person = new Person("Boss1", List.of("Addr"), "One", "b1@test.com", LocalDate.of(1980, 1, 1), validData);
        Employee boss1 = boss1Person.asEmployee();
        Person boss2Person = new Person("Boss2", List.of("Addr"), "Two", "b2@test.com", LocalDate.of(1980, 1, 1), validData);
        Employee boss2 = boss2Person.asEmployee();

        employee.assignManager(boss1);
        employee.assignManager(boss2);

        assertTrue(boss2.getSubordinates().contains(employee));
    }

    @Test
    void assignManager_shouldRemoveFromOldManagerSubordinates_whenReassigned() {
        Person boss1Person = new Person("Boss1", List.of("Addr"), "One", "b1@test.com", LocalDate.of(1980, 1, 1), validData);
        Employee boss1 = boss1Person.asEmployee();
        Person boss2Person = new Person("Boss2", List.of("Addr"), "Two", "b2@test.com", LocalDate.of(1980, 1, 1), validData);
        Employee boss2 = boss2Person.asEmployee();

        employee.assignManager(boss1);
        employee.assignManager(boss2);

        assertFalse(boss1.getSubordinates().contains(employee));
    }

    @Test
    void addSubordinate_shouldAddSubordinateToSet() {
        Person subPerson = new Person("Sub", List.of("Addr"), "Zero", "sub@test.com", LocalDate.of(2000, 1, 1), validData);
        Employee subordinate = subPerson.asEmployee();

        employee.addSubordinate(subordinate);

        assertTrue(employee.getSubordinates().contains(subordinate));
    }

    @Test
    void addSubordinate_shouldSetManagerOnSubordinate() {
        Person subPerson = new Person("Sub", List.of("Addr"), "Zero", "sub@test.com", LocalDate.of(2000, 1, 1), validData);
        Employee subordinate = subPerson.asEmployee();

        employee.addSubordinate(subordinate);

        assertEquals(employee, subordinate.getManager());
    }

    @Test
    void addSubordinate_shouldThrowException_whenSelfAssignment() {
        assertThrows(IllegalArgumentException.class, () -> employee.addSubordinate(employee));
    }

    @Test
    void removeSubordinate_shouldRemoveFromSet() {
        Person subPerson = new Person("Sub", List.of("Addr"), "Zero", "sub@test.com", LocalDate.of(2000, 1, 1), validData);
        Employee subordinate = subPerson.asEmployee();

        employee.addSubordinate(subordinate);
        employee.removeSubordinate(subordinate);

        assertFalse(employee.getSubordinates().contains(subordinate));
    }

    @Test
    void removeSubordinate_shouldNullifyManager() {
        Person subPerson = new Person("Sub", List.of("Addr"), "Zero", "sub@test.com", LocalDate.of(2000, 1, 1), validData);
        Employee subordinate = subPerson.asEmployee();

        employee.addSubordinate(subordinate);
        employee.removeSubordinate(subordinate);

        assertNull(subordinate.getManager());
    }

    @Test
    void removeSubordinate_shouldThrowException_whenNotSubordinate() {
        Person otherPerson = new Person("Other", List.of("Addr"), "Guy", "other@test.com", LocalDate.of(1990, 1, 1), validData);
        Employee other = otherPerson.asEmployee();

        assertThrows(IllegalArgumentException.class, () -> employee.removeSubordinate(other));
    }

    @Test
    void detachEmployee_shouldRemoveFromManager() {
        Person bossPerson = new Person("Boss", List.of("Addr"), "Big", "boss@test.com", LocalDate.of(1980, 1, 1), validData);
        Employee boss = bossPerson.asEmployee();
        employee.assignManager(boss);

        // Attach customer to satisfy completeness before detaching employee
        DebitCard card = new DebitCard("1234123412341234", LocalDate.now().plusYears(1), "123");
        Customer.CustomerData cData = new Customer.CustomerData("acc", LocalDate.now(), 0, card);
        validPerson.attachCustomer(cData);

        validPerson.detachEmployee();

        assertFalse(boss.getSubordinates().contains(employee));
    }

    @Test
    void detachEmployee_shouldNullifySubordinateManager() {
        Person subPerson = new Person("Sub", List.of("Addr"), "Zero", "sub@test.com", LocalDate.of(2000, 1, 1), validData);
        Employee subordinate = subPerson.asEmployee();
        employee.addSubordinate(subordinate);

        DebitCard card = new DebitCard("1234123412341234", LocalDate.now().plusYears(1), "123");
        Customer.CustomerData cData = new Customer.CustomerData("acc", LocalDate.now(), 0, card);
        validPerson.attachCustomer(cData);

        validPerson.detachEmployee();

        assertNull(subordinate.getManager());
    }

    @Test
    void detachEmployee_shouldRemoveRoleFromPerson() {
        DebitCard card = new DebitCard("1234123412341234", LocalDate.now().plusYears(1), "123");
        Customer.CustomerData cData = new Customer.CustomerData("acc", LocalDate.now(), 0, card);
        validPerson.attachCustomer(cData);

        validPerson.detachEmployee();

        assertFalse(validPerson.isEmployee());
    }

    @Test
    void deleteEmployee_shouldRemoveContracts() {
//        Contract.setExtent(Collections.emptyList());
        Contract contract = new Contract(ContractType.COMMISSION, LocalDate.now(), employee);

        employee.deleteEmployee();

        assertFalse(Contract.getExtent().contains(contract));
    }
}