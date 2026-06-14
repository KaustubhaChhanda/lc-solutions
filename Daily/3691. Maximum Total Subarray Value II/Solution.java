import java.util.*;

class Solution {
    class SegmentTree {
        private static class Node {
            int start, end;
            int min, max;
            Node left, right;

            public Node(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }

        public static class Result {
            public int min;
            public int max;

            public Result(int min, int max) {
                this.min = min;
                this.max = max;
            }
        }

        private Node root;

        public SegmentTree(int[] arr) {
            root = constructTree(arr, 0, arr.length - 1);
        }

        private Node constructTree(int[] arr, int start, int end) {
            if (start > end) return null;

            Node node = new Node(start, end);

            if (start == end) {
                node.min = arr[start];
                node.max = arr[start];
            } else {
                int mid = start + (end - start) / 2;

                node.left = constructTree(arr, start, mid);
                node.right = constructTree(arr, mid + 1, end);

                node.min = Math.min(node.left.min, node.right.min);
                node.max = Math.max(node.left.max, node.right.max);
            }

            return node;
        }

        public int query(int start, int end) {
            Result res = query(root, start, end);
            return res.max - res.min;
        }

        private Result query(Node node, int start, int end) {
            if (node == null || start > node.end || end < node.start) {
                return new Result(Integer.MAX_VALUE, Integer.MIN_VALUE);
            }

            if (start <= node.start && node.end <= end) {
                return new Result(node.min, node.max);
            }

            Result left = query(node.left, start, end);
            Result right = query(node.right, start, end);

            return new Result(
                Math.min(left.min, right.min),
                Math.max(left.max, right.max)
            );
        }
    }

    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        SegmentTree st = new SegmentTree(nums);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));

        for (int l = 0; l < n; l++) {
            pq.offer(new int[] {
                st.query(l, n - 1),
                l,
                n - 1
            });
        }

        long ans = 0;
        while (k-- > 0) {
            int[] top = pq.poll();

            int value = top[0];
            int l = top[1];
            int r = top[2];

            ans += value;

            if (r > l) {
                pq.offer(new int[] {
                    st.query(l, r - 1),
                    l,
                    r - 1
                });
            }
        }

        return ans;
    }
}