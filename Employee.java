public class Employee {
    private int id;
    private String name;
    private double monthlySalary;

    // Constructor
    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
        this.monthlySalary = 0.0; // Default value
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }
}
