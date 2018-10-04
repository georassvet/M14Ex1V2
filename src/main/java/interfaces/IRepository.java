package main.java.interfaces;

import java.util.Collection;

public interface IRepository<T,K> {
    T add(T t);
    void update(T t);
    void delete(K id);
    T get(K id);
    Collection<T> getAll();
}
