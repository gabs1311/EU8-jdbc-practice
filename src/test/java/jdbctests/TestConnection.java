package jdbctests;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class TestConnection {
    public static void main(String[] args) throws SQLException {
        // this is the connection string. In real life, this will be either set, or given to me
        String dbUrl = "jdbc:oracle:thin:@44.201.250.13:1521:XE";
        String dbUsername = "hr";
        String dbPassword = "hr";

        Connection connection = getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM regions");

    // next: we move pointer to te next row
        resultSet.next();

        // getting information with column name
        System.out.println(resultSet.getString("region_name"));

        //getting information with column index ( starts from 1)
        System.out.println(resultSet.getString(2));
        // 1. Europe
        //2. Americas

        System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));

        //move to the 2nd row
        resultSet.next();
        System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));

        //move to he third row
        resultSet.next();
        System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));
        // to make it easier, for automation purpose, we can use loop

        // so, while it is moving to the next row, it is also applying the row formula
        // this loop is dynamic, so it will stop executing the code when there are no more rows
       while(resultSet.next()){

           System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));

       }



        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }
}
