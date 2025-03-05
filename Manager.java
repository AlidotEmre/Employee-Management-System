public class Manager {
    private int id;
    private String name;
    private double salaryLimit;

    // Constructor
    public Manager(int id, String name) {
        this.id = id;
        this.name = name;
        this.salaryLimit = 0.0; // Default value
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

    public double getSalaryLimit() {
        return salaryLimit;
    }

    public void setSalaryLimit(double salaryLimit) {
        this.salaryLimit = salaryLimit;
    }
}
