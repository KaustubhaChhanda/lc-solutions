class Solution {
  public int pairSum(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    ListNode prev = null, curr = slow.next;
    while (curr != null) {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }

    int max = 0;
    while (head != null && prev != null) {
      max = Math.max(max, head.val + prev.val);
      head = head.next;
      prev = prev.next;
    }

    return max;
  }
}