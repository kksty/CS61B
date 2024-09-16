package hw4.puzzle;

public class SearchNode implements Comparable<SearchNode> {
    public WorldState word;
    public int moves;
    public SearchNode previous; //引用上一个节点，避免将自己的父节点添加进队列
    public int priority;

    public SearchNode(WorldState w, int m, SearchNode p) {
        word = w;
        moves = m;
        previous = p;
        priority = moves + w.estimatedDistanceToGoal();
    }

    @Override
    public int compareTo(SearchNode o) {
        return this.priority - o.priority;
    }
}