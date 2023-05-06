package br.org.soujava.jakarta.data.tck;

import java.util.Iterator;
import java.util.Objects;
import java.util.ServiceLoader;

/**
 * A book is a medium for recording information in the form of writing or images,
 * typically composed of many pages bound together and protected by a cover.
 */
public interface Book {

    /**
     * International Standard Book Number (ISBN) is a numeric
     * commercial book identifier that is intended to be unique.
     *
     * @return the Book's id.
     */
    String isdn();

    /**
     * The Book's title
     *
     * @return The Book's title
     */
    String title();

    /**
     * The bibliographical definition of an edition is all copies of
     * a book printed from substantially
     * the same setting of type, including all minor typographical variants.
     *
     * @return the book's edition
     */
    int edition();


    static Book of(String isbn, String title, int edition) {
        Objects.requireNonNull(isbn, "isbn is required");
        Objects.requireNonNull(title, "title is required");
        if (edition < 0) {
            throw new IllegalArgumentException("The edition is negative");
        }
        ServiceLoader<BookSupplier> serviceLoader = ServiceLoader.load(BookSupplier.class);
        final Iterator<BookSupplier> iterator = serviceLoader.iterator();
        if (iterator.hasNext()) {
            BookSupplier supplier = iterator.next();
            return supplier.apply(isbn, title, edition);
        }
        throw new IllegalStateException("No BookSupplier implementation found!");

    }
}
