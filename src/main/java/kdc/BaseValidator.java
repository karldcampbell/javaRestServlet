package kdc;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by kdc on 1/30/16.
 */
@FunctionalInterface
public interface BaseValidator<A> {
    public A validate(HttpServletRequest req, Map<String, String> params) throws IllegalArgumentException;
}
