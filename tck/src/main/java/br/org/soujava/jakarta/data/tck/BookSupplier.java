package br.org.soujava.jakarta.data.tck;

public interface BookSupplier {
    Book apply(String isbn, String title, int edition);
}
