package utils;

public class Validaciones {
	public static final String NOMBRE = "[a-zA-ZАИМСЗаимсзяЯ\\s]{3,30}";
	public static final String APELLIDO = "[a-zA-ZАИМСЗаимсзяЯ\\s]{3,30}";
	public static final String CELULAR = "\\d{9}";
	public static final String EMAIL = "[a-zA-Z0-9]+[@]+[a-z]+[.][a-z]{2,3}";
	public static final String USER = "[U]\\d{3}";
	public static final String PRODUCTO = "[P]\\d{4}";
	public static final String NOMBREPRO = "[a-zA-ZАИМСЗаимсзяЯ\\s]{3,50}";
	public static final String DIRECCION = "[a-zA-ZАИМСЗаимсзяЯ0-9.\\s]{3,200}";	
	public static final String EMPRESA = "[a-zA-ZАИМСЗаимсзяЯ0-9.\\s]{3,50}";
	
}
