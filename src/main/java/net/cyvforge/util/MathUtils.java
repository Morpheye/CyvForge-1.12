package net.cyvforge.util;


import mcpk.utils.MathHelper;

public class MathUtils
{
    public static final float PI = (float)Math.PI;
    public static final float PI2 = ((float)Math.PI * 2F);
    public static final float PId2 = ((float)Math.PI / 2F);
    private static final float[] ASIN_TABLE = new float[65536];
    private static final double[] b = new double[360];

    public static float asin(float value) {
        return ASIN_TABLE[(int)((double)(value + 1.0F) * 32767.5D) & 65535];
    }

    public static float acos(float value) {
        return ((float)Math.PI / 2F) - ASIN_TABLE[(int)((double)(value + 1.0F) * 32767.5D) & 65535];
    }

    public static int getAverage(int[] vals) {
        if (vals.length <= 0) return 0;
        else {
            int i = getSum(vals);
            int j = i / vals.length;
            return j;
        }
    }

    public static int getSum(int[] vals) {
        if (vals.length <= 0) return 0;
        else {
            int i = 0;

            for (int j = 0; j < vals.length; ++j) {
                int k = vals[j];
                i += k;
            }

            return i;
        }
    }

    public static boolean equalsDelta(float f1, float f2, float delta) {
        return Math.abs(f1 - f2) <= delta;
    }

    public static float toDeg(float angle) {
        return angle * 180.0F / roundToFloat(Math.PI);
    }

    public static float toRad(float angle) {
        return angle / 180.0F * roundToFloat(Math.PI);
    }

    public static float roundToFloat(double d) {
        return (float)((double)Math.round(d * 1.0E8D) / 1.0E8D);
    }

    public static double getAngle(int paramInt) {
        paramInt %= 360;
        return b[paramInt];
    }

    public static double getRightAngle(int paramInt) {
        paramInt += 90;
        paramInt %= 360;
        return b[paramInt];
    }

    static {
        int i;
        for (i = 0; i < 65536; ++i) ASIN_TABLE[i] = (float)Math.asin((double)i / 32767.5D - 1.0D);
        for (i = -1; i < 2; ++i) ASIN_TABLE[(int)(((double)i + 1.0D) * 32767.5D) & 65535] = (float)Math.asin((double)i);
        for (i = 0; i < 360; i++) b[i] = Math.sin(Math.toRadians(i));
    }
}