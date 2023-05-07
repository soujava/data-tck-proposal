package br.org.soujava.jakarta.data.jnosql.di;

import jakarta.enterprise.inject.se.SeContainer;

import java.util.function.Consumer;

class CDIContainer implements DIContainer {
    private final SeContainer container;

    CDIContainer(SeContainer container) {
        this.container = container;
    }

    @Override
    public Consumer<Object> inject(InjectJUnitExtension injectJUnitExtension) {
        return null;
    }

    @Override
    public void close() {
        this.container.close();
    }
}
