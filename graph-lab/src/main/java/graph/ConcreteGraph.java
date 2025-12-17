package graph;

import java.util.*;

/**
 * AF: vertices represents graph nodes; edges maps source -> (target -> weight)
 * RI: vertices != null, edges != null,
 *     all vertices referenced in edges exist in vertices,
 *     all weights > 0
 * Safety: returns unmodifiable views to prevent rep exposure
 */
public class ConcreteGraph<L> implements Graph<L> {

    private final Set<L> vertices = new HashSet<>();
    private final Map<L, Map<L, Integer>> edges = new HashMap<>();

    private void checkRep() {
        for (L v : edges.keySet()) {
            assert vertices.contains(v);
            for (Map.Entry<L, Integer> e : edges.get(v).entrySet()) {
                assert vertices.contains(e.getKey());
                assert e.getValue() > 0;
            }
        }
    }

    @Override
    public boolean add(L vertex) {
        if (vertex == null) return false;
        boolean added = vertices.add(vertex);
        checkRep();
        return added;
    }

    @Override
    public int set(L source, L target, int weight) {
        if (source == null || target == null || weight < 0) {
            throw new IllegalArgumentException();
        }
        vertices.add(source);
        vertices.add(target);

        edges.putIfAbsent(source, new HashMap<>());
        Map<L, Integer> m = edges.get(source);

        int prev = m.getOrDefault(target, 0);
        if (weight == 0) {
            m.remove(target);
        } else {
            m.put(target, weight);
        }
        checkRep();
        return prev;
    }

    @Override
    public boolean remove(L vertex) {
        if (!vertices.remove(vertex)) return false;
        edges.remove(vertex);
        for (Map<L, Integer> m : edges.values()) {
            m.remove(vertex);
        }
        checkRep();
        return true;
    }

    @Override
    public Set<L> vertices() {
        return Collections.unmodifiableSet(vertices);
    }

    @Override
    public Map<L, Integer> sources(L target) {
        Map<L, Integer> result = new HashMap<>();
        for (L src : edges.keySet()) {
            if (edges.get(src).containsKey(target)) {
                result.put(src, edges.get(src).get(target));
            }
        }
        return Collections.unmodifiableMap(result);
    }

    @Override
    public Map<L, Integer> targets(L source) {
        if (!edges.containsKey(source)) return Collections.emptyMap();
        return Collections.unmodifiableMap(new HashMap<>(edges.get(source)));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (L v : vertices) sb.append(v).append("\n");
        for (L s : edges.keySet()) {
            for (Map.Entry<L, Integer> e : edges.get(s).entrySet()) {
                sb.append(s).append(" -> ").append(e.getKey())
                  .append(" (").append(e.getValue()).append(")\n");
            }
        }
        return sb.toString();
    }
}
