package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgramTest {

    @Test
    void testGraph() {
        DirectedGraph g = new DirectedGraph();
        g.addVertex(1);
        g.addVertex(2);
        assertNotNull(g.getVertex(1));
    }

    @Test
    void testGraph2() {
        DirectedGraph g = new DirectedGraph();
        g.addVertex(1);
        g.addVertex(2);
        assertNotNull(g.getVertex(2));
    }

    @Test
    void connectArcTest() {
        DirectedGraph g = new DirectedGraph();
        g.addVertex(1);
        g.addVertex(2);
        g.connectArc(1, 2, 3);
        assertNotNull(g.getVertex(1));
    }

    @Test
    void removeArcTest() {
        DirectedGraph g = new DirectedGraph();
        g.addVertex(1);
        g.addVertex(2);
        g.connectArc(1, 2, 3);
        g.removeArc(1, 2);
        assertNull(g.getVertex(1));
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
    void reWeight() {
        DirectedGraph g = new DirectedGraph();
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.connectArc(1, 2, 3);
        g.connectArc(1, 3, 10);
        g.reWeight(1, 2, 11);
    }
}