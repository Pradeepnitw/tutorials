package app.construction;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import app.common.IndexTree;
import app.common.IndexTreeUtil;

public class ConstructFile {
	private String DEFAULT_FILE_NAME;
	private String DEFAULT_OBJECT_NAME;
	private static String INSTRUCTION = "\n    -d to specify directory\n    -n to specify filename\n    -p to print tree\n    -o to specify serilized object name";

	public ConstructFile() {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("config.properties");
			// load a properties file
			prop.load(input);

			// get the property value and print it out
			DEFAULT_FILE_NAME = prop.getProperty("DEFAULT_FILE_NAME");
			DEFAULT_OBJECT_NAME = prop.getProperty("DEFAULT_OBJECT_NAME");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		ConstructFile cf = new ConstructFile();

		boolean printTree = false;
		String path = null;
		String fileName = null;
		String objectName = null;
		
		if (args.length == 0) {
			System.out.println("Loading Indexed Tree Program from" +
					"\n    Default directory: " + System.getProperty("user.dir") + 
					"\n    Default file: " + cf.getDefaultFileName() +
					INSTRUCTION +
					"\n--------------------\nSupported Text File Format: \n    word, #\ne.g.: \n    tedtulip, 5\n    ted_tulip, 2" +
					"\n--------------------"
					);
		} else {
			for (int i = 0; i < args.length; i++) {
				if (args[i].equalsIgnoreCase("-p")) {
					printTree = true;
				} else if (args[i].equalsIgnoreCase("-d") && i != args.length) {
					path = args[++i];
				} else if (args[i].equalsIgnoreCase("-n") && i != args.length) {
					fileName = args[++i];
				} else if (args[i].equalsIgnoreCase("-o") && i != args.length) {
					objectName = args[++i];
				} else 
					System.out.println("Illegal argument:" + args[i] + "\n" + INSTRUCTION);
			}
		}

		if (path == null) {
			path = System.getProperty("user.dir");
		}
		
		if (fileName == null) {
			fileName = cf.DEFAULT_FILE_NAME;
		}
		
		if (objectName == null) {
			objectName = cf.DEFAULT_OBJECT_NAME;
		}
		
		System.out.println("  path = " + path);
		System.out.println("  fileName = " + fileName);
		System.out.println("  objectName = " + objectName);
		
		IndexTree tree = new IndexTree();
		// This may cause problem on PC
		cf.constructTreeFromTxtFile(path + "/" + fileName, tree);

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(objectName);
			IndexTreeUtil.serializeTreeToFile(fos, tree);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null)
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		if (printTree) {
			System.out.println("Printing Tree by Level");
			System.out.println(tree.toString());
		}
	}

	public void constructTreeFromTxtFile(String fileName, IndexTree tree) {
		if (fileName == null) {
			fileName = DEFAULT_FILE_NAME;
		}
		System.out.println("Constructing tree from text file " + fileName);

		BufferedReader textReader = null;
		int numOfLines = 0;
		try {
			textReader = new BufferedReader(new FileReader(fileName));

			String line = null;
			while ((line = textReader.readLine())!= null) {
				processOneLine(line, tree);
				numOfLines++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (textReader != null) 
				try {
					textReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		System.out.println("Construction success\n    IndexTree with size: " + tree.size() + 
				"\n    Text file total number of non-empty lines: " + numOfLines +
				"\n    Bad Record: " + (numOfLines - tree.size()) +
				"\n--------------------");
	}

	private void processOneLine(String line, IndexTree tree) {
		if (line == null || line.trim().equalsIgnoreCase(""))
			return;
		String[] s = line.split(",");
		try {
			tree.insertWord(s[0].trim(), Integer.parseInt(s[1].trim()));
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			System.out.println("    Skipping bad line:" + line);
		}
	}

	// setters and getters
	public String getDefaultFileName() {
		return DEFAULT_FILE_NAME;
	}

	public String getDefaultObjectName() {
		return DEFAULT_OBJECT_NAME;
	}
}
