import greenfoot.*;

/**
 * A classe Squidward representa o personagem Squidward que entra no jogo e solta bolhas.
 * Ele se move para dentro da tela e, após um tempo, solta bolhas que atordoam o jogador.
 * 
 * @autor SeuNome
 * @versão DataOuVersão
 */
public class Squidward extends Actor {
    private long startTime;
    private int duration; // Duração em milissegundos
    private int targetX; // Posição X alvo
    private int speed = 1; // Velocidade de movimento
    private boolean movingIn; // Flag para indicar se está se movendo para dentro
    private int bubbleCooldown = 0;
    private final int bubbleCooldownTime = 250; // Tempo de cooldown em atos
    GreenfootSound moveInSound = new GreenfootSound("squidward-walking-sound.mp3");

    public Squidward(int duration) {
        this.duration = duration;
        this.movingIn = true;
        setImage("LulaMolusco.png"); // Substitua pelo nome correto da sua imagem
        getImage().scale(200, 400); // Ajuste os valores de escala conforme necessário
    }

    protected void addedToWorld(World world) {
        startTime = System.currentTimeMillis();
        targetX = world.getWidth() - getImage().getWidth() / 2;
        setLocation(world.getWidth() + getImage().getWidth() / 2, world.getHeight() / 2);
    }

    public void act() {
        if (movingIn) {
            moveIn();
        } else {
            if (System.currentTimeMillis() - startTime >= duration) {
                getWorld().removeObject(this);
            } else {
                releaseBubbles();
            }
        }
    }

    private void moveIn() {
        if (getX() > targetX) {
            setLocation(getX() - speed, getY());
            moveInSound.play();
        } else {
            movingIn = false;
        }
    }

    private void releaseBubbles() {
        if (bubbleCooldown <= 0) {
            getWorld().addObject(new Bubble(), getX() - getImage().getWidth() / 2, getY());
            bubbleCooldown = bubbleCooldownTime; // Define o cooldown novamente
        } else {
            bubbleCooldown--; // Decrementa o cooldown
        }
    }
}
