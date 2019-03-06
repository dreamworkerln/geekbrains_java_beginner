package ru.home.geekbrains;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Calculator extends JPanel implements ActionListener {

    private JTextField display = new JTextField("0");
    private double result = 0;
    private String operator = "=";
    private boolean calculating = true;
    private DecimalFormat df;

    public Calculator() {

        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ROOT);
        df = (DecimalFormat)nf;
        df.applyPattern("#.####");
        df.setRoundingMode(RoundingMode.CEILING);


        setLayout(new BorderLayout());

        display.setEditable(false);
        Font font1 = new Font("SansSerif", Font.PLAIN, 40);
        display.setFont(font1);
        add(display, "North");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));

        String buttonLabels = "789/456*123-0.=+C  ^";
        for (int i = 0; i < buttonLabels.length(); i++) {
            JButton b = new JButton(buttonLabels.substring(i, i + 1));
            panel.add(b);

            if (i == 14)
                b.setFont(new Font("SansSerif", Font.BOLD, 40));
            else if (i == 16)
                b.setFont(new Font("SansSerif", Font.BOLD, 40));
            else
                b.setFont(new Font("SansSerif", Font.PLAIN, 18));




            b.addActionListener(this);
        }
        add(panel, "Center");
    }

    public void actionPerformed(ActionEvent evt) {
        
        String cmd = evt.getActionCommand();

        if(cmd.equals("C")) {
            calculating = true;
            display.setText("0");
            result = 0;
            return;
        }

        if ('0' <= cmd.charAt(0) && cmd.charAt(0) <= '9' || cmd.equals(".")) {
            if (calculating)
                display.setText(cmd);
            else
                display.setText(display.getText() + cmd);
            calculating = false;
        }
        else {

            if (calculating) {
                  // BUGFIGZ
//                if (cmd.equals("-")) {
//                    display.setText(cmd);
//                    calculating = false;
//                } else
                    operator = cmd;
            } else {
                double x = Double.parseDouble(display.getText());
                calculate(x);
                operator = cmd;
                calculating = true;
            }
        }
    }

    private void calculate(double n) {

        switch (operator) {
            case "+":
                result += n;
                break;
            case "-":
                result -= n;
                break;
            case "*":
                result *= n;
                break;
            case "/":
                result /= n;
                break;
            case "^":
                result = Math.pow(result, n);
                break;
            case "=":
                result = n;
                break;
        }

        display.setText("" + df.format(result));
    }
}
