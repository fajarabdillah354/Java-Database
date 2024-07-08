package belajar.java.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class StatmentTest {

    @Test
    void testCreateStatment() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        Statement statement = connection.createStatement();

        /*
        block untuk kita memasukan statement

         */


        // jangan lupa untuk selalu menutup connection dan resource lain yang harus ditutup kembali
        statement.close();
        connection.close();

    }

    @Test
    void testExecuteUpdate() throws SQLException{//kita bisa langsungg try pada method testnya
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        // isi block perintah SQL
        String sql = """
                INSERT INTO costumers(id, name, email)
                VALUES ('abdillah', 'ABDILLAH', 'abdillah@test.com')
                """;//jika record ini berhasil maka nilai balikan akan bernilai 1 karna 1 data berhasil di update ke dalam DB

        int update = statement.executeUpdate(sql);//nilai balikan dari ini adalah integer, setiap data yang berhasil maka akan terhitung
        System.out.println(update);

        statement.close();
        connection.close();

    }

    @Test
    void testDelete() throws SQLException{
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = """
                DELETE FROM costumers
                """;//perintah untuk mendelete isi table costumers, akan mengembalikan int sejumlah data yang berhasil dihapus
//saat kita mendelete maka akan mengembalikan banyaknya record yang terdelete

        int update = statement.executeUpdate(sql);
        System.out.println(update);

        statement.close();
        connection.close();
    }



}
