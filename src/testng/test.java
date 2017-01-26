package testng;

import org.testng.annotations.Test;

import junit.framework.Assert;

public class test {

	@Test
	public void testSample() {
		
		
		String value1="lakshman";
		String value2="lakshmanA";
		String value3="lakshman";
		
		Assert.assertEquals(value1, value3);
		System.out.println("First is OK");
		Assert.assertEquals(value1, value2);
		System.out.println("Second is OK");
		
		
	}
	
}
