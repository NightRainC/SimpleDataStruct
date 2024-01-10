package com.fc;

public class SortTool {
    private SortTool() {
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    //冒泡排序
    public static void BubbleSort(int[] arr, boolean desc) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (desc) {
                    if (arr[j] < arr[j + 1]) {
                        swap(arr, j, j + 1);
                    }
                } else {
                    if (arr[j] > arr[j + 1]) {
                        swap(arr, j, j + 1);
                    }
                }
            }
        }
    }

    public static void BubbleSort(int[] arr) {
        BubbleSort(arr, false);
    }

    //选择排序
    public static void SelectSort(int[] arr, boolean desc) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (desc) {
                    if (arr[i] < arr[j]) swap(arr, i, j);
                } else {
                    if (arr[i] > arr[j]) swap(arr, i, j);
                }
            }
        }
    }

    public static void SelectSort(int[] arr){
        SelectSort(arr,false);
    }





}
