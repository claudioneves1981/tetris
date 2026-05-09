package frame;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tela extends JFrame {

    public Tela(final JPanel mainFrame) {

        super("Tetris");
        this.setSize(726,700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(mainFrame);

    }





}