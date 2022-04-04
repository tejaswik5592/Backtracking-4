// Time Complexity : Exponential
// Space Complexity : O(HW), for queue, visited array, recursive stack space
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach
// We use a combination of backtracking and BFS to solve this question:
// Backtracking will help us to place the buildings and
// BFS will give the min distance from the buildings to the farthest parking spot
public class Main {
    public static class BuildingPlacement {
        int min;

        public int findMinDistance(int H, int W, int N) {
            int[][] grid = new int[H][W];
            min = Integer.MAX_VALUE;

            // -1 indicates parking lot and 0 indicates building
            for (int i=0; i<H; i++) {
                for (int j=0; j<W; j++) {
                    grid[i][j] = -1;
                }
            }

            // use a backtrack function to optimally place the buildings to minimize the min distance
            backtrack(grid, 0, 0, H, W, N);

            return min;
        }

        private void backtrack(int[][] grid, int r, int c, int H, int W, int N) {
            // base
            if (N == 0) {
                bfs(grid, H, W);  // compute the distance to the farthest parking spot from the building
                return;
            }

            // logic
            if (c == W) {
                r++;
                c=0;
            }
            for (int i=r; i<H; i++) {
                for (int j=c; j<W; j++) {
                    //action
                    grid[i][j] = 0;

                    // recurse
                    backtrack(grid, i, j+1, H, W, N-1);     // here, increment col and reduce N

                    //backtrack
                    grid[i][j] = -1;
                }
            }
        }

        private void bfs(int[][] grid, int H, int W) {
            boolean[][] visited = new boolean[H][W];
            int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            Queue<int[]> q = new LinkedList<>();

            // we will add building co-ordinatess to the queue
            for (int i=0; i<H; i++) {
                for (int j=0; j<W; j++) {
                    if (grid[i][j] == 0) {
                        visited[i][j] = true;
                        q.add(new int[]{i, j});
                    }
                }
            }

            int dist = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i=0; i<size; i++) {
                    int[] curr = q.poll();
                    for (int[] dir: dirs) {
                        int nr = dir[0] + curr[0];
                        int nc = dir[1] + curr[1];
                        if (nr >= 0 && nc >= 0 && nr < H && nc < H && visited[nr][nc] == false) {
                            visited[nr][nc] = true;
                            q.add(new int[]{nr, nc});
                        }
                    }
                }
                dist++;
            }

            min = Math.min(min, dist-1);
        }
    }

    public static void main(String[] args) {
        BuildingPlacement bp = new BuildingPlacement();
        System.out.println(bp.findMinDistance(4,4,2));
    }
}
