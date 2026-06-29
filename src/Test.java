import java.sql.Connection;
import java.sql.DriverManager;

public class Test {

    public static void t() {

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306",
                    "root",
                    "12345"
            );

            System.out.println("Connected Successfully!");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}