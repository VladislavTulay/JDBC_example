import pojo.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostgreSqlSampler {

    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "mysecretpassword";

    public static void main(String[] args) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("No postgres driver");
            return;
        }

        String selectRequest = "INSERT INTO students (student_id, student_name, last_name, average_grade) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement();
             PreparedStatement preparedStatement = connection.prepareStatement(selectRequest)){

            System.out.println("Select and put into Object:");

            ResultSet rs = selectFromDB(statement, "students", "student_id", "student_name", "last_name", "average_grade");

            List<Student> students = new ArrayList<>();

            while(rs.next()) {
                int studentId = Integer.parseInt(rs.getString("student_id"));
                String name = rs.getString("student_name");
                String lastName = rs.getString("last_name");
                double averageGrade = Double.parseDouble(rs.getString("average_grade"));
                students.add(new Student(studentId, name, lastName, averageGrade));
            }

            for(Student student: students) {
                System.out.println(student);
            }

            System.out.println("-----------------------------------------------------");

            System.out.println("Insert new student");

            int id = 5;
            String name = "Alpha";
            String lastName = "Omega";
            double averageGrade = 9.9;

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setDouble(4, averageGrade);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }

    }

    private static ResultSet selectFromDB(Statement statement, String tableName, String ... columns) throws SQLException {
        String req = Arrays.toString(columns);
        String strStatement = "SELECT " + req.substring(1, req.length() - 1) + " FROM " + tableName;
        System.out.println(strStatement);
        return statement.executeQuery(strStatement);
    }

}
