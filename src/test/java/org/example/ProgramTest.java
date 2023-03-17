package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProgramTest {

    @Test
    void addVertexTest() {
        DirectedGraph g = new DirectedGraph();
        g.addVertex(1);
        assertNotNull(g.getVertex(1));
    }

    @Test
    void testRemoveVertex() {
        DirectedGraph g = new DirectedGraph();
        g.addVertex(1);
        g.removeVertex(1);
        assertNull(g.getVertex(1));
    }

    @Test
    void connectArcTest() {
        DirectedGraph g = new DirectedGraph();
        g.addVertex(1);
        g.addVertex(2);
        g.connectArc(1, 2, 3);
        assertNotNull(g.getListArc(1));
    }

    @Test
    void removeArcTest() {
        DirectedGraph g = new DirectedGraph();
        g.addVertex(1);
        g.addVertex(2);
        g.connectArc(1, 2, 3);
        g.removeArc(1, 2);
        assertNull(g.getListArc(1));
    }
    @Test
    void renameVertexTest() {
        DirectedGraph g = new DirectedGraph();
        g.addVertex(1);
        g.addVertex(2);
        g.connectArc(1, 2, 3);
        g.renameVertex(1, 11);
        assertNull(g.getVertex(1));
    }
    @Test
    void reWeightTest() {
        DirectedGraph g = new DirectedGraph();
        g.addVertex(1);
        g.addVertex(2);
        g.connectArc(1, 2, 3);
        int old = g.getListArc(1).hashCode();
        g.reWeight(1, 2, 11);
        assertNotEquals(g.getListArc(1).hashCode(), old);
    }
    @Test
    void getOutsTest() {
        DirectedGraph g = new DirectedGraph();
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.connectArc(1, 2, 3);
        g.connectArc(1, 3, 10);
        assertEquals(g.getOuts(1), List.of(2,3));
    }
    @Test
    void getInsTest() {
        DirectedGraph g = new DirectedGraph();
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.connectArc(1, 2, 3);
        g.connectArc(1, 3, 10);
        assertEquals(g.getIns(2), List.of(1));
    }
}