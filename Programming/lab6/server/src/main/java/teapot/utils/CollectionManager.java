package teapot.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

/**
 * A manager class for handling a collection of comparable objects.
 *
 * @param <T> The type of objects stored in the collection, must be Comparable.
 *
 * @author Gleb Kiva
 */
public final class CollectionManager<T extends Comparable<T>> {
    private final HashSet<T> collection;
    private final Date creationDate;

    /**
     * Constructs a CollectionManager with an empty collection initialized and the
     * creation date set to the current date.
     */
    public CollectionManager() {
        collection = new HashSet<>();
        creationDate = new Date();
    }

    /**
     * Adds an object to the collection.
     *
     * @param o The object to add.
     */
    public void add(T o) {
        collection.add(o);
    }

    /**
     * Adds all objects from a list to the collection.
     *
     * @param l The list of objects to add.
     */
    public void addAll(List<T> l) {
        collection.addAll(l);
    }

    /**
     * Filters the collection based on a predicate, removing elements that do not
     * satisfy the predicate.
     *
     * @param p The predicate to filter by.
     */
    public void filter(Predicate<T> p) {
        collection.removeIf(p);
    }

    /**
     * Removes elements from the collection based on a predicate.
     *
     * @param p The predicate to use for removal.
     */
    public void remove(Predicate<T> p) {
        collection.removeIf(p);
    }

    /**
     * Clears the entire collection.
     */
    public void clear() {
        collection.clear();
    }

    /**
     * Retrieves a copy of the collection as a list.
     *
     * @return A list containing all elements of the collection.
     */
    public List<T> get() {
        return new ArrayList<>(collection);
    }

    /**
     * Retrieves a sorted list of elements from the collection.
     *
     * @return A sorted list of elements.
     */
    public List<T> getSorted() {
        List<T> collectionList = new ArrayList<>(collection);
        Collections.sort(collectionList);
        return collectionList;
    }

    /**
     * Searches for elements in the collection based on a predicate.
     *
     * @param p The predicate to use for searching.
     * @return A list of elements that match the predicate.
     */
    public List<T> search(Predicate<T> p) {
        return collection.stream().filter(p).toList();
    }

    /**
     * Searches for the first element in the collection that matches a predicate.
     *
     * @param p The predicate to use for searching.
     * @return The first matching element, or null if none is found.
     */
    public T searchFirst(Predicate<T> p) {
        return collection.stream().filter(p).findFirst().orElse(null);
    }

    /**
     * Retrieves the creation date of the collection.
     *
     * @return The creation date.
     */
    public Date getCreationDate() {
        return creationDate;
    }
}