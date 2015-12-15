package dev.sanket;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandExecutor {

	public String execute(String command) {
		StringBuffer output = new StringBuffer();

		ProcessBuilder pb;
		try {
			pb = new ProcessBuilder("javac", "src/main/java/dev/sanket/GenericExtFilter.java");
//			p = Runtime.getRuntime().exec(command);
			pb.redirectErrorStream(true);
			Process p = pb.start();
			p.waitFor();			
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();
	}
}
