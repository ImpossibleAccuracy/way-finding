package com.example.way;

import com.example.way.map.Loader;
import com.example.way.map.LoaderImpl;
import com.example.way.map.Map;
import com.example.way.models.Node;
import com.example.way.models.Way;
import org.junit.Assert;
import org.junit.Test;


public class HunterTest {
    private final String[] possibleMaps = {
            "map.txt",
            "map2.txt",
    };
    private final String[] impossibleMaps = {
            "map3.txt",
    };

    @Test
    public void testPossibleMaps() {
        Loader loader = new LoaderImpl();

        for (String mapName : possibleMaps) {
            Map map = loader.loadMap(mapName);
            Hunter hunter = new HunterImpl(map);

            Node start = map.findFirstNode(Map.CELL_START),
                    quit = map.findFirstNode(Map.CELL_QUIT);

            long d1 = System.currentTimeMillis();
            Way way = hunter.findWay(start, quit);
            long d2 = System.currentTimeMillis();

            Assert.assertNotNull(way);

            System.out.printf("Way length: %d. Work time: %dms.%n", way.size(), d2 - d1);
        }
    }

    @Test
    public void testImpossibleMaps() {
        Loader loader = new LoaderImpl();

        for (String mapName : impossibleMaps) {
            Map map = loader.loadMap(mapName);
            Hunter hunter = new HunterImpl(map);

            Node start = map.findFirstNode(Map.CELL_START),
                    quit = map.findFirstNode(Map.CELL_QUIT);

            long d1 = System.currentTimeMillis();
            Way way = hunter.findWay(start, quit);
            long d2 = System.currentTimeMillis();

            Assert.assertNull(way);

            System.out.printf("Work time: %dms.%n", d2 - d1);
        }
    }
}
