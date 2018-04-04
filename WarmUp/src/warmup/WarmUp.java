
package warmup;


public class WarmUp {

   
    public static void main(String[] args) {
    double[]score = {10.0,5.0,60.0,11.0};          
        
    System.out.println(findAvg(score));
    }    
    
    public static double findAvg(double[] score){
    double avg = 0;
    for(int i=0;i<(score.length);i++){
    avg = avg+score[i];
    };
    return avg/score.length;
    }
    
}
