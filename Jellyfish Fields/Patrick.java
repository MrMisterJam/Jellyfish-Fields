import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A classe Patrick representa o personagem Patrick que pode ser controlado pelo jogador.
 * 
 * @autor SeuNome
 * @versão DataOuVersão
 */
public class Patrick extends Jogador {
    GifImage Idle = new GifImage("PatrickIdle.gif");
    GifImage Walk = new GifImage("PatrickWalk.gif");
    GifImage WalkL = new GifImage("PatrickWalkLeft.gif");
    GifImage Hit = new GifImage("PatrickHit.gif");

    void idle() {
        setImage(Idle.getCurrentImage());
    }

    void movimentacao() {
        if (Greenfoot.isKeyDown("w")) {
            setLocation(getX(), getY() - velocidade);
            setImage(Walk.getCurrentImage());
        } else if (Greenfoot.isKeyDown("s")) {
            setLocation(getX(), getY() + velocidade);
            setImage(Walk.getCurrentImage());
        }
        if (Greenfoot.isKeyDown("d")) {
            move(velocidade);
            setImage(Walk.getCurrentImage());
        } else if (Greenfoot.isKeyDown("a")) {
            move(-velocidade);
            setImage(WalkL.getCurrentImage());
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
