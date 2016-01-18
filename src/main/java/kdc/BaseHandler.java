package kdc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kdc on 1/18/16.
 */

@FunctionalInterface
public interface BaseHandler {
    public default void handle(HttpServletRequest req, HttpServletResponse resp) {
        runRoute(req, resp);
    }

    public void runRoute(HttpServletRequest req, HttpServletResponse resp);
}
