package com.huawei.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Mp3 {
	public static void main(String[] args) throws Exception {
		//Mp3ToTxt();
		txtToMp3();
	}

	public static void Mp3ToTxt() throws Exception {
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
		String mp3Path = "file/0.mp3";
		String desPath = "file/out.txt";
		InputStream is = new FileInputStream(mp3Path);
		BufferedWriter bw = new BufferedWriter(new FileWriter(desPath));
		bw.write("begin");
		byte[] buffer = new byte[512];
		int len = 0;
		while ((len = is.read(buffer, 0, buffer.length)) != -1) {

			for (int i = 0; i < len; i++) {
				bw.write(map.get((buffer[i] & 0xF0) >>> 4));
				bw.write(map.get(buffer[i] & 0x0F));
			}
			bw.write("\n");
		}
		bw.write("end");
		bw.flush();
		bw.close();

	}
	public static void txtToMp3() throws Exception {
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
		String mp3Path = "file/out.mp3";
		String sourcePath = "file/out.txt";
		BufferedReader br = new BufferedReader(new FileReader(sourcePath));
		String line = "";
		while ((line = br.readLine()) != null) {
			System.out.println(line);
			for (int i = 0; i < line.length(); i++) {
				
			}
		}
//		InputStream is = new FileInputStream(mp3Path);
//		BufferedWriter bw = new BufferedWriter(new FileWriter(desPath));
//		bw.write("begin");
//		byte[] buffer = new byte[1024];
//		int len = 0;
//		while ((len = is.read(buffer, 0, buffer.length)) != -1) {
//
//			for (int i = 0; i < len; i++) {
//				bw.write(map.get((buffer[i] & 0xF0) >>> 4));
//				bw.write(map.get(buffer[i] & 0x0F));
//			}
//		}
//		bw.write("end");
//		bw.flush();
//		bw.close();


	}
}
