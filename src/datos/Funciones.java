package datos;

import java.util.Random;

/* Clase con funciones auxiliares para realizar los test */

public class Funciones {

	public static void mostrarArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		System.out.println();
	}
	
	public static void cargarArrayAleatorio(int arr[], int min, int max) {
		Random rand = new Random();
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(min, max);
		}
	}
}
