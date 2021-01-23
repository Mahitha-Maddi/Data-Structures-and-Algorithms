/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.functions;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;

/*
 * Shiva Mahitha Maddi
 * 001061161
 */
public class ImageSort {

    ArrayList<Integer> pixelIntensity;

    public ImageSort() {
        pixelIntensity = new ArrayList<Integer>();
        //String filePathName = "C:\\Users\\mahit\\Boston.jpeg";
        try {
            // get the BufferedImage, using the ImageIO class
            BufferedImage image = ImageIO.read(this.getClass().getResource("Boston.jpeg"));

            //BufferedImage image = ImageIO.read(new File(filePathName));
            getPixels(image);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public float printPixelARGB(int pixel) {

        //int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        float intensity = (float) (0.2989 * red + 0.5870 * green + 0.1140 * blue);

        return intensity;
    }

    public void getPixels(BufferedImage image) {

        int w = image.getWidth();
        int h = image.getHeight();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int pixel = image.getRGB(j, i);
                int intens = (int) printPixelARGB(pixel);
                pixelIntensity.add(intens);
            }
        }
    }

    static class SelectionSort {

        void selectionSort(int[] a) {
            int n = a.length;
            for (int i = 0; i < n - 1; i++) {
                /* set current element as minimum*/
                int max = i;

                /* check the element to be minimum */
                for (int j = i + 1; j < n; j++) {
                    if (a[j] > a[max]) {
                        max = j;
                    }
                }

                /* swap the minimum element with the current element*/
                if (max != i) {
                    int temp = a[max];
                    a[max] = a[i];
                    a[i] = temp;
                }
            }
        }

        void print(int arr[]) {
            /* for (int i = 0; i < arr.length; i++) {
		     System.out.print(arr[i] + " ");
		  }*/

            System.out.print(Arrays.toString(arr));
        }
    }
    
      static class QuickSort {
          
          void quickSort(int[] a, int l,int h){
              
              if (h <= l) {
			return;
		}
                  int pi=partition(a,l,h);
                  quickSort(a,l,pi-1);
                  quickSort(a,pi+1,h);
              
          }
          
          int partition(int[] a, int l, int h){
              int pivot=a[l];
              int start=l;
              int end =h;
              while(start<end){
                  while(a[start]>=pivot &&start<end) {start++;}
                  while(a[end]<pivot) {end--;}
                  if(start<end){
                      int temp =a[start];
                      a[start]=a[end];
                      a[end]=temp;
                  }
              }
              int temp1=a[l];
              a[l]=a[end];
              a[end]=temp1;
              return end;
                  
              }
            public void print(int arr[]) {
           
            System.out.print(Arrays.toString(arr));
        }
          
      }

       static class RandomizedQuickSort {
          
          void quickSort(int[] a, int l,int h){
              if(l<h){
                  int pi=randomziedPartition(a,l,h);
                  quickSort(a,l,pi-1);
                  quickSort(a,pi+1,h);
              }
          }
          
         /* int randomziedPartition(int[] a,int l,int h){
             //Random rand= new Random(); 
        int pivot = new Random().nextInt(h-l) + l; 
          
        int temp1=a[pivot]; 
        a[pivot]=a[l]; 
        a[l]=temp1;
        int pi=partition(a,l,h);
        return pi;
          }*/
          
          int randomziedPartition(int[] a, int l, int h){
              int pivotPosition = new Random().nextInt(h-l) + l; 
          
        int temp1=a[pivotPosition]; 
        a[pivotPosition]=a[l]; 
        a[l]=temp1;
              int pivot=a[l];
              int start=l;
              int end =h;
              while(start<end){
                  while(a[start]>=pivot &&start<end) {start++;}
                  while(pivot>a[end]  ) {end--;}
                  if(start<end){
                      int temp =a[start];
                      a[start]=a[end];
                      a[end]=temp;
                  }
              }
              int temp2=a[l];
              a[l]=a[end];
              a[end]=temp2;
              return end;
                  
              }
            public void print(int arr[]) {
           
            System.out.print(Arrays.toString(arr));
        }
          
      }

    static class InsertionSort {

