package org.ran3ys.simple.csv;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by yoshika on 2017/04/09.
 */
public class CsvLoaderTest {

    private Path dir;
    private File file1;
    private File file2;

    @Before
    public void setUp() throws Exception {
        dir = Files.createTempDirectory(null);
        createTestFile();
    }

    private void createTestFile() throws Exception {
        file1 = new File(dir.toString(), "a.csv");
        FileWriter fw = new FileWriter(file1);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("a,b,c,d,e" + System.lineSeparator());
        bw.write("1,2,3,4,5" + System.lineSeparator());
        bw.write("6,7,8,9,10" + System.lineSeparator());
        bw.write(",,,,20" + System.lineSeparator());
        bw.flush();
        bw.close();

        file2 = new File(dir.toString(), "b.csv");
        fw = new FileWriter(file2);
        bw = new BufferedWriter(fw);

        bw.write("a,b,c,d,e" + System.lineSeparator());
        bw.write("21,22,23,24,25" + System.lineSeparator());
        bw.write("26,27,28,29,30" + System.lineSeparator());
        bw.flush();
        bw.close();
    }

    @After
    public void tearDown() throws Exception {
        File[] files = dir.toFile().listFiles();

        if (files != null) {
            Arrays.stream(files)
                    .forEach(file -> file.delete());
        }

        dir.toFile().delete();
    }

    @Test
    public void load() throws Exception {
//        Header header = new Header("a,b,c,d,e");
        DataSet dataSet = CsvLoader.load(file1.toPath(), null);

        assertEquals("a,b,c,d,e", dataSet.getHeader().toString());

        String expected = "a,b,c,d,e" + System.lineSeparator()
                + "1,2,3,4,5" + System.lineSeparator()
                + "6,7,8,9,10" + System.lineSeparator()
                + ",,,,20" + System.lineSeparator();

        assertEquals(expected, dataSet.toString());
    }

    @Test
    public void loadDir() throws Exception {
        DataSet dataSet = CsvLoader.loadDir(dir, null);

        assertEquals("a,b,c,d,e", dataSet.getHeader().toString());

        String expected = "a,b,c,d,e" + System.lineSeparator()
                + "1,2,3,4,5" + System.lineSeparator()
                + "6,7,8,9,10" + System.lineSeparator()
                + ",,,,20" + System.lineSeparator()
                + "21,22,23,24,25" + System.lineSeparator()
                + "26,27,28,29,30" + System.lineSeparator();

        assertEquals(expected, dataSet.toString());
    }

}