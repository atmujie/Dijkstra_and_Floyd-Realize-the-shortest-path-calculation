package com.atmujie.Dijkstra;

import com.atmujie.Graph;

public class Dijkstra {
    private Graph graph; // 初始化后的图
    private int len; // 节点数
    private DisPojo disPojo; // 迪杰斯特拉算法用到的结构
    private boolean[] accessNode; // 标识访问节点
    private int[] shortestDistance; // 最短距离
    private int[] prefixNode; // 前缀节点
    private final int MAX = Integer.MAX_VALUE;

    /**
     * 将初始化后的图交给 Dijkstra算法去实现
     * @param graph 图的对象
     */
    public Dijkstra(Graph graph) {
        this.graph = graph;
        this.len = graph.getLen();
        this.disPojo = new DisPojo(len);
        accessNode = disPojo.getAccessNode();
        shortestDistance = disPojo.getShortestDistance();
        prefixNode = disPojo.getPrefixNode();
    }

    /**
     * 传入初始节点，获取在邻接矩阵中对应的位置后遍历所有节点
     * 当节点没有访问并且最短距离不是最大值时，对节点进行广度优先遍历
     * @param node 初始节点
     */
    public void djs(int node){
        int index = graph.getRealNode(node);
        shortestDistance[index] = 0;
        for (int i = index; i < len; i++) {
            boolean flag = true;
            // 如果节点没有被访问并且距离不是最大值
            if (!accessNode[i] && shortestDistance[i] != MAX){
                findNodeLink(i);
                flag = false;
            }
            if (i == len -1) i = -1; // 让循环遍历全部节点
            if (i == index - 1 && flag) break; // 结束条件
        }
    }

    /**
     * 广度优先 查找指定节点连接的所有节点，更新最短距离和前缀节点
     * @param index 要访问的节点
     */
    private void findNodeLink(int index){
        accessNode[index] = true; // 标记节点为以访问
        int min = shortestDistance[index] == MAX ? 0 : shortestDistance[index];
        for (int i = 0; i < len; i++) {
            int weight = graph.getMatrix()[index][i];
            // 找到和 index 连接的节点, 并检查当前路径是否小于已经存在的路径距离
            // 小于则更新最短距离和前缀节点
            if(weight != MAX && (min + weight) < shortestDistance[i]){
                shortestDistance[i] = min + weight; // 更新最短距离
                prefixNode[i] = index; // 更新前缀节点
            }
        }
    }

    public int[] getShortestDistance() {
        return shortestDistance;
    }

    public int[] getPrefixNode() {
        return prefixNode;
    }
}
