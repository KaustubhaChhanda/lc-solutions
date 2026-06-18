class Solution {
  public char processStr(String s, long k) {
    long len = 0;

    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      switch (ch) {
        case '*':
          if (len > 0) {
            len--;
          }

          break;
        case '#':
          len *= 2;
          break;
        case '%':
          break;
        default:
          len++;
          break;
      }
    }

    if (k + 1 > len) {
      return '.';
    }

    for (int i = s.length() - 1; i >= 0; i--) {
      char ch = s.charAt(i);
      switch (ch) {
        case '*':
          len++;
          break;
        case '#':
          if (k + 1 > (len + 1) / 2) {
            k -= len / 2;
          }

          len = (len + 1) / 2;
          break;
        case '%':
          k = len - k - 1;
          break;
        default:
          if (k + 1 == len) {
            return ch;
          }

          len--;
          break;
      }
    }

    return '.';
  }
}