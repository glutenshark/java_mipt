package ru.mipt.hw7;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Кратчайший путь в ориентированном графе (Дейкстра).
 * Формат ввода/вывода описан в README.md.
 */
public class Solution {
    public static void main(String[] args) throws IOException {
        byte[] bytes = System.in.readAllBytes();
        String input = new String(bytes, StandardCharsets.UTF_8);
        String out = solveFromString(input);
        System.out.print(out);
    }

    /** Возвращает строку результата с завершающим переводом строки. */
    public static String solveFromString(String input) {
        String trimmed = input.trim();
        if (trimmed.isEmpty()) return "INF\n";

        String[] tok = trimmed.split("\\s+");
        int idx = 0;
        if (tok.length < 4) return "INF\n";

        int n = Integer.parseInt(tok[idx++]);
        int m = Integer.parseInt(tok[idx++]);
        int s = Integer.parseInt(tok[idx++]);
        int t = Integer.parseInt(tok[idx++]);

        List<List<Edge>> g = new ArrayList<>(n);
        for (int i = 0; i < n; i++) g.add(new ArrayList<>());

        for (int i = 0; i < m && idx + 2 < tok.length; i++) {
            int u = Integer.parseInt(tok[idx++]);
            int v = Integer.parseInt(tok[idx++]);
            long w = Long.parseLong(tok[idx++]);
            if (u >= 0 && u < n) g.get(u).add(new Edge(v, w));
        }

        long[] dist = dijkstra(g, s);
        long ans = (t >= 0 && t < dist.length) ? dist[t] : Long.MAX_VALUE;
        return ans == Long.MAX_VALUE ? "INF\n" : (ans + "\n");
    }

    private static long[] dijkstra(List<List<Edge>> g, int s) {
        int n = g.size();
        long INF = Long.MAX_VALUE;
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        if (s < 0 || s >= n) return dist;
        dist[s] = 0L;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.add(new long[]{0L, s});
        boolean[] used = new boolean[n];

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1];
            if (used[u]) continue;
            used[u] = true;
            if (d != dist[u]) continue;

            for (Edge e : g.get(u)) {
                if (e.to < 0 || e.to >= n) continue;
                long nd = dist[u] + e.w;
                if (nd < 0) nd = Long.MAX_VALUE; // защита от переполнения
                if (dist[e.to] > nd) {
                    dist[e.to] = nd;
                    pq.add(new long[]{nd, e.to});
                }
            }
        }
        return dist;
    }

    private static final class Edge {
        final int to;
        final long w;
        Edge(int to, long w) { this.to = to; this.w = w; }
    }
}
