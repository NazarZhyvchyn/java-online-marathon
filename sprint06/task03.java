import java.util.function.BinaryOperator;
import java.util.List;
import java.util.stream.Collectors;

 class App {

    public static final BinaryOperator<String> greetingOperator =
            (parameter1, parameter2) ->
            "Hello " + parameter1 + " " + parameter2 + "!!!";

    public static List<String> createGreetings(
            List<Person> people,
            BinaryOperator<String> greetingMaker
    ){
        return people.stream()
                .map(person -> greetingMaker.apply(person.name, person.surname))
                .collect(Collectors.toList());
    }
}

