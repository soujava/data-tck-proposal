package br.org.soujava.jakarta.data.tck;

import jakarta.data.repository.CrudRepository;

import java.util.ServiceLoader;
import java.util.function.Supplier;

/**
 * A supplier of a {@link CrudRepository} implementation
 * @param <B> the Book implementation where the vendor will give the annotations
 * @param <T> the CrudRepository implementation
 */
public interface LibrarySupplier<B extends Book, T extends CrudRepository<B, String>>
        extends Supplier<T> {

    /**
     * Create a LibrarySupplier instance supplier
     *
     * @return a LibrarySupplier's instance
     */
    static <B extends Book, T extends CrudRepository<B, String>> T supplier() {
        var serviceLoader = ServiceLoader.load(LibrarySupplier.class);
        var iterator = serviceLoader.iterator();
        if (iterator.hasNext()) {
            return (T) iterator.next().get();
        }
        throw new IllegalStateException("No LibrarySupplier implementation found!");
    }
}
