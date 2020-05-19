import java.util.ArrayList;
import java.util.Scanner;

public class Grafo {
	public static int n;
	public static int[][] matriz;
	public static int[] aristas, etiquetas;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		matriz = new int[n][n];
		aristas = new int[n];
		etiquetas = new int[n];

		Integer maxArista = 0, 
				maxAristaAcum = 0, 
				maxAristaAnterior = 0, 
				maxAristaAnteriorAcum = 0;

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = sc.nextInt();
				if (matriz[i][j] == 1) {
					aristas[i]++;
				}
			}

			if (aristas[i] > maxAristaAcum) {
				maxArista = i;
				maxAristaAcum = aristas[i];
			} else if (aristas[i] > maxAristaAnteriorAcum) {
				maxAristaAnterior = i;
				maxAristaAnteriorAcum = aristas[i];
			}
		}
		
		etiquetas[maxArista] = 1;
		etiquetas[maxAristaAnterior] = 2;

		ArrayList<Integer> arre = new ArrayList<Integer>();
		for (int i = 3; i <= n;  i++) {
			arre.add(i);
		}
		
		Permutacion permutacion = new Permutacion(arre);
		
		int aux = 0;
		for (int i = 0; i < n; i++) {
			if (etiquetas[i] != 1 && etiquetas[i] != 2) {
				etiquetas[i] = Permutacion.genotype.get(aux);
				aux++;
			}
			if (aux == Permutacion.genotype.size()) {
				break;
			}
		}

		System.out.println("Etiquetas");
		for (int i = 0; i < n; i++) {
			System.out.print(etiquetas[i] + " ");
		}

	}
}
