package com.atmujie;

import java.util.Arrays;

public class Graph {
    private int[] nodes; // 节点数组
    private int[][] matrix; // 邻接矩阵
    private int len; // 节点长度
    private int stat; // 节点开始位置
    private int end; // 节点结束位置
    private final int MAX = Integer.MAX_VALUE;

    /**
     * 对图进行初始化
     * @param stat 节点开始位置
     * @param end 节点结束位置
     * @param len 节点长度
     */
    public Graph(int stat, int end,int len) {
        if (end - stat + 1 != len) throw new RuntimeException("节点长度与数组长度不符");
        this.len = len;
        this.end = end;
        this.stat = stat;
        this.nodes = new int[len]; // 声明节点数组长度
        this.matrix = new int[len][len]; // 声明邻接矩阵大小
        // 初始化邻接矩阵
        for (int i = 0; i < len; i++) {
            Arrays.fill(matrix[i],MAX);
            matrix[i][i] = 0;
        }
        initNodes(stat); // 初始化节点数组
    }
    private void initNodes(int stat){
        for (int i = 0; i < len; i++) {
            nodes[i] = stat++;
        }
    }

    /**
     * 邻接矩阵节点下标从0开始, 但节点根据传入文件定，一般从 1 开始
     * 所以用 node - stat 得到节点在矩阵中的位置
     * @param node 传入一个节点
     * @return 返回节点再邻接矩阵中的真实位置
     */
    public int getRealNode(int node){
        return node - stat;
    }

    /**
     * 获取节点数组
     * @return 节点数组
     */
    public int[] getNodes() {
        return nodes;
    }

    /**
     * 获取邻接表
     * @return 邻接表
     */
    public int[][] getMatrix() {
        return matrix;
    }

    /**
     * 获取节点数
     * @return 当前节点总数
     */
    public int getLen() {
        return len;
    }

    /**
     * 检查节点是否合法
     * @param node 传入需要检查的节点
     * @return 传入节点不在节点数组内返回false,否则返回true
     */
    private boolean nodeNumCheck(int node){
        return node <= end;
    }

    /**
     * 更新邻接矩阵中两个节点的链接信息
     * 节点1 或 节点2 超过范围则抛出异常
     * @param node1 节点1
     * @param node2 节点2
     * @param weight 权值
     */
    public void updateNodeLink(int node1, int node2, int weight){
        if (nodeNumCheck(node1) && nodeNumCheck(node2)){
            node1 = getRealNode(node1);
            node2 = getRealNode(node2);
            this.matrix[node1][node2] = weight;
            this.matrix[node2][node1] = weight;
        }else {
            throw new RuntimeException("添加链接失败");
        }
    }

    /**
     * 格式化输出邻接矩阵,不能过大，过大抛出异常
     */
    public void formatPrintEdge(){
        if (len > 20) throw new RuntimeException("不能格式化输出过大的邻接矩阵");
        for (int i: nodes) System.out.printf("\t%d",i);
        System.out.println();
        int node = 0;
        for (int[] i: matrix) {
            System.out.print(nodes[node++]);
            for (int weight: i) System.out.printf("\t%d",weight);
            System.out.println();
        }
    }
}
