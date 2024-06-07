package test;

import datos.Funciones;

// Implementación de QuickSort secuencial
public class QuickSort
{
	
	/* Esta función elige un pivote y lo deja en la posición correcta,
	con los números menores este a la izquierda y los mayores a la derecha del array, 
	recibe como parámetros un array, la posición inicial y la final 
	y retorna la posición final del pivote */
	
	int partition(int arr[], int low, int high)
	{
		
		int pivot = arr[high];	// Toma el último elemento del array como pivote
		int i = (low-1); 		// Index de cambio o "swap"
								// Empieza una posición antes ya que avanza cada vez que tiene que hacer el reemplazo
		
		for (int j=low; j<high; j++)	// Recorre el array excepto la última posición (pivote)
		{
			
			// Si el elemento actual es menor o igual al pivote
			if (arr[j] <= pivot)
			{
				i++;	// Avanza el index de cambio

				// Intercambia el elemento actual con el de cambio
				// De esta forma ubicamos los elementos menores a la izquierda
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}

		// Intercambio entre el primer elemento mayor del array y el pivote
		// Ubicando al pivote en la posición correcta
		int temp = arr[i+1];
		arr[i+1] = arr[high];
		arr[high] = temp;

		return i+1;	// Retorna el index del pivote para dividir el array en dos
	}

	
	/* Función principal recursiva para ordenar un array por QuickSort
	recibe como parámetros el array a ordenar, la posición inicial y la final */
	void sort(int arr[], int low, int high)
	{
		/* Este try espera un milisegundo para simular la duración 
		de un proceso más complejo, solo utilizado para testeos */
		/*
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		
		/* Caso base para la recursividad
		comprueba que las posiciones del array sean correctas,
		cuando el array sea de una posición termina*/
		if (low < high)
		{
			// Utilizamos la función partition y guardamos la posición del pivote
			// pi = partitioning index
			int pi = partition(arr, low, high);

			// Se divide el array en dos (antes y después de pi)
			// y se ordena mediante recursividad
			sort(arr, low, pi-1);
			sort(arr, pi+1, high);
		}
	}
	

	// Programa
	public static void main(String args[])
	{
		// creo un array de n posiciones
		int n = 1000;
		int arr[] = new int[n];
		
		Funciones.cargarArrayAleatorio(arr, 1, 1000);	// Cargo el array con números aleatorios
		
		double tiempoInicial = System.nanoTime();	// Tiempo inicial
		
		QuickSort ob = new QuickSort();	// Instancio la clase
		ob.sort(arr, 0, n-1);			// y ordeno el array
		
		double tiempoFinal = System.nanoTime() - tiempoInicial;	// Cálculo del tiempo tardado de la función

		// Resultados
		System.out.println("sorted array");
		System.out.println("Tiempo tardado: " + tiempoFinal/1000000);	// Muestro el tiempo que tardó la función en milisegundos
		
		/* Función para comprobar que el array efectivamente está ordenado
		oculto para mejor visibilidad del test */
		
		//Funciones.mostrarArray(arr);
	}
}
// Código proporcionado por geeksforgeeks.org
// Modificado por Leandro Aranda
