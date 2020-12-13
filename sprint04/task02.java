import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Objects;

public class MyUtils {
    public static class Student {
        private final int id;
        private final String name;

        public Student(int id, String name) {
            Objects.requireNonNull(name);
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return id == student.id &&
                    name.equals(student.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public Set<Student> commonStudents(List<Student> list1, List<Student> list2) {
        var res = new HashSet<>(list1);
        res.retainAll(new HashSet<>(list2));
        return res;
    }
}