package br.org.soujava.jakarta.data.jnosql;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
@Target(TYPE)
@Retention(RUNTIME)
@ExtendWith(InjectJUnitExtension.class)
public @interface InjectExtension {
}
