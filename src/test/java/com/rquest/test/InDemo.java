package com.rquest.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InDemo {

	public static void main(String[] args) {
		List<String> l = new ArrayList<String>();
		l.add("a");
		l.add("b");
		l.add("c");
		l.add("d");
		l.add("e");
		
		l.forEach(e -> System.out.print(e));
		System.out.println();
		Collections.rotate(l, 3);
		l.forEach(e -> System.out.print(e));
		
	}

}
