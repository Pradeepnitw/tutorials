package app.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IndexTreeUtil {
	/*
	 * Usage:
	 * 	FileOutputStream fos = null;
	 *  try {
	 *  	fos = new FileOutputStream("ObjectFileName");
	 *  	IndexTreeUtil.serializeTreeToFile(fos, tree);
	 *  } catch (FileNotFoundException e) {
	 *  	e.printStackTrace();
	 *  } catch (IOException e) {
	 *  	e.printStackTrace();
	 *  } finally {
	 *  	if (fos != null)
	 *  		try {
	 *  			fos.close();
	 *  		} catch (IOException e) {
	 *  			e.printStackTrace();
	 *  		}
	 *  }
	 */
	public static void serializeTreeToFile(FileOutputStream fos, IndexTree tree) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(tree);
		oos.close();
	}
	
	/*
	 * Usage:
	 * 	FileInputStream fis = null;
	 *  IndexTree tree = null;
	 *  try {
	 *  	fis = new FileInputStream("ObjectFileName");
	 *  	tree = IndexTreeUtil.deserializeTreeFromFile(fis);
	 *  } catch (ClassNotFoundException | IOException e) {
	 *  	e.printStackTrace();
	 *  } finally {
	 *  	if (fis != null)
	 *  		try {
	 *  			fis.close();
	 *  		} catch (IOException e) {
	 *  			e.printStackTrace();
	 *  		}
	 *  }
	 */
	public static IndexTree deserializeTreeFromFile(FileInputStream fis) throws ClassNotFoundException, IOException {
		IndexTree tree = null;
		ObjectInputStream ois = new ObjectInputStream(fis);
		tree = (IndexTree) ois.readObject();
		ois.close();
		return tree;
	}
	

}
