import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class EMS {
    private String companyName;
    private Manager manager;
    private Set<Employee> employees;
    private Map<Employee, Double> salaryMap;

    // Constructor
    public EMS(String companyName, Manager manager) {
        this.companyName = companyName;
        this.manager = manager;
        this.employees = new HashSet<>();
        this.salaryMap = new HashMap<>();
    }

    // Getters
    public String getCompanyName() {
        return companyName;
    }

    public Manager getManager() {
        return manager;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public Map<Employee, Double> getSalaryMap() {
        return salaryMap;
    }

    // Methods
    public void registerEmployee(Employee employee) {
        employees.add(employee);
        salaryMap.put(employee, 0.0);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        salaryMap.remove(employee);
    }

    public void calculateSalaries(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean headerSkipped = false;
            while ((line = br.readLine()) != null) {
                // Skip header( "name,dailySalary")
                if (!headerSkipped) {
                    if (line.trim().toLowerCase().startsWith("name")) {
                        headerSkipped = true;
                        continue;
                    }
                    headerSkipped = true; // Process the first line even if no header detected.
                }

                String[] data = line.split(",");
                if (data.length == 2) {
                    String name = data[0].trim();
                    double dailySalary = Double.parseDouble(data[1].trim());

                    // Retrieve the employee by name; if not found, create and register one.
                    Employee employee = employees.stream()
                            .filter(e -> e.getName().equals(name))
                            .findFirst()
                            .orElseGet(() -> {
                                Employee newEmployee = new Employee(employees.size() + 1, name);
                                registerEmployee(newEmployee);
                                return newEmployee;
                            });

                    // Accumulate the daily salary for this employee.
                    double currentDailyTotal = salaryMap.getOrDefault(employee, 0.0);
                    double newDailyTotal = currentDailyTotal + dailySalary;
                    salaryMap.put(employee, newDailyTotal);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // After reading the file, update each employee's monthly salary.
        // Here, we assume that the CSV already contains the daily salaries for the month.
        for (Employee employee : employees) {
            double dailyTotal = salaryMap.getOrDefault(employee, 0.0);
            employee.setMonthlySalary(dailyTotal);
        }
    }
    public void askForSalaryLimit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter salary limit for the manager (enter 0 to skip): ");
        double limit = scanner.nextDouble();
        // Only assign and enforce limit if a positive number is entered.
        if (limit > 0) {
            manager.setSalaryLimit(limit);
            // Remove employees whose monthly salary exceeds the limit.
            employees.removeIf(employee -> employee.getMonthlySalary() > limit);
            salaryMap.entrySet().removeIf(entry -> entry.getKey().getMonthlySalary() > limit);
            System.out.println("Employees exceeding the salary limit have been fired.");
        } else {
            System.out.println("No salary limit assigned. No employees were fired.");
        }
    }
}
