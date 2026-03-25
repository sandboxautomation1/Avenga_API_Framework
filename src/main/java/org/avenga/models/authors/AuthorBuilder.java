package org.avenga.models.authors;

public class AuthorBuilder {

    private Author author;

    public AuthorBuilder() {
        author = new Author();
    }

    public AuthorBuilder withId(Integer id) {
        author.setId(id);
        return this;
    }

    public AuthorBuilder withBookId(int bookId) {
        author.setIdBook(bookId);
        return this;
    }

    public AuthorBuilder withFirstName(String firstName) {
        author.setFirstName(firstName);
        return this;
    }

    public AuthorBuilder withLastName(String lastName) {
        author.setLastName(lastName);
        return this;
    }

    public Author build() {
        return author;
    }
}