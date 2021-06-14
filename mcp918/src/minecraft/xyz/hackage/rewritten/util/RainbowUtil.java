package xyz.hackage.rewritten.util;

import java.awt.Color;

public class RainbowUtil {
	
	public static int getRainbow(double offset) {
		return Color.HSBtoRGB((float) ((float)(System.currentTimeMillis() % 20000L+(offset*100)) / 1000.0F+(offset*50)), 0.4F, 0.8F);
	}
	
	public static int SkyRainbow(float var2, float bright, float st) {
        double v1 = Math.ceil(System.currentTimeMillis() + (long) (var2 * 109)) / 5;
        return Color.getHSBColor((double) ((float) ((v1 %= 360.0) / 360.0)) < 0.5 ? -((float) (v1 / 360.0)) : (float) (v1 / 360.0), st, bright).getRGB();
    }

}
