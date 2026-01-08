package test.Inheritance;

import main.PersistenceModels.Customer;
import main.PersistenceModels.DebitCard;
import main.PersistenceModels.Employee;
import main.PersistenceModels.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonInheritanceTest {

    private DebitCard mockDebitCard;
    private Customer.CustomerData validCustomerData;
    private Employee.EmployeeData validEmployeeData;
    private List<String> validAddress;
    private LocalDate validBirthDate;

    @BeforeEach
    void setUp() {
        mockDebitCard = new DebitCard("1234", LocalDate.now().plusYears(10), "abc");
        validAddress = List.of("123 Main St", "City");
        validBirthDate = LocalDate.of(1990, 1, 1);

        validCustomerData = new Customer.CustomerData(
                "john_account",
                LocalDate.now(),
                0.0,
                mockDebitCard
        );

        validEmployeeData = new Employee.EmployeeData(
                5000.0,
                10
        );
    }

    // Overlapping (Constructors)

    @Test
    void constructor_ShouldCreateCustomerRole_WhenOnlyCustomerDataProvided() {
        Person person = new Person("John", validAddress, "Doe", "john@ex.com", validBirthDate, validCustomerData);

        assertTrue(person.isCustomer());
    }

    @Test
    void constructor_ShouldNotCreateEmployeeRole_WhenOnlyCustomerDataProvided() {
        Person person = new Person("John", validAddress, "Doe", "john@ex.com", validBirthDate, validCustomerData);

        assertFalse(person.isEmployee());
    }

    @Test
    void constructor_ShouldCreateBothRoles_WhenBothDataProvided() {
        Person person = new Person("John", validAddress, "Doe", "john@ex.com", validBirthDate, validCustomerData, validEmployeeData);

        assertTrue(person.isCustomer() && person.isEmployee());
    }

    // Dynamic (Attach/Detach)

    @Test
    void attachEmployee_ShouldAddEmployeeRole_WhenPersonIsCustomer() {
        Person person = new Person("John", validAddress, "Doe", "john@ex.com", validBirthDate, validCustomerData);

        person.attachEmployee(validEmployeeData);

        assertTrue(person.isEmployee());
    }

    @Test
    void attachCustomer_ShouldAddCustomerRole_WhenPersonIsEmployee() {
        Person person = new Person("John", validAddress, "Doe", "john@ex.com", validBirthDate, validEmployeeData);

        person.attachCustomer(validCustomerData);

        assertTrue(person.isCustomer());
    }

    @Test
    void detachEmployee_ShouldRemoveEmployeeRole_WhenPersonHasBothRoles() {
        Person person = new Person("John", validAddress, "Doe", "john@ex.com", validBirthDate, validCustomerData, validEmployeeData);

        person.detachEmployee();

        assertFalse(person.isEmployee());
    }

    // Complete

    @Test
    void detachEmployee_ShouldThrowException_WhenPersonIsOnlyEmployee() {
        Person person = new Person("John", validAddress, "Doe", "john@ex.com", validBirthDate, validEmployeeData);

        assertThrows(IllegalStateException.class, person::detachEmployee);
    }

    @Test
    void detachCustomer_ShouldThrowException_WhenPersonIsOnlyCustomer() {
        Person person = new Person("John", validAddress, "Doe", "john@ex.com", validBirthDate, validCustomerData);

        assertThrows(IllegalStateException.class, person::detachCustomer);
    }

    @Test
    void asCustomer_ShouldReturnCustomerObject_WhenRoleExists() {
        Person person = new Person("John", validAddress, "Doe", "john@ex.com", validBirthDate, validCustomerData);

        Customer role = person.asCustomer();

        assertNotNull(role);
    }

    @Test
    void asEmployee_ShouldThrowException_WhenRoleDoesNotExist() {
        Person person = new Person("John", validAddress, "Doe", "john@ex.com", validBirthDate, validCustomerData);

        assertThrows(IllegalStateException.class, person::asEmployee);
    }
}