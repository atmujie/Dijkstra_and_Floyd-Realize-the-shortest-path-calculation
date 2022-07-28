package com.atmujie;

import com.atmujie.Dijkstra.Dijkstra;
import com.atmujie.Floyd.Floyd;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    private ReadDataFile readDataFile; // 文件读取
    private Graph graph; // 图
    private Dijkstra dijkstra; // Dijkstra算法实现
    private Floyd floyd; // Floyd算法实现

    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.test();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Graph getGraph(String fileName) throws FileNotFoundException {
        String filePath = "src/com/atmujie/data/" + fileName;
        readDataFile = new ReadDataFile(new File(filePath));
        return readDataFile.fileToGraph();
    }

    // 测试类
    public void test() throws FileNotFoundException {
        File file = new File("src/com/atmujie/data/最短路100.txt");
        readDataFile = new ReadDataFile(file);
        graph = readDataFile.fileToGraph();
        // Dijstra算法测试
        dijkstra = new Dijkstra(graph);
        dijkstra.djs(1);
        int[] shortestDistance = dijkstra.getShortestDistance();
        for (int i = 0; i < shortestDistance.length; i++) {
            System.out.printf("%d;\t",shortestDistance[i]);
        }
        System.out.println();
        // Floyd算法测试
        Floyd floyd = new Floyd(graph);
        floyd.FloydImplementation();
        int[][] res = floyd.getDistanceList();
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                System.out.printf("%d;\t",res[i][j]);
            }
            System.out.println();
        }
    }
}
