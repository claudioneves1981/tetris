package tetris;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Jogo extends JPanel {
    // variaveis
    int variavel = 1;
    int dim = 17;
    int limiteParede = dim - 1;
    boolean isAtive = true;
    int numero;
    int N;
    int VGlobal = 0;
    int cont = 0;
    int nivel = 0;
    String REI;
    int contRei = 1;

    //==========


    //=======Variaves========
    int y, x, rotacao = 0;//nivel 1
    public Point origemPeca;
    public Color[][] parede;//nivel 2
    public ArrayList<Integer> proximaPeca = new ArrayList<>();//nivel 3
    public int pecaAtual;
    public Color[] tetraminoCor = {
            new Color(0, 0, 153), new Color(204, 0, 204), new Color(0, 102, 102), new Color(255, 102, 0), Color.green, Color.cyan, Color.red
    };
//======================

    Point[][][] tetraminoPeca = {// 0
// I-Piece
            {
                    {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1)},
                    {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3)},
                    {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1)},
                    {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3)}
            },
            {
                    {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 0)},
                    {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2)},
                    {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 2)},
                    {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 0)}
            },
            {
                    {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 2)},
                    {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 2)},
                    {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 0)},
                    {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 0)}
            },
            {
                    {new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)},
                    {new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)},
                    {new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)},
                    {new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)}
            },
            {
                    {new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1)},
                    {new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2)},
                    {new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1)},
                    {new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2)}
            },
            {
                    {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1)},
                    {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2)},
                    {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2)},
                    {new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(1, 2)}
            },

            {
                    {new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1)},
                    {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2)},
                    {new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1)},
                    {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2)}
            }
    };


    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, 900, 700);
        desenhar(g);
    }

    public void desenhar(Graphics g) {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < 32; j++) {
                g.setColor(parede[i][j]);
                g.fillRect(21 * i, 21 * j, 20, 20);
            }
        }


