package belajar.java.database.repository;

import belajar.java.database.ConnectionUtil;
import belajar.java.database.entity.Comments;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentsRepositoryImpl implements CommentsRepository{


    @Override
    public void insert(Comments comments) {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()) {
            String sql = "INSERT INTO comments(email, comment) VALUES(?, ?)";
            try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1,comments.getEmail());
                statement.setString(2,comments.getComment());
                statement.executeUpdate();

                try(ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()){
                        comments.setId(resultSet.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Comments findById(Integer id) {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()) {
            String sql = "SELECT * FROM comments WHERE id = ?";
            try(PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try(ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()){
                        return new Comments(
                                resultSet.getString("email"),
                                resultSet.getString("comment")
                        );
                    }else{
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Comments> findAll() {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()) {
            String sql = "SELECT * FROM comments";
            try(Statement statement = connection.createStatement()) {
                try(ResultSet resultSet = statement.executeQuery(sql)) {
                    List<Comments> comments = new ArrayList<>();
                    while (resultSet.next()){
                        comments.add(new Comments(
                                resultSet.getInt("id"),
                                resultSet.getString("email"),
                                resultSet.getString("comment")
                        ));
                    }
                    return comments;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Comments> findByEmail(String email) {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()) {
            String sql = "SELECT * FROM comments WHERE email = ? ";
            try(PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1,email);
                try(ResultSet resultSet = statement.executeQuery()) {
                    List<Comments> comments = new ArrayList<>();
                    while (resultSet.next()){
                        comments.add(new Comments(
                                resultSet.getInt("id"),
                                resultSet.getString("email"),
                                resultSet.getString("comment")
                        ));
                    }
                    return comments;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Comments> deleteById(Integer id) {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()) {
            String sql= "Delete FROM comments WHERE id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1,id);
                List<Comments> comments = new ArrayList<>();
                int update = preparedStatement.executeUpdate();
                System.out.println(update + " data berhasil dihapus");
                return comments;
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Comments deleteAllRecord() {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()) {
            try(Statement statement = connection.createStatement()) {
                Comments comments = new Comments();
                String sql = "DELETE FROM comments";
                statement.executeUpdate(sql);
                System.out.println("sukses menghapus data record table Comments");
                return comments;
            }


        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


}
