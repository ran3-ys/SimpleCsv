package org.ran3ys.simple.csv;

import org.junit.Test;
import org.ran3ys.simple.csv.filter.HeaderFilter;
import org.ran3ys.simple.csv.filter.RowFilter;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by ran3-ys on 2017/04/08.
 */
public class DataSetTest {

    public DataSet simpleData() {
        Header header = new Header(Arrays.asList("a", "b", "c"));
        Row row1 = new Row(Arrays.asList("1", "2", "3"));
        Row row2 = new Row(Arrays.asList("4", "5", "6"));

        return new DataSet(header, Arrays.asList(row1, row2));
    }

    @Test
    public void filter() throws Exception {
        DataSet dataSet = simpleData();

        HeaderFilter headerFilter = new HeaderFilter(Arrays.asList("a", "c"));
        RowFilter rowFilter = new RowFilter("a", Arrays.asList("1"));

        DataSet filtered = dataSet.filter(headerFilter, rowFilter);

        assertNotNull(filtered);

        String expected = "a,c" + System.lineSeparator()
                + "1,3" + System.lineSeparator();
        assertEquals(expected, filtered.toString());
    }

    @Test
    public void headerFilterNoResult() throws Exception {
        DataSet dataSet = simpleData();
        HeaderFilter headerFilter = new HeaderFilter(Arrays.asList("A"));

        DataSet filtered = dataSet.filter(headerFilter);
        assertNotNull(filtered);
        assertEquals(0, filtered.getRows().size());
    }

    @Test
    public void filterHeaderFilterOnly() throws Exception {
        DataSet dataSet = simpleData();
        HeaderFilter headerFilter = new HeaderFilter(Arrays.asList("b","c"));

        DataSet filtered = dataSet.filter(headerFilter);

        assertNotNull(filtered);

        String expected = "b,c" + System.lineSeparator()
                + "2,3" + System.lineSeparator()
                + "5,6" + System.lineSeparator();

        assertEquals(expected, filtered.toString());
    }

    @Test
    public void filterRowFilterOnly() throws Exception {
        DataSet dataSet = simpleData();

        RowFilter rowFilter = new RowFilter("b", Arrays.asList("5"));

        DataSet filtered = dataSet.filter(null, rowFilter);

        assertNotNull(filtered);

        String expected = "a,b,c" + System.lineSeparator()
                + "4,5,6" + System.lineSeparator();
        assertEquals(expected, filtered.toString());

    }

    @Test
    public void testToString() throws Exception {
        DataSet dataSet = simpleData();

        String expected = "a,b,c" + System.lineSeparator()
                + "1,2,3" + System.lineSeparator()
                + "4,5,6" + System.lineSeparator();

        assertEquals(expected, dataSet.toString());
    }

}