package calculator_synopsys.my_calculator;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class CalculatorTestRunner {

	public static void main(String[] args) {
	      Result result = JUnitCore.runClasses(CalculatorTest.class);
			
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
		  if(result.wasSuccessful())
			  System.out.println("All the tests resulted to "+result.wasSuccessful());
		  else
			  System.out.println("One or more tests failed");
	   }
}
