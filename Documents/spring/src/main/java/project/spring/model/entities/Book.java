package project.spring.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

        @Id
        @GeneratedValue
        private Long id;

        private String name;
        private String author;
        private String reference;

    public Book() {}

    public Book(long id, String name, String author, String reference) {
            this.id = id;
            this.name = name;
            this.author = author;
            this.reference = reference;
        }


        public Long getId() {
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
