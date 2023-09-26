package notes.core.application.interfaces;

import java.util.Collection;

public interface Editor<T, TId> {

    void add(T item);

    void edit(T item);

    void remove(T item);

    T getById(TId id);

    Collection<T> getAll();
}
