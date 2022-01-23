import java.awt.*;
import java.awt.event.*;

public class CalcPanel extends Panel {
    TextField tf = new TextField();
    String op1, op2;
    double num1, num2, mem;
    boolean tfClear = false;
    boolean firstOp = false;
    boolean dotUsed = false;

    String[] buttons = {
            "M+", "7", "8", "9", "/",
            "M-", "4", "5", "6", "*",
            "MR", "1", "2", "3", "-",
            "CE", "0", ".", "=", "+"};

    ActionListener nl = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (tfClear) { tf.setText(""); tfClear = false; }
            tf.setText(tf.getText() + e.getActionCommand());
        }
    };
    ActionListener cl = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!firstOp) {
                num1 = Double.parseDouble(tf.getText());
                op1 = e.getActionCommand();
                firstOp = true;
            }
            else {
                num2 = Double.parseDouble(tf.getText());
                op2 = e.getActionCommand();
            }
            switch(op1) {
                case "+":
                    num1 += num2;
                    break;
                case "-":
                    num1 -= num2;
                    break;
                case "*":
                    num1 *= num2;
                    break;
                case "/":
                    if (num2 != 0) num1 /= num2;
                    break;
                case "=":
                    break;
            }
            if (op2 != null) {
                op1 = op2;
                op2 = null;
            }
            tf.setText("" + num1);
            tfClear = true;
            dotUsed = false;
        }
    };
    ActionListener dl = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (tfClear) { tf.setText(""); }
            if (!dotUsed) {
                tf.setText(tf.getText() + e.getActionCommand());
                dotUsed = true; }
        }
    };
    ActionListener ml = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand().charAt(1)) {
                case 'R':
                    tf.setText("" + mem);
                    break;
                case '+':
                    mem += Double.parseDouble(tf.getText());
                    break;
                case '-':
                    mem -= Double.parseDouble(tf.getText());
                    break;
            }
        }
    };
    ActionListener ce = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tf.setText("");
            num1 = 0; num2 = 0;
            op1 = null; op2 = null;
            firstOp = false; dotUsed = false;
        }
    };
    ActionListener[] listeners = {
            ml, nl, nl, nl, cl,
            ml, nl, nl, nl, cl,
            ml, nl, nl, nl, cl,
            ce, nl, dl, cl, cl,
    };
    public CalcPanel() {
        setFont(new Font("System", Font.PLAIN, 30));
        setLayout(new BorderLayout());
        add(tf, BorderLayout.NORTH);
        Panel btnGrid = new Panel(new GridLayout(4,5));
        for(int i = 0; i < buttons.length; i++) {
            Button btn = new Button(buttons[i]);
            btn.addActionListener(listeners[i]);
            btnGrid.add(btn);
        }
        add(btnGrid);
    }
    public static void main(String args[]) {
        Frame F=new Frame();
        CalcPanel CP=new CalcPanel();
        F.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent we){System.exit(0);}});
        F.add(CP);
        F.pack();
        F.setVisible(true);
    }
}
