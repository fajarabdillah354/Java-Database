package belajar.java.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class MetaDataTest {

    /*
    METADATA
    - kadang kita ingin mendapat informasi seputar database  yang kita gunakan
    - informasi tersebut bernama MetaData
    - ada banyak jenis metadata, seperti DatabaseMetaData, ParameterMetaData, dan ResultSetMetaData
     */

    @Test
    void testDataBaseMetaData() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        DatabaseMetaData databaseMetaData = connection.getMetaData();

        /*
            databaseMetaData adalah informasi seputar seluruh database yang kita gunakan misal;
            1. nama database
            2. versi database
            3. table yang ada didatabase
            DLL
         */
        System.out.println(databaseMetaData.getDriverName());//untuk melihat driver sql yang kita pakai
        System.out.println(databaseMetaData.getConnection());//untuk melihat connection jdbc yang kita pakai yaitu HikariCp
        System.out.println(databaseMetaData.getDatabaseProductName());//untuk melihat nama database
        System.out.println(databaseMetaData.getDatabaseProductVersion());//untuk melihat versi database
        ResultSet resultSet = databaseMetaData.getTables("belajar_java_database",null,null,null);
        while (resultSet.next()){
            String tableName = resultSet.getString( "TABLE_NAME");
            System.out.println("table : "+tableName );
        }

        connection.close();



    }


    @Test
    void testParameterMetaData() throws SQLException {
        /*
          PARAMETER METADATA
          - adalah informasi seputar parameter yang terdapat di PreparedStatement
          - dengan ParamaterMetaData kita bisa mendapat banyak informasi parameter yang bisa digunakan untuk input di PreparedStatement, seperti berapa banyak parameter, tipe data parameter, dll
          - namun perlu diperhatikan beberapa Driver Mungking tidak mendukungg untuk mendapatkan jenis tipe parameter di ParameterMetaData
         */


        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email, comment) VALUES(?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
        System.out.println(parameterMetaData.getParameterCount());// akan mencetak berapa banyak parameternya
//        System.out.println(parameterMetaData.getParameterType(4));// pada driver MySql belum support


        preparedStatement.close();
        connection.close();


    }


    @Test
    void testResultSetMetaData() throws SQLException {
        /*
          RESULTSET METADATA
          - ResultSetMetaData adalah informasi seputar hasil ResultSet
          - Dengan ResultSetMetaData, kita bisa mendapatkan informasi tentang jumlah kolom, nama kolom, tipe date tiap kolom dll
         */

        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM sample_time");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        System.out.println(resultSetMetaData.getColumnCount());

        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++){
            System.out.println("Name : "+resultSetMetaData.getColumnName(i));
            System.out.println("Type : "+resultSetMetaData.getColumnType(i));//ini akan mengembalikan nilai int karna berdasarkan java.sql.Type dimana setiap type sudah ada unit intnya masing2
            System.out.println("Type Name : "+resultSetMetaData.getColumnTypeName(i));

            if (resultSetMetaData.getColumnType(i) == 4){
                System.out.println("ini adalah data integer");
            }


        }


        resultSet.close();
        statement.close();
        connection.close();


    }





}
