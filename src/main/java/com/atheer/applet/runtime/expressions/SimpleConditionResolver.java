package com.atheer.applet.runtime.expressions;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import com.atheer.applet.design.Condition;
import com.atheer.applet.runtime.AppletContext;

public class SimpleConditionResolver implements ConditionResolver {

	private AppletContext context;
	private ExpressionResolver expressionResolver; 
	
	public SimpleConditionResolver(AppletContext context) {
		super();
		this.context = context;
		this.expressionResolver = new SimpleExpressionResolver(context);
	}

	@Override
	public boolean resolve(Condition condition) {
		System.out.println("Evaluating condition...");
		String operand = expressionResolver.getValue(condition.getOperand());
		System.out.println(condition.getOperand() + ": " + operand);
		int result = 0;
		if(Pattern.matches("\\d+[\\.]?\\d*", operand) && 
				(Pattern.matches("\\d+[\\.]?\\d*", condition.getValue()))) {
			result = new BigDecimal(operand).compareTo(new BigDecimal(condition.getValue()));
		} else {
			result = operand.compareTo(condition.getValue());
		}
		System.out.println("Result: " + result);
		switch (condition.getOperator()) {
		case Equals:
			return (result == 0);
		case NotEquals:
			return (result != 0);
		case LessThan:
			return (result < 0);
		case LessThanEquals:
			return (result <= 0);
		case GreaterThan:
			return (result > 0);
		case GreaterThanEquals:
			return (result >= 0);
		default: 
			return false;
		}
	}

}
