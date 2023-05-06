package br.org.soujava.jakarta.data.tck;


import jakarta.data.repository.CrudRepository;
import org.assertj.core.api.SoftAssertions;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

@DisplayName("The scenario cover the basic methods to CrudRepository")
public class CrudRepositoryTest {
    private static final Duration DURATION = Duration.ofSeconds(5);

    @ParameterizedTest
    @ArgumentsSource(BookProvider.class)
    @DisplayName("Should insert when the database is not there")
    public void shouldSave(Book book, CrudRepository<Book, String> repository) {
        repository.deleteAll();
        repository.save(book);
        AtomicReference<Book> reference = new AtomicReference<>();
        //BASE
        Awaitility.await().atMost(DURATION)
                .until(() -> {
                    repository.findById(book.isdn()).ifPresent(reference::set);
                    return reference.get() != null;
                });
        Book updated = reference.get();
        SoftAssertions.assertSoftly(soft -> {
            soft.assertThat(book.isdn()).isEqualTo(updated.isdn());
            soft.assertThat(book.author()).isEqualTo(updated.author());
            soft.assertThat(book.title()).isEqualTo(updated.title());
        });
    }
}
