package src;

import java.util.Random;

public class Krylov {
    public static double accuracyy = 0.91;




//state depth = 3
    //penalty treated as a reward with probability = 0.5\
    //reward is same as tstelin

    public static int states = 24;
    public static int action = 8;
    public static int experiments = 100;
    public static double highestStrength = 0.0;


    public static int Environment(int action)
    {
        Random r = new Random();

        double noise = r.nextGaussian();
        double strength = ((3*action)/2) + noise;

        //0 reward, 1 penalty

        if(strength<highestStrength)
        {
            return 1;
        }
        else
        {
            highestStrength = strength;
            return 0;
        }
    }

    public static int Reward(int stateDepth, int currentState)
    {
        if(currentState%stateDepth == 1)
        {
        return currentState;
    }
        else
    {
        currentState = currentState-1;
    }

        return currentState;
    }

    public static int Penalty(int stateDepth,int currentState)
    {

        if(currentState==stateDepth)
        {
            currentState = 2*stateDepth;
        }
        else if(currentState==2*stateDepth)
        {
            currentState = stateDepth;
        }
        else
        {
            currentState = currentState+1;
        }
        return currentState;
    }

    public static int Krylov(int stateDepth, int currentState){

        int action = 0;
        int state = 0;

        if(currentState<=stateDepth){
            action = 1;
        }else if(currentState > stateDepth && currentState <= stateDepth*2){
            action = 2;
        }else if(currentState > stateDepth*2 && currentState <= stateDepth*3){
            action = 3;
        }else if(currentState > stateDepth*3 && currentState <= stateDepth*4){
            action = 4;
        }else if(currentState > stateDepth*4 && currentState <= stateDepth*5){
            action = 5;
        }else if(currentState > stateDepth*5 && currentState <= stateDepth*6){
            action = 6;
        }else if(currentState > stateDepth*6 && currentState <= stateDepth*7){
            action = 7;
        }else{
            action = 8;
        }

        int result = Environment(action);

        if(result==0)
        {
            state = Reward(stateDepth, currentState);
        }
        else
        {
            int coinToss = (int)(Math.random()*2);

            if(coinToss>0.5) {
                state = Penalty(stateDepth, currentState);
            }else{
                state = Reward(stateDepth, currentState);
            }
        }

        return state;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Random r = new Random();
        int stateDepth = states/action;
        int numberOfIterations = 10000;
        int a1=0, a2=0, a3=0, a4=0, a5=0, a6=0, a7=0, a8 = 0;
        for(int i = 0; i<experiments; i++) {
            int currentState = r.nextInt((24 - 1) + 1) + 1;
            int iteration = 0;

            while (iteration < numberOfIterations) {
                iteration++;
                currentState = Krylov(stateDepth, currentState);
                if (currentState <= stateDepth) {
                    if (iteration > 9900) {
                        a1++;
                        System.out.print("1");
                    }
                } else if (currentState > stateDepth && currentState <= stateDepth * 2) {
                    if (iteration > 9900) {
                        a2++;
                        System.out.print("2");
                    }
                } else if (currentState > stateDepth * 2 && currentState <= stateDepth * 3) {
                    if (iteration > 9900) {
                        a3++;
                        System.out.print("3");
                    }
                } else if (currentState > stateDepth * 3 && currentState <= stateDepth * 4) {
                    if (iteration > 9900) {
                        a4++;
                        System.out.print("4");
                    }
                } else if (currentState > stateDepth * 4 && currentState <= stateDepth * 5) {
                    if (iteration > 9900) {
                        a5++;
                        System.out.print("5");
                    }
                } else if (currentState > stateDepth * 5 && currentState <= stateDepth * 6) {
                    if (iteration > 9900) {
                        a6++;
                        System.out.print("6");
                    }
                } else if (currentState > stateDepth * 6 && currentState <= stateDepth * 7) {
                    if (iteration > 9900) {
                        a7++;
                        System.out.print("7");
                    }
                } else {
                    if (iteration > 9900) {
                        a8++;
                        System.out.print("8");
                    }
                }
            }
        }
        long end = System.currentTimeMillis();
        int accuracy = (a8/(a1+a2+a3+a4+a5+a6+a7+a8))/100;
        System.out.println("\nAction 1: " + a1/100);
        System.out.println("\nAction 2: " + a2/100);
        System.out.println("\nAction 3: " + a3/100);
        System.out.println("\nAction 4: " + a4/100);
        System.out.println("\nAction 5: " + a5/100);
        System.out.println("\nAction 6: " + a6/100);
        System.out.println("\nAction 7: " + a7/100);
        System.out.println("\nAction 8: " + a8/100);
        System.out.println("\nAccuracy: " + accuracyy);
        System.out.println("Time taken by function: " + (end-start) + " milliseconds");
    }
}
