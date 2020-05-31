import java.util.ArrayList;
import java.util.Random;

public class PMX {
    public static ArrayList<Integer> parente1;
    public static ArrayList<Integer>parente2;
    public static ArrayList<Integer> hijo1;
    public static ArrayList<Integer> hijo2;
    public static ArrayList<Integer> segment1;
    public static ArrayList<Integer> segment2;
    public static Integer   cutPoint1;
    public static Integer   cutPoint2;

    public static void PMX(ArrayList<Integer> parent1, ArrayList<Integer> parent2){
    	parente1 = new ArrayList<Integer>(parent1.size());
        parente2 = new ArrayList<Integer>(parent2.size());
        
    	for(int i = 0; i < parent1.size(); i++) {
    		parente1.add(0);
    		parente2.add(0);
    	}
        
        for(Integer index = 0; index < parent1.size(); index ++){
           parente1.set(index, parent1.get(index));
           parente2.set(index, parent2.get(index));
        }
        Random firstRNum  = new Random();
        Random secondRNum = new Random();

        // special value randomNo_Boundray required
        // as firstRNum.nextInt(parent1.length) generates a random number
        // from >=0 <= parent1.length, number used as index. However parent1.length is
        // never an array index as aray index are numbered 0 to (parent1.length - 1)
        
        int randomNo_Boundary = (parent1.size()) - 1;
        hijo1 = new ArrayList<Integer>(parent1.size());
        hijo2 = new ArrayList<Integer>(parent2.size());
        
        for(int i = 0; i < parent1.size(); i++) {
    		hijo1.add(0);
    		hijo2.add(0);
    	}
        
        cutPoint1 = firstRNum.nextInt(randomNo_Boundary);
        cutPoint2 = secondRNum.nextInt(randomNo_Boundary);
        while(cutPoint1 == cutPoint2){
            // Make sure cutPoints are not identical to each other //
            cutPoint2 = secondRNum.nextInt(randomNo_Boundary);
        }
        if(cutPoint1 > cutPoint2){
             int temp = cutPoint1;    // Make sure CutPoint1 is greater than
            cutPoint1 = cutPoint2;    // cutPoint2 //
            cutPoint2 = temp;
        }
        create_Segments(cutPoint1, cutPoint2);
        crossOver(hijo1, parent1, parent2);
        crossOver(hijo2, parent2, parent1);
        
    }

    public int get_cutPoint1()   { return cutPoint1;  } // For Testing Purposes //
    public int get_cutPoint2()   { return cutPoint2;  } // For Testing Purposes //
    
    public ArrayList<Integer> get_segment1()  { return segment1;   } // For Testing Purposes //
    public ArrayList<Integer> get_segment2()  { return segment2;   } // For Testing Purposes //

    public ArrayList<Integer> get_parent1()   { return parente1;    }
    public ArrayList<Integer> get_parent2()   { return parente2;    }

    public ArrayList<Integer> get_offspring1(){ return hijo1; }
    public ArrayList<Integer> get_offspring2(){ return hijo2; }

    // For an Element given by its index check that it doesn't appear twice //
    private static boolean check_forDuplicates(ArrayList<Integer> offspring, int indexOfElement){
        for(int index = 0; index < offspring.size(); index++){
            if((offspring.get(index) == offspring.get(indexOfElement)) &&
                    (indexOfElement != index) ){
                return true;
            }
        }
        return false;
    }

    // If Element is Duplicated, replace it by using its mapping //
    private static void sort_Duplicates(ArrayList<Integer> offspring, int indexOfElement){
        for(int index = 0; index < segment1.size(); index++){
            if(segment1.get(index) == offspring.get(indexOfElement)){
                offspring.set(indexOfElement, segment2.get(index));
            }
            else if(segment2.get(index) == offspring.get(indexOfElement)){
                offspring.set(indexOfElement, segment1.get(index));
            }
        }
    }

    private static void create_Segments(int cutPoint1, int cutPoint2){
        int capacity_ofSegments = (cutPoint2 - cutPoint1) + 1;
        segment1 = new ArrayList<Integer>(capacity_ofSegments);
        segment2 = new ArrayList<Integer>(capacity_ofSegments);
        
        for(int i = 0; i < capacity_ofSegments; i++) {
    		segment1.add(0);
    		segment2.add(0);
    	}
        
        int segment1and2Index = 0;
        for(int index = 0; index < parente1.size(); index++){
          if((index >= cutPoint1) && (index <= cutPoint2)){
             int x = parente1.get(index);  int y = parente2.get(index);
             segment1.set(segment1and2Index, x);
             segment2.set(segment1and2Index, y);
             segment1and2Index++;
          }
        }
    }

    private static void insert_Segments(ArrayList<Integer> offspring, ArrayList<Integer> segment){
        int segmentIndex = 0;
        for(int index = 0; index < offspring.size(); index++){
           if((index >= cutPoint1) && (index <= cutPoint2)){
               offspring.set(index, segment.get(segmentIndex));
               segmentIndex++;
           }
        }
    }

    // offspring2 gets segment 1, offspring1 gets segment2 //
 // offspring2 gets segment 1, offspring1 gets segment2 //
    public static void crossOver(ArrayList<Integer> offspring, ArrayList<Integer> parentX, ArrayList<Integer> parentY){
        if(offspring == hijo1){ 
            ArrayList<Integer> segment = segment2;
            insert_Segments(offspring, segment);
        }
        
        else if(offspring == hijo2){ 
            ArrayList<Integer> segment = segment1;
            insert_Segments(offspring, segment);
        }

        for(int index = 0; index < offspring.size(); index++){
            if((index < cutPoint1) || (index > cutPoint2)){
               offspring.set(index, parentX.get(index));
            }
        }
        
        for(int index = 0; index < offspring.size(); index++){
            if((index < cutPoint1) || (index > cutPoint2)){
                while(check_forDuplicates(offspring, index)){
                    sort_Duplicates(offspring, index);
                }
            }
        }
        //System.out.println(hijo1);
        //System.out.println(hijo2);
    }
/*
     public void printOffspring(int [] offspring1, int [] offspring2){
        System.out.println(" ");
        System.out.println("Parents");
        System.out.println("");
        for(int parent1Index = 0; parent1Index < parent1.length; parent1Index++){
            System.out.print(" " + parent1[parent1Index]);
        }
        System.out.println("");
        for(int parent2Index = 0; parent2Index < parent2.length; parent2Index++){
            System.out.print(" " + parent2[parent2Index]);
        }

        System.out.println("");
        System.out.println("Offsprings");
        for(int offspring1Index = 0;
                    offspring1Index < offspring1.length; offspring1Index++){
            System.out.print(" " + offspring1[offspring1Index]);
        }
        
        System.out.println("");
        for(int offspring2Index = 0;
                    offspring2Index < offspring2.length; offspring2Index++){
            System.out.print(" " + offspring2[offspring2Index]);
        }
        System.out.println("");
    }
*/
}