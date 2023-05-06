package br.org.soujava.jakarta.data.jnosql.tck;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.interceptor.Interceptor;
import org.eclipse.jnosql.communication.document.DocumentManager;
import org.eclipse.jnosql.databases.mongodb.communication.MongoDBDocumentManager;

import java.util.function.Supplier;

@ApplicationScoped
@Alternative
@Priority(Interceptor.Priority.APPLICATION)
public class ManagerSupplier implements Supplier<DocumentManager> {

    @Produces
    public DocumentManager get() {
        MongoDBDocumentManager manager = Container.INSTANCE.get("library");
        return manager;
    }

    public void close(@Disposes DocumentManager manager) {
        manager.close();
    }
}