package br.org.soujava.jakarta.data.tck;

/**
 * A book is a medium for recording information in the form of writing or images,
 * typically composed of many pages bound together and protected by a cover.
 *
 * @see BookSupplier to create book instance
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

    /**
     * The author's book
     * @return the book's author
     */
    String author();
}
