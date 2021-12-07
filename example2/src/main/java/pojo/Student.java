package pojo;

import java.util.Objects;

public class Student {

    private int studentId;
    private String name;
    private String lastName;
    private double averageGrade;
    private int classId;

    public Student(int studentId, String name, String lastName, double averageGrade) {
        this.studentId = studentId;
        this.name = name;
        this.lastName = lastName;
        this.averageGrade = averageGrade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return this.studentId == student.studentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, name, lastName, averageGrade);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", averageGrade=" + averageGrade +
                '}';
    }
}
