package br.org.soujava.jakarta.data.tck;

import com.github.javafaker.Book;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class BookProvider implements ArgumentsProvider {

    private final Faker faker = new Faker();

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context)
            throws Exception {

        Book book = faker.book();
        BookSupplier supplier = BookSupplier.supplier();
        String isbn = UUID.randomUUID().toString();
        String title = book.title();
        String author = book.author();
        return Stream.of(Arguments.of(supplier.apply(isbn, title, author, 1),
                LibrarySupplier.supplier()));
    }
}
