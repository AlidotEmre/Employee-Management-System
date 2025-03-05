public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager(1, "Alice");
        manager.setSalaryLimit(5000); // Set salary limit

        EMS ems = new EMS("TechCorp", manager);

        // Load employee data from CSV file
        ems.calculateSalaries("LAB3(2).csv");

        // Print employees before enforcing salary limit
        System.out.println("Employees before enforcing salary limit:");
        for (Employee e : ems.getEmployees()) {
            System.out.printf("%s - %.2f%n", e.getName(), e.getMonthlySalary());
        }

        // Enforce salary limit
        ems.askForSalaryLimit();

        // Print employees after enforcing salary limit
        System.out.println("\nEmployees after enforcing salary limit:");
        for (Employee e : ems.getEmployees()) {
            System.out.printf("%s - %.2f%n", e.getName(), e.getMonthlySalary());

        }
    }
}