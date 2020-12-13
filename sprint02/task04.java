import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.*;
class Employee {
    private final String name;
    private final int experience;
    private final BigDecimal basePayment;

    public Employee(String name, int experience, BigDecimal basePayment) {
        this.name = name == null ? "" : name;
        this.experience = experience;
        this.basePayment = basePayment == null ? BigDecimal.ZERO : basePayment;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    public BigDecimal getPayment() {
        return basePayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return experience == employee.experience &&
                Objects.equals(name, employee.name) &&
                Objects.equals(basePayment, employee.basePayment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, experience, basePayment);
    }

    @Override
    public String toString() {
        return String.format("Employee [name=%s, experience=%d, basePayment=%.2f]",
                name, experience, basePayment.doubleValue());
    }
}

class Manager extends Employee {
    private final double coefficient;

    public Manager(String name, int experience, BigDecimal basePayment, double coefficient) {
        super(name, experience, basePayment);
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }

    @Override
    public BigDecimal getPayment() {
        return super.getPayment().multiply(
                BigDecimal.valueOf(coefficient).setScale(2, RoundingMode.HALF_UP));
    }

    @Override
    public String toString() {
        return String.format("Manager [name=%s, experience=%d, basePayment=%.2f, coefficient=%.1f]",
                getName(), getExperience(), super.getPayment().doubleValue(), coefficient);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return Double.compare(manager.coefficient, coefficient) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), coefficient);
    }
}

class MyUtils {
    public List<Employee> largestEmployees(List<Employee> workers) {
        if (workers == null || workers.isEmpty()) {
            return List.of();
        }
        BigDecimal maxPayment = workers.stream()
                .filter(Objects::nonNull)
                .map(Employee::getPayment)
                .reduce(BigDecimal.ZERO, BigDecimal::max);
        int maxExperience = workers.stream()
                .filter(Objects::nonNull)
                .mapToInt(Employee::getExperience)
                .max().orElse(0);
        Predicate<Employee> filter = employee ->
                employee.getExperience() == maxExperience ||
                        employee.getPayment().compareTo(maxPayment) == 0;
        return workers.stream()
                .filter(Objects::nonNull)
                .filter(filter)
                .filter(e -> e.getExperience() == maxExperience || e.getPayment().compareTo(maxPayment) ==0)
                .distinct()
                .collect(Collectors.toList());
    }
    
   
}