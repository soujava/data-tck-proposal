package br.org.soujava.jakarta.data.tck;

import com.github.javafaker.Book;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class BookProvider implements ArgumentsProvider {

    private final Faker faker = new Faker();

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context)
            throws Exception {

        Book book = faker.book();
        book.
        return Stream.of(Arguments.of());
    }
}
