package org.avenga.models.books;

public class BookBuilder {

    private Book book;

    public BookBuilder() {
        book = new Book();
    }

    public BookBuilder withId(Integer id) {
        book.setId(id);
        return this;
    }

    public BookBuilder withTitle(String title) {
        book.setTitle(title);
        return this;
    }

    public BookBuilder withDescription(String description) {
        book.setDescription(description);
        return this;
    }

    public BookBuilder withPageCount(int pageCount) {
        book.setPageCount(pageCount);
        return this;
    }

    public BookBuilder withExcerpt(String excerpt) {
        book.setExcerpt(excerpt);
        return this;
    }

    public BookBuilder withPublishDate(String date) {
        book.setPublishDate(date);
        return this;
    }

    public Book build() {
        return book;
    }
}