// Time Complexity : Exponential : O(k^n) + (nklognk) -> O(k^n) where k avg length and n blocks
// Space Complexity : O(n), where n is the no. of characters in string s: used in List + recursive stack space
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach
// We create a list of each elements in string s which are in {} and outside and we will iterate over the lists to add
// the character to a stringbuilder
// At the end we will convert the result list to string array and we will sort them for lexicographical order
class Solution {
    List<String> result;
    List<List<Character>> blocks;

    public String[] expand(String s) {
        if (s == null || s.length() == 0) return new String[0];
        blocks = new ArrayList<>();
        result = new ArrayList<>();

        int i=0;
        while (i < s.length()) {
            List<Character> block = new ArrayList<>();
            if (s.charAt(i) == '{') {
                i++;
                while (s.charAt(i) != '}') {
                    if (s.charAt(i) != ',') {
                        block.add(s.charAt(i));
                    }
                    i++;
                }
            }
            else {
                block.add(s.charAt(i));
            }
            i++;
            blocks.add(block);
        }

        backtrack(0, new StringBuilder());

        String[] ans = new String[result.size()];

        for (int j=0; j<result.size(); j++) {
            ans[j] = result.get(j);
        }
        Arrays.sort(ans);

        return ans;
    }

    private void backtrack(int index, StringBuilder sb) {
        // base
        if (index == blocks.size()) {
            result.add(sb.toString());
            return;
        }

        // logic
        List<Character> block = blocks.get(index);
        for (int i=0; i<block.size(); i++) {
            // action
            sb.append(block.get(i));

            // recurse
            backtrack(index+1, sb);         // pass the next index so that we can add new char in the next index

            //backtrack
            sb.setLength(sb.length()-1);    // remove the last element from sb
        }
    }
}