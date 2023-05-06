package br.org.soujava.jakarta.data.tck;

public interface BookSupplier {
    Book of(String isbn, String title, int edition);
}
