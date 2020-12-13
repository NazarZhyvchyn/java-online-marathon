import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.*;



class Person {
    private String name;
    
    public String getName() {
        return name;
    }
    public Person(){}
    public Person(String name){
        this.name = name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    
}
class Student extends Person {
    private String studyPlace;
    private int studyYears;
    
    public String getStudyPlace() {
        return studyPlace;
    }
    public int getStudyYears(){
        return studyYears;
    }
    public Student(){
        super();
}
    
    public Student(String studyPlace, String name, int studyYears){
        super(name);
        this.studyPlace = studyPlace;
        this.studyYears = studyYears;
    }
    public Student(String studyPlace){
        super();
        this.studyPlace = studyPlace;
        this.studyYears = studyYears;
    }
     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return studyYears == student.studyYears &&
                Objects.equals(studyPlace, student.studyPlace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), studyPlace, studyYears);
    }
}
class Worker extends Person {
    private String workPosition;
     private int experienceYears;
    
    public Worker() {
        
    }
    public Worker(String workPosition, String name, int experienceYears) {
        super(name);
        this.workPosition = workPosition;
        this.experienceYears = experienceYears;
    }
    public String getWorkPosition() {
    return workPosition;
        
    }
    public int getExperienceYears() {
    return experienceYears;
        
    }
      @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Worker worker = (Worker) o;
        return experienceYears == worker.experienceYears &&
                Objects.equals(workPosition, worker.workPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), workPosition, experienceYears);
    }
}
public class MyUtils {
      public List<Person> maxDuration(List<Person> persons) {
            List<Person> maxDuration = new ArrayList<Person>();

            final ArrayList<Student> students = new ArrayList<>();
            ArrayList<Person> studentsWithMaxStudyYears = new ArrayList<>();
            final ArrayList<Worker> workers = new ArrayList<>();
            ArrayList<Person> workersWithMaxExperienceYears = new ArrayList<>();
            for (int i = 0; i < persons.size(); i++) {
                if (persons.get(i) instanceof Worker){
                    workers.add((((Worker) persons.get(i))));
                }
                if (persons.get(i) instanceof Student){
                    students.add((((Student) persons.get(i))));
                }
                else {
                    continue;
                }
            }

            int maxYears = 0;
            //We look for the best grade first.
            for (Student student : students) {
                if (student.getStudyYears() > maxYears) {
                    maxYears = student.getStudyYears();
                }
            }
            //Now we can add those students with the best grade in the array
            for (Student student : students) {
                if (student.getStudyYears() == maxYears) {
                    studentsWithMaxStudyYears.add(student);
                }
            }
            //And we return the results


            int maxExp = 0;
            //We look for the best grade first.
            for (Worker worker : workers) {
                if (worker.getExperienceYears() > maxExp) {
                    maxExp = worker.getExperienceYears();
                }
            }
            //Now we can add those students with the best grade in the array
            for (Worker worker : workers) {
                if (worker.getExperienceYears() == maxExp) {
                    workersWithMaxExperienceYears.add(worker);
                }
            }
           maxDuration.addAll(workersWithMaxExperienceYears);
            maxDuration.addAll(studentsWithMaxStudyYears);
            
            return maxDuration;
        }

}


