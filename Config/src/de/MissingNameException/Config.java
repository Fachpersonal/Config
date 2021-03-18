package de.MissingNameException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Config {
	
	ArrayList<String> configName = new ArrayList<String>();
	ArrayList<String> configValue = new ArrayList<String>();

	
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
			} else {
				loadConfig();
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
			configName.add(varName);
			configValue.add(value);
			print("new Config added!");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads all already existing configs
	 */
	public void loadConfig() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(configFile));
			String line;
			while((line = br.readLine()) != null) {
				String[] split = line.split(" = ");
				configName.add(split[0]);
				configValue.add(split[1]);
			}
			print("finished loading Config file!");
			br.close();
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
	 * Simple getter getConfigNime()
	 * @return ArrayList of configName
	 */
	public ArrayList<String> getConfigName() {
		return configName;
	}
	/**
	 * Simple getter getConfigValue()
	 * @return ArrayList of configValue
	 */
	public ArrayList<String> getConfigValue() {
		return configValue;
	}

	/**
	 * toggles the output if activated, you'll see messages like configfile loaded etc.
	 * @param value if true it enables output
	 */
	public void toggleOutput(boolean value) {
		output = value;
	}
	
	/**
	 * Gives a string which holds all configs with name and value
	 */
	public String toString() {
		String result = "";
		for (int i = 0; i < configName.size(); i++) {
			result += configName.get(i) + " = " + configValue.get(i) + "\n";
		}
		return result;
	}
	private void print(String x) {
		if(output) {
			System.out.println(x);
		}
	}
}
