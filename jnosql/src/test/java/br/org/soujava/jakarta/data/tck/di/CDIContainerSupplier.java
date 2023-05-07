package br.org.soujava.jakarta.data.tck.di;

import br.org.soujava.jakarta.data.tck.LibrarySupplierPrducer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.util.stream.Stream;

public class CDIContainerSupplier implements ContainerSupplier {
    @Override
    public DIContainer apply(InjectExtension extension) {
        final SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        if (extension.disableDiscovery()) {
            initializer.disableDiscovery();
        }
        initializer.setClassLoader(Thread.currentThread().getContextClassLoader());
        initializer.addBeanClasses(extension.classes());
        initializer.enableDecorators(extension.decorators());
        initializer.enableInterceptors(extension.interceptors());
        initializer.selectAlternatives(extension.alternatives());
        initializer.selectAlternativeStereotypes(extension.alternativeStereotypes());
        initializer.addPackages(getPackages(extension.packages()));
        initializer.addPackages(true, getPackages(extension.recursivePackages()));
        initializer.addPackages(true, getPackages(new Class[]{LibrarySupplierPrducer.class}));
        System.setProperty("jnosql.document.database", "library");
        System.setProperty("jnosql.mongodb.host", DatabaseContainer.INSTANCE.getHost());
        return new CDIContainer(initializer.initialize());
    }

    private Package[] getPackages(Class<?>[] packages) {
        return Stream.of(packages).map(Class::getPackage).toArray(Package[]::new);
    }
}
