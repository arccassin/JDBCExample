import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
//import com.mysql.cj.jdbc.Driver;

/**
 * Created by User on 09 Сент., 2019
 */
public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";//?serverTimeZone=UTC";
        String user = "root";
        String pass = "testtest";

        try {
//            Driver driver = new Driver();
            //Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT Courses.name AS name, " +
                            "COUNT(Subscriptions.student_id)/12 AS avr_of_month FROM Courses " +
                            "JOIN Subscriptions ON Courses.id=Subscriptions.course_id " +
                            "GROUP BY Courses.id");

            while (resultSet.next()){
                String courseName = resultSet.getString("name");
                String avr = resultSet.getString("avr_of_month");
                System.out.printf("%-30s : %s%n", courseName, avr);
            }


            resultSet.close();
            statement.close();
            connection.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}


/**
 * Написать код, который выведет для каждого курса среднее количество покупок в месяц.
 * Лучше только средствами SQL (вариант “со звёздочкой”), но группировку
 * по месяцам можно реализовать и с помощью Java.
*/
