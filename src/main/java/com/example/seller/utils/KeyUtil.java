package com.example.seller.utils;

import java.util.Random;

public class KeyUtil {
	public static String generateUniqueKey(){
		Random random = new Random();
		Integer num = random.nextInt(900000) + 100000;
		return String.valueOf(num) + System.currentTimeMillis();
	}
}
