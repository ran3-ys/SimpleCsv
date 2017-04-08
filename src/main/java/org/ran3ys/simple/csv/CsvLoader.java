package org.ran3ys.simple.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yoshika on 2017/04/08.
 */
public class CsvLoader {

    private CsvLoader() {
    }

    public static DataSet load(Path filePath, Header header) throws Exception {

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {

            final String firstLine = reader.readLine();
            if (firstLine == null)
                return new DataSet();

            Header _header = header;
            List<Row> _rows = new ArrayList<>();

            final String[] cols = firstLine.split(",\\s*");
            if (_header == null) {
                _header = new Header(Arrays.asList(cols));
            } else {
                _rows.add(new Row(Arrays.asList(cols)));
            }

            reader.lines()
                    .forEach(line -> _rows.add(new Row(Arrays.asList(line.split(",\\s*")))));

            return new DataSet(_header, _rows);

        } catch (IOException e) {
            throw e;
        }
    }

    public static DataSet loadDir(Path path, Header header) throws Exception {

        DataSet dataSet = null;
        for (File file : path.toFile().listFiles()) {
            if (dataSet == null) {
                dataSet = load(file.toPath(), header);
            } else {
                dataSet.join(load(file.toPath(), header));
            }
        }

        return dataSet;
    }
}
