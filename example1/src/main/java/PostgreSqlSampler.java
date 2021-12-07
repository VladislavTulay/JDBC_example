import java.sql.*;

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

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()
        ){

            ResultSet rs = statement.executeQuery("SELECT * FROM students ORDER BY student_id DESC");

            while(rs.next()) {
                System.out.println(rs.getString("student_id") + " " + rs.getString("student_name")
                        + " " + rs.getString("last_name") + " " + rs.getString("average_grade"));
            }

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }


    }

}
