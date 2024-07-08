package belajar.java.database;

import com.mysql.cj.jdbc.Driver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverRegister {


    @Test
    void testRegisterDriver() {

        try{
            Driver testDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(testDriver);
        }catch (SQLException sqlException){
            Assertions.fail();
        }

    }
}
