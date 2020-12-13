import java.util.Objects;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Person {
    private final static Matcher NAME = Pattern.compile(
            "\\p{Lu}[- \\pL]*").matcher("");
    private final static Matcher ID_CODE = Pattern.compile(
            "\\d{10}").matcher("");

    private String firstName;
    private String lastName;
    private String idCode;

    public void setFirstName(String firstName) {
        if (!NAME.reset(firstName).matches()) {
            throw new NameException(String.format(
                    "Incorrect value %s for firstName (should start from upper case and contains only alphabetic characters and symbols -, _)",
                    firstName
            ));
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (!NAME.reset(lastName).matches()) {
            throw new NameException(String.format(
                    "Incorrect value %s for lastName (should start from upper case and contains only alphabetic characters and symbols -, _)",
                    lastName
            ));
        }
        this.lastName = lastName;
    }

    public void setIdCode(String idCode) {
        if (!ID_CODE.reset(idCode).matches()) {
            throw new CodeException(String.format(
                    "Incorrect value %s for code (should contains exactly 10 digits)",
                    idCode));
        }
        this.idCode = idCode;
    }

    public static Person buildPerson(
            String firstName, String lastName, String idCode
    ) {
        Person person = new Person();
        StringJoiner stringJoiner = new StringJoiner("; ");
        try {
            person.setFirstName(firstName);
        } catch (NameException ne) {
            stringJoiner.add(ne.getMessage());
        }
        try {
            person.setLastName(lastName);
        } catch (NameException ne) {
            stringJoiner.add(ne.getMessage());
        }
        try {
            person.setIdCode(idCode);
        } catch (CodeException ce) {
            stringJoiner.add(ce.getMessage());
        }
        if (stringJoiner.length() > 0) {
            throw new IllegalArgumentException(stringJoiner.toString());
        }
        return person;
    }

    @Override
    public String toString() {
        return String.format("%s %s: %s",
                firstName, lastName, idCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(idCode, person.idCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, idCode);
    }
}

class NameException extends RuntimeException {
    public NameException(String message) {
        super(message);
    }
}
class CodeException extends RuntimeException {
    public CodeException(String message) {
        super(message);
    }
}