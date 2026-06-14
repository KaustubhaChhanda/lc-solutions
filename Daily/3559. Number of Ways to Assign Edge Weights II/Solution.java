class Solution {
  private static final int MOD = 1_000_000_007;
  private static final int LOG = 20;

  public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
    int n = edges.length + 1;
    List<List<Integer>> tree = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      tree.add(new ArrayList<>());
    }

    for (int[] edge : edges) {
      tree.get(edge[0]).add(edge[1]);
      tree.get(edge[1]).add(edge[0]);
    }

    int[] depth = new int[n + 1];
    int[][] up = new int[n + 1][LOG];

    for (int i = 0; i <= n; i++) {
      Arrays.fill(up[i], -1);
    }

    int root = edges.length > 0 ? edges[0][0] : 0;
    dfs(root, -1, 0, tree, depth, up);

    for (int j = 1; j < LOG; j++) {
      for (int i = 0; i <= n; i++) {
        if (up[i][j - 1] != -1) {
          up[i][j] = up[up[i][j - 1]][j - 1];
        }
      }
    }

    int[] ans = new int[queries.length];
    for (int i = 0; i < ans.length; i++) {
      int u = queries[i][0];
      int v = queries[i][1];

      if (u == v) {
        ans[i] = 0;
        continue;
      }

      int lca = getLCA(u, v, depth, up);
      int dist = depth[u] + depth[v] - 2 * depth[lca];

      ans[i] = modPow(2, dist - 1);
    }

    return ans;
  }

  private void dfs(int node, int parent, int d, List<List<Integer>> tree, int[] depth, int[][] up) {
    depth[node] = d;
    up[node][0] = parent;

    for (int next : tree.get(node)) {
      if (next != parent) {
        dfs(next, node, d + 1, tree, depth, up);
      }
    }
  }

  private int getLCA(int u, int v, int[] depth, int[][] up) {
    if (depth[u] < depth[v]) {
      int temp = u;
      u = v;
      v = temp;
    }

    int diff = depth[u] - depth[v];
    for (int j = 0; j < LOG; j++) {
      if (((diff >> j) & 1) == 1) {
        u = up[u][j];
      }
    }

    if (u == v) {
      return u;
    }

    for (int j = LOG - 1; j >= 0; j--) {
      if (up[u][j] != -1 && up[u][j] != up[v][j]) {
        u = up[u][j];
        v = up[v][j];
      }
    }

    return up[u][0];
  }

  private int modPow(int a, int exp) {
    long base = a % MOD;
    long res = 1;

    while (exp > 0) {
      if ((exp & 1) == 1) {
        res = (res * base) % MOD;
      }
      base = (base * base) % MOD;
      exp >>= 1;
    }

    return (int) res;
  }
}