package br.org.soujava.jakarta.data.tck.di;

import java.util.function.Consumer;

public interface DIContainer {

    Consumer<Object> inject(InjectJUnitExtension injectJUnitExtension);
    void close();
}
