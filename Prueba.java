
public class Prueba {
	public static int[] v = {1,2,3,4,5};
	public static int[][] matrix = {{0,1,1,0,0},
			                        {1,0,0,1,0},
			                        {1,0,0,1,0},
			                        {0,1,1,0,0},
			                        {0,0,1,0,0}};
	
	public static void main(String[] args) {
		int n = 5;
		//v = new int[n];
		//matrix = new int[n][n];
		
		//Rellenar
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				
			}
		}
		
		int minAcum = 0,
			etiqueta1 = 0,
			etiqueta2 = 0;
		
		//Operaciones
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				if(matrix[i][j] == 1) {
					etiqueta1 = v[i];
					etiqueta2 = v[j];
					matrix[j][i] = 0;
					if(etiqueta1 < etiqueta2) {
						minAcum += etiqueta1;
					} else {
						minAcum += etiqueta2;
					}
				}
			}
		}
		
		System.out.println(minAcum);
		
		
	}
}
