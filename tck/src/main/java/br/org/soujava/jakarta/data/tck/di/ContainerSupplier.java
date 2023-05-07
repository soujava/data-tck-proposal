package br.org.soujava.jakarta.data.tck.di;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.function.Function;

public interface ContainerSupplier extends Function<InjectExtension, DIContainer> {


    static DIContainer supplier(InjectExtension extension) {
        ServiceLoader<ContainerSupplier> serviceLoader = ServiceLoader.load(ContainerSupplier.class);
        final Iterator<ContainerSupplier> iterator = serviceLoader.iterator();
        if (iterator.hasNext()) {
            return iterator.next().apply(extension);
        }
        throw new IllegalStateException("No ContainerSupplier implementation found!");
    }

}
