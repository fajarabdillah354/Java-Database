package belajar.java.database;


import org.junit.jupiter.api.Test;

import java.sql.*;

public class AutoincrementTest {

    @Test
    void testAutoincrement() throws SQLException {

        Connection connection  = ConnectionUtil.getDataSource().getConnection();
        String email = "fajar@test.com";
        String comment = "hello";

        String sql = "INSERT INTO comments(email,comment) VALUES(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//untuk melakukan generate auto increment kita perlu mengenerate keysnya

        preparedStatement.setString(1,email);
        preparedStatement.setString(2,comment);
        preparedStatement.executeUpdate();


        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()){//untuk menampilkan hasil kita perlu ResultSet lalu kita iterasi setiap data didalamnya
            System.out.println("id comment : "+resultSet.getInt(1));
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

    }




}
