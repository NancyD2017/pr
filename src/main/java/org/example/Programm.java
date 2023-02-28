package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Programm {
    static Scanner in = new Scanner(System.in);
    static Set<String> graph = new HashSet<>();
    static Map<String, Map<String, Integer>> arc = new HashMap<>();
    public static void main(String[] args) {
        greeting();
        int number = in.nextInt();
        while (!Objects.equals(number, 0)) {
            switch (number) {
                case 1 -> createSummit();
                case 2 -> deleteSummit();
                case 3 -> createArc();
                case 4 -> deleteArc();
                case 5 -> arcNameChangement();
                case 6 -> arcWeightChangement();
                case 7 -> outsList();
                case 8 -> insList();
                default -> print("Неверное действие. Для работы программы введите цифры 1,2,3,4,5,6,7,8 или 0\n");
            }
            print("Введите новую команду: ");
            number = in.nextInt();
        }
    }

    private static void print(String s) {
        System.out.print(s);
    }
    static void greeting() {
        System.out.print("""
                Добро пожаловать в редактор создания графов!

                Для создания вершины нажмите 1
                Для удаления вершины нажмите 2
                Для создания дуги нажмите 3
                Для удаления дуги нажмите 4
                Для изменения имени дуги нажмите 5
                Для изменения веса дуги нажмите 6

                Чтобы получить список исходящих из вершин дуг нажмите 7
                Чтобы получить список входящих в вершину дуг нажмите 8

                Выйти - нажмите 0
                """);
    }
    static void createSummit() {
        print("Введите имя вершины: ");
        var summit1 = in.next();
        if (!(graph.contains(summit1))) {
            print("Вершина успешно добавлена! ");
            graph.add(summit1);
        } else print("Вершина не добавлена, т.к. она уже существовала. ");
    }
    static void deleteSummit() {
        if (graph.size() > 0) {
            print("Список вершин, доступных для удаления:" + graph + "\nВведите имя вершины: ");
            var summit2 = in.next();
            if (graph.contains(summit2)) {
                print("Вершина успешно удалена! ");
                graph.remove(summit2);
            } else print("Вершина не удалена, т.к. ее не существовало. ");
        } else print("Список вершин пуст.\nПопробуйте сначала добавить вершину");
    }
    static void createArc() {
        BufferedReader toRead = new BufferedReader(new InputStreamReader(System.in));
        print("Введите имя дуги: ");
        var arc3 = in.next();
        print("Введите направление дуги\n(Например, если она направлена из вершины А в В, просто запишите: A B): ");
        String arcDirection;
        try {
            arcDirection = toRead.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] summits = arcDirection.split(" ");
        if (graph.containsAll(List.of(summits)) && summits.length == 2 && !summits[1].equals(summits[0])) {
            print("Введите вес дуги: ");
            Integer arcWeight;
            try {
                arcWeight = in.nextInt();
            } catch (InputMismatchException d) {
                throw new RuntimeException(d);
            }
            if (arc.getOrDefault(arc3, Map.of("err",0)).equals(Map.of("err", 0))) {
                print("Дуга успешно добавлена! ");
                arc.put(arc3, Map.of(summits[0] + " -> " + summits[1], arcWeight));
            }else print("Дуга не добавлена, т.к. уже существует одна с таким именем. ");
        } else
            print("Новая дуга не добавлена. Неправильное направление дуги или введенных вершин не существует. ");
    }
    static void deleteArc() {
        if (arc.size() > 0) {
            print("Список дуг, доступных для удаления:" + arc.keySet() + "\nВведите имя дуги: ");
            var arc4 = in.next();
            if (!arc.getOrDefault(arc4, Map.of("err",0)).equals(Map.of("err", 0))) {
                print("Дуга успешно удалена! ");
                arc.remove(arc4);
            }else print("Дуга не удалена, т.к. ее не существовало. ");
        } else print("Список дуг пуст.\nПопробуйте сначала добавить дугу. ");
    }
    static void arcNameChangement() {
        if (arc.size() > 0) {
            print("Список дуг для изменения:" + arc.keySet() + "\nВведите старое имя дуги: ");
            var arc5 = in.next();
            if (!arc.getOrDefault(arc5, Map.of("err",0)).equals(Map.of("err", 0))) {
                print("Введите новое имя дуги: ");
                arc.put(in.next(), arc.get(arc5));
                arc.remove(arc5);
                print("Дуга успешно изменена! ");
            } else print("Дуга не изменена, т.к. ее не существовало. ");
        } else print("Список дуг пуст.\nПопробуйте сначала добавить дугу. ");
    }
    static void arcWeightChangement() {
        if (arc.size() > 0) {
            print("Список дуг для изменения:" + arc.keySet() + "\nВведите имя изменяемой дуги: ");
            var arc6 = in.next();
            if (!arc.getOrDefault(arc6, Map.of("err",0)).equals(Map.of("err", 0))) {
                print("Введите новый вес дуги: ");
                arc.put(arc6, Map.of(arc.get(arc6).keySet().toString(), in.nextInt()));
                print("Дуга успешно изменена! ");
            } else print("Дуга не изменена, т.к. ее не существовало. ");
        } else print("Список дуг пуст.\nПопробуйте сначала добавить дугу. ");
    }
    static void outsList() {
        if (arc.size() > 0) {
            print("Список вершин:" + graph + "\nВведите имя интересующей вершины: ");
            String name = in.next();
            Set<String> answer = new HashSet<>();
            arc.forEach((key, value) -> value.forEach((direction, weight) -> {
                String from = (Arrays.toString(direction.split(" -> .+")).replaceAll("[^a-zA-Z ]+", "").trim());
                if (from.equals(name))
                    answer.add(Arrays.toString(direction.split(".+ -> ")).replaceAll("[^a-zA-Z ]+", "").trim());
            }));
            print(answer + "\n");
        } else print("Список дуг пуст.\nПопробуйте сначала добавить дугу");
    }
    static void insList() {
        if (arc.size() > 0) {
            print("Список вершин:" + graph + "\nВведите имя интересующей вершины: ");
            String name = in.next();
            Set<String> answer = new HashSet<>();
            arc.forEach((key, value) -> value.forEach((direction, weight) -> {
                String from = (Arrays.toString(direction.split(".+ -> ")).replaceAll("[^a-zA-Z ]+", "").trim());
                if (from.equals(name))
                    answer.add(Arrays.toString(direction.split(" -> .+")).replaceAll("[^a-zA-Z ]+", "").trim());
            }));
            print(answer + "\n");
        } else print("Список дуг пуст.\nПопробуйте сначала добавить дугу");
    }
}