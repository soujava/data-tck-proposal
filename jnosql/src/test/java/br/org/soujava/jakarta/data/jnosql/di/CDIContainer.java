package br.org.soujava.jakarta.data.jnosql.di;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public enum CDIContainer {
    INSTANCE;

    private final SeContainer container;

    {
        System.setProperty("jnosql.document.database", "library");
        System.setProperty("jnosql.mongodb.host", Container.INSTANCE.getHost());
        this.container = SeContainerInitializer.newInstance().initialize();
    }

   public  <T> T get(Class<T> type) {
        return container.select(type).get();
    }
}
