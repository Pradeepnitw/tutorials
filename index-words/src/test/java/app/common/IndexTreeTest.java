package app.common;

import app.common.IndexTree;
import junit.framework.TestCase;

public class IndexTreeTest extends TestCase {
	private IndexTree x001;
	
	protected void setUp() {
		x001 = new IndexTree();
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
}
