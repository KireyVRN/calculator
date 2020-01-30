import javafx.stage.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.desktop.ScreenSleepEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

class ButtonPanel extends JPanel {

    JTextField screen;
    private static final char MINUS = '-';
    private static final char PLUS = '+';
    private static final char MLTP = '*';
    private static final char DIV = '/';

    ButtonPanel() {
        initSettings();
        initButtons();
    }

    private void initButtons() {

        JButton jbCancel = new JButton("C");
        jbCancel.setBackground(Color.orange);
        JButton jbRemove = new JButton("«");
        jbRemove.setBackground(Color.orange);
        JButton jbSN = new JButton("x²");
        jbSN.setBackground(Color.orange);
        JButton jbSQRT = new JButton("√");
        jbSQRT.setBackground(Color.orange);
        JButton jb1 = new JButton("1");
        JButton jb2 = new JButton("2");
        JButton jb3 = new JButton("3");
        JButton jb4 = new JButton("4");
        JButton jb5 = new JButton("5");
        JButton jb6 = new JButton("6");
        JButton jb7 = new JButton("7");
        JButton jb8 = new JButton("8");
        JButton jb9 = new JButton("9");
        JButton jb0 = new JButton("0");
        JButton jbMltp = new JButton("*");
        jbMltp.setBackground(Color.orange);
        JButton jbDivide = new JButton("/");
        jbDivide.setBackground(Color.orange);
        JButton jbPlus = new JButton("+");
        jbPlus.setBackground(Color.orange);
        JButton jbMinus = new JButton("-");
        jbMinus.setBackground(Color.orange);
        JButton jbDot = new JButton(".");
        jbDot.setBackground(Color.orange);
        JButton jbEQ = new JButton("=");
        jbEQ.setBackground(Color.orange);
        add(jbCancel);
        add(jbRemove);
        add(jbSQRT);
        add(jbSN);
        add(jbCancel);
        add(jbRemove);
        add(jbSQRT);
        add(jbSN);
        add(jb1);
        add(jb2);
        add(jb3);
        add(jbPlus);
        add(jb4);
        add(jb5);
        add(jb6);
        add(jbMinus);
        add(jb7);
        add(jb8);
        add(jb9);
        add(jbMltp);
        add(jbDot);
        add(jb0);
        add(jbEQ);
        add(jbDivide);

        jb1.addActionListener(getListener(jb1.getText()));
        jb3.addActionListener(getListener(jb3.getText()));
        jb2.addActionListener(getListener(jb2.getText()));
        jb4.addActionListener(getListener(jb4.getText()));
        jb5.addActionListener(getListener(jb5.getText()));
        jb6.addActionListener(getListener(jb6.getText()));
        jb7.addActionListener(getListener(jb7.getText()));
        jb8.addActionListener(getListener(jb8.getText()));
        jb9.addActionListener(getListener(jb9.getText()));
        jb0.addActionListener(getListener(jb0.getText()));

        jbCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.setText("");
            }
        });

        jbDot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (screen.getText().equals("") | screen.getText() == null) {
                    screen.setText("0.");
                } else if (!screen.getText().contains(".")) {
                    screen.setText(screen.getText() + ".");
                }
            }
        });

        jbRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (screen.getText().length() >= 1) {
                    String s = screen.getText().substring(0, screen.getText().length() - 1);
                    screen.setText(s);
                }
            }
        });

        jbSQRT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sqrt = Math.sqrt(Double.parseDouble(screen.getText())) + "";
                if (sqrt.length() > 17) {
                    sqrt = sqrt.substring(0, 17);
                }
                if (sqrt.split("\\.")[1].equals("0")) {
                    sqrt = sqrt.split("\\.")[0];
                }
                screen.setText(sqrt);
            }
        });

        jbSN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sn = Math.pow(Double.parseDouble(screen.getText()), 2) + "";
                if (sn.split("\\.")[1].equals("0")) {
                    sn = sn.split("\\.")[0];
                }
                screen.setText(sn);
            }
        });

        jbMinus.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String text = screen.getText();
                if (!text.isEmpty()) {
                    char xh = text.charAt(text.length() - 1);
                    if (xh == PLUS || xh == MLTP || xh == DIV) {
                        text = text.substring(0, text.length() - 1);
                    }
                    if (xh != MINUS) {
                        screen.setText(text + MINUS);
                    }
                }
            }
        });

        jbPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = screen.getText();
                if (!text.isEmpty()) {
                    char xh = text.charAt(text.length() - 1);
                    if (xh == MINUS || xh == MLTP || xh == DIV) {
                        text = text.substring(0, text.length() - 1);
                    }
                    if (xh != PLUS) {
                        screen.setText(text + PLUS);
                    }
                }
            }
        });

        jbDivide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = screen.getText();
                if (!text.isEmpty()) {
                    char xh = text.charAt(text.length() - 1);
                    if (xh == MINUS || xh == MLTP || xh == PLUS) {
                        text = text.substring(0, text.length() - 1);
                    }
                    if (xh != DIV) {
                        screen.setText(text + DIV);
                    }
                }
            }
        });

        jbMltp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = screen.getText();
                if (!text.isEmpty()) {
                    char xh = text.charAt(text.length() - 1);
                    if (xh == MINUS || xh == PLUS || xh == DIV) {
                        text = text.substring(0, text.length() - 1);
                    }
                    if (xh != MLTP) {
                        screen.setText(text + MLTP);
                    }
                }
            }
        });

        jbEQ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<String> list = new ArrayList<>();
                String str = screen.getText();
                String number = "";

                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) != MLTP && str.charAt(i) != MINUS && str.charAt(i) != DIV && str.charAt(i) != PLUS) {
                        number += str.charAt(i);
                        if (i == str.length() - 1) {
                            list.add(number);
                        }
                    } else {
                        list.add(number);
                        number = "";
                        list.add(str.charAt(i) + "");
                    }
                }

                String result = recursion2(recursion1(list)).get(0);

                if (result.length() >= 17) {
                    result = result.substring(0, 17);
                }

                if (result.split("\\.")[1].equals("0")) {
                    screen.setText(result.split("\\.")[0]);
                } else {
                    screen.setText(result);
                }
            }
        });
    }

    private ArrayList<String> recursion1(ArrayList<String> list1) {
        if (!list1.contains("*") && !list1.contains("/")) {
            return list1;
        } else {
            for (int i = 0; i < list1.size(); i++) {
                if (list1.get(i).equals("*") || list1.get(i).equals("/")) {
                    String symb = list1.get(i);
                    double num2 = Double.parseDouble(list1.get(i + 1));
                    double num1 = Double.parseDouble(list1.get(i - 1));
                    list1.remove(i + 1);
                    list1.remove(i);
                    if (symb.equals("/")) {
                        list1.set(i - 1, (num1 / num2) + "");
                    } else {
                        list1.set(i - 1, (num1 * num2) + "");
                    }
                    i = 0;
                }
            }
        }
        return recursion1(list1);
    }

    private ArrayList<String> recursion2(ArrayList<String> list2) {
        if (!list2.contains("+") && !list2.contains("-")) {
            return list2;
        } else {
            for (int i = 0; i < list2.size(); i++) {
                if (list2.get(i).equals("+") || list2.get(i).equals("-")) {
                    String symb = list2.get(i);
                    double num2 = Double.parseDouble(list2.get(i + 1));
                    double num1 = Double.parseDouble(list2.get(i - 1));
                    list2.remove(i + 1);
                    list2.remove(i);
                    if (symb.equals("+")) {
                        list2.set(i - 1, (num1 + num2) + "");
                    } else {
                        list2.set(i - 1, (num1 - num2) + "");
                    }
                    i = 0;
                }
            }
        }
        return recursion2(list2);
    }

    private void initSettings() {
        screen = new JTextField("", 17);
        screen.setFont(new Font("TimesRoman", Font.BOLD, 50));
        add(screen, BorderLayout.NORTH);
        setLayout(new GridLayout(5, 4));
    }

    private ActionListener getListener(String text) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!screen.getText().equals("0")) {
                    screen.setText(screen.getText() + text);
                } else {
                    screen.setText(text);
                }
            }
        };
    }
}
