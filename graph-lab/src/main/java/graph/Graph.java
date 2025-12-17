package graph;

import java.util.Map;
import java.util.Set;

public interface Graph<L> {

    static <L> Graph<L> empty() {
        return new ConcreteGraph<>();
    }

    boolean add(L vertex);

    int set(L source, L target, int weight);

    boolean remove(L vertex);

    Set<L> vertices();

    Map<L, Integer> sources(L target);

    Map<L, Integer> targets(L source);
}
