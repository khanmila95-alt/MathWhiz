package model;

import java.io.Serializable;
import java.util.Random;

/** The four questions of one game (kept in the HTTP session). */
public class GameRound implements Serializable {
    private static final Random RNG = new Random();
    private static final String[] TRIG = {"sin", "cos", "tan"};

    private final int primeNumber;
    private final int modA;
    private final int modB;
    private final String trig1Type;
    private final double trig1Angle;
    private final String trig2Type;
    private final double trig2Angle;

    public GameRound() {
        primeNumber = 2 + RNG.nextInt(49);          // 2..50
        modA = 10 + RNG.nextInt(90);                // 10..99
        modB = 2 + RNG.nextInt(11);                 // 2..12 (never zero)
        trig1Type = TRIG[RNG.nextInt(3)];
        trig1Angle = pickAngle(trig1Type);
        String t2;
        do { t2 = TRIG[RNG.nextInt(3)]; } while (t2.equals(trig1Type));
        trig2Type = t2;
        trig2Angle = pickAngle(trig2Type);
    }

    private static double pickAngle(String type) {
        double[] angles = type.equals("tan") ? new double[]{0, 30, 45, 60}
                                             : new double[]{0, 30, 45, 60, 90};
        return angles[RNG.nextInt(angles.length)];
    }

    public int getPrimeNumber() { return primeNumber; }
    public int getModA() { return modA; }
    public int getModB() { return modB; }
    public String getTrig1Type() { return trig1Type; }
    public double getTrig1Angle() { return trig1Angle; }
    public String getTrig2Type() { return trig2Type; }
    public double getTrig2Angle() { return trig2Angle; }
}
