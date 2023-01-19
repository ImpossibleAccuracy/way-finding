package com.example.way.map;

import java.io.*;

public class LoaderImpl implements Loader {
    public Map loadMap(String mapFileName) {
        String[] data = loadMapResource(mapFileName).split("\n");
        int[][] map = parseResource(data);

        return new Map(map);
    }

    private int[][] parseResource(String[] data) {
        final int length = data.length;

        int[][] map = new int[length][];

        for (int i = 0; i < length; i++) {
            String[] line = data[i].split(" ");
            final int lineLength = line.length;

            map[i] = new int[lineLength];
            for (int j = 0; j < lineLength; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        return map;
    }

    private String loadMapResource(String mapFileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream in = classLoader.getResourceAsStream(mapFileName);

        if (in == null) {
            throw new NullPointerException(
                    String.format("Missing resource file: %s", mapFileName));
        }

        StringBuilder builder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader(in))) {
            int c;
            while ((c = reader.read()) != -1) {
                char s = (char) c;
                if (s != '\r') {
                    builder.append(s);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return builder.toString();
    }
}
