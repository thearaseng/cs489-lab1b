package edu.miu.cs.cs489appsd.lab1b.PensionPlans;

import edu.miu.cs.cs489appsd.lab1b.PensionPlans.model.Employee;
import edu.miu.cs.cs489appsd.lab1b.PensionPlans.model.PensionPlan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class EmployeeRepository {

    private static List<Employee> allEmployees() {

        Employee employee1 = new Employee(1, "Daniel", "Agar", LocalDate.of(2018, 1, 17), 105945.50, new PensionPlan("EX1089", LocalDate.of(2018, 1, 17), 100.00));
        Employee employee2 = new Employee(2, "Benard", "Shaw", LocalDate.of(2019, 4, 3), 197750.00, null);
        Employee employee3 = new Employee(3, "Carly", "Agar", LocalDate.of(2014, 5, 16), 842000.75, new PensionPlan("SM2307", LocalDate.of(2019, 11, 4), 1555.50));
        Employee employee4 = new Employee(4, "Wesley", "Schneider", LocalDate.of(2019, 5, 2), 74500.00, null);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);

        return employees;
    }

    public static Employee[] allEmployeesWithSorting() {
        List<Employee> employeeList = allEmployees();
        Employee[] employees = employeeList.toArray(new Employee[employeeList.size()]);

         Arrays.sort(employees,
                 Comparator.comparing(Employee::getLastName)
                    .thenComparing(Comparator.comparing(Employee::getYearlySalary).reversed())
         );

        return employees;
    }

    public static Employee[] getUpcomingEmployeesWithPension() {

        LocalDate currentDate = LocalDate.now();
        LocalDate next2Month = currentDate.plusMonths(2);
        LocalDate firstDayOfNext2Month = LocalDate.of(next2Month.getYear(), next2Month.getMonth(), 1);
        LocalDate lastDayOfNextMonth = firstDayOfNext2Month.minusDays(1);

        List<Employee> employeeList = allEmployees();
        employeeList = employeeList
                .stream()
                .filter(employee ->
                    employee.getPensionPlan() == null
                        && (employee.getEmploymentDate().plusYears(5).isBefore(lastDayOfNextMonth) || employee.getEmploymentDate().plusYears(5).isEqual(lastDayOfNextMonth))
                )
                .toList();

        Employee[] employees = employeeList.toArray(new Employee[employeeList.size()]);

        Arrays.sort(employees,
                Comparator.comparing(Employee::getEmploymentDate)
        );

        return employees;
    }
}