        void insertionSort(int[] a) {
            int holePosition;
            int valueToInsert;
            int n = a.length;
            for (int i = 1; i < n; i++) {
                valueToInsert = a[i];
                holePosition = i;

                /*locate hole position for the element to be inserted */
                while (holePosition > 0 && a[holePosition - 1] < valueToInsert) {
                    a[holePosition] = a[holePosition - 1];
                    holePosition = holePosition - 1;
                }

                /* insert the number at hole position */
                a[holePosition] = valueToInsert;
            }
        }

        public void print(int arr[]) {
            /* for (int i = 0; i < arr.length; i++) {
		     System.out.print(arr[i] + " ");
		  }*/

            System.out.print(Arrays.toString(arr));
        }
    }

    static class BubbleSort {

        void bubbleSort(int arr[]) {
            int n = arr.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (arr[j] < arr[j + 1]) {
                        // swap arr[j+1] and arr[i] 
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }

        public void print(int arr[]) {
            /* for (int i = 0; i < arr.length; i++) {
		     System.out.print(arr[i] + " ");
		  }*/

            System.out.print(Arrays.toString(arr));
        }

    }

    static class TimSort32 {

        static int RUN = 32;

        // this function sorts array from left index to  
        // to right index which is of size atmost RUN  
        public static void insertionSort(int[] arr, int left, int right) {
            for (int i = left + 1; i <= right; i++) {
                int temp = arr[i];
                int j = i - 1;
                while (j >= left && arr[j] < temp) {
                    arr[j + 1] = arr[j];
                    j--;
                }
                arr[j + 1] = temp;
            }
        }

        // merge function merges the sorted runs  
        public static void merge(int[] arr, int l,
                int m, int r) {
            // original array is broken in two parts  
            // left and right array  
            int len1 = m - l + 1, len2 = r - m;
            int[] left = new int[len1];
            int[] right = new int[len2];
            for (int x = 0; x < len1; x++) {
                left[x] = arr[l + x];
            }
            for (int x = 0; x < len2; x++) {
                right[x] = arr[m + 1 + x];
            }

            int i = 0;
            int j = 0;
            int k = l;

            // after comparing, we merge those two array  
            // in larger sub array  
            while (i < len1 && j < len2) {
                if (left[i] >= right[j]) {
                    arr[k] = left[i];
                    i++;
                } else {
                    arr[k] = right[j];
                    j++;
                }
                k++;
            }

            // copy remaining elements of left, if any  
            while (i < len1) {
                arr[k] = left[i];
                k++;
                i++;
            }

            // copy remaining element of right, if any  
            while (j < len2) {
                arr[k] = right[j];
                k++;
                j++;
            }
        }

        // iterative Timsort function to sort the  
        // array[0...n-1] (similar to merge sort)  
        public static void timSort(int[] arr, int n) {

            // Sort individual subarrays of size RUN  
            for (int i = 0; i < n; i += RUN) {
                insertionSort(arr, i, Math.min((i + 31), (n - 1)));
            }

            // start merging from size RUN (or 32). It will merge  
            // to form size 64, then 128, 256 and so on ....  
            for (int size = RUN; size < n; size = 2 * size) {

                // pick starting point of left sub array. We  
                // are going to merge arr[left..left+size-1]  
                // and arr[left+size, left+2*size-1]  
                // After every merge, we increase left by 2*size  
                for (int left = 0; left < n; left += 2 * size) {

                    // find ending point of left sub array  
                    // mid+1 is starting point of right sub array  
                   // int mid = left + size - 1;
                    int right = Math.min((left + 2 * size - 1), (n - 1));
                    int mid = (left + right) / 2;

                    // merge sub array arr[left.....mid] &  
                    // arr[mid+1....right]  
                    merge(arr, left, mid, right);
                }
            }
        }
    
     public void print(int arr[]) {
            /* for (int i = 0; i < arr.length; i++) {
		     System.out.print(arr[i] + " ");
		  }*/

            System.out.print(Arrays.toString(arr));
        }
    }

        static class TimSort64 {

            static int RUN = 64;

            // this function sorts array from left index to  
            // to right index which is of size atmost RUN  
            public static void insertionSort(int[] arr, int left, int right) {
                for (int i = left + 1; i <= right; i++) {
                    int temp = arr[i];
                    int j = i - 1;
                    while (j >= left && arr[j] < temp) {
                        arr[j + 1] = arr[j];
                        j--;
                    }
                    arr[j + 1] = temp;
                }
            }

