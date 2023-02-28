package org.example;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProgramTest {

    @Test
    void createSummit() {
        assertEquals(Set.of("q"),new Program().createSummit("q"));
        assertEquals(Set.of("q","w"), new Program().createSummit("w"));
    }

    @Test
    void deleteSummit() {
        new Program().createSummit("q");
        assertEquals(Set.of("q"),new Program().deleteSummit("w"));
        assertEquals(Set.of(),new Program().deleteSummit("q"));
    }

    @Test
    void createArc() {
        new Program().createSummit("q");
        new Program().createSummit("w");
        assertEquals(Map.of("arc",Map.of("q -> w", 7)),new Program().createArc("arc", "q w", 7));
    }

    @Test
    void deleteArc() {
        new Program().createSummit("q");
        new Program().createSummit("w");
        new Program().createArc("arc", "q w", 7);
        assertEquals(Map.of(),new Program().deleteArc("arc"));
    }

    @Test
    void arcNameChangement() {
        new Program().createSummit("q");
        new Program().createSummit("w");
        new Program().createArc("arc", "q w", 7);
        assertEquals(Map.of("newArc",Map.of("q -> w", 7)),new Program().arcNameChangement("arc", "newArc"));
    }

    @Test
    void arcWeightChangement() {
        new Program().createSummit("q");
        new Program().createSummit("w");
        new Program().createArc("arc", "q w", 7);
        assertEquals(Map.of("arc",Map.of("q -> w", 2)),new Program().arcWeightChangement("arc", 2));
    }

    @Test
    void outsList() {
        new Program().createSummit("q");
        new Program().createSummit("w");
        new Program().createArc("arc", "q w", 7);
        assertEquals(Set.of("w"),new Program().outsList("q"));
    }

    @Test
    void insList() {
        new Program().createSummit("q");
        new Program().createSummit("w");
        new Program().createArc("arc", "q w", 7);
        assertEquals(Set.of("q"),new Program().insList("w"));
    }
}