package kdc;

import java.util.Map;

/**
 * Created by kdc on 1/30/16.
 */
public class RouteMatch {
    public final BaseHandler entry;

    public RouteMatch(BaseHandler entry, Map<String, String> params) {
        this.entry = entry;
        this.params = params;
    }

    public final Map<String, String> params;
}
