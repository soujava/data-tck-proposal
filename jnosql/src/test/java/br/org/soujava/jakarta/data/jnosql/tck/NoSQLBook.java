package br.org.soujava.jakarta.data.jnosql.tck;


import br.org.soujava.jakarta.data.jnosql.Book;
import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

import java.util.Objects;

@Entity
public record NoSQLBook(@Id String isbn,
                        @Column("title") String title,
                        @Column("edition") int edition,
                        @Column("author") String author) implements Book {

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NoSQLBook noSQLBook = (NoSQLBook) o;
        return Objects.equals(isbn, noSQLBook.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isbn);
    }
}
