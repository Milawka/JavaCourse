package model.entities;

public class Book {
    private long id;
    private String name;
    private String author;
    private String reference;

    public Book(long id, String name, String author, String reference) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.reference = reference;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getReference() {
        return reference;
    }


    public void setReference(String reference) {
        this.reference = reference;
    }
}
