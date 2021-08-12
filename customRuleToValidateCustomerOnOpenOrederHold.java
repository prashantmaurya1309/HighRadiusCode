import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public boolean validateOpenOrderOnHold(String minValueWithOperator) {
		LOG.info("Entered validateOpenOrderOnHold method");
		boolean isRuleSatisfied = false;
		String refField2 = null;
		
		String[] chars = minValueWithOperator.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
		
		String operator = chars[0];
		String num = chars[1];
		
		try {
			
			refField2 = cmsCustomerDocHeader.getMapCustomerAccountRad().getReferenceFiled2();
			
			if(refField2 == null) {
				LOG.info("Reference Field2 ie. Number of open order on hold  for this customer is null");
				return isRuleSatisfied;
			}
			LOG.info("Reference Field2 : "+refField2+" operation to be done: "+ operator+ " and lower limit for Number of open order on hold : "+ num);
			
	        ScriptEngineManager mgr = new ScriptEngineManager();
	        ScriptEngine engine = mgr.getEngineByName("JavaScript");
	        
	        String toBeEvaluated = refField2 + operator+num;
	        isRuleSatisfied=(boolean)engine.eval(toBeEvaluated);
			
		}
		catch (Exception e) {
				LOG.error("Exception occurred in validateOpenOrderOnHold method"+e);
		}
		LOG.info("Leaving from validatePaymentPlanStatus method");
		return isRuleSatisfied;
	}
