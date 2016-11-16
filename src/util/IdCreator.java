package util;

import java.util.Date;

public class IdCreator {
	public static long getId(){
		return new Date().getTime();
	}
	public static void main(String[] args) {
		System.out.println(getId());
	}
}
