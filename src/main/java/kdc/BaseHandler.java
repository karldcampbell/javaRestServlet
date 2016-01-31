package kdc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kdc on 1/18/16.
 */

@FunctionalInterface
public interface BaseHandler<A> {
    public default void handle(HttpServletRequest req, HttpServletResponse resp, A a) {
        runRoute(req, resp, a);
    }

    public void runRoute(HttpServletRequest req, HttpServletResponse resp, A a);
}
