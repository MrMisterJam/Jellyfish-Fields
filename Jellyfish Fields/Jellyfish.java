import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A classe Jellyfish representa uma água-viva rosa no jogo.
 * Ela se move em direções aleatórias dentro do mundo.
 * 
 * @autor SeuNome
 * @versão DataOuVersão
 */
public class Jellyfish extends Actor {
    GifImage GIF = new GifImage("Jellyfish.gif");
    
    boolean Direcao = false;
    int MovimentoCooldown = 0;
    int distancia = 60;
    int direcao = 0;

    /**
     * Método chamado em cada ato do jogo.
     * Atualiza a imagem e movimenta a água-viva.
     */
    public void act() {
        setImage(GIF.getCurrentImage());
        mover();
    }

    /**
     * Controla o movimento da água-viva em direções aleatórias.
     */
    void mover() {
        if (!Direcao) {
            direcao = Greenfoot.getRandomNumber(8) + 1;
            Direcao = true;
        } else {
            if (direcao <= 2) { // baixo
                setLocation(getX(), getY() + 2);
            } else if (direcao <= 4) { // cima
                setLocation(getX(), getY() - 1);
            } else if (direcao <= 6) { // noroeste
                setLocation(getX() - 2, getY() - 1);
            } else if (direcao <= 8) { // nordeste
                setLocation(getX() + 2, getY() - 1);
            }
            MovimentoCooldown++;
            if (MovimentoCooldown >= distancia) {
                MovimentoCooldown = 0;
                Direcao = false;
            }
        }
        if (isAtEdge()) {
            mudarDirecaoNaBeira();
        }
    }

    /**
     * Altera a direção quando a água-viva atinge a borda do mundo.
     */
    void mudarDirecaoNaBeira() {
        int x = getX();
        int y = getY();
        int largura = getWorld().getWidth();
        int altura = getWorld().getHeight();
        
        if (x <= 5) { // Beira esquerda
            direcao = 8; // Mudar para nordeste
        } else if (x >= largura - 5) { // Beira direita
            direcao = 6; // Mudar para noroeste
        } else if (y <= 5) { // Beira superior
            direcao = 1; // Mudar para baixo
        } else if (y >= altura - 5) { // Beira inferior
            direcao = 4; // Mudar para cima
        }
        Direcao = true;
    }
}