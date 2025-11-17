package Utils;

import java.time.LocalDate;
import java.util.List;

public class ValidationUtil {

    public static void notEmpty(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " cannot be empty.");
        }
    }

    public static void positive(double value, String field) {
        if (value <= 0) {
            throw new IllegalArgumentException(field + " must be > 0.");
        }
    }

    public static void nonNegative(double value, String field) {
        if (value < 0) {
            throw new IllegalArgumentException(field + " cannot be negative.");
        }
    }

    public static void notFuture(LocalDate date, String field) {
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(field + " cannot be in the future.");
        }
    }

    public static void dateOrder(LocalDate start, LocalDate end) {
        if (end != null && end.isBefore(start)) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }
    }

    public static void emptyList(List<String> list, String field) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException(field + " cannot be empty.");
        }
    }

    public static void notNull(Object object, String field) {
        if (object == null) {
            throw new IllegalArgumentException(field + " cannot be null.");
        }
    }
}
