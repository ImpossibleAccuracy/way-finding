package com.example.way;

import com.example.way.models.Node;
import com.example.way.models.Way;

import java.util.List;

public interface Hunter {
    Way findWay(Node start, Node quit);

    List<Way> findAllWays(Node start, Node quit);
}
