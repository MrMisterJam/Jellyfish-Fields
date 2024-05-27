import greenfoot.*;

public class Bubble extends Actor {
    private int horizontalSpeed; // Velocidade horizontal
    private double verticalAmplitude; // Amplitude vertical
    private double verticalFrequency; // Frequência vertical
    private double verticalPhase; // Fase vertical
    private long creationTime;

    public Bubble() {
        horizontalSpeed = 2; // Ajuste a velocidade horizontal conforme necessário
        verticalAmplitude = 5; // Ajuste a amplitude vertical conforme necessário
        verticalFrequency = 0.5; // Ajuste a frequência vertical conforme necessário
        verticalPhase = Greenfoot.getRandomNumber(360); // Fase inicial aleatória
        creationTime = System.currentTimeMillis();
        
        // Escolhe uma imagem aleatória entre duas
        if (Greenfoot.getRandomNumber(2) == 0) {
            setImage("bolhas1.png");
        } else {
            setImage("bolhas2.png");
        }
        
        // Ajusta o tamanho da imagem da bolha
        getImage().scale(50, 50); // Ajuste conforme necessário
    }

    public void act() {
        long elapsedTime = System.currentTimeMillis() - creationTime;
        double angle = Math.toRadians((elapsedTime * verticalFrequency + verticalPhase) % 360);
        int offsetY = (int) (Math.sin(angle) * verticalAmplitude);
        
        setLocation(getX() - horizontalSpeed, getY() + offsetY);

        if (getX() <= 0 || getY() < 0 || getY() >= getWorld().getHeight()) {
            getWorld().removeObject(this); // Remover bolhas quando saem da tela
        } else {
            checkCollisions();
        }
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
    
    int repelDirectionX = 0; // Não repelir na horizontal
    int repelDirectionY = (deltaY >= 0) ? 1 : -1; // Repelir para baixo se deltaY for positivo, senão para cima

    // Calcula as novas posições
    int newX = jogador.getX() + (repelDirectionX * repelForce);
    int newY = jogador.getY() + (repelDirectionY * repelForce);

    // Move o jogador para a nova posição
    jogador.setLocation(newX, newY);
}
}
