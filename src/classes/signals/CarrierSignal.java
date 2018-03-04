package classes.signals;

import java.util.Arrays;

public class CarrierSignal extends Signal {
    public CarrierSignal(WaveType wavetype, int samples, double frequency, double phase) {
        super(samples);
        
        this.frequency = frequency;
        this.wavetype = wavetype;
        this.phase = phase;
        
        makeCarrier();
    }

    private final double frequency;
    private final WaveType wavetype;
    private final double phase;

    public double getFrequency() {
        return frequency;
    }
    
    private void makeCarrier() {
        double[] carrier = new double[this.getLength()];
        
        switch (wavetype) {
            case SIN:
                for (int i = 0; i < this.getLength(); i++) {
                    carrier[i] = Math.sin((2 * Math.PI * frequency * i / this.getLength()) + phase);
                }
                break;
            case COS:
                for (int i = 0; i < this.getLength(); i++) {
                    carrier[i] = Math.cos((2 * Math.PI * frequency * i / this.getLength()) + phase);
                }
                break;
            case SQUARE:
                for (int i = 0; i < frequency; i++) {
                        for (
                                int j = (int) (this.getLength() * i / frequency); 
                                j < ((this.getLength() * i / frequency) + this.getLength() / (frequency * 2)); 
                                j++) {
                            carrier[j] = 1;
                        }
                }
                break;
            case TRIANGULAR:                
                for (int i = 0; i < frequency; i++) {
                    int start = (int) (i / frequency * 2048);
                    System.out.println(start);
                    int end = (int) ((i + 1) / frequency * 2048);
                    System.out.println(end);
                    
                    int limit = (int) (2048 / frequency);
                    System.out.println();
//                    
//                    int limit = end / 4;
//                    int start1 = start + (end / 4);
//                    int start2 = start + (end / 2);
//                    int start3 = start + (end * 3 / 4);
//                    int start4 = start + end;
//                
//                    for (int j = start; j < start1; j++) {
//                        carrier[j] = j * Math.pow(end / 4, -1);
//                    }
//                    
//                    for (int j = start1; j < start2; j++) {
//                        carrier[j] = (-1 * (j - start1) * Math.pow(limit, -1)) + 1;
//                    }
//                    
//                    for (int j = start2; j < start3; j++) {
//                        carrier[j] = (-1 * (j - start2) * Math.pow(limit, -1));
//                    }
//                    
//                    for (int j = start3; j < start4; j++) {
//                        carrier[j] = ((j - start3) * Math.pow(limit, -1)) - 1;
//                    }
                }
                break;
            default:
                throw new IllegalArgumentException();
        }
        
        this.setSignal(carrier);
    }
    
    public static void main(String[] args) {
        CarrierSignal carr = new CarrierSignal(WaveType.TRIANGULAR, 2048, 8, 0);
        
//        double m = Math.pow(2048, -1) * 2047;
        
//        System.out.println(m);
    }
}
