package kdc;

import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Created by kdc on 1/18/16.
 */
public class RestServletRouterUnitTest {

    @Test
    public void staticRoute(){
        RestServletRouter r = new RestServletRouter();

        BaseHandler h = ((req, resp) -> System.out.print(3));

        r.register("abc", "GET", h);

        assertSame(h, r.route("abc", "GET"));
        assertNull(r.route("abc", "POST"));
        assertNull(r.route("123", "GET"));

    }
}
