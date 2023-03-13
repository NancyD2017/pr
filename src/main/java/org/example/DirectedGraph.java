package org.example;

import java.util.*;
import java.util.function.Predicate;

public class DirectedGraph {

    private Map<Integer, ArrayList<Map<Integer, Integer>>> nodes = new HashMap<>();

    static class Vertex {
        int value;
        Vertex(int value) {
            this.value = value;
        }
    }

    void addVertex(int value) {
        Vertex v = new Vertex(value);
        nodes.put(v.value, new ArrayList<>());
    }

    Integer getVertex(int value) {
        for (Integer k: nodes.keySet()) {
            if (k == value) {
                return k;
            }
        }
        return null;
    }
    void connectArc(int src, int dst, int weight) {
        Integer srcVertex = getVertex(src);
        Integer dstVertex = getVertex(dst);
        nodes.get(srcVertex).add(Map.of(dstVertex, weight));
    }
    void removeArc(int src, int dst) {
        Integer srcVertex = getVertex(src);
        Integer dstVertex = getVertex(dst);
        nodes.get(srcVertex).remove(dstVertex);
    }
    void renameVertex(int value, int valueN) {
        Integer v = new Vertex(valueN).value;
        nodes.put(v, nodes.get(getVertex(value)));
        nodes.remove(getVertex(value));
        System.out.println(nodes);
    }
    void reWeight(int src, int dst, int weightN) {
        System.out.println(nodes);
        Integer srcVertex = getVertex(src);
        Integer dstVertex = getVertex(dst);
        System.out.println(srcVertex + " " + dstVertex);
        System.out.println(nodes);
    }
}