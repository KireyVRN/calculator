import javax.swing.*;
import java.awt.*;

class Calculator extends JFrame {

    Calculator() {

        setLocation(600, 300);
        setSize(500, 600);
        setTitle("THE   BEST   CALCULATOR");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ButtonPanel bp = new ButtonPanel();
        add(bp.screen, BorderLayout.NORTH);
        add(bp, BorderLayout.CENTER);
        setVisible(true);

    }
}