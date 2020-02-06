import org.apache.commons.lang3.math.NumberUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

class ButtonPanel extends JPanel {

    private static final int SCREEN_LIMIT = 17;
    private static final char MINUS = '-';
    private static final char PLUS = '+';
    private static final char MLTP = '*';
    private static final char DIV = '/';
    static JTextField screen;

    enum Symbols {
        CLEAR("C", getClearListener()), ONE("1", getNumeralListener("1")), TWO("2", getNumeralListener("2")),
        MULT("*", getMultiListener()), REMOVE("«", getRemoveListener()), THREE("3", getNumeralListener("3")),
        FOUR("4", getNumeralListener("4")), DIVISION("/", getDivisionListener()), INVOLUTE("x²", getInvoluteListener()),
        FIVE("5", getNumeralListener("5")), SIX("6", getNumeralListener("6")), PLUS("+", getPlusListener()),
        SQUARE("√", getSquareListener()), SEVEN("7", getNumeralListener("7")), EIGHT("8", getNumeralListener("8")),
        MINUS("-", getMinusLIstener()), DOT(".", getDotListener()), NINE("9", getNumeralListener("9")),
        ZERO("0", getNumeralListener("0")), EQUALS("=", getEqualsListener());

        private String name;
        private ActionListener listener;

        Symbols(String name, ActionListener listener) {
            this.name = name;
            this.listener = listener;
        }
    }

    ButtonPanel() {
        initViewSettings();
        initButtons(Symbols.values());
    }

    private static ActionListener getClearListener() {
        return e -> screen.setText("");
    }

    private static ActionListener getRemoveListener() {
        return e -> {
            if (screen.getText().length() >= 1) {
                String s = screen.getText().substring(0, screen.getText().length() - 1);
                screen.setText(s);
            }
        };
    }

    private static ActionListener getInvoluteListener() {
        return e -> {
            String sn = Math.pow(Double.parseDouble(screen.getText()), 2) + "";
            if (sn.split("\\.")[1].equals("0")) {
                sn = sn.split("\\.")[0];
            }
            screen.setText(sn);
        };
    }

    private static ActionListener getSquareListener() {
        return e -> {
            String sqrt = Math.sqrt(Double.parseDouble(screen.getText())) + "";
            if (sqrt.length() > SCREEN_LIMIT) {
                sqrt = sqrt.substring(0, SCREEN_LIMIT);
            }
            if (sqrt.split("\\.")[1].equals("0")) {
                sqrt = sqrt.split("\\.")[0];
            }
            screen.setText(sqrt);
        };
    }

    private static ActionListener getDotListener() {
        return e -> {
            if (screen.getText().equals("") | screen.getText() == null) {
                screen.setText("0.");
            } else if (!screen.getText().contains(".")) {
                screen.setText(screen.getText() + ".");
            }
        };
    }

    private static ActionListener getMultiListener() {
        return e -> {
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
        };
    }

    private static ActionListener getDivisionListener() {
        return e -> {
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
        };
    }

    private static ActionListener getPlusListener() {
        return e -> {
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
        };
    }

    private static ActionListener getMinusLIstener() {
        return e -> {
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
        };
    }

    private static ActionListener getEqualsListener() {
        return e -> {
            String str = screen.getText();
            int count = 0;
            boolean lastSymbol = false;

            for (int a = 0; a < str.length(); a++) {
                if (str.charAt(a) == MINUS || str.charAt(a) == PLUS || str.charAt(a) == DIV || str.charAt(a) == MLTP) {
                    count++;
                    if (a == str.length() - 1) {
                        lastSymbol = true;
                    }
                }
            }

            if (count == 0 || (count == 1 && lastSymbol)) {
                //todo: WTF is this empty block ???
            } else {
                String number = "";
                ArrayList<String> list = new ArrayList<>();
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

                String result =
                        calculateSequence(calculateSequence(list, "*", "/"), "+", "-").get(0);

                if (result.length() >= SCREEN_LIMIT) {
                    result = result.substring(0, SCREEN_LIMIT);
                }

                if (result.split("\\.")[1].equals("0")) {
                    screen.setText(result.split("\\.")[0]);
                } else {
                    screen.setText(result);
                }
            }
        };
    }

    private void initButtons(Symbols[] buttons){
        Arrays.stream(buttons).forEach(b->{
            JButton button = new JButton(b.name);
            if(!NumberUtils.isCreatable(b.name)){
                button.setBackground(Color.orange);
            }
            button.addActionListener(b.listener);
            add(button);
        });
    }

    private static ArrayList<String> calculateSequence(ArrayList<String> sequence, String sym1, String sym2) {
        if (!sequence.contains(sym1) && !sequence.contains(sym2))
            return sequence;

        for (int i = 0; i < sequence.size(); i++) {
            if (sequence.get(i).equals(sym1) || sequence.get(i).equals(sym2)) {
                String sym = sequence.get(i);
                double num2 = Double.parseDouble(sequence.get(i + 1));
                double num1 = Double.parseDouble(sequence.get(i - 1));
                sequence.remove(i + 1);
                sequence.remove(i);

                switch (sym) {
                    case ("*"):
                        sequence.set(i - 1, (num1 * num2) + "");
                        break;
                    case ("/"):
                        sequence.set(i - 1, (num1 / num2) + "");
                        break;
                    case ("+"):
                        sequence.set(i - 1, (num1 + num2) + "");
                        break;
                    default:
                        sequence.set(i - 1, (num1 - num2) + "");
                        break;
                }
                i = 0;
            }
        }
        return calculateSequence(sequence, sym1, sym2);

    }

    private void initViewSettings() {
        screen = new JTextField("", SCREEN_LIMIT);
        screen.setFont(new Font("TimesRoman", Font.BOLD, 50));
        add(screen, BorderLayout.NORTH);
        setLayout(new GridLayout(5, 4));
    }

    private static ActionListener getNumeralListener(String text) {
        return e -> {
            if (!screen.getText().equals("0"))
                screen.setText(screen.getText() + text);
            else
                screen.setText(text);

        };
    }
}