import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A classe SpongeBob representa o personagem SpongeBob que pode ser controlado pelo jogador.
 * 
 * @autor SeuNome
 * @versão DataOuVersão
 */
public class SpongeBob extends Jogador {
    GifImage Idle = new GifImage("SpongeBobIdle.gif");
    GifImage Walk = new GifImage("SpongeBobWalk.gif");
    GifImage WalkL = new GifImage("SpongeBobWalkLeft.gif");
    GifImage Hit = new GifImage("BobHit.gif");

    void idle() {
        setImage(Idle.getCurrentImage());
    }

    void movimentacao() {
        if (Greenfoot.isKeyDown("up")) {
            setLocation(getX(), getY() - velocidade);
            setImage(Walk.getCurrentImage());
        } else if (Greenfoot.isKeyDown("down")) {
            setLocation(getX(), getY() + velocidade);
            setImage(Walk.getCurrentImage());
        }
        if (Greenfoot.isKeyDown("right")) {
            move(velocidade);
            setImage(Walk.getCurrentImage());
        } else if (Greenfoot.isKeyDown("left")) {
            move(-velocidade);
            setImage(WalkL.getCurrentImage());
        }
    }

    void checkWalking() {
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("left")) {
            walking = true;
        }
    }

    void aumentarPontos(int pontos) {
        myworld.AumentarPontosBob(pontos);
    }

    void showHitAnimation() {
        setImage(Hit.getCurrentImage());
    }
}
