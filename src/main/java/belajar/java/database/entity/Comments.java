package belajar.java.database.entity;

public class Comments {

    private String email;

    private String comment;

    private int id;

    public Comments() {
    }

    public Comments(String email, String comment) {
        this.email = email;
        this.comment = comment;
    }

    public Comments(String email, String comment, int id) {
        this.email = email;
        this.comment = comment;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
