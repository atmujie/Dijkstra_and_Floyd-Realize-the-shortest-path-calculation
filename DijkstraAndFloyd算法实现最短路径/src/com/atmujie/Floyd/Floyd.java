package com.atmujie.Floyd;

import com.atmujie.Graph;

import java.util.Arrays;

public class Floyd {
    private final int MAX = Integer.MAX_VALUE;
    private int[][] distanceList; // 距离表
    private int[][] prefixList; // 前驱节点表
    private int len; // 节点数目

    /**
     * 初始化弗洛伊德算法基本数组
     */
    public Floyd(Graph graph) {
        this.distanceList = graph.getMatrix(); // 初始化距离表
        this.len = graph.getLen(); // 获取节点长度
        // 初始化前驱表
        this.prefixList = new int[len][len];
        for (int i = 0; i < len; i++) {
            Arrays.fill(prefixList[i],i);
        }
    }

    /**
     * 三层循环实现弗洛伊德算法
     */
    public void FloydImplementation(){
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                for (int k = 0; k < len; k++) {
                    int resLen =
                            (distanceList[i][j] + distanceList[j][k] < 0)
                            ? MAX : distanceList[i][j] + distanceList[j][k];
                    if (resLen < distanceList[i][k]){
                        distanceList[i][k] = resLen;  // 更新最短距离
                        prefixList[i][k] = prefixList[j][k]; // 更新前缀节点
                    }
                }
            }
        }
    }

    /**
     * 获取节点数
     * @return 节点数目
     */
    public int getLen() {
        return len;
    }

    /**
     * 获取节点最短距离
     * @return 节点最短距离矩阵
     */
    public int[][] getDistanceList() {
        return distanceList;
    }

    /**
     * 获取前缀节点
     * @return 前缀节点举证矩阵
     */
    public int[][] getPrefixList() {
        return prefixList;
    }
}
