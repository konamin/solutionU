package solutionU;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	// public static final int NUMBER = 0;

	public static void main(String args[]) {
		RuleList ruleList = new RuleList();
		String fileName;
		if (args.length > 0) {
			fileName = args[0];
		} else {
			fileName = "sample.txt";
		}
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				ruleList.addRule(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		long start, stop, time_sign;

		start = System.currentTimeMillis();
		Sing algorithm = new Sing();
		long min_nyan = algorithm.pre_sign(ruleList);
		stop = System.currentTimeMillis();
		time_sign = stop - start;

		System.out.println("sign:Execution time is " + time_sign
				+ " ms, min = " + min_nyan);

		System.out.println("- fin -");
	}


}
