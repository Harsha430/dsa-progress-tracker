import java.util.Arrays;

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
}
