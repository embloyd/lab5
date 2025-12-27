package lab5;

public class Student {
    private String lastName;
    private String firstName;
    private int school;
    private int score;

    public Student(String lastName, String firstName, int school, int score) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.school = school;
        this.score = score;
    }

    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public int getSchool() { return school; }
    public int getScore() { return score; }

    public String getFullName() {
        return lastName + " " + firstName;
    }
}