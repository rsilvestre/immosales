package util;


public class Util {
	public static void checkObject(Object o)  {
		if (o == null)
			throw new NullPointerException("objet null");
	}
	
	public static void checkString(String s) {
		checkObject(s);
		if (s.matches("\\s*"))
			throw new IllegalArgumentException("cha�ne vide");
	}
	
	public static void checkNum�rique(String s) {
		checkString(s);
		try {
			Long.parseLong(s);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("num�ro invalide");
		}
	}

    public static void checkPositive(double nombre) {
        if (nombre <= 0)
            throw new IllegalArgumentException("valeur n�gative ou nulle");
        
    }
}
