package belajar.java.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TrunsactionCommitTest {


    @Test
    void testDelete() throws SQLException{
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = "DELETE FROM comments";

        int update = statement.executeUpdate(sql);

        System.out.println(update);//akan mencetak sejumlah data yang dihapus

        statement.close();
        connection.close();


    }

    @Test
    void testCommit() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);//KARNA DEFAULT YANG TRUE MAKA KITA PERLU MENGUBAH MENJADI false

        String sql = "INSERT INTO comments(email, comment) VALUES(?, ?)";


        for(int i = 0; i<100; i++){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"fajar@test.com");
            preparedStatement.setString(2, "hi");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }


        connection.commit();//untuk mengunci dan mengeksusi perintah diatas ini sekaligus menyatakan perintah sql telah selesai
        connection.close();

    }

    @Test
    void testRollback() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        String sql = "INSERT INTO comments(email, comment) VALUES(?, ?)";


        for(int i = 0; i<100; i++){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"fajar@test.com");
            preparedStatement.setString(2, "hi");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }


        connection.rollback();//untuk membatalkan proses transaksi (rollback) kita bisa menggunakan method rollback() milik Connection
        connection.close();

    }




}
