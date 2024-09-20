package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

/**
 * @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s; // 起始节点
    private int t; // 目标节点
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0; //距离
        edgeTo[s] = s; //路径中的上一个节点
    }

    /**
     * 从源开始对迷宫进行广度优先搜索。
     */
    private void bfs() {
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;
        announce();

        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();  // 取出队列头部的节点
            if (v == t) {
                targetFound = true;
                break;  // 如果找到目标节点，停止搜索
            }
            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    queue.enqueue(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    announce();
                }
            }
        }

    }


    @Override
    public void solve() {
        bfs();
    }
}

