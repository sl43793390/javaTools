package com.srccodes.tools.crypto;

public class TestUitls {

	public static void main(String[] args) {
		String str = "SAIF2019.";
		String string = EncryptionUtils.getkeyByAlgorithm("SM3", str);
		System.out.println(string);
		
	}
}