            // merge function merges the sorted runs  
            public static void merge(int[] arr, int l,
                    int m, int r) {
                // original array is broken in two parts  
                // left and right array  
                int len1 = m - l + 1, len2 = r - m;
                int[] left = new int[len1];
                int[] right = new int[len2];
                for (int x = 0; x < len1; x++) {
                    left[x] = arr[l + x];
                }
                for (int x = 0; x < len2; x++) {
                    right[x] = arr[m + 1 + x];
                }

                int i = 0;
                int j = 0;
                int k = l;

                // after comparing, we merge those two array  
                // in larger sub array  
                while (i < len1 && j < len2) {
                    if (left[i] >= right[j]) {
                        arr[k] = left[i];
                        i++;
                    } else {
                        arr[k] = right[j];
                        j++;
                    }
                    k++;
                }

                // copy remaining elements of left, if any  
                while (i < len1) {
                    arr[k] = left[i];
                    k++;
                    i++;
                }

                // copy remaining element of right, if any  
                while (j < len2) {
                    arr[k] = right[j];
                    k++;
                    j++;
                }
            }

            // iterative Timsort function to sort the  
            // array[0...n-1] (similar to merge sort)  
            public static void timSort(int[] arr, int n) {

                // Sort individual subarrays of size RUN  
                for (int i = 0; i < n; i += RUN) {
                    insertionSort(arr, i, Math.min((i + 63), (n - 1)));
                }

                // start merging from size RUN (or 32). It will merge  
                // to form size 64, then 128, 256 and so on ....  
                for (int size = RUN; size < n; size = 2 * size) {

                    // pick starting point of left sub array. We  
                    // are going to merge arr[left..left+size-1]  
                    // and arr[left+size, left+2*size-1]  
                    // After every merge, we increase left by 2*size  
                    for (int left = 0; left < n; left += 2 * size) {

                        // find ending point of left sub array  
                        // mid+1 is starting point of right sub array  
                       // int mid = left + size - 1;
                        int right = Math.min((left + 2 * size - 1), (n - 1));
                        int mid = (left + right)/2;
                        // merge sub array arr[left.....mid] &  
                        // arr[mid+1....right]  
                        merge(arr, left, mid, right);
                    }
                }
            }
             public void print(int arr[]) {
           
            System.out.print(Arrays.toString(arr));
        }
        }
        
        
        
