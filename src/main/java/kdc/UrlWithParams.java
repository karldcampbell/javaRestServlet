package kdc;

import java.util.Map;

/**
 * Created by kdc on 1/30/16.
 */
public class UrlWithParams {
    public final Map<String, BaseHandler> methods;
    public final Map<String, String> params;

    public UrlWithParams(Map<String, BaseHandler> methods, Map<String, String> params) {
        this.methods = methods;
        this.params = params;
    }
}
