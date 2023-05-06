package br.org.soujava.jakarta.data.tck;

import jakarta.data.repository.CrudRepository;

import java.util.function.Supplier;

/**
 * A supplier of a {@link CrudRepository} implementation
 * @param <B> the Book implementation where the vendor will give the annotations
 * @param <T> the CrudRepository implementation
 */
public interface LibrarySupplier<B extends Book, T extends CrudRepository<B, String>>
        extends Supplier<T> {
}
