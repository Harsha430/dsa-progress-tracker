import java.util.Arrays;
import java.util.PriorityQueue;

//there's nothing special about me feel free to leave if you're bored with me
public class Medium {

    // ? 34. Find First and Last Position of Element in Sorted Array
    public int[] searchRange(int[] nums, int target) {
        int first = findBound(nums, target, true);
        int last = findBound(nums, target, false);
        return new int[] { first, last };
    }

    private int findBound(int[] nums, int target, boolean findFirst) {
        int left = 0, right = nums.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                result = mid;
                if (findFirst) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    // ? 540. Single Element in a Sorted Array
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (mid % 2 == 1)
                mid--;
            if (nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }

    // ? 33. Search in Rotated Sorted Array
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target)
                return mid;

            // Left half is sorted
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // Right half is sorted
            else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    // ? 81. Search in Rotated Sorted Array II
    public boolean searchII(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target)
                return true;

            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
                continue;
            }
            // Left half is sorted
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // Right half is sorted
            else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return false;
    }

    // ? 153. Find Minimum in Rotated Sorted Array
    public int findMin(int[] nums) {
        int s = 0;
        int e = nums.length - 1;
        while (s < e) {
            int mid = s + (e - s) / 2;
            if (nums[e] < nums[mid]) {
                s = mid + 1;
            } else {
                e = mid;
            }
        }
        return nums[s];
    }

    // ? 162. Find Peak Element
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // ? 436. Find Right Interval
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[] res = new int[n];

        int[][] temp = new int[n][2];
        for (int i = 0; i < n; i++) {
            temp[i][0] = intervals[i][0];
            temp[i][1] = i;
        }

        Arrays.sort(temp, (a, b) -> a[0] - b[0]);

        for (int i = 0; i < n; i++) {
            int end = intervals[i][1];

            int idx = binarySearch(temp, end);
            res[i] = idx;
        }
        return res;
    }

    static int binarySearch(int[][] arr, int target) {
        int left = 0, right = arr.length - 1;
        int answer = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid][0] > target) {
                answer = arr[mid][1];
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return answer;
    }

    // ? 754. Reach a Number
    public int reachNumber(int target) {
        target = Math.abs(target); // symmetry (left or right doesn't matter)

        int sum = 0;
        int moves = 0;

        // Keep increasing moves until we reach or exceed target
        while (sum < target || (sum - target) % 2 != 0) {
            moves++;
            sum += moves;
        }

        return moves;
    }

    // ? 1802. Maximum Value at a Given Index in a Bounded Array
    // public int maxValue(int n, int index, int maxSum) {
    // int[] nums = new int[n];
    // int sum = maxSum;
    // for (int i = 0; i < n; i++) {

    // }
    // }

    // ? 875. Koko Eating Bananas
    public int minEatingSpeed(int[] piles, int h) {

        int left = 1, high = 0;
        for (int pile : piles) {
            high = Math.max(high, pile);
        }

        int answer = high;

        while (left <= high) {
            int mid = left + (high - left) / 2;
            long totalHours = 0;

            for (int pile : piles) {
                totalHours += (pile + mid - 1) / mid;
            }
            if (totalHours <= h) {
                answer = mid;
                high = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return answer;
    }

    // ? 1818. Minimum Absolute Sum Difference
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {

        int MOD = 1_000_000_007;
        int n = nums1.length;

        int[] sorted = nums1.clone();
        Arrays.sort(sorted);

        long total = 0;
        long maxGain = 0;
        for (int i = 0; i < n; i++) {
            int a = nums1[i];
            int b = nums2[i];
            long diff = Math.abs(a - b);
            total = (total + diff) % MOD;

            int j = Arrays.binarySearch(sorted, b);
            if (j < 0) {
                j = -j - 1;
            }

            if (j < n) {
                maxGain = Math.max(maxGain, diff - Math.abs(sorted[j] - b));
            }
            if (j > 0) {
                maxGain = Math.max(maxGain, diff - Math.abs(sorted[j - 1] - b));
            }
        }

        return (int) ((total - maxGain + MOD) % MOD);
    }

    // ? 1834. Single-Threaded CPU
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[] order = new int[n];
        int[][] sortedTasks = new int[n][3];

        for (int i = 0; i < n; i++) {
            sortedTasks[i][0] = tasks[i][0];
            sortedTasks[i][1] = tasks[i][1];
            sortedTasks[i][2] = i;
        }

        Arrays.sort(sortedTasks, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) {
                return Integer.compare(a[2], b[2]);
            }
            return Integer.compare(a[1], b[1]);
        });

        int currentTime = 0;
        int taskIndex = 0;
        int orderIndex = 0;

        while (taskIndex < n || !minHeap.isEmpty()) {
            if (minHeap.isEmpty() && currentTime < sortedTasks[taskIndex][0]) {
                currentTime = sortedTasks[taskIndex][0];
            }

            while (taskIndex < n && currentTime >= sortedTasks[taskIndex][0]) {
                minHeap.offer(sortedTasks[taskIndex]);
                taskIndex++;
            }

            if (!minHeap.isEmpty()) {
                int[] currentTask = minHeap.poll();
                order[orderIndex++] = currentTask[2];
                currentTime += currentTask[1];
            }
        }

        return order;
    }

    // ? 74. Search a 2D Matrix
    public boolean searchMatrix(int[][] matrix , int target) {
        int row = 0, col = matrix[0].length-1;

        while(row<matrix.length && col>=0){
            if(matrix[row][col] == target){
                return true;
            }else if(target < matrix[row][col]){
                col--;
            }else{
                row++;
            }
        }
        return false;
    }

    // ? 1901. Find a Peak Element II
    public int[] findPeakGrid(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        int left = 0;
        int right = cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Step 1: Find row index of max element in column mid
            int maxRow = 0;
            for (int i = 0; i < rows; i++) {
                if (mat[i][mid] > mat[maxRow][mid]) {
                    maxRow = i;
                }
            }

            // Step 2: Check left and right neighbors
            int leftVal = (mid - 1 >= 0) ? mat[maxRow][mid - 1] : -1;
            int rightVal = (mid + 1 < cols) ? mat[maxRow][mid + 1] : -1;

            // Step 3: Peak condition
            if (mat[maxRow][mid] > leftVal && mat[maxRow][mid] > rightVal) {
                return new int[]{maxRow, mid};
            }

            // Step 4: Move search space
            if (leftVal > mat[maxRow][mid]) {
                right = mid - 1;  // search left side
            } else {
                left = mid + 1;   // search right side
            }
        }

        return new int[]{-1, -1};  // shouldn't reach here
    }

    // ? 1838. Frequency of the Most Frequent Element
    public int maxFrequency(int[] nums, int k) {
        
    }

}
