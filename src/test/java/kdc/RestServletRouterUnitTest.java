package kdc;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.*;

/**
 * Created by kdc on 1/18/16.
 */
public class RestServletRouterUnitTest {

    @Test
    public void staticRoute(){
        RestServletRouter r = new RestServletRouter();

        BaseHandler h = ((req, resp, p) -> System.out.print(3));

        r.register("/abc", "GET", h);

        assertSame(h, r.route("/abc", "GET").entry);
        assertSame(h, r.route("/abc", "GET").entry);
        assertNull(r.route("/abc", "POST"));
        assertNull(r.route("/123", "GET"));

    }

    @Test
    public void dynamicRoute_correctHandler(){
        RestServletRouter r = new RestServletRouter();

        BaseHandler h = ((req, resp, a) -> System.out.print(3));

        r.register("/rec/{id}", "GET", h);

        assertSame(h, r.route("/rec/123", "GET").entry);
        assertSame(h, r.route("/rec/hhg", "GET").entry);
        assertNull(r.route("/123", "GET"));
        assertNull(r.route("/rec/123", "POST"));
    }

    @Test
    public void dynamicRoute_extractsParams(){
        RestServletRouter r = new RestServletRouter();
         AtomicReference<String> extractedId = new AtomicReference<>();

        BaseHandler<String> h = ((req, resp, id) -> extractedId.set(id));

        r.register("/rec/{id}", "GET", h);


        RouteMatch routed = r.route("/rec/123", "GET");
        assertSame(h, routed.entry);
        assertEquals("123", routed.params.get("id"));


        routed = r.route("/rec/abcdde", "GET");
        assertSame(h, routed.entry);
        assertEquals("abcdde", routed.params.get("id"));
    }
}
