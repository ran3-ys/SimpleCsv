package org.ran3ys.simple.csv;

import java.util.List;

/**
 * Created by ran3-ys on 2017/04/08.
 */
public class RowFilter extends Filter {
    private String headerName;

    public String getHeaderName() {
        return headerName;
    }

    public RowFilter(String headerName, List<String> values) {
        super(values);
        this.headerName = headerName;
    }
}
