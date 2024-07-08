package belajar.java.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultTest {

    @Test
    void testExecuteQuery() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = """
                SELECT * FROM costumers
                """;

        /*
        TODO :
            interface ResultSet digunakan untuk merepsentasikan hasil dari sebuah query yang dijalankan pada database dan dapat membaca data secara baris perbaris dan kolom per kolom
         */

        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()){
            String id = resultSet.getString("id");//kita bisa menyesuaikan methodnya sesuai dengan yang ada di database
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            // kita juga bisa menggunaka prefix jika kolom tabel kita membingungkan contoh setelah melakukan join
            // contoh penggunaan prefix namatabel.kolom : costumers.name


            System.out.println(
                    String.join(", ", id, name, email)
            );
        }


        resultSet.close();//jangan lupa untuk menutup kembali resultsetnya
        statement.close();
        connection.close();



    }


}
