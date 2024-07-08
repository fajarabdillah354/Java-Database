package belajar.java.database;

import belajar.java.database.entity.Comments;
import belajar.java.database.repository.CommentsRepository;
import belajar.java.database.repository.CommentsRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.BooleanSupplier;

public class RepositoryTest {

    private CommentsRepository commentsRepository;
    @BeforeEach
    void setUp() {
        commentsRepository = new CommentsRepositoryImpl();
    }

    @Test
    void testInsert() {
        Comments comments = new Comments("nafal@test.com","this is awesome");
        commentsRepository.insert(comments);

        Assertions.assertNotNull(comments.getComment());
        System.out.println(comments.getId());
    }



    @Test
    void testFindById() {
        Comments comments = commentsRepository.findById(1618);
        Assertions.assertNotNull(comments);
        System.out.println(comments.getEmail());
        System.out.println(comments.getComment());


//        Comments notFound = commentsRepository.findById(1615);
//        Assertions.assertNull(notFound);

    }


    @Test
    void testFindAll() {
        List<Comments> comments = commentsRepository.findAll();
        System.out.println(comments.size());


    }

    @Test
    void testByEmail() {
        List<Comments> comments = commentsRepository.findByEmail("fajar@test.com");//jika email tidak ada maka hasil dari getsize() akan bernilai 0
        System.out.println(comments.size());
        System.out.println(comments.isEmpty());

    }


    @Test
    void testDeleteById() {
        List<Comments> comments = commentsRepository.deleteById(1616);
        Assertions.assertNotNull(comments);

    }


    @Test
    void testDeleteAllRecord() {
        Comments comment = commentsRepository.deleteAllRecord();
        Assertions.assertNotNull(comment);
    }
}
