package de.MissingNameException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
	
	private boolean output = true;
	
	private File configFile;
	
	/**
	 * Creates config file if its not already created.
	 * If file already exists it loads all existing configs
	 * @param path Set the path where the config.cfg can be located
	 */
	public Config(String path) {
		try {
			configFile = new File(path + "config.cfg");
			if(configFile.createNewFile()) {
				print("Config file created");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * write the variable into config file
	 * @param varName defines the name of the variable
	 * @param value defines the value of the variable
	 */
	public void addConfig(String varName, String value) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(configFile, true));
			BufferedReader br = new BufferedReader(new FileReader(configFile));
			String line;
			while((line = br.readLine()) != null) {
				if((line.split(" = "))[0].equalsIgnoreCase(varName)) {
					print("Config already exists! [" + varName + "]");
					System.exit(0);
				}
			}
			br.close();
			bw.write(varName + " = " + value);
			bw.newLine();
			print("new Config added!");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Simple getter getConfigFile()
	 * @return configFile
	 */
	public File getConfigFile() {
		return configFile;
	}

	/**
	 * toggles the output if activated, you'll see messages like configfile loaded etc.
	 * @param value if true it enables output
	 */
	public void toggleOutput(boolean value) {
		output = value;
	}
	
	/**
	 * gets the value of the given variable
	 * @param varName variable name
	 * @return Value of given variable
	 */
	public String getValue(String varName) {
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(configFile));
			String line;
			while((line = br.readLine()) != null) {
				if(line.contains(varName)) {
					String[] split = line.split(" = ");
					return split[1];
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Outputs file
	 */
	public void readFile() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(configFile));
			String line;
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void print(String x) {
		if(output) {
			System.out.println(x);
		}
	}
}
