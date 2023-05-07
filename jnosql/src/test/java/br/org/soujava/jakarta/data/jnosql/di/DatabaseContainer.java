package br.org.soujava.jakarta.data.jnosql.di;

import org.eclipse.jnosql.communication.Settings;
import org.eclipse.jnosql.databases.mongodb.communication.MongoDBDocumentConfiguration;
import org.eclipse.jnosql.databases.mongodb.communication.MongoDBDocumentConfigurations;
import org.eclipse.jnosql.databases.mongodb.communication.MongoDBDocumentManager;
import org.eclipse.jnosql.databases.mongodb.communication.MongoDBDocumentManagerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.util.HashMap;
import java.util.Map;

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
