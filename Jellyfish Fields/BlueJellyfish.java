import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Jellyfish here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BlueJellyfish extends Actor
{
    GifImage GIF = new GifImage("Bluejellyfish.gif");
    
    boolean Direcao = false;
    int MovimentoCooldown = 0;
    int distancia = 40;
    int direcao = 0;
    int velocidade = 2;
    /**
     * Act - do whatever the Jellyfish wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setImage( GIF.getCurrentImage() );
        
        mover();
    }
    
    void mover() {
        if (!Direcao) {
            direcao = Greenfoot.getRandomNumber(8) + 1;
            Direcao = true;
        } else {
            if (direcao <= 2) { // baixo
                setLocation(getX(), getY() + (velocidade * 2));
                MovimentoCooldown++;
            } else if (direcao <= 4) { // cima
                setLocation(getX(), getY() - velocidade);
                MovimentoCooldown++;
            } else if (direcao <= 6) { // noroeste
                setLocation(getX() - (velocidade * 2), getY() - velocidade);
                MovimentoCooldown++;
            } else if (direcao <= 8) { // nordeste
                setLocation(getX() + (velocidade * 2), getY() - velocidade);
                MovimentoCooldown++;
            }
            if (MovimentoCooldown >= distancia) {
                MovimentoCooldown = 0;
                Direcao = false;
            }
        }
        if (isAtEdge()) {
            mudarDirecaoNaBeira();
        }
    }
    
    void mudarDirecaoNaBeira() {
        int x = getX();
        int y = getY();
        int largura = getWorld().getWidth();
        int altura = getWorld().getHeight();
        
        // Checa se está na beira e ajusta a direção
        if (x <= 5) { // Beira esquerda
            direcao = 8; // Mudar para nordeste
        } else if (x >= largura - 5) { // Beira direita
            direcao = 6; // Mudar para noroeste
        } else if (y <= 5) { // Beira superior
            direcao = 1; // Mudar para baixo
        } else if (y >= altura - 5) { // Beira inferior
            direcao = 4; // Mudar para cima
        }
        Direcao = true; // Garante que a nova direção será aplicada imediatamente
    }
}
