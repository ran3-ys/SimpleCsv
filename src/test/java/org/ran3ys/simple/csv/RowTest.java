package org.ran3ys.simple.csv;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ran3-ys on 2017/04/08.
 */
public class RowTest {
    @Test
    public void testToString() throws Exception {
        List<String> values = Arrays.asList("a","b","c");
        Row row = new Row(values);

        assertEquals("a,b,c", row.toString());
    }

}