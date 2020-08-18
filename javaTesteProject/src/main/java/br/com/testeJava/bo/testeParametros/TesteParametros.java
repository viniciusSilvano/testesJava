package br.com.testeJava.bo.testeParametros;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;

public class TesteParametros {
	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now();
		String ano = leftPad(now.getYear(), 4);
		String mes = leftPad(now.getMonthValue(), 2);
		String dia = leftPad(now.getDayOfMonth(), 2);
		String horaCompleta = leftPad(now.getHour(), 2) + leftPad(now.getMinute(), 2) + leftPad(now.getSecond(), 2) + leftPad(now.getNano(), 4);
		System.out.println("Horas: " + now.getHour());
		System.out.println("Minutos: " + now.getMinute());
		System.out.println("Segundos: " + now.getSecond());
		System.out.println("nano segundos: " + now.getNano());
		System.out.println("Hora completa: " + horaCompleta);
	}
	
	
	private static String leftPad(Object o, int size){
		return StringUtils.leftPad(o.toString(), size, "0");
	}
	
}
