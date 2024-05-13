package teapot.builder.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

public class CollectionManager<T extends Comparable<T>> {
    private final HashSet<T> collection;
    private final Date creationDate;

    public CollectionManager() {
        collection = new HashSet<>();
        creationDate = new Date();
    }

    public void clear() {
        collection.clear();
    }

    public List<T> get() {
        return new ArrayList<>(collection);
    }

    public List<T> getSorted() {
        List<T> collectionList = new ArrayList<>(collection);
        Collections.sort(collectionList);
        return collectionList;
    }

    public List<T> search(Predicate<T> p) {
        return collection.stream().filter(p).toList();
    }

    public T searchFirst(Predicate<T> p) {
        return collection.stream().filter(p).findFirst().orElse(null);
    }

    public void filter(Predicate<T> p) {
        collection.removeIf(p);
    }

    public void add(T o) {
        collection.add(o);
    }

    public void addConditional(T o, Predicate<T> p) throws IllegalArgumentException {
        if (collection.stream().anyMatch(p)) {
            throw new IllegalArgumentException("Element satisfying the condition already exists");
        } else {
            collection.add(o);
        }
    }

    public void remove(Predicate<T> p) {
        collection.removeIf(p);
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
