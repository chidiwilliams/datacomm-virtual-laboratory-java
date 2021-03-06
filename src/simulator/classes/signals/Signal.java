/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator.classes.signals;

/**
 *
 * @author HP
 */
public class Signal {
    public Signal(int samples) {
        signal = new double[samples];
    }

    private double[] signal;
    private String name = "Signal";

    public double[] getSignal() {
        return signal;
    }

    public double getSignalValue(int index) {
        return this.signal[index];
    }

    public void setSignal(double[] signal) {
        if (signal.length != this.signal.length) {
            throw new IllegalArgumentException("Invalid signal length");
        }
        
        this.signal = signal;
    }

    public void setSignalValue(int index, double value) {
        this.signal[index] = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return this.signal.length;
    }

    public void print(String header, int printMode) {
        System.out.println(header);
        for (int i = 0; i < signal.length; i++) {
            double value = signal[i];
            switch (printMode) {
                case 0:
                    System.out.print(value + " ");
                    break;
                case 1:
                    System.out.println(value);
                    break;
                default:
                    System.out.println(String.format("%-7s", (i + ".")) + value);
                    break;
            }
        }
        System.out.println("");
    }
    
    public double[] getThresholds(int num_thresholds) {
        if (num_thresholds > signal.length) {
            throw new IllegalArgumentException("Number of thresholds must be less than number of samples.");
        }
        
        double[] thresholds = new double[num_thresholds];
        double[] thr_points = getThresholdPoints(num_thresholds);
        
        for (int i = 0; i < num_thresholds; i++) {
            int point = (int) thr_points[i];
            thresholds[i] = (signal[point] > 0) ? 1 : 0;
        }
        
        return thresholds;
    }
    
    private double[] getThresholdPoints(int num_thresholds) {
        if (num_thresholds >= signal.length) {
            throw new IllegalArgumentException("Number of thresholds must be less than number of samples.");
        }
        
        double[] thr_points = new double[num_thresholds];
        int beginning = (int) Math.floor(signal.length / num_thresholds / 2) - 1;
        int width = (int) Math.floor(signal.length / num_thresholds);
        
        for (int i = 0; i < num_thresholds; i++) {
            thr_points[i] = (width * i) + beginning;
        }
        
        return thr_points;
    }
}
