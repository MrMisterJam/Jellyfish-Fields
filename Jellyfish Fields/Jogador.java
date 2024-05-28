import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Jogador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Jogador extends Actor
{
    private boolean stunned;
    private long stunStartTime;
    private int stunDuration = 1500; // duração do stun em milissegundos
    int velocidade = 3;
    int velocidadeBase = 3;
    int pontos = 1;
    int pontosBase = 1;
    int oculosDuracao = 0, oculosTempoDecorrido = 0;
    int boostDuracao = 0, boostTempoDecorrido = 0;
    static boolean walking = false;
    GreenfootSound walkSound = new GreenfootSound("WalkingSound.mp3");
    MyWorld myworld;
    /**
     * Act - do whatever the Jogador wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public final void act()
    {
        myworld = (MyWorld) getWorld();
        if (stunned) {
            if (System.currentTimeMillis() - stunStartTime >= stunDuration) {
                stunned = false; // Remove o stun após o tempo especificado
            } else {
                showHitAnimation();
                return; // Se estiver stunado, não faz nada
            }
        }
        idle();
        if (isTouching(Jellyfish.class)) {
            removeTouching(Jellyfish.class);
            aumentarPontos(pontos);
        }
        if (isTouching(BlueJellyfish.class)) {
            removeTouching(BlueJellyfish.class);
            aumentarPontos(pontos * 3);
        }
        if (boostTempoDecorrido < boostDuracao) {
            boostTempoDecorrido++;
        }
        if (oculosTempoDecorrido < oculosDuracao) {
            oculosTempoDecorrido++;
        }
        if (oculosTempoDecorrido >= oculosDuracao) {
            pontos = pontosBase;
        }
        movimentacao();
        checkWalking();
        if (walking) {
            walkSound.playLoop();
        } else {
            walkSound.stop();
        }
        if (boostTempoDecorrido >= boostDuracao) {
            velocidade = velocidadeBase;
        }
        walking = false;
    }

    public void stun() {
        stunned = true;
        stunStartTime = System.currentTimeMillis();
        walkSound.stop();
    }
    abstract void idle();
    abstract void movimentacao();
    abstract void checkWalking();
    abstract void aumentarPontos(int pontos);
    abstract void showHitAnimation();
    public void aumentarVelocidade(int boost, int duracao) {
        velocidade += boost;
        boostDuracao = duracao;
        boostTempoDecorrido = 0;
    }
    public void duplicarPontos(int duracao) {
        pontos *= 2;
        oculosDuracao = duracao;
        oculosTempoDecorrido = 0;
    }
}
