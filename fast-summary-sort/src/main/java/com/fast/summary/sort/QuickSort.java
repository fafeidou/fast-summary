package com.fast.summary.sort;

import java.util.Arrays;

import static com.fast.summary.sort.Utils.swap;

/**
 * 1. 选择最右元素作为基准点元素
 *
 * 2. j 指针负责找到比基准点小的元素，一旦找到则与 i 进行交换
 *
 * 3. i 指针维护小于基准点元素的边界，也是每次交换的目标索引
 *
 * 4. 最后基准点与 i 交换，i 即为分区位置
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] a = {5, 3, 7, 2, 9, 8, 1, 4};
        System.out.println(Arrays.toString(a));
        quick(a, 0, a.length - 1);
    }

    public static void quick(int[] a, int l, int h) {
        if (l >= h) {
            return;
        }
        int p = partition(a, l, h); // p 索引值
        quick(a, l, p - 1); // 左边分区的范围确定
        quick(a, p + 1, h); // 左边分区的范围确定
    }

    private static int partition(int[] a, int l, int h) {
        int pv = a[h];
        int i = l;

        for (int j = l; j < h; j++) {
            if (a[j] < pv) {
                if (i != j) {
                    swap(a, i, j);
                }
                i++;
            }
        }

        if (i != h) {
            swap(a, i, h);
        }
        System.out.println(Arrays.toString(a) + " i=" + i);
        // 返回值代表了基准点元素所在的正确索引，用它确定下一轮分区的边界
        return i;
    }
}
