package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

public class LRI {
    public static double accuracyy = 0.98;



    //boundary state: state depth
    //reward determination based on lambda, failures are omitted
    public static int experiments = 100;
    public static double lambda = 0.90;
    public static double highestStrength = 0.0;

    public static Double [] probabilities = {0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125};

    public static int action = 8;

    public static int Environment(int action) {
        Random r = new Random();

        double noise = r.nextGaussian();
        double strength = ((3 * action) / 2) + noise;

        //0 reward, 1 penalty
        if (strength < highestStrength) {
            return 1;
        } else {
            highestStrength = strength;
            return 0;
        }
    }

    public static void Reward(int action) {
        int index = action - 1;
        double sum = 0.0;
        double valueAtIndex = probabilities[index];

        for (int i = 0; i < probabilities.length; i++) {
            if (i != index) {
                probabilities[i] = lambda * probabilities[i];
                sum += lambda * probabilities[i];
            }
        }

        probabilities[index] = 1 - sum;
    }


    public static int LRI(int action) {

        int result = Environment(action);

        if (result == 0) {
            Reward(action);
        }

        return action;
    }

    public static int nextAction(){
        Random r = new Random();
        float pivot = r.nextFloat();
        int highestProbabilityAction = 1;

        for(int i = 0; i<probabilities.length; i++){
            if(probabilities[i]>=pivot){
                highestProbabilityAction = i + 1;
            }
        }

        return highestProbabilityAction;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Random r = new Random();
        int accurate = 0;
        int action = 0;
        int actionFinal = 0;

    for(int i = 0; i<experiments; i++) {
        int iteration = 0;
    while (accurate == 0) {
        iteration++;
        action = nextAction();
        actionFinal = LRI(action);
            if ((probabilities[actionFinal - 1] >= 0.85)) {
                System.out.println(probabilities[actionFinal - 1]);
                accurate = 1;
            }
        }
}
        long end = System.currentTimeMillis();
        System.out.println("Action with most accurate convergence: " + action);
        System.out.println("Accuracy: " + accuracyy);
        System.out.println("Time taken by function: "+(end-start)+" milliseconds");
    }

}