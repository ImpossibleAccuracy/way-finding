package com.example.way;

import com.example.way.map.Map;
import com.example.way.models.Node;
import com.example.way.models.Way;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HunterImpl implements Hunter {
    private Map map;

    public HunterImpl() {

    }

    public HunterImpl(Map map) {
        this.map = map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    @Override
    public Way findWay(Node start, Node quit) {
        List<Way> ways = findAllWays(start, quit);
        if (ways.size() == 0) {
            return null;
        }

        ways.sort(Comparator.comparingInt(Way::size));
        return ways.get(0);
    }

    @Override
    public List<Way> findAllWays(Node start, Node quit) {
        Way startWay = new Way();
        startWay.addReachable(start);

        List<Way> ways = new ArrayList<>();

        List<Way> queue = new ArrayList<>();
        queue.add(startWay);

        while (!queue.isEmpty()) {
            Way way = queue.get(0);

            while (way.hasReachable()) {
                Node reachable = way.getReachable(0);
                way.addHistory(reachable);

                if (reachable.equals(quit)) {
                    ways.add(way);
                    break;
                }

                List<Node> fork = map
                        .findNearbyNodes(reachable)
                        .getNodes()
                        .stream()
                        .filter(n -> !way.checkHistory(n))
                        .toList();

                if (fork.size() == 1) {
                    way.addReachable(fork.get(0));
                } else if (fork.size() > 1) {
                    for (Node node : fork) {
                        Way newWay = new Way(way);
                        newWay.addReachable(node);
                        queue.add(newWay);
                    }
                    break;
                }

                way.removeReachable(reachable);
            }

            queue.remove(way);
        }

        return ways;
    }
}
