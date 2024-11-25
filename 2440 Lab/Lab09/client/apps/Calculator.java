package apps;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Simple new calculator GUI class.
 * Provides basic infix operations for two operands.
 *
 * @author Carson Fouts
 * @version 4/10
 */
public class Calculator
{

    private static final int X_LOC = 100;
    private static final int Y_LOC = 100;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final String NAME = "Carson's Calculator TWO POINT O!";
    private static final String RESULT_PREAMBLE = "Result = ";
    private static final String ERROR_MESSAGE = "Error";

    private JFrame frame;
    private JTextField infixExpression;
    private JLabel resultLabel;

    /**
     * Constructor for the Calculator class.
     * Creates the main frame, initializes components, and displays the frame.
     */
    public Calculator()
    {
        createFrame();
        initializeComponents();
        displayFrame();
    }

    /**
     * Gets the main frame of the calculator.
     *
     * @return The main frame.
     */
    public JFrame getFrame() 
    {
        return frame;
    }

    /**
     * Creates the main frame for the calculator.
     * Sets location, size, default close operation, and title.
     */
    private void createFrame()
    {
        frame = new JFrame();
        frame.setLocation(X_LOC, Y_LOC);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(NAME);
    }

    /**
     * Initializes the components of the calculator.
     * Calls methods to initialize input fields, buttons, and result label.
     */
    private void initializeComponents()
    {
        initializeInput();
        initializeButtons();
        initializeResult();
    }

    /**
     * Displays the main frame of the calculator.
     * Calls pack and setVisible methods.
     */
    private void displayFrame()
    {
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Initializes input fields for left and right operands.
     * Adds them to a panel and sets their names.
     */
    private void initializeInput()
    {

        infixExpression = new JTextField(6);
        infixExpression.setName("infixExpression");

        JPanel jpCalcInp = new JPanel();
        jpCalcInp.add(infixExpression);
        frame.add(jpCalcInp, BorderLayout.NORTH);
    }

    /**
     * Initializes the result label.
     * Adds it to a panel and sets its name.
     */
    private void initializeResult()
    {
        resultLabel = new JLabel(RESULT_PREAMBLE);
        resultLabel.setName("resultLabel");
        JPanel jpCalcRes = new JPanel();
        jpCalcRes.add(resultLabel);

        frame.add(jpCalcRes, BorderLayout.WEST);
    }

    /**
     * Initializes arithmetic operation buttons.
     * Adds them to a panel, sets their names, and adds action listeners.
     */
    private void initializeButtons()
    {
        JButton calculateButton = new JButton("calculateButton");
        JButton clearButton = new JButton("clearButton");

        calculateButton.setName("calculateButton");
        clearButton.setName("clearButton");

        JPanel jpCalcBut = new JPanel(new FlowLayout());
        jpCalcBut.add(calculateButton);
        jpCalcBut.add(clearButton);


        frame.add(jpCalcBut, BorderLayout.SOUTH);

        calculateButton.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent event)
            {

                String res = calculate(infixExpression.getText());
                updateResult(res);
            }

        });

        clearButton.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent event)
            {

                infixExpression.setText("");
            }

        });
    }

    /**
     * Calculate.
     * 
     *
     */
    private String calculate(String expression) 
    {
        String postFix = ExpressionEvaluator.toPostfix(infixExpression.getText());
        Double correct = ExpressionEvaluator.evaluate(postFix);
        return correct.toString();
    }
    

    /**
     * Updates the result label based on the provided result value.
     * If the result is NaN, displays an error message.
     *
     * @param result The result value to display.
     */
    private void updateResult(String result)
    {
        if("NaN".equals(result))
        {
            resultLabel.setText(RESULT_PREAMBLE + ERROR_MESSAGE);
        }else
        {
            resultLabel.setText(RESULT_PREAMBLE + result);
        }
    }
}
