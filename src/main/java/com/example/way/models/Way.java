package com.example.way.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Way {
    private static final AtomicInteger atomicInteger = new AtomicInteger();

    private final int id;

    private final List<Node> history, reachable;

    public Way() {
        this.reachable = new ArrayList<>();
        this.history = new ArrayList<>();

        this.id = atomicInteger.incrementAndGet();
    }

    public Way(Way way) {
        this.reachable = new ArrayList<>();
        this.history = new ArrayList<>(way.history);

        this.id = atomicInteger.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public int size() {
        return history.size() - 1;
    }

    public void addHistory(Node node) {
        this.history.add(node);
    }

    public void removeHistory(Node node) {
        this.history.remove(node);
    }

    public boolean checkHistory(Node node) {
        return this.history.contains(node);
    }

    public void addReachable(Node node) {
        this.reachable.add(node);
    }

    public void removeReachable(Node node) {
        this.reachable.remove(node);
    }

    public Node getReachable(int i) {
        return this.reachable.get(i);
    }

    public boolean hasReachable() {
        return !this.reachable.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Way way = (Way) o;

        if (id != way.id) return false;
        if (!Objects.equals(history, way.history)) return false;
        return Objects.equals(reachable, way.reachable);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (history != null ? history.hashCode() : 0);
        result = 31 * result + (reachable != null ? reachable.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Way{" +
                "id=" + id +
                ", history=" + history +
                '}';
    }
}
