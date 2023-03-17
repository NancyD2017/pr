package org.example;

import java.util.*;

public class DirectedGraph {

    private Map<Vertex, List<Arc>> nodes = new HashMap<>();

    List<Arc> getListArc(int src){
        if (nodes.get(getVertex(src)).isEmpty()) return null;
        return nodes.get(getVertex(src));
    }
        class Arc {
        Vertex dst;
        int weight;
        Arc(Vertex dst, int weight) {
            this.dst = dst;
            this.weight = weight;
        }
    }

    class Vertex {
        int value;
        Vertex(int value) {
            this.value = value;
        }
    }

    public void addVertex(int value) {
        Vertex v = new Vertex(value);
        nodes.put(v, new ArrayList<>());
    }

    void removeVertex(int value) {
        nodes.remove(getVertex(value));
    }

    Vertex getVertex(int value) {
        for (Vertex k: nodes.keySet()) {
            if (k.value == value) {
                return k;
            }
        }
        return null;
    }
    void connectArc(int src, int dst, int weight) {
        Vertex srcVertex = getVertex(src);
        Vertex dstVertex = getVertex(dst);
        nodes.get(srcVertex).add(new Arc(dstVertex, weight));
    }
    void removeArc(int src, int dst) {
        Vertex srcVertex = getVertex(src);
        Vertex dstVertex = getVertex(dst);
        Arc toDelete = new Arc(dstVertex, 0);
        List<Arc> v = nodes.get(srcVertex);
        for (Arc each: v) if (each.dst != null) if (each.dst.equals(dstVertex)) toDelete = each;
        nodes.get(srcVertex).remove(toDelete);
    }
    void renameVertex(int value, int valueN) {
        Vertex v = new Vertex(valueN);
        nodes.put(v, nodes.get(getVertex(value)));
        nodes.remove(getVertex(value));
    }
    void reWeight(int src, int dst, int weightN) {
        Vertex srcVertex = getVertex(src);
        Vertex dstVertex = getVertex(dst);
        Arc toChange = new Arc(dstVertex, 0);
        Vertex toChangeDst = toChange.dst;
        List<Arc> mapValue = nodes.get(srcVertex);
        for (Arc each: mapValue) if (each.dst != null) if (each.dst.equals(dstVertex)) {
            toChange = each;
            toChangeDst = each.dst;
        }
        nodes.get(srcVertex).remove(toChange);
        nodes.get(srcVertex).add(new Arc(toChangeDst, weightN));
    }
    List<Integer> getOuts(int src){
        Vertex source = getVertex(src);
        List<Arc> mapValue = nodes.get(source);
        ArrayList<Integer> outcomes = new ArrayList<>();
        for (Arc each: mapValue) {
            outcomes.add(each.dst.value);
        }
        return outcomes;
    }
    List<Integer> getIns(int dst){
        Vertex source = getVertex(dst);
        ArrayList<Integer> incomes = new ArrayList<>();
        nodes.forEach((root, info) -> {
            for (Arc each: info) {
                if (each.dst.equals(source)) incomes.add(root.value);
            }
        });
        return incomes;
    }
}