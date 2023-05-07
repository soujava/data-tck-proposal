package br.org.soujava.jakarta.data.jnosql.di;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.util.AnnotationUtils;

import java.util.function.Consumer;

public class InjectJUnitExtension implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback  {

    private DIContainer container;
    private DIContext context;

    @Override
    public void beforeAll(final ExtensionContext extensionContext) {
        final InjectExtension config = AnnotationUtils.findAnnotation(extensionContext.getElement(), InjectExtension.class)
                .orElse(null);
        if (config == null) {
            return;
        }
        container = ContainerSupplier.supplier(config);
    }

    @Override
    public void afterAll(final ExtensionContext extensionContext) {
        if (container != null) {
            doClose(container);
            container = null;
        }
    }

    @Override
    public void beforeEach(final ExtensionContext extensionContext) {
        if (container == null) {
            return;
        }
        extensionContext.getTestInstance().ifPresent(container.inject(this));
    }

    @Override
    public void afterEach(final ExtensionContext extensionContext) {
        if (context != null) {
            context.release();
            context = null;
        }
    }

    public void setContext(DIContext context){
        this.context = context;
    }

    private void doClose(final DIContainer container) {
        container.close();
    }
}
