package br.org.soujava.jakarta.data.jnosql;

public class NoSQLLibrarySupplier implements LibrarySupplier<NoSQLBook, Library>{
    @Override
    public Library get() {
        return CDIContainer.INSTANCE.get(Library.class);
    }
}
