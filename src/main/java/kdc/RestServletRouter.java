package kdc;

import java.util.Collections;
import java.util.HashMap;

/**
 * Created by kdc on 1/18/16.
 */
public class RestServletRouter {
    private HashMap<String, HashMap<String, BaseHandler>> routes = new HashMap<>();

    public <A> void  register(String route, String method, BaseHandler<A> handler){
        method = method.toUpperCase();
        HashMap<String, BaseHandler> methods = routes.get(route);
        if(methods == null){
            methods = new HashMap<>();
            routes.put(route, methods);
        }
        methods.put(method, handler);
    }

    public RouteMatch route(String url, String method){
        method = method.toUpperCase();
        UrlWithParams matchedRoute = findRoute(url);
        if(matchedRoute == null || matchedRoute.methods.get(method) == null){
            return null;
        }
        return new RouteMatch(matchedRoute.methods.get(method), matchedRoute.params);
    }

    private UrlWithParams findRoute(String url){
        HashMap<String, BaseHandler> route = routes.get(url);
        if(route != null) return new UrlWithParams(route, Collections.emptyMap());

        for(String dynamicRoute : routes.keySet()){
            HashMap<String, String> routeParams = routesMatch(url, dynamicRoute);
            if(routeParams != null){
                return new UrlWithParams(routes.get(dynamicRoute), routeParams);
            }
        }
        return null;
    }

    private HashMap<String, String> routesMatch(String url, String dynamicRoute) {
        HashMap<String, String> params = new HashMap<>();
        String[] urlParts = url.split("/");
        String[] routeParts = dynamicRoute.split("/");

        if(urlParts.length != routeParts.length){
            return null;
        }

        for(int i=0; i < urlParts.length; i++){
            String p = isParam(routeParts[i]);
            if(p != null){
               // System.out.println(p + " : " + urlParts[i]);
               params.put(p, urlParts[i]);
                continue;
            } else if (routeParts[i].equals(urlParts[i])){
                continue;
            }else{
                return null;
            }
        }
        return params;
    }

    private String isParam(String part){
        if(part.isEmpty()) {
            return null;
        }
        else if (part.charAt(0) == '{' && part.charAt(part.length() - 1) == '}'){
            return part.substring(1,part.length()-1);
        } else {
            return null;
        }
    }
}
