package br.org.soujava.jakarta.data.jnosql.tck;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface Library extends CrudRepository<NoSQLBook, String> {
}
