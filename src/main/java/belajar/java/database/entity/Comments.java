package belajar.java.database.entity;

public class Comments {
    private Integer id;
    private String email;
    private String comment;

    public Comments() {
    }

    public Comments(String email, String comment) {
        this.email = email;
        this.comment = comment;
    }

    public Comments(Integer id, String email, String comment) {
        this.id = id;
        this.email = email;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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


}
