package app.common;

import java.util.Comparator;

import app.common.IndexTree;
import junit.framework.TestCase;

public class IndexTreeTest extends TestCase {
	private IndexTree x001;
	private IndexTree x002;
	
	protected void setUp() {
		x001 = new IndexTree();
		x002 = new IndexTree();
		x002.insertWord("RED", 3);
		x002.insertWord("redTulip", 5);
		x002.insertWord("Red_Tulip", 7);
	}
	
	public void testInsertWord() {
		x001.insertWord("RED", 3);
		x001.insertWord("redTulip", 5);
		x001.insertWord("Red_Tulip", 7);
//		System.out.println(x001.toString());
//		Key=t Words={} Key=r Words={} 
//		Key=u Words={} Key=e Words={} 
//		Key=l Words={} Key=d Words={[RED-3] [Red_Tulip-7] } 
//		Key=i Words={} Key=t Words={} 
//		Key=p Words={[Red_Tulip-7] } Key=u Words={} 
//		Key=l Words={} 
//		Key=i Words={} 
//		Key=p Words={[redTulip-5] } 
		assertEquals(x001.getChildren().size(), 2);
	}
	
	public void testGetNodeByCharacterArray() {
		IndexTree.TreeNode<WordTuple> node = x002.getNodeByCharacterArray(new char[]{'r', 'e', 'd'});
		assertTrue(node.getKey() == 'd');
		assertTrue(node.getWordList().toString().contains("RED-3"));
		assertTrue(node.getWordList().toString().contains("Red_Tulip-7"));
	}
	
	public void testQueryByComparator() {
		WordTuple[] result = x002.queryByComparator(null, 10, new WordTupleByScoreComparator());
		assertTrue(result.length == 10);
		assertTrue(result[0].getScore() == 7);
		assertTrue(result[1].getScore() == 5);
		assertTrue(result[2].getScore() == 3);
	}
}
