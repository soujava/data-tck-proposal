package br.org.soujava.jakarta.data.jnosql.tck;

import br.org.soujava.jakarta.data.jnosql.Book;
import br.org.soujava.jakarta.data.jnosql.BookSupplier;

import java.util.Objects;

public class NoSQLBookSupplier implements BookSupplier {
    @Override
    public Book apply(String isbn, String title, String author, int edition) {
        Objects.requireNonNull(isbn, "isbn is required");
        Objects.requireNonNull(title, "title is required");
        Objects.requireNonNull(author, "author is required");
        if (edition < 0) {
            throw new IllegalArgumentException("the edition cannot be negative");
        }
        return new NoSQLBook(isbn, title, edition, author);
    }
}
