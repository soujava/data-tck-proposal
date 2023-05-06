package br.org.soujava.jakarta.data.jnosql;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public enum CDIContainer {
    INSTANCE;

    private final SeContainer container;

    {
        this.container = SeContainerInitializer.newInstance().initialize();
    }

    <T> T get(Class<T> type) {
        return container.select(type).get();
    }
}
