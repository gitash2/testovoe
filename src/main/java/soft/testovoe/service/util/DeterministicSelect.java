package soft.testovoe.service.util;

import java.util.Arrays;

public class DeterministicSelect {
    public static int select(int[] arr, int k) {
        if (arr.length == 1) {
            return arr[0];
        }

        int pivot = medianOfMedians(arr);
        int[] lows = Arrays.stream(arr).filter(x -> x < pivot).toArray();
        int[] highs = Arrays.stream(arr).filter(x -> x > pivot).toArray();
        int[] pivots = Arrays.stream(arr).filter(x -> x == pivot).toArray();

        if (k < lows.length) {
            return select(lows, k);
        } else if (k < lows.length + pivots.length) {
            return pivot;
        } else {
            return select(highs, k - lows.length - pivots.length);
        }
    }

    private static int medianOfMedians(int[] arr) {
        if (arr.length <= 5) {
            Arrays.sort(arr);
            return arr[arr.length / 2];
        }

        int numGroups = (int) Math.ceil((double) arr.length / 5);
        int[] medians = new int[numGroups];

        for (int i = 0; i < numGroups; i++) {
            int start = i * 5;
            int end = Math.min(start + 5, arr.length);
            int[] group = Arrays.copyOfRange(arr, start, end);
            Arrays.sort(group);
            medians[i] = group[group.length / 2];
        }

        return medianOfMedians(medians);
    }
}
