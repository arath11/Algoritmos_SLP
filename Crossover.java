import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Crossover {
    public static ArrayList<Integer> stick = new ArrayList<Integer>(),
            stick2 = new ArrayList<Integer>(),
            hijo1,
            hijo2;


    public static void combinar(ArrayList<Integer> etiquetas, ArrayList<Integer> etiquetas2) {

        //esto imprime para ver primero las dos entradas
        //imprimir(1);

        //asignamos los hijos al tamaño
        hijo1=new ArrayList<Integer>();
        hijo2=new ArrayList<Integer>();


        //definir punto de corte 1
        int corte1=etiquetas.size()/2-1;

        //definir punto de corte 2
        int corte2=etiquetas.size()/2;

        //System.out.println("Primer for:");
        //izquierda
        for(int i = 0; i < corte1; i++) {
            hijo1.add(etiquetas.get(i));
        }

        //centro
        for(int i = corte1; i < corte2+1; i++) {
            hijo1.add(etiquetas2.get(i));
        }

        //System.out.println("Segundo for:");
        //derecha
        for(int i = corte2+1; i < etiquetas.size(); i++) {
            hijo1.add(etiquetas.get(i));
        }


        //izqueirda
        //System.out.println("Primer for:");
        for(int i = 0; i < corte1; i++) {
            hijo2.add(etiquetas2.get(i));
        }

        //centro
        for(int i = corte1; i < corte2+1; i++) {
            hijo2.add(etiquetas.get(i));
        }

        //derecha
        //System.out.println("Segundo for:");
        for(int i = corte2+1; i < etiquetas.size(); i++) {
            hijo2.add(etiquetas2.get(i));
        }



        //caso 1, todos son iguales y no se hace nada
        if(hijo1.get(corte1)==hijo2.get(corte2) && hijo1.get(corte2)==hijo2.get(corte1)) {

            //caso 2, dos son iguales
        }
        else if(hijo1.get(corte1)==hijo2.get(corte1) && hijo1.get(corte2)==hijo2.get(corte2)) {

            //caso 2, dos son iguales
        }
        else if(hijo1.get(corte1)==hijo2.get(corte2) || hijo1.get(corte2)==hijo2.get(corte1)){

            if(hijo1.get(corte1)==hijo2.get(corte2)){
                //hijo 1 parte izquierda
                for(int i = 0; i < corte1; i++) {
                    if(hijo1.get(i) == hijo1.get(corte2)){
                        hijo1.set(i, hijo2.get(corte1));
                    } else if(hijo1.get(i) == hijo1.get(corte2)){
                        hijo1.set(i, hijo2.get(corte1));
                    }
                }
                //hijo 1 parte derecha
                for(int i = corte2+1; i < etiquetas.size(); i++) {
                    if(hijo1.get(i) == hijo1.get(corte2)){
                        hijo1.set(i, hijo2.get(corte1));
                    } else if(hijo1.get(i) == hijo1.get(corte2)){
                        hijo1.set(i, hijo2.get(corte1));
                    }
                }

                //hijo 2 parte izquierda
                for(int i = 0; i < corte1; i++) {
                    if(hijo2.get(i) == hijo2.get(corte1)){
                        hijo2.set(i, hijo1.get(corte2));
                    } else if(hijo2.get(i) == hijo2.get(corte1)){
                        hijo2.set(i, hijo1.get(corte2));
                    }
                }
                //hijo 2 parte derecha
                for(int i = corte2+1; i < etiquetas.size(); i++) {
                    if(hijo2.get(i) == hijo2.get(corte1)){
                        hijo2.set(i, hijo1.get(corte2));
                    } else if(hijo2.get(i) == hijo2.get(corte1)){
                        hijo2.set(i, hijo1.get(corte2));
                    }
                }

            }else if (hijo1.get(corte2)==hijo2.get(corte1)) {
                //hijo 1 parte izquierda
                for (int i = 0; i < corte1; i++) {
                    if (hijo1.get(i) == hijo1.get(corte1)) {
                        hijo1.set(i, hijo2.get(corte2));
                    } else if (hijo1.get(i) == hijo1.get(corte1)) {
                        hijo1.set(i, hijo2.get(corte2));
                    }
                }
                //hijo 1 parte derecha
                for (int i = corte2 + 1; i < etiquetas.size(); i++) {
                    if (hijo1.get(i) == hijo1.get(corte1)) {
                        hijo1.set(i, hijo2.get(corte2));
                    } else if (hijo1.get(i) == hijo1.get(corte1)) {
                        hijo1.set(i, hijo2.get(corte2));
                    }
                }

                //hijo 2 parte izquierda
                for (int i = 0; i < corte1; i++) {
                    if (hijo2.get(i) == hijo2.get(corte2)) {
                        hijo2.set(i, hijo1.get(corte1));
                    } else if (hijo2.get(i) == hijo2.get(corte2)) {
                        hijo2.set(i, hijo1.get(corte1));
                    }
                }
                //hijo 2 parte derecha
                for (int i = corte2 + 1; i < etiquetas.size(); i++) {
                    if (hijo2.get(i) == hijo2.get(corte2)) {
                        hijo2.set(i, hijo1.get(corte1));
                    } else if (hijo2.get(i) == hijo2.get(corte2)) {
                        hijo2.set(i, hijo1.get(corte1));
                    }
                }
            }
        }//caso base, ninguno es igu
        else if(hijo1.get(corte1)!=hijo2.get(corte2) || hijo1.get(corte2)!=hijo2.get(corte1)){
            //hijo 1 parte izquierda
            for(int i = 0; i < corte1; i++) {
                if(hijo1.get(i) == hijo1.get(corte1)){
                    hijo1.set(i, hijo2.get(corte1));
                } else if(hijo1.get(i) == hijo1.get(corte2)){
                    hijo1.set(i, hijo2.get(corte2));
                }
            }
            //hijo 1 parte derecha
            for(int i = corte2+1; i < etiquetas.size(); i++) {
                if(hijo1.get(i) == hijo1.get(corte1)){
                    hijo1.set(i, hijo2.get(corte1));
                } else if(hijo1.get(i) == hijo1.get(corte2)){
                    hijo1.set(i, hijo2.get(corte2));
                }
            }

            //hijo 2 parte izquierda
            for(int i = 0; i < corte1; i++) {
                if(hijo2.get(i) == hijo2.get(corte1)){
                    hijo2.set(i, hijo1.get(corte1));
                } else if(hijo2.get(i) == hijo2.get(corte2)){
                    hijo2.set(i, hijo1.get(corte2));
                }
            }
            //hijo 2 parte derecha
            for(int i = corte2+1; i < etiquetas.size(); i++) {
                if(hijo2.get(i) == hijo2.get(corte1)){
                    hijo2.set(i, hijo1.get(corte1));
                } else if(hijo2.get(i) == hijo2.get(corte2)){
                    hijo2.set(i, hijo1.get(corte2));
                }
            }
        }
    }

    public static void imprimir(int entrada) {
        if(entrada==1){
            System.out.println();
            System.out.println("Etiquetas");
            for (int i = 0; i < stick.size(); i++) {
                System.out.print(stick.get(i) + " ");
            }
        }else {
            System.out.println();
            System.out.println("Etiquetas2");
            for (int i = 0; i < stick2.size(); i++) {
                System.out.print(stick2.get(i) + " ");
            }
        }
    }

    public static void imprimirHijo(int hijo) {
        if(hijo==1) {
            System.out.println("Hijo1");
            for (int i = 0; i < hijo1.size(); i++) {
                System.out.print(hijo1.get(i) + " ");
            }
            System.out.println();
        }else if( hijo ==2) {
            System.out.println("Hijo2");
            for (int i = 0; i < hijo2.size(); i++) {
                System.out.print(hijo2.get(i) + " ");
            }

        }else{

        }
    }
    /*
    public static void main(String[] args) {
        ArrayList<Integer> l1 = new ArrayList<Integer>();
        ArrayList<Integer> l2 = new ArrayList<Integer>();

        int[] arr1 = {1,6 ,4, 3,10 ,2 ,9 ,8 ,7, 5},
                arr2 = {1 ,10,4, 3, 8, 2, 5, 9, 7, 6};

        for(int i = 0; i < arr1.length; i++) {
            l1.add(arr1[i]);
           // System.out.print(l1.get(i) + " ");
        }
       // System.out.println();

        for(int i = 0; i < arr2.length; i++) {
            l2.add(arr2[i]);
           // System.out.print(l2.get(i) + " ");
        }
        combinar(l1,l2);
    }*/
}