import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SpongeBob here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Patrick extends Jogador
{
    GifImage Idle = new GifImage("PatrickIdle.gif");
    GifImage Walk = new GifImage("PatrickWalk.gif");
    GifImage WalkL = new GifImage("PatrickWalkLeft.gif");
    GifImage Hit = new GifImage("PatrickHit.gif");
    /**
     * Act - do whatever the SpongeBob wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    void idle() {
        setImage( Idle.getCurrentImage() );
    }
    
    void movimentacao() {
        if (Greenfoot.isKeyDown("d") ) {
            move(velocidade);
            setImage( Walk.getCurrentImage() );
        }
        if (Greenfoot.isKeyDown("w") ) {
            setLocation(getX(), getY() - velocidade);
            setImage( Walk.getCurrentImage() );
        }
        if (Greenfoot.isKeyDown("s") ) {
            setLocation(getX(), getY() + velocidade);
            setImage( Walk.getCurrentImage() );
        }
        if (Greenfoot.isKeyDown("a") ) {
            move(-(velocidade));
            setImage( WalkL.getCurrentImage() );
        }
    }
    void checkWalking() {
        if (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("a")) {
            walking = true;
        }
    }
    void aumentarPontos(int pontos) {
        myworld.AumentarPontosPatrick(pontos);
    }
    
    void showHitAnimation() {
        setImage(Hit.getCurrentImage());
    }
}
