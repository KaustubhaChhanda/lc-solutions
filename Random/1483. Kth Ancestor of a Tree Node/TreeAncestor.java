class TreeAncestor {
  int[][] up;

  public TreeAncestor(int n, int[] parent) {
    up = new int[n][20];

    for (int i = 0; i < n; i++) {
      up[i][0] = parent[i];
    }

    for (int j = 1; j < 20; j++) {
      for (int i = 0; i < n; i++) {
        if (up[i][j - 1] == -1) {
          up[i][j] = -1;
        } else {
          up[i][j] = up[up[i][j - 1]][j - 1];
        }
      }
    }
  }

  public int getKthAncestor(int node, int k) {
    for (int j = 0; j < 20; j++) {
      if ((k & (1 << j)) != 0) {
        node = up[node][j];
        if (node == -1) {
          return -1;
        }
      }
    }

    return node;
  }
}