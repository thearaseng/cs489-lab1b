package edu.miu.cs.cs489appsd.lab1b.PensionPlans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs.cs489appsd.lab1b.PensionPlans.model.Employee;

import java.text.SimpleDateFormat;

public class PensionPlanApp {

    public static void main(String[] args) throws JsonProcessingException {
//        printOutAllEmployees();
        printOutUpcomingPension();
    }

    private static void printOutAllEmployees() throws JsonProcessingException {
        Employee[] employees = EmployeeRepository.allEmployeesWithSorting();

        ObjectWriter ow = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd"))
                .writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(employees);
        System.out.println("Print out all employee");
        System.out.println(json);
    }

    private static void printOutUpcomingPension() throws JsonProcessingException {
        Employee[] employees = EmployeeRepository.getUpcomingEmployeesWithPension();

        ObjectWriter ow = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd"))
                .writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(employees);

        System.out.println("---------------------------------------");
        System.out.println("Print employee with upcoming pension");
        System.out.println(json);
    }

}