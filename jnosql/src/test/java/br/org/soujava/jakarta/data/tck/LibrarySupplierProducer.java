package br.org.soujava.jakarta.data.tck;


import br.org.soujava.jakarta.data.tck.tests.LibrarySupplier;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class LibrarySupplierProducer implements LibrarySupplier{

    @Inject
    private Library library;

    @Override
    public Library get() {
        return library;
    }
}
