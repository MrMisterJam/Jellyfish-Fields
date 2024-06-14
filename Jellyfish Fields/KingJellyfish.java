import greenfoot.*;

public class KingJellyfish extends Actor {
    private long startTime; // Tempo de início de aparição
    private int duration; // Duração de aparição
    private boolean fadeOut = false; // Flag para indicar se deve desaparecer gradualmente

    /**
     * Cria um novo KingJellyfish com a duração especificada.
     * @param duration A duração em milissegundos que o King Jellyfish permanecerá visível antes de desaparecer.
     */
    public KingJellyfish(int duration) {
        // Configura a imagem do King Jellyfish
        setImage("King.png");
        getImage().scale(500, 600); // Ajusta o tamanho da imagem conforme necessário
        getImage().setTransparency(0); // Define a transparência inicial como 0 (invisível)
        this.duration = duration; // Define a duração de aparição
    }

    /**
     * Atualiza o estado do King Jellyfish a cada ato.
     * A imagem aparece gradualmente e depois desaparece.
     */
    public void act() {
        if (getImage().getTransparency() < 255 && !fadeOut) {
            getImage().setTransparency(getImage().getTransparency() + 1); // Aumenta gradualmente a opacidade da imagem
            return;
        }
        if (System.currentTimeMillis() - startTime >= duration && !fadeOut) {
            fadeOut = true; // Define que deve começar a desaparecer gradualmente
        }
        if (fadeOut) {
            if (getImage().getTransparency() > 0) {
                getImage().setTransparency(getImage().getTransparency() - 1); // Diminui gradualmente a opacidade da imagem
            } else {
                getWorld().removeObject(this); // Remove o King Jellyfish do mundo quando estiver completamente transparente
            }
        }
    }

    /**
     * Registra o tempo de início da aparição quando o ator é adicionado ao mundo.
     */
    protected void addedToWorld(World world) {
        startTime = System.currentTimeMillis(); // Registra o tempo de início
        setLocation(world.getWidth() / 2, world.getHeight() / 2); // Define a posição inicial no centro do mundo
    }
}