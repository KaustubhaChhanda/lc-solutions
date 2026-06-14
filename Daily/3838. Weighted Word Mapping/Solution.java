class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            int weight = 0;
            for (char ch : word.toCharArray()) {
                weight += weights[ch - 'a'];
            }

            int mod = weight % 26;
            sb.append((char) ('z' - mod));
        }

        return sb.toString();
    }
}