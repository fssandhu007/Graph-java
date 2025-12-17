package graph;

import static org.junit.Assert.*;
import org.junit.Test;

public class GraphInstanceTest {

    private Graph<String> emptyInstance() {
        return Graph.empty();
    }

    @Test
    public void testAddVertex() {
        Graph<String> g = emptyInstance();
        assertTrue(g.add("A"));
        assertFalse(g.add("A"));
    }

    @Test
    public void testRemoveVertex() {
        Graph<String> g = emptyInstance();
        g.add("A");
        assertTrue(g.remove("A"));
        assertFalse(g.remove("A"));
    }

    @Test
    public void testSetEdge() {
        Graph<String> g = emptyInstance();
        assertEquals(0, g.set("A", "B", 5));
        assertEquals(5, g.set("A", "B", 7));
        assertEquals(7, g.set("A", "B", 0));
    }

    @Test
    public void testSourcesAndTargets() {
        Graph<String> g = emptyInstance();
        g.set("A", "B", 3);
        assertTrue(g.targets("A").containsKey("B"));
        assertTrue(g.sources("B").containsKey("A"));
    }

    @Test
    public void testVerticesUnmodifiable() {
        Graph<String> g = emptyInstance();
        g.add("A");
        try {
            g.vertices().add("B");
            fail();
        } catch (UnsupportedOperationException expected) {}
    }
}
