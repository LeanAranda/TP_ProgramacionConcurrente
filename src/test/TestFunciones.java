package test;

import datos.Funciones;

/* Test de prueba de la clase funciones */

public class TestFunciones {

	public static void main(String[] args) {
		
		int tam = 10;
		int array[] = new int[tam];
		
		Funciones.cargarArrayAleatorio(array, 1, 1000);
		
		System.out.println("array");
		Funciones.mostrarArray(array);
	}

}
