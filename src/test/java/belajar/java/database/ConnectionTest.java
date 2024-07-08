package belajar.java.database;

import com.mysql.cj.jdbc.Driver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

    @Test
    void beforeAll() {
        try {
            Driver connectDb = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(connectDb);
        }catch (SQLException exception){
            exception.printStackTrace();
        }

    }


    @Test
    void testConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/mobil";
        String  username = "root";
        String password = "fahmifadilah25";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl,username,password);

            System.out.println("sukses connect to databases");
        }catch (SQLException exception){
            Assertions.fail(exception);
        }

    }


    @Test
    void testConnectionClose() {
        //saat membukan conneksi ke DB pastikan kita menutupnya kembali, terlebih lagi pada apk web atau desktop yang berjalan terus menerus
        // kita bisa memasukan block code connect database kedalam try supaya otomatis menutup jika sudah selesai

        String jdbcUrl = "jdbc:mysql://localhost:3306/mobil";
        String  username = "root";
        String password = "fahmifadilah25";

        try(Connection connection = DriverManager.getConnection(jdbcUrl, username, password)){//ini adalah contoh yang dalam 1 parameter
            System.out.println("sukses menutup connection");
        }catch (SQLException exceptione){
            Assertions.fail(exceptione);
        }


    }
}
