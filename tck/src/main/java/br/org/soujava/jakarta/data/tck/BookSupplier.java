package br.org.soujava.jakarta.data.tck;

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
}
