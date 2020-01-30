import javax.swing.*;
import java.awt.*;
import java.awt.desktop.ScreenSleepEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
                String text = screen.getText();
                if (text.contains("*")) {

                }
            }
        });
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

    private void count(String text) {

        String element = "";
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != MLTP || text.charAt(i) != DIV || text.charAt(i) != MINUS || text.charAt(i) != PLUS) {
                element += text.charAt(i);
            } else {
                list.add(element);
                list.add(text.charAt(i) + "");
                element = "";
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("*")) {
                Double itog = Double.parseDouble(list.get(i - 1)) * Double.parseDouble(list.get(i + 1));
                list.remove(i + 1);
                list.remove(i);
                list.set(i - 1, itog + "");
            }
            if (list.get(i).equals("/")) {
                Double itog = Double.parseDouble(list.get(i - 1)) / Double.parseDouble(list.get(i + 1));
                list.remove(i + 1);
                list.remove(i);
                list.set(i - 1, itog + "");
            }
        }
    }

    private ArrayList<String> recursion(ArrayList<String> list) {
        if (list.contains("*") && list.contains("/")) {
            return list;
        } else {

        }
        return null;
    }
}
