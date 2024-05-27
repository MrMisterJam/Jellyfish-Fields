import greenfoot.*;

public class Squidward extends Actor {
    private long startTime;
    private int duration; // duração em milissegundos
    private int targetX; // posição X alvo
    private int speed = 1; // velocidade de movimento
    private boolean movingIn; // flag para indicar se está se movendo para dentro
    private int bubbleCooldown = 0;
private final int bubbleCooldownTime = 250; // Tempo de cooldown em atos (ajuste conforme necessário)
GreenfootSound MoveInSound = new GreenfootSound("squidward-walking-sound.mp3");
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
            MoveInSound.play();
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