package br.org.soujava.jakarta.data.tck.di;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public enum DatabaseContainer {

    INSTANCE;

    private final GenericContainer<?> mongodb =
            new GenericContainer<>("mongo:latest")
                    .withExposedPorts(27017)
                    .waitingFor(Wait.defaultWaitStrategy());

    {
        mongodb.start();
    }

    String getHost() {
        return mongodb.getHost() + ":" + mongodb.getFirstMappedPort();
    }
}
