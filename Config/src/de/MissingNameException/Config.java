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
		loadConfig();
	}
	
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
	
	public File getConfigFile() {
		return configFile;
	}
	public ArrayList<String> getConfigName() {
		return configName;
	}
	public ArrayList<String> getConfigValue() {
		return configValue;
	}

	
	public void toggleOutput(boolean value) {
		output = value;
	}
	
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
