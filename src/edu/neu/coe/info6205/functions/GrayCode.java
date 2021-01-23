/*
 * Shiva Mahitha Maddi
 * 001061161
 */
package edu.neu.coe.info6205.functions;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mahit
 */
public class GrayCode {
    List<String> code;

	GrayCode() {
		code = new ArrayList<>();
	}

	List<String> generateCode(int n) {
		List<String> res = new ArrayList<>();
		List<String> res1 = new ArrayList<>();
		if (n == 0) {
			return null;
		}
		if (n == 1) {
			res.add("0");
			res.add("1");
			return res;
		}

		res = generateCode(n - 1);
		for (int i = res.size() - 1; i >= 0; i--) {
			res1.add(res.get(i));
		}
		code = new ArrayList<>();
		for (int i = 0; i < res.size(); i++) {
			code.add("0" + res.get(i));
		}
		for (int i = 0; i < res1.size(); i++) {
			code.add("1" + res1.get(i));
		}
		return code;
	}

	public static void main(String args[]) {
		GrayCode gc = new GrayCode();
		System.out.println("Grey-Code for n = 4 is: ");
		gc.generateCode(4);
		System.out.println(gc.code);
	}
}
