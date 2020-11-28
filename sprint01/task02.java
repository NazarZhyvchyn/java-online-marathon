// Create an instances of Employee class and use they here
Employee emp1 = new Employee();
Employee emp2 = new Employee();
emp1.fullName = "Andriy";
emp1.salary = 19999;
emp2.fullName = "Oleg";
emp2.salary = 10000;
java.util.List<Employee> employees = new java.util.ArrayList<Employee>();
employees.add(emp1);
employees.add(emp2);
    String employeesInfo = "" ;
            employeesInfo += String.format("[{fullName: \"%s\", salary: %s}, {fullName: \"%s\", salary: %s}]", emp1.fullName, emp1.salary,emp2.fullName, emp2.salary);

 


 