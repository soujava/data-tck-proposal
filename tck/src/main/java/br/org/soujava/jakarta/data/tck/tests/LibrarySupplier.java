package br.org.soujava.jakarta.data.tck.tests;

import br.org.soujava.jakarta.data.tck.Book;
import jakarta.data.repository.CrudRepository;

import java.util.function.Supplier;

public interface LibrarySupplier {

    <T extends CrudRepository<? extends Book, String>> T get();
}
