package kdc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by kdc on 1/30/16.
 */
public class RouteEntry<A> {
    private final BaseHandler<A> handler;
    private final BaseValidator<A> validator;

    public RouteEntry(BaseHandler<A> handler, BaseValidator validator) {
        this.handler = handler;
        this.validator = validator;
    }

    public void run(HttpServletRequest req, HttpServletResponse resp, Map<String, String> params) throws IllegalArgumentException{
        handler.handle(req, resp, validator.validate(req, params));
    }
}
