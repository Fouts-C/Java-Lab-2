package algorithms;
import java.util.Random;

public class BigOh {

    private static final double MILLISECONDS_PER_SECOND = 1000.0;
    private static final int NUM_TRIALS = 5;
    private Random rand;


    public BigOh(){
        this.rand = new Random();
    }

    public BigOh(Random rand){
        this.rand = rand;
    }

    public int runAlgorithm(int choice, int numElements){

        switch (choice){

            case 1:
                return MysteryAlgorithms.alg1(numElements, rand);
            case 2:
                return MysteryAlgorithms.alg2(numElements, rand);
            case 3:
                return MysteryAlgorithms.alg3(numElements, rand);
            case 4:
                return MysteryAlgorithms.alg4(numElements, rand);
            case 5: 
                return MysteryAlgorithms.alg5(numElements, rand);
            case 6:
                return MysteryAlgorithms.alg6(numElements, rand);
            default:
                return -1;
        }
    }

    public double bigOhFunc(int choice, double n){
        
        switch (choice){

            case 1:
                return Math.pow(n, 1);
            case 2:
                return Math.pow(n, 3);
            case 3:
                return Math.pow(n, 2);
            case 4:
                return Math.pow(n, 2);
            case 5: 
                return Math.pow(n, 5);
            case 6:
                return Math.pow(n, 4);
            default:
                return -1;
        }
    }


    public double timeAlgorithm(int choice, int n){
        System.gc();
        
        double initialTimeStamp = System.currentTimeMillis();
        runAlgorithm(choice, n);
        double postTimeStamp = System.currentTimeMillis();

        return (postTimeStamp - initialTimeStamp)/(MILLISECONDS_PER_SECOND);
    }

    public double robustTimeAlgorithm(int choice, int n){
        double smallestTimeRec = 9999;

        for(int i = 0; i < NUM_TRIALS; i++){
            double thisTest = timeAlgorithm(choice, n);

            if (thisTest < smallestTimeRec){
                smallestTimeRec = thisTest;
            }
        }
        return smallestTimeRec;
    }

    public double estimateTiming(int choice, int n1, double t1, int n2){

        double expectedTimeRatio = bigOhFunc(choice, n2) / bigOhFunc(choice, n1);
        double estimatedTime = t1 * expectedTimeRatio;

        return estimatedTime;
    }

    public double percentError(double correct, double estimate){
        return ((estimate - correct) / correct);
    }

    public double computePercentError(int choice, int n1, int n2){
        
        double t1 = robustTimeAlgorithm(choice, n1); 
        double estimatedTime = estimateTiming(choice, n1, t1, n2); 
        double actualTime = robustTimeAlgorithm(choice, n2); 

        return percentError(actualTime, estimatedTime);
    }
    

}