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

    public MongoDBDocumentManager get(String database) {
        Settings settings = getSettings();
        MongoDBDocumentConfiguration configuration = new MongoDBDocumentConfiguration();
        MongoDBDocumentManagerFactory factory = configuration.apply(settings);
        return factory.apply(database);
    }


    private Settings getSettings() {
        Map<String,Object> settings = new HashMap<>();
        settings.put(MongoDBDocumentConfigurations.HOST.get()+".1", getHost());
        return Settings.of(settings);
    }

    String getHost() {
        return mongodb.getHost() + ":" + mongodb.getFirstMappedPort();
    }
}
