package org.lld.bestmatching;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class WordsLoader {
    public static List<String> load(String location) {
        List<String> output = new ArrayList<>();
        try {
            InputStream inputStream = WordsLoader.class.getClassLoader()
                    .getResourceAsStream(location);
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            for (String line; (line = reader.readLine()) != null;) {
                output.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}
