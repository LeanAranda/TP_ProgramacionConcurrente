package datos;

import java.util.Random;

public class Funciones {

	public static void mostrarArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+" ");
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
