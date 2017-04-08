package org.ran3ys.simple.csv;

import org.junit.Test;

import javax.xml.crypto.Data;
import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by ran3-ys on 2017/04/08.
 */
public class DatasetTest {

    public Dataset simpleData() {
        Header header = new Header(Arrays.asList("a", "b", "c"));
        Row row1 = new Row(Arrays.asList("1", "2", "3"));
        Row row2 = new Row(Arrays.asList("4", "5", "6"));

        return new Dataset(header, Arrays.asList(row1, row2));
    }

    @Test
    public void filter() throws Exception {
        Dataset dataset = simpleData();

        HeaderFilter headerFilter = new HeaderFilter(Arrays.asList("a", "c"));
        RowFilter rowFilter = new RowFilter("a", Arrays.asList("1"));

        Dataset filtered = dataset.filter(headerFilter, rowFilter);

        assertNotNull(filtered);

        String expected = "a,c" + System.lineSeparator()
                + "1,3" + System.lineSeparator();
        assertEquals(expected, filtered.toString());
    }

    @Test
    public void filterHeaderFilterOnly() throws Exception {
        Dataset dataset = simpleData();
        HeaderFilter headerFilter = new HeaderFilter(Arrays.asList("b","c"));

        Dataset filtered = dataset.filter(headerFilter);

        assertNotNull(filtered);

        String expected = "b,c" + System.lineSeparator()
                + "2,3" + System.lineSeparator()
                + "5,6" + System.lineSeparator();

        assertEquals(expected, filtered.toString());
    }

    @Test
    public void filterRowFilterOnly() throws Exception {
        Dataset dataset = simpleData();

        RowFilter rowFilter = new RowFilter("b", Arrays.asList("5"));

        Dataset filtered = dataset.filter(null, rowFilter);

        assertNotNull(filtered);

        String expected = "a,b,c" + System.lineSeparator()
                + "4,5,6" + System.lineSeparator();
        assertEquals(expected, filtered.toString());

    }

    @Test
    public void testToString() throws Exception {
        Dataset dataset = simpleData();

        String expected = "a,b,c" + System.lineSeparator()
                + "1,2,3" + System.lineSeparator()
                + "4,5,6" + System.lineSeparator();

        assertEquals(expected, dataset.toString());
    }

}