package main.java.repository;

import java.util.Collection;

public interface IRepository<T,K> {
    T save(T t);
    void update(T t);
    void delete(K id);
    T getById(K id);
    Collection<T> getAll();
}
