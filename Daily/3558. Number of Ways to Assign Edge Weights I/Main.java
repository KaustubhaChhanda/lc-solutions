class Solution {
    private static final long MOD = 1_000_000_007L;

    public int assignEdgeWeights(int[][] edges) {
        int totalNodes = edges.length + 1;

        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int node = 0; node <= totalNodes; node++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }

        int longestPathNodes = maxNodesInPath(adjacencyList, 1, 0);

        if (longestPathNodes <= 2) {
            return 1;
        }

        return (int) modPow(2, longestPathNodes - 2, MOD);
    }

    private int maxNodesInPath(
        List<List<Integer>> adjacencyList,
        int currentNode,
        int parentNode
    ) {
        int deepestChildPath = 0;

        for (int neighbor : adjacencyList.get(currentNode)) {
            if (neighbor == parentNode) {
                continue;
            }

            deepestChildPath = Math.max(
                deepestChildPath,
                maxNodesInPath(adjacencyList, neighbor, currentNode)
            );
        }

        return deepestChildPath + 1;
    }

    private long modPow(long baseValue, long exponent, long modulus) {
        long result = 1;

        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = (result * baseValue) % modulus;
            }

            baseValue = (baseValue * baseValue) % modulus;
            exponent >>= 1;
        }

        return result;
    }
}