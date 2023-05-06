package br.org.soujava.jakarta.data.tck;

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
     * @return a book instance using vendor annotation
     */
    Book apply(String isbn, String title, int edition);

    /**
     * Create a book instance
     *
     * @return a book's instance
     * @throws NullPointerException     when isbn or title is null
     * @throws IllegalArgumentException when edition is negative
     */
    static BookSupplier instance() {
        ServiceLoader<BookSupplier> serviceLoader = ServiceLoader.load(BookSupplier.class);
        final Iterator<BookSupplier> iterator = serviceLoader.iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        throw new IllegalStateException("No BookSupplier implementation found!");

    }
}