        //Main method----------------------------------------------------------------------------------------------------------------------------------------------
            public static void main(String args[]) {
                ImagePixelsSort obj = new ImagePixelsSort();

                int[] arr = new int[obj.pixelIntensity.size()];

                for (int i = 0; i < obj.pixelIntensity.size(); i++) {
                    if (obj.pixelIntensity.get(i) != null) {
                        arr[i] = obj.pixelIntensity.get(i);
                    }
                }

                System.out.println("Array length to sort: " + arr.length);

                System.out.println("Array before sorting : " + Arrays.toString(arr));

                System.out.println("-------------------------------------------------------------------");
                
                
                
                //Quick Sort 
                Instant start;
                Instant end;
                int[] arr6 = new int[obj.pixelIntensity.size()];
                for (int i = 0; i < obj.pixelIntensity.size(); i++) {
                    if (obj.pixelIntensity.get(i) != null) {
                        arr6[i] = obj.pixelIntensity.get(i);
                    }
                }
                int l=0;
                int h=arr6.length-1;
                QuickSort qs = new QuickSort();
                
                start = Instant.now();
                qs.quickSort(arr6,l,h);
                end = Instant.now();
                Duration qsTime = Duration.between(start, end);
                System.out.println("printing array after Quick sort");
                qs.print(arr6);
                System.out.println();
                System.out.println("Quick Sort Time in nanoseconds: " + qsTime.getNano());

                System.out.println("-------------------------------------------------------------------");
                
                
                
                
                //Quick Sort - Randomized
                int[] arr7 = new int[obj.pixelIntensity.size()];
                for (int i = 0; i < obj.pixelIntensity.size(); i++) {
                    if (obj.pixelIntensity.get(i) != null) {
                        arr7[i] = obj.pixelIntensity.get(i);
                    }
                }
                int l1=0;
                int h1=arr7.length-1;
                RandomizedQuickSort qs1 = new RandomizedQuickSort();
                
                start = Instant.now();
                qs1.quickSort(arr7,l1,h1);
                end = Instant.now();
                Duration qsTime1 = Duration.between(start, end);
                System.out.println("printing array after Randomized Quick sort");
                qs1.print(arr7);
                System.out.println();
                System.out.println("Randomized Quick Sort Time in nanoseconds: " + qsTime1.getNano());

                System.out.println("-------------------------------------------------------------------");

                // Selection Sort
                int[] arr1 = new int[obj.pixelIntensity.size()];
                for (int i = 0; i < obj.pixelIntensity.size(); i++) {
                    if (obj.pixelIntensity.get(i) != null) {
                        arr1[i] = obj.pixelIntensity.get(i);
                    }
                }
                start = Instant.now();
                SelectionSort ss = new SelectionSort();
                ss.selectionSort(arr1);
                end = Instant.now();
                Duration ssTime = Duration.between(start, end);
                System.out.println("printing array after Selection sort");
                ss.print(arr1);
                System.out.println();
                System.out.println("Selection Sort Time in nanoseconds: " + ssTime.getNano());

                System.out.println("-------------------------------------------------------------------");

                // Insertion Sort
                int[] arr2 = new int[obj.pixelIntensity.size()];
                for (int i = 0; i < obj.pixelIntensity.size(); i++) {
                    if (obj.pixelIntensity.get(i) != null) {
                        arr2[i] = obj.pixelIntensity.get(i);
                    }
                }
                start = Instant.now();
                InsertionSort is = new InsertionSort();
                is.insertionSort(arr2);
                end = Instant.now();
                Duration isTime = Duration.between(start, end);
                System.out.println("printing array after Insertion sort");
                is.print(arr2);
                System.out.println();
                System.out.println("Insertion Sort Time in nanoseconds: " + isTime.getNano());

                System.out.println("-------------------------------------------------------------------");

                // Bubble Sort
                int[] arr3 = new int[obj.pixelIntensity.size()];
                for (int i = 0; i < obj.pixelIntensity.size(); i++) {
                    if (obj.pixelIntensity.get(i) != null) {
                        arr3[i] = obj.pixelIntensity.get(i);
                    }
                }
                start = Instant.now();
                BubbleSort bs = new BubbleSort();
                bs.bubbleSort(arr3);
                end = Instant.now();
                Duration bsTime = Duration.between(start, end);
                System.out.println("printing array after Bubble sort");
                bs.print(arr3);
                System.out.println();
                System.out.println("Bubble Sort Time in nanoseconds: " + bsTime.getNano());

                System.out.println("-------------------------------------------------------------------");
                
                // Tim Sort -- Run 32
                int[] arr4 = new int[obj.pixelIntensity.size()];
                for (int i = 0; i < obj.pixelIntensity.size(); i++) {
                    if (obj.pixelIntensity.get(i) != null) {
                        arr4[i] = obj.pixelIntensity.get(i);
                    }
                }
                int n=arr4.length;
                start = Instant.now();
                TimSort32 ts32 = new TimSort32();
                ts32.timSort(arr4,n);
                end = Instant.now();
                Duration tsTime32 = Duration.between(start, end);
                System.out.println("printing array after Tim sort with Run 32");
                ts32.print(arr4);
                System.out.println();
                System.out.println("Tim Sort(Run - 32) Time in nanoseconds: " + tsTime32.getNano());

                System.out.println("-------------------------------------------------------------------");
            
            
            // Tim Sort -- Run 64
                int[] arr5 = new int[obj.pixelIntensity.size()];
                for (int i = 0; i < obj.pixelIntensity.size(); i++) {
                    if (obj.pixelIntensity.get(i) != null) {
                        arr5[i] = obj.pixelIntensity.get(i);
                    }
                }
                int n1=arr5.length;
                start = Instant.now();
                TimSort64 ts64 = new TimSort64();
                ts64.timSort(arr5,n1);
                end = Instant.now();
                Duration tsTime64 = Duration.between(start, end);
                System.out.println("printing array after Tim sort -- Run 64");
                ts32.print(arr5);
                System.out.println();
                System.out.println("Tim Sort (Run - 64) Time in nanoseconds: " + tsTime64.getNano());

                System.out.println("-------------------------------------------------------------------");
            }
        }
