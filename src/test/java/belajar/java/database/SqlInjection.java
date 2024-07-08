package belajar.java.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlInjection {


    @Test
    void testSqlInjection() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        /*
        INI ADALAH CONTOH YANG SALAH KARNA JIKA KITA UBAH USERNAME MENJADI "admin';#" dan passwordnya kita ubah menjadi "salah" maka kita akan tetep masuk
        padahal pada database kita tidak ada password "salah", ini karna perintah sql yang kita buat dibawah akan berubah menjadi statement dan semua kondisi setelah "#" pada username
        akan dianggep comment sehingga user bisa bebas masuk... INI BERBAHAYA.......
         */

        String username = "admin';#";
        String password = "salah";

        //ini BERBAHAYA KARNA KITA MENGGUNAKAN STRING APPEND ATAU CONCATNATE ATAU PENAMBAHAN DALAM STRING
        String sql = "SELECT * FROM admin WHERE username = '" +username+"' AND password = '"+password+"' ";

        System.out.println(sql);

        /*
        TODO :
            interface ResultSet digunakan untuk merepsentasikan hasil dari sebuah query yang dijalankan pada database dan dapat membaca data secara baris perbaris dan kolom per kolom
         */

        ResultSet resultSet = statement.executeQuery(sql);

        if(resultSet.next()){
            System.out.println("sukses login : "+resultSet.getString("username"));
        }else {
            System.out.println("gagal login");
        }



        resultSet.close();//jangan lupa untuk menutup kembali resultsetnya
        statement.close();
        connection.close();



    }



}
