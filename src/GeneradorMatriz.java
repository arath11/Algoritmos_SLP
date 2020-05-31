import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class GeneradorMatriz {

	// Atributos
	private int n;
	private int[][] matriz, guia;

	// Constructor
	public GeneradorMatriz(int n) {
		this.n = n;
		this.matriz = new int[this.n][this.n];
		this.guia = new int[(int) Math.sqrt(this.n)][(int) Math.sqrt(this.n)];

		this.generarGuia();
		this.rellenarMatriz();
	}

	// Generar matriz Guía
	public void generarGuia() {
		int cont = 0;
		for (int i = 0; i < this.guia.length; i++) {
			for (int j = 0; j < this.guia[0].length; j++) {
				this.guia[i][j] = cont++;
			}
		}
	}

	// Método para rellanar la matriz
	public void rellenarMatriz() {
		for (int i = 0; i < this.guia.length; i++) {
			for (int j = 0; j < this.guia[0].length; j++) {

				// Primera fila
				if (i == 0) {
					if (j == 0) {
						this.matriz[(int) (this.guia[i][j])][(int) (this.guia[i][j + 1])] = 1;
						this.matriz[(int) (this.guia[i][j])][(int) (this.guia[i + 1][j])] = 1;
					} else if (j + 1 >= this.guia[0].length) {
						this.matriz[(int) (this.guia[i][j])][(int) (this.guia[i][j - 1])] = 1;
						this.matriz[(int) (this.guia[i][j])][(int) (this.guia[i + 1][j])] = 1;
					} else {
						this.matriz[(int) (this.guia[i][j])][(int) (this.guia[i][j - 1])] = 1;
						this.matriz[(int) (this.guia[i][j])][(int) (this.guia[i + 1][j])] = 1;
						this.matriz[(int) (this.guia[i][j])][(int) (this.guia[i][j + 1])] = 1;
					}
					// Ultima fila
				} else if (i + 1 >= this.guia.length) {
					if (j == 0) {
						this.matriz[(int) (this.guia[i][j])][(int) (this.guia[i][j + 1])] = 1;
						this.matriz[(int) (this.guia[i][j])][(int) (this.guia[i - 1][j])] = 1;
					} else if (j + 1 >= this.guia[0].length) {
						this.matriz[(int) (this.guia[i][j])][(int) (this.guia[i][j - 1])] = 1;
						this.matriz[(int) (this.guia[i][j])][(int) (this.guia[i - 1][j])] = 1;
					} else {
						this.matriz[(int) (this.guia[i][j])][(int) (this.guia[i][j - 1])] = 1;
						this.matriz[(int) (this.guia[i][j])][(int) (this.guia[i][j + 1])] = 1;
						this.matriz[(int) (this.guia[i][j])][(int) (this.guia[i - 1][j])] = 1;
					}
				}
				// Las demás filas
				else {
					if (j == 0) {
						this.matriz[(int) (this.guia[i][j])][(int) (this.guia[i - 1][j])] = 1;
						this.matriz[(int) (this.guia[i][j])][(int) (this.guia[i][j + 1])] = 1;
						this.matriz[(int) (this.guia[i][j])][(int) (this.guia[i + 1][j])] = 1;
					} else if (j + 1 >= this.guia[0].length) {
						this.matriz[(int) (this.guia[i][j])][(int) (this.guia[i - 1][j])] = 1;
						this.matriz[(int) (this.guia[i][j])][(int) (this.guia[i][j - 1])] = 1;
						this.matriz[(int) (this.guia[i][j])][(int) (this.guia[i + 1][j])] = 1;
					} else {
						this.matriz[(int) (this.guia[i][j])][(int) (this.guia[i][j - 1])] = 1;
						this.matriz[(int) (this.guia[i][j])][(int) (this.guia[i][j + 1])] = 1;
						this.matriz[(int) (this.guia[i][j])][(int) (this.guia[i - 1][j])] = 1;
						this.matriz[(int) (this.guia[i][j])][(int) (this.guia[i + 1][j])] = 1;
					}
				}
			}
		}
	}

	// Imprimir matriz Guía
	public void printPrinc() {
		for (int i = 0; i < this.guia.length; i++) {
			for (int j = 0; j < this.guia[0].length; j++) {
				System.out.print(this.guia[i][j] + " ");
			}
			System.out.println();
		}
	}

	// Imprimir matriz
	public void printMatriz() {
		for (int i = 0; i < this.matriz.length; i++) {
			for (int j = 0; j < this.matriz[0].length; j++) {
				System.out.print(this.matriz[i][j] + " ");
			}
			System.out.println();
		}
	}

	// Triplet
	public void Triplet(int[][] data) {
		int size = data.length * data[0].length;
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

		this.print(array1, array2, array3);
	}

	// ToString
	public void print(int[] array1, int[] array2, int[] array3) {
		// String msg = "";
		try {
			FileWriter fw = new FileWriter(new File("Triplet.txt"));
			for (int i = 0; i < array1.length; i++) {
				if (array3[i] != 0) {
					fw.write(array1[i] + "     " + array2[i] + "\n");
				}
			}
			
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println(msg);
	}

	public static void main(String[] args) {
		GeneradorMatriz mx = new GeneradorMatriz(144);
		// mx.printMatriz();
		mx.Triplet(mx.matriz);
	}

}
