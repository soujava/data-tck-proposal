package br.org.soujava.jakarta.data.jnosql.tck;

import br.org.soujava.jakarta.data.jnosql.LibrarySupplier;

public class NoSQLLibrarySupplier implements LibrarySupplier<NoSQLBook, Library> {
    @Override
    public Library get() {
        return CDIContainer.INSTANCE.get(Library.class);
    }
}
