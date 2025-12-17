package graph;

/**
 * Immutable directed weighted edge.
 * AF(e) = an edge from e.source to e.target with weight e.weight
 * RI: source != null, target != null, weight >= 0
 */
public class Edge {
    private final String source;
    private final String target;
    private final int weight;

    public Edge(String source, String target, int weight) {
        if (source == null || target == null || weight < 0) {
            throw new IllegalArgumentException("Invalid edge arguments");
        }
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public String getSource() { return source; }
    public String getTarget() { return target; }
    public int getWeight() { return weight; }

    @Override
    public String toString() {
        return source + " -> " + target + " (" + weight + ")";
    }
}
