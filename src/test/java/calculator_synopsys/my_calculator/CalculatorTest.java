package calculator_synopsys.my_calculator;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple Calculator.
 */


public class CalculatorTest extends TestCase
{
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public CalculatorTest( String testName )
	{
		super( testName );
	}
	/*public CalculatorTest()
	{
		super();
	}*/

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite()
	{
		return new TestSuite( CalculatorTest.class );
	}

	/**
	 * Rigourous Test :-)
	 * @throws Exception 
	 */
	public void testApp() throws Exception
	{
		//Tests below - First argument describes each test
		//String errorMsg = "Input structure invalid - Please check usage";
		assertEquals("1 + 2 = 3", Calculator.performCalculation("add(1, 2)", new HashMap<String, Integer>()), 3);
		assertEquals("2 - 1 = 1", Calculator.performCalculation("sub(2, 1)", new HashMap<String, Integer>()), 1);
		assertEquals("5 + 4 = 20", Calculator.performCalculation("mult(5, 4)", new HashMap<String, Integer>()), 20);
		assertEquals("2 / 3 = 0", Calculator.performCalculation("div(2, 3)", new HashMap<String, Integer>()), 0);
		assertEquals("1 + 6 = 7", Calculator.performCalculation("add(1, mult(2, 3))", new HashMap<String, Integer>()), 7);
		assertEquals("(2+2) * (9/3) = 12", Calculator.performCalculation("mult(add(2, 2), div(9, 3))", new HashMap<String, Integer>()), 12);
		assertEquals("5+5 = 10", Calculator.performCalculation("let(a, 5, add(a, a))", new HashMap<String, Integer>()), 10);
		assertEquals("50 + 5 = 55", Calculator.performCalculation("let(a, 5, let(b, mult(a, 10), add(b, a)))", new HashMap<String, Integer>()), 55);
		assertEquals("20 + 20 = 40", Calculator.performCalculation("let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b)))", new HashMap<String, Integer>()), 40);
		assertFalse("Invalid input - paranthesis missing", Calculator.isValidExpression("let("));
		assertTrue("Valid input", Calculator.isValidExpression("add(1,2)"));
		assertFalse("Invalid input - Mod operation", Calculator.isValidOperator("mod(1,2)"));
		assertFalse("Invalid input - paranthesis missing", Calculator.isValidExpression("())"));
		assertEquals("2 + 3 = 5", Calculator.operationResolver(new ArrayList<String>(Arrays.asList("add", "2", "3")), new HashMap<String, Integer>()), 5);
	}
	
	
}
