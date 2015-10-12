package com.huawei.java.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

public class Test {

	private static Log log = LogFactory.getLog(Test.class);

	private static String PROPERTIESKEY = "files";

	private static String SEPARATOR = ",";

	private static boolean flag = false;

	public static void main(String[] args) throws Exception {
		PropertyConfigurator.configure("config/log4j.properties");
		log.info("----------------------------------------------------------------------------------------------------");
		String append = "";
		List<String> fileList = new ArrayList<String>();
		Properties properties = getProperties();
		String props = properties.getProperty(PROPERTIESKEY);
		String[] propfiles = props.split(SEPARATOR);
		for (String propfile : propfiles) {
			fileList.add(propfile);
		}
		File dir = new File("InputFiles");
		if (!dir.isDirectory()) {
			return;
		}
		File[] files = dir.listFiles();
		for (File f : files) {
			if (fileList.contains(f.getName())) {
				continue;
			}
			log.info(f.getName());
			flag = true;
			append += props + f.getName() + SEPARATOR;
			if (f.getName().endsWith(".txt")) {
				txtToFile(f);
			} else {
				fileToTxt(f);
			}
		}
		if (flag) {

			File file = new File("config/file.properties");
			properties.setProperty(PROPERTIESKEY, props + append);
			properties.store(new FileOutputStream(file), "files");
		}

	}

	public static void fileToTxt(File f) throws Exception {
		Map<Integer, String> map = new HashMap<Integer, String>() {
			{
				put(0, "a");
				put(1, "b");
				put(2, "c");
				put(3, "d");
				put(4, "e");
				put(5, "f");
				put(6, "g");
				put(7, "h");
				put(8, "i");
				put(9, "j");
				put(10, "k");
				put(11, "l");
				put(12, "m");
				put(13, "n");
				put(14, "o");
				put(15, "p");
			}
		};
		InputStream is = new FileInputStream(f);
		BufferedWriter bw = new BufferedWriter(new FileWriter("OutputFiles/"
				+ (f.getName().split("\\."))[0] + ".txt"));
		// bw.write("begin");
		int len = 0;
		byte[] title = f.getName().getBytes();
		len = title.length;
		for (int i = 0; i < len; i++) {
			bw.write(map.get((title[i] & 0xF0) >>> 4));
			bw.write(map.get(title[i] & 0x0F));

		}
		bw.write("\n");
		byte[] buffer = new byte[512];
		while ((len = is.read(buffer, 0, buffer.length)) != -1) {

			for (int i = 0; i < len; i++) {
				bw.write(map.get((buffer[i] & 0xF0) >>> 4));
				bw.write(map.get(buffer[i] & 0x0F));
			}
			bw.write("\n");
		}
		// bw.write("end");
		bw.flush();
		bw.close();

	}

	public static void txtToFile(File f) throws Exception {
		Map<Character, Integer> map = new HashMap<Character, Integer>() {
			{
				put('a', 0);
				put('b', 1);
				put('c', 2);
				put('d', 3);
				put('e', 4);
				put('f', 5);
				put('g', 6);
				put('h', 7);
				put('i', 8);
				put('j', 9);
				put('k', 10);
				put('l', 11);
				put('m', 12);
				put('n', 13);
				put('o', 14);
				put('p', 15);
			}
		};
		int BUFFERSIZE = 512;
		String line = "";
		byte[] buffer = new byte[BUFFERSIZE];
		BufferedReader br = new BufferedReader(new FileReader(f));
		line = br.readLine();
		for (int i = 0; i < line.length(); i = i + 2) {
			buffer[i / 2] = (byte) ((map.get(line.charAt(i)) << 4) | (map
					.get(line.charAt(i + 1))));
		}
		OutputStream os = new FileOutputStream("OutputFiles/"
				+ new String(buffer));
		while ((line = br.readLine()) != null) {
			for (int i = 0; i < line.length(); i = i + 2) {
				buffer[i / 2] = (byte) ((map.get(line.charAt(i)) << 4) | (map
						.get(line.charAt(i + 1))));
			}
			os.write(buffer, 0, line.length() / 2);
		}
		os.flush();
		os.close();
	}

	private static Properties getProperties() throws Exception {
		// log.debug("getProperties");
		Properties properties = new Properties();
		File file = new File("config/file.properties");
		if (file.exists()) {
			properties.load(new FileInputStream(file));
		} else {
			properties.setProperty("files", "");

			properties.store(new FileOutputStream(file), "files");
		}
		return properties;

	}

}