// tetraminoPecas
        g.setColor(tetraminoCor[pecaAtual]); //Cor Do Teris
        for (Point p : tetraminoPeca[pecaAtual][rotacao]) {
            g.fillRect((p.x + origemPeca.x) * 21 + x, (p.y + origemPeca.y) * 21 + y, 20, 20);
        }
        g.setColor(tetraminoCor[VGlobal]); //Cor Do Teris
        for (Point c : tetraminoPeca[VGlobal][0]) {
            g.fillRect((c.x + 23) * 21, (c.y + 14) * 21, 20, 20);
        }

        g.setColor(Color.red);

        String[] palavrasmenu = {"INICIO", "PAUSE/PLAY", "SAIR"};
        for (int i = 0; i < 3; i++) {
            g.setColor(Color.red);
            g.fillRect(448, 60 + 35 * i, 150, 30);
            g.setColor(Color.red);
            g.drawString("REI:" + REI, 600, 20);
            g.setColor(Color.GREEN);
            g.drawString(palavrasmenu[i], 448 + 40, 80 + 30 * i);
        }


        g.setColor(Color.red);
        g.drawRect(449, 233, 150, 150);
    }

    public void Motor() throws InterruptedException {
        parede = new Color[20][32];
        for (int i = 0; i < limiteParede; i++) {
            for (int j = 0; j < 32; j++) {

                if (i == 0 || j == 31 || j == 0) {
                    parede[i][j] = Color.orange;
                } else {
                    parede[i][j] = Color.PINK;
                }

            }
        }
        novaPeca();
    }

    public boolean colisao(int x, int y, int rotacao) {
        for (Point p : tetraminoPeca[pecaAtual][rotacao]) {
            if (parede[p.x + x][p.y + y] != Color.PINK) {
                return false;
            }
        }
        return true;

    }

    public void correr() {
        new Thread(() -> {
            while (true) {
                try {
                    baixar();
                    if (cont == 0) {
                        nivel = 1;
                        sleep(1000);
                    }
                    if (cont >= 1 && cont < 3) {
                        nivel = 1;
                        sleep(700);
                    }
                    if (cont >= 3 && cont < 6) {
                        nivel = 2;
                        sleep(500);
                    }
                    if (cont >= 6 && cont < 9) {
                        nivel = 3;
                        sleep(300);
                    }
                    if (cont >= 9 && cont < 12) {
                        nivel = 4;
                        sleep(200);
                    }
                    if (cont >= 12 && cont < 15) {
                        nivel = 5;
                        sleep(150);
                    }
                    if (cont >= 15) {
                        sleep(150);
                    }

                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }).start();
    }

    //=============Rota��o das Pe�as=================
    public void rotacao(int v) {
        if (isAtive) {
            int novaRotacao = (rotacao + v) % 4;
            if (novaRotacao < 0) {
                novaRotacao = 3;
            }
            if (colisao(origemPeca.x, origemPeca.y, novaRotacao)) {
                rotacao = novaRotacao;
            }
        }
        repaint();
    }
//=============================================

    //== Movimentos Das Pe�as direita esquerda e baixo assim como as colisoes com a parede==
    void baixar() throws InterruptedException {
        if (colisao(origemPeca.x, origemPeca.y + 1, rotacao)) {
            origemPeca.y += variavel;
            if (isAtive) {
                repaint();
            }
        } else {
            montar();
        }
        repaint();
    }

    void direita() {
        if (colisao(origemPeca.x + 1, origemPeca.y, rotacao)) {
            origemPeca.x += variavel;
        }
        repaint();
    }

    void esquerda() {
        if (colisao(origemPeca.x - 1, origemPeca.y, rotacao)) {
            origemPeca.x -= variavel;

        }
        repaint();
    }

    //====================================================================
    public void novaPeca() throws InterruptedException {
        origemPeca = new Point(dim / 2 - 1, 0);

        Random r = new Random();
        for (int i = 0; i < 2; i++) {
            VGlobal = r.nextInt(7);
        }


        if (proximaPeca.isEmpty()) {
            Collections.addAll(proximaPeca, VGlobal);//Escolhas
            Collections.shuffle(proximaPeca);// responsavel pela Aleatoriedade
        }
        sleep(1);
        pecaAtual = proximaPeca.get(0);
        proximaPeca.remove(0);//Remove a peca atual

    }

    public void montar() throws InterruptedException {
        for (Point p : tetraminoPeca[pecaAtual][rotacao]) {
            parede[origemPeca.x + p.x][origemPeca.y + p.y] = tetraminoCor[pecaAtual];
        }
        limparLinha();
        novaPeca();
    }

    public void destruir(int linha, int N) {
        for (int j = linha - 1 - N; j > 0; j--) {
            for (int i = 1; i < dim; i++) {
                parede[i][j + 1] = parede[i][j];
            }
        }
    }

    public void limparLinha(){
        boolean isActive;
        numero = 0;
        for (int j = 30; j > 0; j--) {
            isActive = false;
            for (int i = 0; i < dim; i++) {
                if (parede[i][j] == Color.PINK) {
                    isActive = true;
                    break;
                }
            }
            if (!isActive) {
                destruir(j, N);
                j += 1;
                numero += 1;
                cont++;
                pontos();
            }
        }
    }

    void pausePlay() {
        isAtive = !isAtive;
        if (isAtive) {
            variavel = 1;
        } else {
            variavel = 0;
        }
    }

//public int contar(){

    /// /return cont;
//}

//int ponto =Integer.parseInt(pontos.MostraPontos());
    void pontos(){

//if(cont>ponto){
//pontos.EntrarDados(""+cont);
        contRei++;
        System.out.println("" + contRei);
        Reinado();
    }


    void Reinado(){
//if(cont>ponto&&contRei==2){
//pausePlay() ;
//REI=JOptionPane.showInputDialog("Digite o seu Nome Meu Rei");
//pontos. MeuRei(REI) ;
//if(REI!=null){
//pausePlay() ;
    }
//}
//}

//public String getREI() {
//return REI;
//}

    public void setREI(String REI) {
//this.REI = REI;
    }
}