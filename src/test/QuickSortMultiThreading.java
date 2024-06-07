package test;

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import datos.Funciones;

//Implementación de QuickSort concurrente

@SuppressWarnings("serial")
// Clase que implementa quicksort
// Extiende de la clase RecursiveTask para poder trabajar con hilos
public class QuickSortMultiThreading extends RecursiveTask<Integer> {

	// Tiene como atributos un array y sus posiciones inicio y final
	private int start, end;
	private int[] arr;

	/* Al igual que el algoritmo secuencial elige un pivote y
	lo ubica en la posición correcta, dejando los menores que
	este a la izquierda y los mayores a la derecha del array */
	private int partition(int start, int end, int[] arr) {
		
		// Las variables i y j van a recorrer el array,
		// una desde el principio y otra desde el final
		int i = start, j = end;

		// Elige como pivote un elemento aleatorio del array
		int pivoted = new Random().nextInt(j - i) + i;

		// Intercambia el pivote con el elemento final
		int t = arr[j];
		arr[j] = arr[pivoted];
		arr[pivoted] = t;
		j--; // j se posiciona en el penúltimo elemento

		/* A pesar a ser diferente a la versión secuencial,
		es simplemente otra forma de hacer el método partition
		y funciona de la misma manera. */
		
		// Mientras i sea menor o igual que j 
		while (i <= j) {
			
			// Si la posición i es menor o igual al pivote
			if (arr[i] <= arr[end]) {
				i++;		// i aumenta
				continue;	// la declaración continue salta a la siguiente iteración
							// ignorando el resto del código dentro del while / for				
			}

			// Si la posición j es mayor o igual al pivote
			if (arr[j] >= arr[end]) {
				j--;		// j disminuye
				continue;	// salta a la siguiente iteración
			}
			
			// si ninguna de las anteriores condiciones se cumplen
			// realiza el intercambio entre las posiciones i y j
			t = arr[j];
			arr[j] = arr[i];
			arr[i] = t;
			
			j--;	// j disminuye
			i++;	// i aumenta
		}

		// Deja al pivote en la posición correcta
		t = arr[j + 1];
		arr[j + 1] = arr[end];
		arr[end] = t;
		
		return j + 1;	// Retorna el index del pivote
	}

	// Constructor de la clase
	public QuickSortMultiThreading(int start, int end, int[] arr) {
		this.arr = arr;
		this.start = start;
		this.end = end;
	}

	// Similar al método Thread.run, es la función que se va a ejecutar al lanzar un hilo
	// Utilizada en este caso como función principal de ordenamiento
	@Override
	protected Integer compute() {
		
		/* Este try espera un milisegundo para simular la duración 
		de un proceso más complejo, solo utilizado para testeos */
		/*
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/

		// Caso base para verificar que el array no esté vacío o sea nulo
		if (start >= end || arr == null || arr.length == 0)
			return null;

		// Caso base que se asegura de que el rango de los índices sea válido
		if (start < 0 || end >= arr.length)
			return null;

		// Llamado a la función partition
		int p = partition(start, end, arr);

		// Dividimos el array cortando por el pivote
		// En dos nuevas instancias de la clase
		QuickSortMultiThreading left = new QuickSortMultiThreading(start, p - 1, arr);
		QuickSortMultiThreading right = new QuickSortMultiThreading(p + 1, end, arr);
		
		left.fork();		// Ordenamos el lado izquierdo de forma recursiva en un nuevo hilo
		right.compute();	// Ordenamos el lado derecho de forma recursiva en el hilo actual

		// Espera a que termine el hilo del lado izquierdo
		left.join();

		// Fin del método
		return null;
	}

	// Programa
	public static void main(String args[]) {
		
		// creo un array de n posiciones
		int n = 1000;
		int[] arr = new int[n];

		Funciones.cargarArrayAleatorio(arr, 1, 1000);	// Cargo el array con números aleatorios

		double tiempoInicial = System.nanoTime();	// Tiempo inicial

		// Utilizamos ForkJoin para la creación de hilos a medida que se necesitan
		ForkJoinPool pool = ForkJoinPool.commonPool();

		// Instanciamos la clase
		// Iniciamos el ordenamiento
		pool.invoke(new QuickSortMultiThreading(0, n - 1, arr));

		double tiempoFinal = System.nanoTime() - tiempoInicial;	// Cálculo del tiempo tardado de la función

		// Resultados
		System.out.println("sorted array");
		System.out.println("Tiempo tardado: " + tiempoFinal/1000000);	// Muestro el tiempo que tardó la función en milisegundos
		
		/* Función para comprobar que el array efectivamente está ordenado
		oculto para mejor visibilidad del test */
		
		//Funciones.mostrarArray(arr);
	}
}
// Código proporcionado por venkys.io
// Modificado por Leandro Aranda
