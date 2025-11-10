
import java.time.LocalDate;

public abstract class Person {
    protected String name;
    protected String surname;
    protected String email;
    protected LocalDate birthDate;

    public int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }
}