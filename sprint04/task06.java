// Write PersonComparator, EmployeeComparator and DeveloperComparator here
import java.util.Comparator;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsLast;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;


class PersonComparator implements Comparator<Person> {
    
    //     @Override
//     public int compare(Person p1, Person p2) {
//         String name1 = p1.getName();
//         String name2 = p2.getName();
//         Integer age1 = p1.getAge();
//         Integer age2 = p2.getAge();

//         // ascending order (descending order would be: name2.compareTo(name1))
//         return name1.compareTo(name2);
// }
    final static Comparator<Person> COMPARATOR = Comparator.nullsFirst(
            Comparator.comparing(Person::getName)
                    .thenComparingInt(Person::getAge));

    @Override
    public int compare(Person o1, Person o2) {
        return COMPARATOR.compare(o1, o2);
    }
}

class EmployeeComparator implements Comparator<Employee> {
    final static Comparator<Employee> COMPARATOR =
            Comparator.<Employee, Person>comparing(Function.identity(),
                    PersonComparator.COMPARATOR)
                    .thenComparingDouble(Employee::getSalary);

    @Override
    public int compare(Employee o1, Employee o2) {
        return COMPARATOR.compare(o1, o2);
    }
}

class DeveloperComparator implements Comparator<Developer> {
    final static Comparator<Developer> COMPARATOR =
            Comparator.<Developer, Employee>comparing(Function.identity(),
                    EmployeeComparator.COMPARATOR)
                    .thenComparing(Developer::getLevel);

    @Override
    public int compare(Developer o1, Developer o2) {
        return COMPARATOR.compare(o1, o2);
    }
}

class Utility {
    public static <T extends Person> void sortPeople(T[] people, Comparator<? super T> comparator) {
        Arrays.sort(people, comparator);
    }
}