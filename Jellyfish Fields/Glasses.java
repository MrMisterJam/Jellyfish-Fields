import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A classe Glasses representa um item que cai na tela e ao ser coletado por um jogador
 * duplica os pontos ganhos pelo jogador por um período.
 * 
 * @autor SeuNome
 * @versão DataOuVersão
 */
public class Glasses extends Actor {
    int speed = 3;
    int duracao = 300;

    public Glasses() {
        getImage().scale(90, 60);
    }

    /**
     * Método act é chamado repetidamente para realizar as ações do ator.
     */
    public void act() {
        setLocation(getX(), getY() + speed);

        // Verifica se colidiu com um jogador
        if (isTouching(Jogador.class)) {
            Jogador jogador = (Jogador) getOneIntersectingObject(Jogador.class);
            jogador.duplicarPontos(duracao);
            getWorld().removeObject(this);
        } else if (getY() >= getWorld().getHeight() - 1) {
            getWorld().removeObject(this);
        }
    }
}