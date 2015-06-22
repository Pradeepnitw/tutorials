package interview.airbnb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CSVReader {

	public void runCode() {
		String fileName = "/Users/snowsguoguo/git/tutorials/leetcode/src/main/java/interview/airbnb/csvReader.csv";
		BufferedReader br = null;
		String line = null;
		String splitBy = "\\|";
		
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		try {
			br = new BufferedReader(new FileReader(fileName));
			
			while ((line = br.readLine()) != null) {
				list.add(line.split(splitBy));
				System.out.println(line);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {
			FileWriter outputFile = new FileWriter("/Users/snowsguoguo/git/tutorials/leetcode/src/main/java/interview/airbnb/csvReader.json");
			
			JSONArray people = new JSONArray();
			
			for (String[] s: list) {
				JSONObject json = new JSONObject();
				json.put("FirstName", s[0]);
				System.out.println(s[0]);
				json.put("LastName", s[1]);
				json.put("Email", s[2]);
				json.put("City", s[3]);
				json.put("Num", s[4]);
				people.add(json);
				
			}
			
			outputFile.write(people.toJSONString());
			
			outputFile.flush();
			outputFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] arg) {
		new CSVReader().runCode();
	}
}
