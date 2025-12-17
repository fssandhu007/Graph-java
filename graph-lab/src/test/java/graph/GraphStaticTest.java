package graph;

import static org.junit.Assert.*;
import org.junit.Test;

public class GraphStaticTest {

    @Test
    public void testEmptyGraphNotNull() {
        Graph<String> g = Graph.empty();
        assertNotNull(g);
        assertTrue(g.vertices().isEmpty());
    }
}
