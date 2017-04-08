package org.ran3ys.simple.csv;

import org.ran3ys.simple.csv.filter.HeaderFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ran3-ys on 2017/04/07.
 */
public class Header {
    private List<String> names;

    public Header(List<String> names) {
        this.names = names;
    }

    public List<String> getNames() {
        return names;
    }

    public int indexOf(String name) {
        return this.names.indexOf(name);
    }

    public List<Integer> indexOf(HeaderFilter headerFilter) {
        List<Integer> list = new ArrayList<Integer>();
        for (String filterName : headerFilter.getValues()) {
            int idx = names.indexOf(filterName);
            if (idx >= 0)
                list.add(idx);
        }

        return list;
    }

    @Override
    public String toString() {
        return String.join(",", this.names);
    }
}
