package org.example;

import java.util.*;

public class Program {
    static Set<String> graph = new HashSet<>();
    static Map<String, Map<String, Integer>> arc = new HashMap<>();

    public Object Program(int number, String summit1, String summit2, String arc3, String arcDirection,
                          Integer arcWeight, String arc4, String arc5, String newName, String arc6, int newWeight,
                          String getOuts, String getIns) {
        switch (number) {
            case 1 -> {
                return (createSummit(summit1));
            }
            case 2 -> {
                return (deleteSummit(summit2));
            }
            case 3 -> {
                return (createArc(arc3, arcDirection, arcWeight));
            }
            case 4 -> {
                return (deleteArc(arc4));
            }
            case 5 -> {
                return (arcNameChangement(arc5, newName));
            }
            case 6 -> {
                return (arcWeightChangement(arc6, newWeight));
            }
            case 7 -> {
                return (outsList(getOuts));
            }
            case 8 -> {
                return (insList(getIns));
            }
            default -> {
                return ("Неверное действие. Для работы программы введите цифры 1,2,3,4,5,6,7,8 или 0\n");
            }
        }
    }

    static Set<String> createSummit(String summit1) {
        graph.add(summit1);
        return (graph);
    }

    static Set<String> deleteSummit(String summit2) {
        graph.remove(summit2);
        return (graph);
    }

    static Map<String, Map<String, Integer>> createArc(String arc3, String arcDirection, Integer arcWeight) {
        String[] summits = arcDirection.split(" ");
        arc.put(arc3, Map.of(summits[0] + " -> " + summits[1], arcWeight));
        return arc;
    }

    static Map<String, Map<String, Integer>> deleteArc(String arc4) {
        arc.remove(arc4);
        return arc;
    }

    static Map<String, Map<String, Integer>> arcNameChangement(String arc5, String newName) {
        arc.put(newName, arc.get(arc5));
        arc.remove(arc5);
        return (arc);
    }

    static Map<String, Map<String, Integer>> arcWeightChangement(String arc6, int newWeight) {
        arc.put(arc6, Map.of(arc.get(arc6).keySet().toString().replaceAll("[^a-zA-Z ->]+", ""), newWeight));
        return (arc);
    }

    static Set<String> outsList(String getOuts) {
        Set<String> answer = new HashSet<>();
        arc.forEach((key, value) -> value.forEach((direction, weight) -> {
            String from = (Arrays.toString(direction.split(" -> .+")).replaceAll("[^a-zA-Z ]+", "").trim());
            if (from.equals(getOuts))
                answer.add(Arrays.toString(direction.split(".+ -> ")).replaceAll("[^a-zA-Z ]+", "").trim());
        }));
        return (answer);
    }

    static Set<String> insList(String getIns) {
        Set<String> answer = new HashSet<>();
        arc.forEach((key, value) -> value.forEach((direction, weight) -> {
            String from = (Arrays.toString(direction.split(".+ -> ")).replaceAll("[^a-zA-Z ]+", "").trim());
            if (from.equals(getIns))
                answer.add(Arrays.toString(direction.split(" -> .+")).replaceAll("[^a-zA-Z ]+", "").trim());
        }));
        return (answer);
    }
}