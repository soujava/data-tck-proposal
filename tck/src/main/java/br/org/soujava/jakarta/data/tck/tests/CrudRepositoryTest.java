package br.org.soujava.jakarta.data.tck.tests;


import br.org.soujava.jakarta.data.tck.Book;
import br.org.soujava.jakarta.data.tck.di.InjectExtension;
import br.org.soujava.jakarta.data.tck.provider.BookProvider;
import jakarta.data.repository.CrudRepository;
import jakarta.inject.Inject;
import org.assertj.core.api.SoftAssertions;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

@DisplayName("The scenario cover the basic methods to CrudRepository")
@InjectExtension
public class CrudRepositoryTest {
    private static final Duration DURATION = Duration.ofSeconds(5);

    @Inject
    private LibrarySupplier supplier;

    @ParameterizedTest
    @ArgumentsSource(BookProvider.class)
    @DisplayName("Should insert when the database is not there")
    public void shouldSave(Book book) {
        CrudRepository<Book, String> repository = (CrudRepository<Book, String>) this.supplier.get();
        repository.deleteAll();
        repository.save(book);
        AtomicReference<Book> reference = new AtomicReference<>();
        //BASE
        Awaitility.await().atMost(DURATION)
                .until(() -> {
                    repository.findById(book.isbn()).ifPresent(reference::set);
                    return reference.get() != null;
                });
        Book updated = reference.get();
        SoftAssertions.assertSoftly(soft -> {
            soft.assertThat(book.isbn()).isEqualTo(updated.isbn());
            soft.assertThat(book.author()).isEqualTo(updated.author());
            soft.assertThat(book.title()).isEqualTo(updated.title());
        });
    }
}
