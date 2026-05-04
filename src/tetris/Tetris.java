package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tetris extends JPanel{
    
//Construtor
    public Tetris() throws InterruptedException {
        construirJogo() ;
    }

    public void construirJogo() throws InterruptedException{

        Tela tela=new Tela();
        Jogo jog=new Jogo();
        tela.add(jog);
        jog.correr();
        jog.Motor();
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
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case KeyEvent.VK_ENTER:
                        jog.pausePlay();
                     break;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });
    }

    public static void main(String[] args) throws InterruptedException {
        new Tetris();
    }

}