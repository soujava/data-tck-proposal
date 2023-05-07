package br.org.soujava.jakarta.data.tck.di;

import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.spi.AnnotatedType;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.enterprise.inject.spi.InjectionTarget;
import jakarta.enterprise.inject.spi.InjectionTargetFactory;

import java.util.function.Consumer;

class CDIContainer implements DIContainer {
    private final SeContainer container;

    CDIContainer(SeContainer container) {
        this.container = container;
    }

    @Override
    public Consumer<Object> inject(InjectJUnitExtension extension) {
        return instance ->  {
            final BeanManager manager = container.getBeanManager();
            final AnnotatedType<?> annotatedType = manager.createAnnotatedType(instance.getClass());
            InjectionTargetFactory<?> factory = manager.getInjectionTargetFactory(annotatedType);
            final InjectionTarget injectionTarget = factory.createInjectionTarget(null);
            CreationalContext<Object> context = manager.createCreationalContext(null);
            extension.setContext(CDIContext.of(context));
            injectionTarget.inject(instance, context);
        };
    }

    @Override
    public void close() {
        this.container.close();
    }
}
