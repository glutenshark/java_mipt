package ru.mipt.hw7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

    @Test
    void sampleShortestPath() {
        String in = String.join("\n",
            "4 4 0 3",
            "0 1 1",
            "1 2 2",
            "0 2 5",
            "2 3 3",
            ""
        );
        String out = Solution.solveFromString(in);
        assertEquals("6\n", out);
    }

    @Test
    void unreachable() {
        String in = String.join("\n",
            "3 1 0 2",
            "0 1 5",
            ""
        );
        String out = Solution.solveFromString(in);
        assertEquals("INF\n", out);
    }
}
