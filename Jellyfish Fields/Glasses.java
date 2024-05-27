import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Glasses here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Glasses extends Actor
{
    int speed = 3;
    int duracao = 300;
    public Glasses () {
        getImage().scale(90, 60);
    }
    /**
     * Act - do whatever the Glasses wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setLocation(getX(), getY() + speed);
        // Verifica se colidiu com um jogador
        if (isTouching(Jogador.class)) {
            Jogador jogador = (Jogador) getOneIntersectingObject(Jogador.class);
            jogador.duplicarPontos(duracao);
            getWorld().removeObject(this);
        } else
        // Verifica se chegou na base do mundo
        if (getY() >= getWorld().getHeight() - 1) {
            getWorld().removeObject(this);
        }
    }
}
