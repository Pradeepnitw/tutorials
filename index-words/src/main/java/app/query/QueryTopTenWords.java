package app.query;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import app.common.IndexTree;
import app.common.IndexTreeUtil;
import app.common.WordTuple;
import app.common.WordTupleByScoreComparator;

public class QueryTopTenWords {
	private String DEFAULT_OBJECT_NAME;
	private IndexTree tree = null;
	
	public QueryTopTenWords() {
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			input = new FileInputStream("config.properties");
			// load a properties file
			prop.load(input);

			// get the property value and print it out
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
		loadTreeFromFile();
	}
	
	public void loadTreeFromFile() {
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(DEFAULT_OBJECT_NAME);
			tree = IndexTreeUtil.deserializeTreeFromFile(fis);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
	}
	
	public WordTuple[] query(String query) {
		return tree.queryByComparator(query, 10, new WordTupleByScoreComparator());
	}
	
	public static void main(String[] args) {
		
		String queryString = null;
		System.out.println("Loading Indexed Tree Program from" +
				"\n    Default directory: " + System.getProperty("user.dir"));
		QueryTopTenWords qttw = new QueryTopTenWords();
		System.out.println("    Default file for serialized IndexTree object: " + qttw.DEFAULT_OBJECT_NAME);
		
		if (args.length == 0) {
			System.out.println("\n    Type searching String to start searching, default will list all the words" +
					"\n--------------------"
					);
		} else {
			queryString = args[0];
			System.out.println("    Searching with query String = " + queryString + "\n--------------------");
		}

		WordTuple[] array = qttw.query(queryString);
		System.out.println("Result:");
		for (WordTuple w : array) {
			if (w != null) {
				System.out.println("    " + w.toString());
			}
		}

	}
}
