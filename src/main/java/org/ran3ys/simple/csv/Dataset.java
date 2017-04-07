package org.ran3ys.simple.csv;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ran3-ys on 2017/04/07.
 */
public class Dataset {
    private Header header;
    private List<Row> rows;
    private boolean useHeader;

    public Dataset(Header header, List<Row> rows) {
        this.header = header;
        this.rows = rows;
        this.useHeader = true;
    }

    public Dataset(List<Row> rows) {
        this.rows = rows;
        this.useHeader = false;
    }

    public Dataset filter(HeaderFilter headerFilter, RowFilter... rowFilters) {
        Header header = filterHeader(headerFilter);
        List<Row> rows = filterRow(headerFilter, rowFilters);

        return new Dataset(header, rows);
    }

    private Header filterHeader(HeaderFilter filter) {
        if (filter == null)
            return null;

        List<Integer> indexs = header.indexOf(filter);

        List<String> list = new ArrayList<String>();
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
        if (rowFilters == null)
            return null;

        return this.rows.stream().filter(row -> {
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
                (sb, str) -> sb.append(str + System.lineSeparator()),
                (sb1, sb2) -> sb1.append(sb2)).toString();

        return val;
    }
}
