package br.org.soujava.jakarta.data.jnosql.provider;

import br.org.soujava.jakarta.data.jnosql.BookSupplier;
import com.github.javafaker.Book;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.UUID;
import java.util.stream.Stream;

public class BookProvider implements ArgumentsProvider {

    private static final Faker FAKER = new Faker();

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context)
            throws Exception {

        br.org.soujava.jakarta.data.jnosql.Book entity = getBook();
        return Stream.of(Arguments.of(entity));
    }

    private static br.org.soujava.jakarta.data.jnosql.Book getBook() {
        Book book = FAKER.book();
        BookSupplier supplier = BookSupplier.supplier();
        String isbn = UUID.randomUUID().toString();
        String title = book.title();
        String author = book.author();
        br.org.soujava.jakarta.data.jnosql.Book entity = supplier.apply(isbn, title, author, 1);
        return entity;
    }
}
