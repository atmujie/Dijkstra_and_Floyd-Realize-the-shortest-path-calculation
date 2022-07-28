package com.atmujie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadDataFile {
    private Graph graph; // 图
    private File file; // 要读取的文件
    private int len; // 得到的节点数目

    public ReadDataFile(File file) {
        try {
            this.file = file;
        }
        catch (Exception ignored){
            throw new RuntimeException("文件不存在");
        }
    }

    /**
     * 读取传入的文件，初始化图的节点列表和链接列表
     */
    public Graph fileToGraph() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        initNodes(scanner);
        initLink(scanner);
        return graph;
    }

    /**
     * 通过读入文件的前三个数，初始化节点列表
     * @param scanner 读入的文件流
     */
    private Graph initNodes(Scanner scanner){
        int count = 1; // 计数器
        int stat = -1; // 节点开始位置
        int end = -1; // 节点结束位置
        while (scanner.hasNextInt() && count <= 3){
            // 文件中读到的第一个值是节点总数
            if (count == 1) this.len = scanner.nextInt();
            if (count == 2) stat = scanner.nextInt();
            if (count == 3) end = scanner.nextInt();
            count++;
        }
        this.graph = new Graph(stat,end,len);
        return graph;
    }

    /**
     * 通过读入文件的其他数，初始化节点链接
     * @param scanner 读入的文件流
     */
    private Graph initLink(Scanner scanner){
        while (scanner.hasNextInt()){
            graph.updateNodeLink(
                    scanner.nextInt(),
                    scanner.nextInt(),
                    scanner.nextInt()
            );
        }
        return graph;
    }
}
