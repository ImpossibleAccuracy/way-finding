package com.example.way;

import com.example.way.map.Loader;
import com.example.way.map.LoaderImpl;
import com.example.way.map.Map;
import com.example.way.models.Node;
import com.example.way.models.Way;

public class WayFindingApplication {
    public static void main(String[] args) {
        Loader loader = new LoaderImpl();
        Map map = loader.loadMap("map.txt");

        Node startNode = map.findFirstNode(Map.CELL_START);
        Node quitNode = map.findFirstNode(Map.CELL_QUIT);

        Hunter hunter = new HunterImpl(map);

        long d1 = System.currentTimeMillis();
        Way way = hunter.findWay(startNode, quitNode);
        long d2 = System.currentTimeMillis();

        if (way == null) {
            System.out.println("Way not found");
        } else {
            System.out.printf("Way found. Way length: %d. Work time: %dms.%n", way.size(), d2 - d1);
        }
    }
}
