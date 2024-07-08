package belajar.java.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchTest {
    /***
     * TODO: NOTE
     Batch statement dalam Java database adalah sebuah mekanisme yang memungkinkan Anda untuk mengeksekusi sekelompok pernyataan SQL sebagai satu kesatuan, daripada mengeksekusi mereka satu per satu secara terpisah. Ini dapat meningkatkan kinerja aplikasi dengan mengurangi jumlah koneksi yang diperlukan ke database, karena Anda dapat mengeksekusi beberapa pernyataan dalam satu koneksi. Batch statement juga dapat berguna untuk melakukan operasi pada dataset yang sama secara bersamaan, seperti menambahkan, memperbarui, atau menghapus beberapa baris data.
     */



    @Test
    void testBatch() throws SQLException {
        // ini hanya bisa untuk perintah insert,update,delete
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();
    // jika ada parameter dari user kita harus menggunakan prepared statement, jika tidak menggunakan Statement
        String sql = "INSERT INTO comments(email, comment) VALUES('fajar@tilabs.com', 'HELLOW')";

        for (int i=0;i<100;i++){
            statement.addBatch(sql);
        }

        statement.executeBatch();

        statement.close();
        connection.close();
    }


    @Test
    void testDelete() throws SQLException{
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = """
                DELETE FROM comments
                """;//perintah untuk mendelete isi table costumers, akan mengembalikan int sejumlah data yang berhasil dihapus
//saat kita mendelete maka akan mengembalikan banyaknya record yang terdelete

        int update = statement.executeUpdate(sql);
        System.out.println(update);

        statement.close();
        connection.close();
    }

    @Test
    void testBatchPreparedStatement() throws SQLException {
        // ini hanya bisa untuk perintah insert,update,delete
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email, comment) VALUES(?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // jika ada parameternya kita harus menggunakan prepared statement


        for (int i=0; i<1000; i++){
            preparedStatement.clearParameters();//untuk membersihkan semua parameter sebelum perintah PreparedStatement dilakukan
            preparedStatement.setString(1,"fajar@tilabs.com");// Menset paramater untuk PraparedStatement
            preparedStatement.setString(2,"hello");
            preparedStatement.addBatch();// eksekusi var praparedStatement ke method addBatch()
        }

        preparedStatement.executeBatch();

        preparedStatement.close();
        connection.close();
    }

    /*
    PERINGATAN
    - hati2 jangan terlalu banyak menambahkan batchnya misal per 100 atau 1000 data
    - jika sudah mencapai 100 atau 1000 ,kita bisa mengirim batch tersebut menggunakan perintah executeBatch()
     */



}
