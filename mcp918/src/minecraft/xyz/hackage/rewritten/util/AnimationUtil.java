package xyz.hackage.rewritten.util;

public class AnimationUtil {
	static double PI = Math.PI;



    /////////// INFO ///////////
    // Big Thanks to https://easings.net/ for the Animation Functions
    // We don't own them, translated from Typescript to Java
    //  All animations
    //  preview can be find at https://easings.net/
    // Simple Usage:
    //   Example: AnimationUtil.easeInSine(0.3);
    //   Means 30% of total render time
    //   Will output the thing you want
    //   Which is 0.1089934758116321
    //   Means 0.10899...% of the total value you want to display
    // Translated by fan87, Animations by easings.net
    ///////////////////////////



    public static double easeInSine(double t) {
        return 1 - Math.cos((t * Math.PI) / 2);
    }

    public static double easeOutSine(double t) {
        return Math.cos((t * Math.PI) / 2);
    }

    public static double easeInOutSine(double t) {
        return -(Math.cos(PI * t) - 1) / 2;
    }

    public static double easeInQuad(double t) {
        return t*t;
    }

    public static double easeOutQuad(double t) {
        return 1 - (1 - t) * (1 - t);
    }

    public static double easeInOutQuad(double t) {
        return t < 0.5 ? 2 * t * t : 1 - Math.pow(-2 * t + 2, 2) / 2;
    }

    public static double easeInCubic(double t) {
        return t * t * t;
    }

    public static double easeOutCubic(double t) {
        return 1 - Math.pow(1 - t, 3);
    }

    public static double easeInOutCubic(double t) {
        return t < 0.5 ? 4 * t * t * t : 1 - Math.pow(-2 * t + 2, 3) / 2;
    }

    public static double easeInQuart(double t) {
        return t * t * t * t;
    }

    public static double easeOutQuart(double t) {
        return 1 - Math.pow(1 - t, 4);
    }

    public static double easeInOutQuart(double t) {
        return t < 0.5 ? 8 * t * t * t * t : 1 - Math.pow(-2 * t + 2, 4) / 2;
    }

    public static double easeInQuint(double t) {
        return t * t * t * t * t;
    }

    public static double easeOutQuint(double t) {
        return 1 - Math.pow(1 - t, 5);
    }

    public static double easeInOutQuint(double t) {
        return t < 0.5 ? 16 * t * t * t * t * t : 1 - Math.pow(-2 * t + 2, 5) / 2;
    }

    public static double easeInExpo(double t) {
        return t == 0 ? 0 : Math.pow(2, 10 * t - 10);
    }

    public static double easeOutExpo(double t) {
        return t == 1 ? 1 : 1 - Math.pow(2, -10 * t);
    }

    public static double easeInOutExpo(double t) {
        return t == 0
            ? 0
            : t == 1
            ? 1
            : t < 0.5 ? Math.pow(2, 20 * t - 10) / 2
            : (2 - Math.pow(2, -20 * t + 10)) / 2;
    }

    public static double easeInCirc(double t) {
        return 1 - Math.sqrt(1 - Math.pow(t, 2));
    }

    public static double easeOutCirc(double t) {
        return Math.sqrt(1 - Math.pow(t - 1, 2));
    }

    public static double easeInOutCirc(double t) {
        return t < 0.5
            ? (1 - Math.sqrt(1 - Math.pow(2 * t, 2))) / 2
            : (Math.sqrt(1 - Math.pow(-2 * t + 2, 2)) + 1) / 2;
    }

    public static double easeInBack(double t) {
        double c1 = 1.70158;
        double c3 = c1 + 1;
        return c3 * t * t * t - c1 * t * t;
    }

    public static double easeOutBack(double t) {
        double c1 = 1.70158;
        double c3 = c1 + 1;
        return 1 + c3 * Math.pow(t - 1, 3) + c1 * Math.pow(t - 1, 2);
    }

    public static double easeInOutBack(double t) {
        double c1 = 1.70158;
        double c2 = c1 * 1.525;

        return t < 0.5
            ? (Math.pow(2 * t, 2) * ((c2 + 1) * 2 * t - c2)) / 2
            : (Math.pow(2 * t - 2, 2) * ((c2 + 1) * (t * 2 - 2) + c2) + 2) / 2;
    }

    public static double easeInElastic(double t) {
        double c4 = (2 * Math.PI) / 3;

        return t == 0
            ? 0
            : t == 1
            ? 1
            : -Math.pow(2, 10 * t - 10) * Math.sin((t * 10 - 10.75) * c4);
    }

    public static double easeOutElastic(double t) {
        double c4 = (2 * Math.PI) / 3;

        return t == 0
            ? 0
            : t == 1
            ? 1
            : Math.pow(2, -10 * t) * Math.sin((t * 10 - 0.75) * c4) + 1;
    }

    public static double easeInOutElastic(double t) {
        double c5 = (2 * Math.PI) / 4.5;
        return t == 0
            ? 0
            : t == 1
            ? 1
            : t < 0.5
            ? -(Math.pow(2, 20 * t - 10) * Math.sin((20 * t - 11.125) * c5)) / 2
            : (Math.pow(2, -20 * t + 10) * Math.sin((20 * t - 11.125) * c5)) / 2 + 1;
    }

    public static double easeInBounce(double t) {
        return 1 - easeOutBounce(1 - t);
    }

     public static double easeOutBounce(double t) {
        double n1 = 7.5625;
        double d1 = 2.75;
        if (t < 1 / d1) {
            return n1 * t * t;
        } else if (t < 2 / d1) {
            return n1 * (t -= 1.5 / d1) * t + 0.75;
        } else if (t < 2.5 / d1) {
            return n1 * (t -= 2.25 / d1) * t + 0.9375;
        } else {
            return n1 * (t -= 2.625 / d1) * t + 0.984375;
        }
    }

    public static double easeInOutBounce(double t) {
        return t < 0.5
            ? (1 - easeOutBounce(1 - 2 * t)) / 2
            : (1 + easeOutBounce(2 * t - 1)) / 2;
    }
}
