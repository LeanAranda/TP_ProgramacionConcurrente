package test;

//Copyrights to venkys.io
//For more programs, visit venkys.io
//Java program for Multi-threaded quick sort

//Time Complexity: O(N*log N) 
//Space Complexity: O(N)

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import datos.Funciones;

@SuppressWarnings("serial")
public class QuickSortMultiThreading extends RecursiveTask<Integer> {

 private int start, end;
 private int[] arr;

 /*
 * Finding random pivoted and partition
 */
 private int partition(int start, int end, int[] arr) {
     int i = start, j = end;

     // Decide random pivot
     int pivoted = new Random().nextInt(j - i) + i;

     // Swap the pivoted with end
     // element of array;
     int t = arr[j];
     arr[j] = arr[pivoted];
     arr[pivoted] = t;
     j--;

     // Start partitioning
     while (i <= j) {
         if (arr[i] <= arr[end]) {
             i++;
             continue;
         }

         if (arr[j] >= arr[end]) {
             j--;
             continue;
         }

         t = arr[j];
         arr[j] = arr[i];
         arr[i] = t;
         j--;
         i++;
     }

     // Swap pivoted to its
     // correct position
     t = arr[j + 1];
     arr[j + 1] = arr[end];
     arr[end] = t;
     return j + 1;
 }

 // Function to implement
 // QuickSort method
 public QuickSortMultiThreading(int start, int end, int[] arr) {
     this.arr = arr;
     this.start = start;
     this.end = end;
 }

 @Override
 protected Integer compute() {
	
	/*
	try {
		Thread.sleep(1);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	*/
	 
     // Base case for null safety
     if (start >= end || arr == null || arr.length == 0)
         return null;

     // Ensure indices are within the valid range
     if (start < 0 || end >= arr.length)
         return null;

     // Find partition
     int p = partition(start, end, arr);

     // Divide array
     QuickSortMultiThreading left = new QuickSortMultiThreading(start, p - 1, arr);
     QuickSortMultiThreading right = new QuickSortMultiThreading(p + 1, end, arr);

     // Left subproblem as a separate thread
     left.fork();
     right.compute();

     // Wait until the left thread completes
     left.join();

     // We don't want anything as return
     return null;
 }

 // Driver Code
public static void main(String args[]) {
	 
	 int n = 10000;
     int[] arr = new int[n];

     Funciones.cargarArrayAleatorio(arr, 1, 1000);

     double tiempoInicial = System.nanoTime();
     
     // ForkJoin ThreadPool to keep
     // thread creation as per resources
     ForkJoinPool pool = ForkJoinPool.commonPool();

     // Start the first thread in fork
     // join pool for range 0, n-1
     pool.invoke(new QuickSortMultiThreading(0, n - 1, arr));

     double tiempoFinal = System.nanoTime() - tiempoInicial;
     
     // Print sorted elements
     System.out.println("sorted array");
     System.out.println("Tiempo tardado: " + tiempoFinal/1000000);
     //Funciones.mostrarArray(arr);
 }
}