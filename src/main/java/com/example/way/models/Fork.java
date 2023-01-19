package com.example.way.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Fork {
    private Node top, right, down, left;

    public Fork() {
    }

    public Fork(Node top, Node right, Node down, Node left) {
        this.top = top;
        this.right = right;
        this.down = down;
        this.left = left;
    }

    public List<Node> getNodes() {
        List<Node> nodes = new ArrayList<>();

        if (top != null) nodes.add(top);
        if (right != null) nodes.add(right);
        if (down != null) nodes.add(down);
        if (left != null) nodes.add(left);

        return nodes;
    }

    public boolean isEmpty() {
        return top == null && right == null && down == null && left == null;
    }

    public Node getTop() {
        return top;
    }

    public void setTop(Node top) {
        this.top = top;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getDown() {
        return down;
    }

    public void setDown(Node down) {
        this.down = down;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fork fork = (Fork) o;

        if (!Objects.equals(top, fork.top)) return false;
        if (!Objects.equals(right, fork.right)) return false;
        if (!Objects.equals(down, fork.down)) return false;
        return Objects.equals(left, fork.left);
    }

    @Override
    public int hashCode() {
        int result = top != null ? top.hashCode() : 0;
        result = 31 * result + (right != null ? right.hashCode() : 0);
        result = 31 * result + (down != null ? down.hashCode() : 0);
        result = 31 * result + (left != null ? left.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Fork{" +
                "top=" + top +
                ", right=" + right +
                ", down=" + down +
                ", left=" + left +
                '}';
    }
}
