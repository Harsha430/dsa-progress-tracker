import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Easy extends GuessGame {

    public Easy(int picked) {
        super(picked);
    }

    // * 1920. Build Array from Permutation
    public int[] buildArray(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[nums[i]];
        }
        return ans;
    }

    // * 374. Guess Number Higher or Lower
    public int guessNumber(int n) {
        int start = 0;
        int end = n;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (guess(mid) == 0) {
                return mid;
            } else if (guess(mid) == 1) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    // * 278. First Bad Version
    public int firstBadVersion(int n) {
        int start = 1;
        int end = n;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    public boolean isBadVersion(int mid) {
        return mid >= 0;
    }

    // * 167. Two Sum II - Input Array Is Sorted
    public int[] twoSum(int[] numbers, int target) {
        int s = 0;
        int e = numbers.length - 1;
        while (s < e) {
            if (numbers[s] + numbers[e] == target) {
                return new int[] { s + 1, e + 1 };
            } else if (numbers[s] + numbers[e] > target) {
                e--;
            } else {
                s++;
            }
        }
        return new int[] { -1, -1 };
    }

    // * 367. Valid Perfect Square
    public boolean isPerfectSquare(int num) {
        long start = 1;
        long end = num;

        while (start <= end) {
            long mid = start + (end - start) / 2;
            long square = mid * mid;

            if (square == num) {
                return true;
            } else if (square < num) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }

    // * 441. Arranging Coins
    public int arrangeCoins(int n) {
        if (n == 1) {
            return 1;
        }
        int start = 1;
        int end = n;
        int ans = 0;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if ((long) mid * (mid + 1) / 2 <= n) {
                ans = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return ans;
    }

    // * 744. Find Smallest Letter Greater Than Target
    public char nextGreatestLetter(char[] letters, char target) {
        int start = 0;
        int end = letters.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (letters[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return letters[start % letters.length];
    }

    // * 1539. Kth Missing Positive Number
    public int findKthPositive(int[] arr, int k) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int missed = arr[mid] - (mid + 1);
            if (missed < k) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start + k;
    }

    // * 35. Search Insert Position
    public int searchInsert(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;
        while (s <= e) {
            int m = s + (e - s) / 2;
            if (nums[m] == target) {
                return m;
            } else if (nums[m] < target) {
                s = m + 1;
            } else {
                e = m - 1;
            }
        }
        return s;
    }

    // * 852. Peak Index in a Mountain Array
    public int peakIndexInMountainArray(int[] arr) {
        int s = 0;
        int e = arr.length - 1;
        while (s < e) {
            int m = s + (e - s) / 2;
            if (arr[m] > arr[m + 1]) {
                e = m;
            } else {
                s = m + 1;
            }
        }
        return s;
    }

    // * 1351. Count Negative Numbers in a Sorted Matrix
    public int countNegatives(int[][] grid) {
        int count = 0;
        for (int[] arr : grid) {
            int start = 0;
            int end = arr.length - 1;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (arr[mid] < 0) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            count += arr.length - start;
        }
        return count;
    }

    // * 349. Intersection of Two Arrays
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (set.contains(num)) {
                list.add(num);
                set.remove(num);
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    // * 350. Intersection of Two Arrays II
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                list.add(num);
                map.put(num, map.get(num) - 1);
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    // * 888. Fair Candy Swap
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int sumA = 0;
        int sumB = 0;
        for (int i : bobSizes)
            sumB += i;
        for (int i : aliceSizes)
            sumA += i;

        int target = (sumA - sumB) / 2;
        Set<Integer> set = new HashSet<>();
        for (int i : bobSizes)
            set.add(i);
        for (int i : aliceSizes) {
            if (set.contains(i - target))
                return new int[] { i, i - target };
        }
        return new int[0];
    }

    // * 1346. Check If N and Its Double Exist
    public boolean checkIfExist(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int x : arr) {
            if (set.contains(2 * x) || (x % 2 == 0 && set.contains(x / 2))) {
                return true;
            }
            set.add(x);
        }
        return false;
    }

    // * 1608. Special Array With X Elements Greater Than or Equal X
    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        for (int x = 0; x <= n; x++) {
            // binary search for first index where nums[idx] >= x
            int left = 0, right = n - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] >= x)
                    right = mid - 1;
                else
                    left = mid + 1;
            }

            int count = n - left;
            if (count == x)
                return x;
        }

        return -1;
    }

    // * 704. Binary Search
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

}