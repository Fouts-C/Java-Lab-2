package apps;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * Simple new calculator backend.
 * 
 *
 * @author Carson Fouts
 * @version 4/10
 */
public class ExpressionEvaluator
{

    private static final Pattern UNSIGNED_DOUBLE = Pattern.compile("((\\d+\\.?\\d*)|(\\.\\d+))([Ee][-+]?\\d+)?.*?");
    private static final Pattern CHARACTER = Pattern.compile("\\S.*?");


    /**
     * toPostfix method.
     * 
     *
     * @param expression The expression toPostfix.
     */
    public static String toPostfix(String expression) 
    {

        String result = "";
        Stack<Character> stack = new Stack<>();
        Scanner input = new Scanner(expression);

        try 
        {
            while (input.hasNext()) 
            {
                if (input.hasNext(UNSIGNED_DOUBLE)) 
                {
                    result += input.findInLine(UNSIGNED_DOUBLE) + " ";
                } else 
                {
                    String next = input.findInLine(CHARACTER);
                    char operator = next.charAt(0);

                    switch (operator) 
                    {
                        case '+':
                        case '-':
                        case '*':
                        case '/':
                            while (!stack.isEmpty() && higherPrecedence(operator, stack.peek())) 
                            {
                                result += stack.pop() + " ";
                            }
                            stack.push(operator);
                            break;
                        case '(':
                            stack.push(operator);
                            break;
                        case ')':
                            while (!stack.isEmpty() && stack.peek() != '(') 
                            {
                                result += stack.pop() + " ";
                            }
                            if (stack.isEmpty()) 
                            {
                                return null; 
                            }
                            stack.pop(); 
                            break;
                        default:
                            return null; 
                    }
                }
            }

            while (!stack.isEmpty()) 
            {
                if (stack.peek() == '(') 
                {
                    return null; 
                }
                result += stack.pop() + " ";
            }
        } finally 
        {
            input.close();
        }

        return result;
    }

    /**
     * Evaluates expression.
     *
     * @param postfixExpression The expression to evaluate.
     */
    public static double evaluate(String postfixExpression) 
    {
        Stack<Double> stack = new Stack<>();
        Scanner input = new Scanner(postfixExpression);

        try 
        {
            while (input.hasNext()) 
            {
                if (input.hasNext(UNSIGNED_DOUBLE)) 
                {
                    stack.push(Double.parseDouble(input.findInLine(UNSIGNED_DOUBLE)));
                } else 
                {
                    String next = input.findInLine(CHARACTER);
                    char operator = next.charAt(0);

                    if (stack.size() < 2) 
                    {
                        return Double.NaN;
                    }

                    double rightOperand = stack.pop();
                    double leftOperand = stack.pop();
                    double result = performOperation(leftOperand, rightOperand, operator);

                    if (Double.isNaN(result)) 
                    {
                        return Double.NaN; 
                    }

                    stack.push(result);
                }
            }

            if (stack.size() != 1) 
            {
                return Double.NaN; 
            }

            return stack.pop();
        } finally {
            input.close();
        }
    }


    /**
     * perform operation.
     *
     * @param leftOperand The left value.
     * @param rightOperand The right value.
     * @param operator Operator.
     */
    private static double performOperation(double leftOperand, double rightOperand, char operator) {
        switch (operator) 
        {
            case '+':
                return leftOperand + rightOperand;
            case '-':
                return leftOperand - rightOperand;
            case '*':
                return leftOperand * rightOperand;
            case '/':
                if (rightOperand == 0) 
                {
                    return Double.NaN; 
                }
                return leftOperand / rightOperand;
            default:
                return Double.NaN; 
        }
    }


    /**
     * Find more higher operation.
     *
     * @param current current.
     * @param top top stack.
     */
    private static boolean higherPrecedence(char current, char top)
    {
        return (top == '*' || top == '/') || (top == '+' || top == '-') && (current == '+' || current == '-');
    }


}
