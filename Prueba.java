import java.lang.reflect.Array;
import java.util.ArrayList;

public class Prueba {
	public static byte[] v = {1,2,3,4,5,6,7,8,9,10};
	public static ArrayList<Integer> array1 = new ArrayList<Integer>(),
									 array2 = new ArrayList<Integer>(),
									 array3 = new ArrayList<Integer>();
	public static byte[][] matrix = { { 0, 1, 1, 1, 1, 0, 0, 0, 0, 0 }, 
									 { 1, 0, 0, 1, 1, 1, 1, 0, 0, 0 },
									 { 1, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, 
									 { 1, 1, 0, 0, 0, 0, 0, 0, 1, 0 }, 
									 { 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 },
									 { 0, 1, 0, 0, 0, 0, 0, 1, 1, 0 }, 
									 { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, 
									 { 0, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
									 { 0, 0, 0, 1, 0, 1, 0, 0, 0, 1 }, 
									 { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0 }};

	public static void main(String[] args) {
		// v = new int[n];
		// matrix = new int[n][n];

		
		//Triplet(matrix);
		
		//TripletArr(matrix);
		//TripletArr(matrix);

		//solveMatrixMin(matrix);
		
	}
	
	public static void solveMatrixMin(byte[][] matrix) {
		int minAcum = 0, etiqueta1 = 0, etiqueta2 = 0;

		// Operaciones
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 1) {
					etiqueta1 = v[i];
					etiqueta2 = v[j];
					matrix[j][i] = 0;
					if (etiqueta1 < etiqueta2) {
						minAcum += etiqueta1;
					} else {
						minAcum += etiqueta2;
					}
				}
			}
		}

		System.out.println(minAcum);
	}

	public static void calcularTripletMin(int[] array1, int[] array2, int[] array3) {
		// Operaciones
		int minAcum = 0, etiqueta1 = 0, etiqueta2 = 0;
		
		for (int i = 0; i < array1.length; i++) {
			for(int j = 0; j < array2.length;j++) {
				if(array3[i] != 0) {
					if(array1[i] == array2[j] && array2[i] == array1[j]) {
						etiqueta1 = v[array1[i]];
						etiqueta2 = v[array1[j]];
						array3[j] = 0;
						if (etiqueta1 < etiqueta2) {
							minAcum += etiqueta1;
						} else {
							minAcum += etiqueta2;
						}
					}
				}
			}
			
		}

		System.out.println(minAcum);
	}
	
	//Triplet
		public static void Triplet(byte[][] data) {
			int size = data.length * (data.length / 2);
			int[] array1 = new int[size];
			int[] array2 = new int[size];
			int[] array3 = new int[size];

			int k = 0;
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[0].length; j++) {
					if (data[i][j] != 0) {
						array1[k] = i;
						array2[k] = j;
						array3[k] = data[i][j];
						k++;
					}
				}
			}
			
			calcularTripletMin(array1, array2, array3);
		}
		
		public static void TripletArr(byte[][] data) {
			
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[0].length; j++) {
					if (data[i][j] != 0) {
						array1.add(i);
						array2.add(j);
						array3.add((int) data[i][j]);
					}
				}
			}
			calcularTripletMinArr(array1, array2, array3);
		}
		
		public static void quitarRepetidos(ArrayList<Integer> array1, ArrayList<Integer> array2, ArrayList<Integer> array3) {
			for (int i = 0; i < array1.size(); i++) {
				for(int j = 0; j < array2.size();j++) {
					if(array1.get(i) == array2.get(j) && array2.get(i) == array1.get(j)) {
						array1.remove(j);
						array2.remove(j);
						array3.remove(j);
					}
				}
			}
		}
		
		public static void calcularTripletMinArr(ArrayList<Integer> array1, ArrayList<Integer> array2, ArrayList<Integer> array3) {
			quitarRepetidos(array1, array2, array3);

			int minAcum = 0, etiqueta1 = 0, etiqueta2 = 0;
			
			for (int i = 0; i < array1.size(); i++) {
				etiqueta1 = v[array1.get(i)];
				etiqueta2 = v[array2.get(i)];

				if (etiqueta1 < etiqueta2) {
					minAcum += etiqueta1;
				} else {
					minAcum += etiqueta2;
				}
			}

			System.out.println(minAcum);
		}
		
}
