package tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Tetris extends JPanel{

    public Tetris() throws InterruptedException {
        construirJogo() ;
    }

    public void construirJogo() throws InterruptedException{

        Tela tela=new Tela();
        tela.setTitle("Tetris");
        Jogo jog=new Jogo();
        tela.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        jog.esquerda();
                        break;
                    case KeyEvent.VK_RIGHT:
                        jog.direita();
                        break;
                    case KeyEvent.VK_UP:
                        jog.rotacao(1);
                        break;
                    case KeyEvent.VK_DOWN:
                        jog.rotacao(-1);
                        break;
                    case KeyEvent.VK_SPACE:
                        try {
                            jog.baixar();
                        } catch (Throwable ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case KeyEvent.VK_ENTER:
                        jog.pausePlay();
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
        jog.correr();
        jog.motor();
        tela.add(jog);

    }

    public static void main(String[] args) throws InterruptedException {
        new Tetris();
    }

}