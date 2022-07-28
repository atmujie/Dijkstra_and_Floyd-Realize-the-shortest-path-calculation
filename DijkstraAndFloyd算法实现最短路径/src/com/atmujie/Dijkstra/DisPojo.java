package com.atmujie.Dijkstra;

import java.util.Arrays;

/**
 * 迪杰斯特拉算法数组结构
 */
public class DisPojo {
    private static final int MAX = Integer.MAX_VALUE;
    private boolean[] accessNode; // 标识访问节点
    private int[] shortestDistance; // 最短距离
    private int[] prefixNode; // 前缀节点
    private int len; // 节点数目

    public DisPojo(int len) {
        this.len = len;
        accessNode = new boolean[len];
        shortestDistance = new int[len];
        prefixNode = new int[len];
        Arrays.fill(shortestDistance,MAX);
        Arrays.fill(prefixNode,-1);
    }

    public boolean[] getAccessNode() {
        return accessNode;
    }

    public int[] getShortestDistance() {
        return shortestDistance;
    }

    public int[] getPrefixNode() {
        return prefixNode;
    }

    public int getLen() {
        return len;
    }
}
