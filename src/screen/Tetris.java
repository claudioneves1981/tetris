package screen;

import frame.Tela;
import panel.Jogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static javax.swing.JOptionPane.*;

public class Tetris {


    private final static Dimension dimension = new Dimension(600,600);
    private static JFrame mainFrame = new JFrame();

    public Tetris(){

    }

    public void novoJogo() throws InterruptedException {

        var dialogResult = showConfirmDialog(
                null,
                "Deseja iniciar um novo jogo?",
                "Novo jogo",
                YES_NO_OPTION,
                QUESTION_MESSAGE
        );


        if (dialogResult == 0) {


            mainFrame.removeAll();
            mainFrame.dispose();
            startNewGame();

        } else {
            System.exit(0);
        }



    }

    private void startNewGame() throws InterruptedException {

        //JPanel mainPanel = new Jogo(dimension);
        Jogo jogo = new Jogo(dimension);
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        try {
                            jogo.esquerda();
                        } catch (Throwable ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        try {
                            jogo.direita();
                        } catch (Throwable ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case KeyEvent.VK_UP:
                        try {
                            jogo.rotacao(1);
                        } catch (Throwable ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        try {
                            jogo.rotacao(-1);
                        } catch (Throwable ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        try {
                            jogo.baixar();
                        } catch (Throwable ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case KeyEvent.VK_ENTER:
                        jogo.pausePlay();
                        break;
                    case KeyEvent.VK_ESCAPE:
                        System.exit(0);
                        break;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });
        jogo.correr();
        jogo.motor();
        mainFrame = new Tela(jogo);

    }

    public void buildMainScreen() throws InterruptedException {

        JPanel mainPanel = new Jogo(dimension);
        Jogo jogo = new Jogo(dimension);
        mainFrame = new Tela(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        try {
                            jogo.esquerda();
                        } catch (Throwable ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        try {
                            jogo.direita();
                        } catch (Throwable ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case KeyEvent.VK_UP:
                        try {
                            jogo.rotacao(1);
                        } catch (Throwable ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        try {
                            jogo.rotacao(-1);
                        } catch (Throwable ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        try {
                            jogo.baixar();
                        } catch (Throwable ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case KeyEvent.VK_ENTER:
                        jogo.pausePlay();
                        break;
                    case KeyEvent.VK_ESCAPE:
                        System.exit(0);
                        break;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });
        jogo.correr();
        jogo.motor();
        mainFrame.add(jogo);

    }


}
