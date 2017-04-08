package org.ran3ys.simple.csv;

import java.util.List;

/**
 * Created by ran3-ys on 2017/04/08.
 */
public abstract class Filter {
    private List<String> values;

    private Filter() {
    }

    public Filter(List<String> values) {
        this.values = values;
    }

    public List<String> getValues() {
        return values;
    }
}
