package belajar.java.database.repository;

import belajar.java.database.entity.Comments;

import java.util.List;

public interface CommentsRepository{
    void insert(Comments comments);

    Comments findById(Integer id);

    List<Comments> findAll();

    List<Comments> findByEmail(String email);

}
