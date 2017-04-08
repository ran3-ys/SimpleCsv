package org.ran3ys.simple.csv;

import org.junit.Test;
import org.ran3ys.simple.csv.filter.HeaderFilter;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ran3-ys on 2017/04/08.
 */
public class HeaderTest {

    @Test
    public void testToString() {
        List<String> names = Arrays.asList("a","b","c");

        Header header = new Header(names);

        assertEquals("a,b,c", header.toString());
    }

    @Test
    public void testIndexOf() {
        List<String> names = Arrays.asList("a","b","c");

        Header header = new Header(names);

        assertEquals(1, header.indexOf("b"));
    }

    @Test
    public void indexOf() throws Exception {
        List<String> names = Arrays.asList("a", "b", "c");

        Header header = new Header(names);
        HeaderFilter filter = new HeaderFilter(Arrays.asList("a", "c"));
        assertEquals(Arrays.asList(0, 2), header.indexOf(filter));

    }
}