package br.org.soujava.jakarta.data.jnosql;


import jakarta.data.repository.CrudRepository;
import org.assertj.core.api.SoftAssertions;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.InjectMocks;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

@DisplayName("The scenario cover the basic methods to CrudRepository")
public class CrudRepositoryTest {
    private static final Duration DURATION = Duration.ofSeconds(5);


    private CrudRepository<Book, String> repository;
    @ParameterizedTest
    @ArgumentsSource(BookProvider.class)
    @DisplayName("Should insert when the database is not there")
    public void shouldSave(Book book) {
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
