package Staff;
import java.time.LocalDate;

public class Employee {



    private String name;
    private LocalDate birthDate;
    private String phoneNumber;
    private String post;
    private String department;
    private String manager;
    private LocalDate hireDate;
    private int salary;
    private Gender gender;
    private int id;

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee(String name, LocalDate birthDate, Gender gender, String phoneNumber, String post, String department, String manager, LocalDate hireDate, int salary, int id) {
        this.name = name;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.post = post;
        this.department = department;
        this.manager = manager;
        this.hireDate = hireDate;
        this.salary = salary;
        this.gender = gender;
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPost() {
        return post;
    }

    public String getDepartment() {
        return department;
    }

    public String getManager() {
        return manager;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public int getSalary() {
        return salary;
    }

    public Gender getGender() {
        return gender;
    }
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name=" + name +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                ", phoneNumber=" + phoneNumber +
                ", post=" + post +
                ", department=" + department +
                ", manager=" + manager +
                ", hireDate=" + hireDate +
                ", salary=" + salary +
                ", id=" + id +
                '}';
    }

}