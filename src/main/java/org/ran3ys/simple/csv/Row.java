package org.ran3ys.simple.csv;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ran3-ys on 2017/04/07.
 */
public class Row {
    List<String> values;

    public Row(List<String> values) {
        this.values = values;
    }

    public List<String> getValues() {
        return values;
    }

    public List<String> getValues(List<Integer> indexs) {
        List<String> list = new ArrayList<String>();
        for (int i : indexs) {
            list.add(this.values.get(i));
        }

        return list;
    }

    @Override
    public String toString() {
        return String.join(",", this.values);
    }
}
