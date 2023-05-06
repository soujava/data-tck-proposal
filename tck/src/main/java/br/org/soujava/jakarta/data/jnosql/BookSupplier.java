package br.org.soujava.jakarta.data.jnosql;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * A supplier of Book.
 * It will create a implementation of {@link Book} with the respective annotation.
 */
public interface BookSupplier {
    /**
     * Creates Book instance using the custom annotation
     * @param isbn the isbn
     * @param title the book's title
     * @param edition the book's edition
     * @param author the book's author
     * @return a book instance using vendor annotation
     */
    Book apply(String isbn, String title, String author, int edition);

    /**
     * Create a book instance supplier
     *
     * @return a book's instance
     */
    static BookSupplier supplier() {
        ServiceLoader<BookSupplier> serviceLoader = ServiceLoader.load(BookSupplier.class);
        final Iterator<BookSupplier> iterator = serviceLoader.iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        throw new IllegalStateException("No BookSupplier implementation found!");
    }
}
