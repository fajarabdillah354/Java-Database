package belajar.java.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementTest {

    @Test
    void testPreparedStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        //saat menggunakan preparedstatement maka kita harus mendeklarasikan sqlnya terlebih dahulu
        String username = "admin';#";//sekalipun kita trigger dengan sql injection maka query sql kita tetep aman
        String password = "salah";
        //kita juga tidak boleh menggunakan penjumalahan string atau concatenate
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";//untuk valuenya kita ubah menjadi tanda tanya
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);

        ResultSet resultSet = preparedStatement.executeQuery();//kita tidak perlu memberi parameter sql lagi karna sudah di method preparedStatement

        if(resultSet.next()){
            System.out.println("sukses login : "+resultSet.getString("username"));
        }else {
            System.out.println("gagal login");
        }

        //ini lebih aman karna terhindar dari sql injection
        preparedStatement.close();
        connection.close();


    }




}
