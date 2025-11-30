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

//    @BeforeEach
//    void resetExtent() {
//        Employee.setExtent(new ArrayList<>());
//    }

    private List<String> address() {
        return List.of("Street 1", "City", "00000");
    }

    private LocalDate birthDate() {
        return LocalDate.of(1990, 1, 1);
    }

    private Contract sampleContract() {
        return new Contract(ContractType.EMPLOYMENT, LocalDate.now().minusYears(1));
    }

    private List<Employee> emptySubordinates() {
        return new ArrayList<>();
    }

    //fails because i cant reset extent
    @Test
    void constructor_WithManager_ShouldAddToExtent() {
        Employee manager = new Employee("Alice", address(), "Smith", "alice@mail.com", birthDate(),
                1000, 10, sampleContract(), emptySubordinates());

        Employee e = new Employee("Bob", address(), "Jones", "bob@mail.com", birthDate(),
                500, 5, sampleContract(), manager, emptySubordinates());

        assertEquals(2, Employee.getExtent().size());
    }

    //fails because i cant reset extent
    @Test
    void constructor_WithoutManager_ShouldAddToExtent() {
        Employee e = new Employee("Bob", address(), "Jones", "bob@mail.com", birthDate(),
                500, 5, sampleContract(), emptySubordinates());

        assertEquals(1, Employee.getExtent().size());
    }

    @Test
    void getSalary_ShouldReturnSalary() {
        Employee e = new Employee("Bob", address(), "Jones", "bob@mail.com", birthDate(),
                500, 5, sampleContract(), emptySubordinates());

        assertEquals(500, e.getSalary());
    }

    @Test
    void setSalary_ShouldUpdateValue() {
        Employee e = new Employee("Bob", address(), "Jones", "bob@mail.com", birthDate(),
                500, 5, sampleContract(), emptySubordinates());

        e.setSalary(600);

        assertEquals(600, e.getSalary());
    }

    @Test
    void setSalary_ShouldRejectNegative() {
        Employee e = new Employee("Bob", address(), "Jones", "bob@mail.com", birthDate(),
                500, 5, sampleContract(), emptySubordinates());

        assertThrows(IllegalArgumentException.class, () -> e.setSalary(-1));
    }

    @Test
    void getItemsSold_ShouldReturnValue() {
        Employee e = new Employee("Bob", address(), "Jones", "bob@mail.com", birthDate(),
                500, 5, sampleContract(), emptySubordinates());

        assertEquals(5, e.getItemsSold());
    }

    @Test
    void setItemsSold_ShouldUpdateValue() {
        Employee e = new Employee("Bob", address(), "Jones", "bob@mail.com", birthDate(),
                500, 5, sampleContract(), emptySubordinates());

        e.setItemsSold(10);

        assertEquals(10, e.getItemsSold());
    }

    @Test
    void setItemsSold_ShouldRejectNegative() {
        Employee e = new Employee("Bob", address(), "Jones", "bob@mail.com", birthDate(),
                500, 5, sampleContract(), emptySubordinates());

        assertThrows(IllegalArgumentException.class, () -> e.setItemsSold(-5));
    }

    @Test
    void getContract_ShouldReturnContract() {
        Contract contract = sampleContract();
        Employee e = new Employee("Bob", address(), "Jones", "bob@mail.com", birthDate(),
                500, 5, contract, emptySubordinates());

        assertEquals(contract, e.getContract());
    }

    @Test
    void setContract_ShouldUpdateContract() {
        Employee e = new Employee("Bob", address(), "Jones", "bob@mail.com", birthDate(),
                500, 5, sampleContract(), emptySubordinates());

        Contract newContract = new Contract(ContractType.COMMISSION, LocalDate.now().minusMonths(2));

        e.setContract(newContract);

        assertEquals(newContract, e.getContract());
    }

    @Test
    void setContract_ShouldRejectNull() {
        Employee e = new Employee("Bob", address(), "Jones", "bob@mail.com", birthDate(),
                500, 5, sampleContract(), emptySubordinates());

        assertThrows(IllegalArgumentException.class, () -> e.setContract(null));
    }

    @Test
    void getManager_ShouldReturnManager() {
        Employee manager = new Employee("Alice", address(), "Smith", "alice@mail.com", birthDate(),
                1000, 10, sampleContract(), emptySubordinates());

        Employee e = new Employee("Bob", address(), "Jones", "bob@mail.com", birthDate(),
                500, 5, sampleContract(), manager, emptySubordinates());

        assertEquals(manager, e.getManager());
    }

    @Test
    void setManager_ShouldUpdateManager() {
        Employee manager1 = new Employee("Alice", address(), "Smith", "alice@mail.com", birthDate(),
                1000, 10, sampleContract(), emptySubordinates());

        Employee manager2 = new Employee("Carol", address(), "White", "carol@mail.com", birthDate(),
                1100, 12, sampleContract(), emptySubordinates());

        Employee e = new Employee("Bob", address(), "Jones", "bob@mail.com", birthDate(),
                500, 5, sampleContract(), manager1, emptySubordinates());

        e.setManager(manager2);

        assertEquals(manager2, e.getManager());
    }

    @Test
    void getSubordinates_ShouldReturnList() {
        List<Employee> subs = emptySubordinates();

        Employee e = new Employee("Bob", address(), "Jones", "bob@mail.com", birthDate(),
                500, 5, sampleContract(), subs);

        assertEquals(subs, e.getSubordinates());
    }

    @Test
    void setSubordinates_ShouldUpdateList() {
        List<Employee> subs1 = emptySubordinates();

        List<Employee> subs2 = new ArrayList<>();

        subs2.add(new Employee("Alice", address(), "Smith", "alice@mail.com", birthDate(),
                1000, 10, sampleContract(), emptySubordinates()));

        Employee e = new Employee("Bob", address(), "Jones", "bob@mail.com", birthDate(),
                500, 5, sampleContract(), subs1);

        e.setSubordinates(subs2);

        assertEquals(subs2, e.getSubordinates());
    }

    @Test
    void setSubordinates_ShouldRejectNull() {
        Employee e = new Employee("Bob", address(), "Jones", "bob@mail.com", birthDate(),
                500, 5, sampleContract(), emptySubordinates());

        assertThrows(IllegalArgumentException.class, () -> e.setSubordinates(null));
    }

    @Test
    void extent_ShouldReturnCopy() {
        new Employee("Bob", address(), "Jones", "bob@mail.com", birthDate(),
                500, 5, sampleContract(), emptySubordinates());

        assertNotSame(Employee.getExtent(), Employee.getExtent());
    }
}
