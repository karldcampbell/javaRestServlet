package kdc;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by kdc on 1/18/16.
 */
public class RestServletRouter {
    private HashMap<String, HashMap<String, BaseHandler>> routes = new HashMap<>();

    public void register(String route, String method, BaseHandler handler){
        method = method.toUpperCase();
        HashMap<String, BaseHandler> methods = routes.get(route);
        if(methods == null){
            methods = new HashMap<>();
            routes.put(route, methods);
        }
        methods.put(method, handler);
    }

    public BaseHandler route(String url, String method){
        method = method.toUpperCase();
        HashMap<String, BaseHandler> registeredMethods = routes.get(url);
        if(registeredMethods == null){
            return null;
        }
        return registeredMethods.get(method);
    }
}
