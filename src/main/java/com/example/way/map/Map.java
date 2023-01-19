package com.example.way.map;

import com.example.way.models.Fork;
import com.example.way.models.Node;

public class Map {
    public static final int CELL_WALL = 0;
    public static final int CELL_HALL = 1;
    public static final int CELL_START = 2;
    public static final int CELL_QUIT = 3;

    private final int[][] data;

    public Map(int[][] data) {
        this.data = data;
    }

    public int[][] getData() {
        return data;
    }

    public boolean isEmpty(int x, int y) {
        return data[y][x] != CELL_WALL;
    }

    public Fork findNearbyNodes(Node node) {
        int x = node.getX(),
                y = node.getY();

        Fork fork = new Fork();
        // Top
        if (y - 1 >= 0 && isEmpty(x, y - 1)) {
            fork.setTop(new Node(x, y - 1));
        }
        // Bottom
        if (y + 1 < data.length && isEmpty(x, y + 1)) {
            fork.setDown(new Node(x, y + 1));
        }

        // Left
        if (x - 1 >= 0 && isEmpty(x - 1, y)) {
            fork.setLeft(new Node(x - 1, y));
        }
        // Right
        if (x + 1 < data[0].length && isEmpty(x + 1, y)) {
            fork.setRight(new Node(x + 1, y));
        }

        return fork;
    }

    public Node findFirstNode(int type) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] == type) {
                    return new Node(j, i);
                }
            }
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Map {\n");

        for (int i = 0; i < data.length; i++) {
            str.append(String.format("Line (%d)|", i + 1));

            int[] line = data[i];
            for (int cell : line) {
                switch (cell) {
                    case 0 -> str.append("■");
                    case 1 -> str.append(" ");
                    case 2 -> str.append("S");
                    case 3 -> str.append("Q");
                }
            }
            str.append("|\n");
        }

        str.append("}");
        return str.toString();
    }
}
