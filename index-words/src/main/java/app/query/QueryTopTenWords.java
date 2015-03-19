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
	
	public QueryTopTenWords(String fileName) {
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
		
		loadTreeFromFile(fileName);
	}
	
	private void loadTreeFromFile(String fileName) {
		if (fileName == null)
			fileName = DEFAULT_OBJECT_NAME;
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(fileName);
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
		return tree.queryByComparator(query, 5, new WordTupleByScoreComparator());
	}
	
	public static void main(String[] args) {
		// Load the default testing file
		QueryTopTenWords qttw = new QueryTopTenWords(null);
//		System.out.println("Printing Entire Tree");
//		System.out.println(qttw.tree);
//		System.out.println("----------------------");
		
		
		WordTuple[] array = qttw.query(null);
		for (WordTuple w : array) {
			if (w != null) {
				System.out.println(w.toString());
			}
		}

	}
	
//	// setters and getters
//	public String getDefaultObjectName() {
//		return DEFAULT_OBJECT_NAME;
//	}
}
