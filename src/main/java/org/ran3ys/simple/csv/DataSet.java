package org.ran3ys.simple.csv;

import org.ran3ys.simple.csv.filter.HeaderFilter;
import org.ran3ys.simple.csv.filter.RowFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ran3-ys on 2017/04/07.
 */
public class DataSet {
    private Header header;
    private List<Row> rows;

    DataSet(){
    }

    DataSet(Header header, List<Row> rows) {
        this.header = header;
        this.rows = rows;
    }

    DataSet filter(HeaderFilter headerFilter, RowFilter... rowFilters) {
        Header header = filterHeader(headerFilter);
        List<Row> rows = filterRow(headerFilter, rowFilters);

        return new DataSet(header, rows);
    }

    public Header getHeader() {
        return header;
    }

    public List<Row> getRows() {
        return rows;
    }

    private Header filterHeader(HeaderFilter filter) {
        if (filter == null)
            return this.header;

        List<Integer> indexs = header.indexOf(filter);

        List<String> list = new ArrayList<>();
        for (int idx : indexs) {
            list.add(header.getNames().get(idx));
        }

        if (list.isEmpty()) {
            return null;
        } else {
            return new Header(list);
        }
    }

    private List<Row> filterRow(HeaderFilter headerFilter, RowFilter... rowFilters) {
        return this.rows.stream().filter(row -> {

            if (headerFilter != null &&
                    this.header.indexOf(headerFilter).isEmpty())
                return false;

            if (rowFilters.length == 0)
                return true;

            for (RowFilter filter : rowFilters) {
                int idx = this.header.indexOf(filter.getHeaderName());
                String value = row.getValues().get(idx);

                if (filter.getValues().contains(value))
                    return true;
            }

            return false;
        }).map(row -> {
            if (headerFilter == null)
                return row;

            return new Row(row.getValues(this.header.indexOf(headerFilter)));
        }).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        String val = "";
        if (header != null)
            val += header.toString() + System.lineSeparator();

        val += rows.stream().collect(StringBuilder::new,
                (sb, str) -> sb.append(str).append(System.lineSeparator()),
                StringBuilder::append).toString();

        return val;
    }
}
