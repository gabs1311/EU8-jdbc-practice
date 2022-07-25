package jdbctests;

import com.mysql.cj.protocol.Resultset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class jdbc_examples {

    String dbUrl = "jdbc:oracle:thin:@44.201.250.13:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public  void test1 () throws SQLException {

        Connection connection = getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");

 /*  // move to 1st row
        resultSet.next();
        // I want  tot print the table the Oracle shows me after the   resultSet.next(); command

        System.out.println(resultSet.getString(2));
        // display departments table in 10- Administration-200 -1700 format

*/
        // code for iterating for each row
        while(resultSet.next()){
            System.out.println(resultSet.getInt(1)+ " - "+ resultSet.getString(2)+
                    " - "+ resultSet.getInt(3)+ resultSet.getInt(4));
        }
//=====================================================

        resultSet = statement.executeQuery("SELECT * FROM regions" );

        while(resultSet.next()){
            System.out.println(resultSet.getInt(1)+ " - "+ resultSet.getString(2));
        }

        //close connection
        resultSet.close();
        statement.close();
        connection.close();


    }

    @DisplayName("ResultSet Methods")
    @Test
    public void test2() throws SQLException {
        Connection connection = getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");


        // how to find out how many rows we have for the query? ==> move to the last row and

        resultSet.last();
// and then get the row count
        int rowCount =resultSet.getRow();
        System.out.println(rowCount);

      // to move before first row after we used last method, so that the pointer is set for the next move
    // if the pointer is not set from tha last raw, it is not going to execute my next command, because it
        // is already in the last row


        resultSet.beforeFirst();
        // print the 2nd column

        while (resultSet.next())
        {
            System.out.println(resultSet.getString(2));
        }
        //close connection
        resultSet.close();
        statement.close();
        connection.close();

    }


    @Test
    public void test3() throws SQLException {
        Connection connection = getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");


        // get the database related data inside the MeteData object

        DatabaseMetaData dbMetaData= connection.getMetaData();

        System.out.println("dbMetaData.getUserName() = " + dbMetaData.getUserName());
        System.out.println("dbMetaData.getDatabaseProductName() = " + dbMetaData.getDatabaseProductName());
        System.out.println("dbMetaData.getDatabaseProductVersion() = " + dbMetaData.getDatabaseProductVersion());
        System.out.println("dbMetaData.getDriverName() = " + dbMetaData.getDriverName());
        System.out.println("dbMetaData.getDriverVersion() = " + dbMetaData.getDriverVersion());


        // get the resultsetmetadata// rsmd

        ResultSetMetaData rsMetaData= resultSet.getMetaData();

        // how many columns do we have?
            int colCount = rsMetaData.getColumnCount();
        System.out.println(colCount);

        // getting column names
        System.out.println("rsMetaData.getColumnName(1) = " + rsMetaData.getColumnName(1));
        System.out.println("rsMetaData.getColumnName(2) = " + rsMetaData.getColumnName(2));

        //rsMetadata.getColumnName(i) --> gets column name
        //rsMetadata.getColumnCount() --> total number of columns
        //print all the column names dynamically
        for (int i = 1; i <= colCount; i++) {
            System.out.println(rsMetaData.getColumnName(i));
        }

//close connection
        resultSet.close();
        statement.close();
        connection.close();
    }
    }
