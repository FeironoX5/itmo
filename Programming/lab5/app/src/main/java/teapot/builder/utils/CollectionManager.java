package teapot.builder.utils;

import java.util.HashSet;

import teapot.builder.Route;

public final class CollectionManager {
    public static final CollectionManager instance = new CollectionManager();
    private final HashSet<Route> routes;

    private CollectionManager() {
        routes = new HashSet<>();
    }

    public HashSet<Route> getRoutes() {
        return routes;
    }

}
