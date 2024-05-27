import greenfoot.*;

public class SquidwardHouse extends Actor {
    private long startTime;
    private int duration; // duração em milissegundos
    private int targetY; // posição Y alvo
    private int startY; // posição Y inicial
    private int speed = 2; // velocidade de movimento
    private boolean movingDown; // flag para indicar se está se movendo para baixo
    private int shakeAmplitude = 15; // amplitude do chacoalhar
    private double shakeFrequency = 0.05; // frequência do chacoalhar

    public SquidwardHouse(int duration) {
        this.duration = duration;
        this.movingDown = true;
    }

    protected void addedToWorld(World world) {
    startTime = System.currentTimeMillis(); // Registra o momento em que o ator foi adicionado ao mundo
    startY = 0; // Inicia no topo do mundo
    targetY = world.getHeight() - getImage().getHeight() / 2; // Define o alvo como próximo à base do mundo, considerando a altura da imagem da casa
    setLocation(world.getWidth() / 2, startY); // Posiciona o ator no topo do meio da tela
}

    public void act() {
        if (movingDown) {
            moveDown();
        }
        if (System.currentTimeMillis() - startTime >= duration) {
            MyWorld myWorld = (MyWorld) getWorld();
            myWorld.setSquidwardHousePresent(false); // Notifica o mundo que a casa foi removida
            getWorld().removeObject(this); // Remove o ator após o tempo especificado
        } else {
            checkCollisions();
        }
    }
    
    private void moveDown() {
        int currentY = getY();
        if (currentY < targetY) {
            setLocation(getX() + shakeOffset(), currentY + speed);
        } else {
            movingDown = false; // Para de se mover para baixo ao alcançar o alvo
        }
    }

    private int shakeOffset() {
        // Calcula o offset do chacoalhar baseado no tempo
        double time = System.currentTimeMillis();
        return (int) (Math.sin(time * shakeFrequency) * shakeAmplitude);
    }

    private void checkCollisions() {
        // Detecta colisões com outros atores
        Actor collidedActor = getOneIntersectingObject(Jogador.class);
        if (collidedActor != null && collidedActor instanceof Jogador) {
            Jogador jogador = (Jogador) collidedActor;
            jogador.stun(); // Chama o método stun no ator específico
            repelJogador(jogador); // Aplica a força para lançar o jogador
        }
    }

    private void repelJogador(Jogador jogador) {
    int repelForce = 100; // Força com que o jogador será repelido
    int deltaX = jogador.getX() - getX();
    int deltaY = jogador.getY() - getY();
    
    int repelDirectionX = (deltaX >= 0) ? 1 : -1; // Repelir para a direita se deltaX for positivo, senão para a esquerda
    int repelDirectionY = 0; // Repelir apenas na horizontal

    // Calcula as novas posições
    int newX = jogador.getX() + (repelDirectionX * repelForce);
    int newY = jogador.getY() + (repelDirectionY * repelForce);

    // Move o jogador para a nova posição
    jogador.setLocation(newX, newY);
}

    
}