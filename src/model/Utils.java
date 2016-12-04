package model;

public class Utils {

	public static float clamp(float val, float min, float max) {
    	return Math.max(min, Math.min(max, val));
	}

	public static String getNextLoop(String str, int startPos) {
		str = str.substring(startPos);
		return str.substring(str.indexOf('['), str.indexOf(']'));
	}

}