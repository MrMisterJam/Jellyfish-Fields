import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Classe MyWorld representa o mundo do jogo.
 * É responsável por inicializar e gerenciar os elementos do jogo, incluindo atores e eventos.
 * 
 * @autor SeuNome
 * @versão DataOuVersão
 */
public class MyWorld extends World {
    // Intervalos para gerar novos atores
    int JellyfishIntervalo = 200;  
    int BlueJellyfishIntervalo = 600;
    
    // Contadores de ticks
    int timerJ = 0;  
    int timerBJ = 0;
    int contador = 0;
    
    // Contadores de pontos e cronômetro
    Counter PontosBob, PontosPatrick, Contagem;
    
    // Som do jogo
    GreenfootSound JogoSom = new GreenfootSound("Jogo.mp3");
    
    // Controle de eventos especiais
    private boolean squidwardHousePresent = false;
    private int krabbyPattyIntervalo = 1000;  // Intervalo para gerar novos Krabby Patties
    private int krabbyPattyTimer = 0;  // Contador de atos

    /**
     * Construtor para objetos da classe MyWorld.
     */
    public MyWorld() {    
        // Cria um novo mundo com 1366x768 células, cada uma com 1x1 pixels.
        super(1366, 768, 1);
        prepare();
        JogoSom.setVolume(55);
        JogoSom.playLoop();
        
        // Inicializa contadores de pontos
        PontosBob = new Counter("Pontos: ", Color.YELLOW);
        addObject(PontosBob, 1200, 50);
        
        PontosPatrick = new Counter("Pontos: ", Color.PINK);
        addObject(PontosPatrick, 100, 50);
        
        Contagem = new Counter("", Color.WHITE);
        Contagem.setFontSize(100);
        Contagem.setValue(180);
        addObject(Contagem, 683, 50);
        
        // Define a ordem de pintura dos atores
        setPaintOrder(Counter.class, Jogador.class, KrabbyPatty.class, Glasses.class, Bubble.class, Jellyfish.class, BlueJellyfish.class, SquidwardHouse.class, KingJellyfish.class);
    }

    /**
     * Prepara o mundo para o início do programa.
     * Cria os objetos iniciais e os adiciona ao mundo.
     */
    private void prepare() {
        addObject(new Jellyfish(), 680, 339);
        SpongeBob spongeBob = new SpongeBob();
        addObject(spongeBob, 131, 638);
        Patrick patrick = new Patrick();
        addObject(patrick, 1234, 614);
        spongeBob.setLocation(1199, 632);
        patrick.setLocation(114, 634);
    }

    /**
     * Método chamado em cada ato do jogo.
     * Atualiza contadores, gera novos atores e verifica eventos.
     */
    public void act() {
        timerJ++;
        timerBJ++;
        contador++;
        definirJellyfishCooldown();
        
        if (timerJ >= JellyfishIntervalo) {
            generateActor(new Jellyfish());
            timerJ = 0;
        }
        
        if (timerBJ >= BlueJellyfishIntervalo) {
            generateActor(new BlueJellyfish());
            timerBJ = 0;
        }
        
        if (Greenfoot.getRandomNumber(10000) < 5) {
            SquidwardHouseEvent();
        }

        if (Greenfoot.getRandomNumber(10000) < 5) {
            SquidwardEvent();
        }
        
        if (Greenfoot.getRandomNumber(7500) < 5) {
            gerarOculos();
        }
        
        if (Greenfoot.getRandomNumber(10000) < 5) {
            KingJellyfishEvent();
        }
        
        if (krabbyPattyTimer >= krabbyPattyIntervalo) {
            gerarKrabbyPatty();
            krabbyPattyTimer = 0;
        } else {
            krabbyPattyTimer++;
        }
        
        if (contador >= 100) {
            Contagem.decrement();
            contador = 0;
        }
        
        if (Contagem.getValue() <= 0) {
            Actor vencedor = checarVencendor(PontosBob.getValue(), PontosPatrick.getValue());
            Greenfoot.setWorld(new Fim(vencedor));
        }
    }

    /**
     * Aumenta os pontos de SpongeBob.
     */
    void AumentarPontosBob(int valor) {
        PontosBob.aumentar(valor);
    }

    /**
     * Aumenta os pontos de Patrick.
     */
    void AumentarPontosPatrick(int valor) {
        PontosPatrick.aumentar(valor);
    }

    /**
     * Gera um ator em uma posição aleatória sem colisão.
     */
    private void generateActor(Actor ator) {
        int x, y;
        boolean collision;
        do {
            x = Greenfoot.getRandomNumber(getWidth());
            y = Greenfoot.getRandomNumber(getHeight());
            collision = checkCollisionAt(ator, x, y);
        } while (collision);
        addObject(ator, x, y);
    }

    /**
     * Verifica colisões em uma posição específica.
     */
    private boolean checkCollisionAt(Actor ator, int x, int y) {
        List<Jogador> jogadores = getObjects(Jogador.class);
        for (Actor jogador : jogadores) {
            if (jogador != ator && areActorsColliding(jogador, ator, x, y)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se dois atores estão colidindo.
     */
    private boolean areActorsColliding(Actor a, Actor b, int bX, int bY) {
        int aX = a.getX();
        int aY = a.getY();
        int aWidth = a.getImage().getWidth();
        int aHeight = a.getImage().getHeight();
        int bWidth = b.getImage().getWidth();
        int bHeight = b.getImage().getHeight();
        return Math.abs(aX - bX) < (aWidth + bWidth) / 2 && Math.abs(aY - bY) < (aHeight + bHeight) / 2;
    }

    /**
     * Checa o vencedor com base nos pontos.
     */
    Actor checarVencendor(int pontosBob, int pontosPatrick) {
        if (pontosBob > pontosPatrick) {
            return new SpongeBob();
        } else if (pontosPatrick > pontosBob) {
            return new Patrick();
        }
        return null;
    }

    /**
     * Evento da casa do Squidward.
     */
    private void SquidwardHouseEvent() {
        if (!squidwardHousePresent) {
            addObject(new SquidwardHouse(10000), getWidth() / 2, 0);
            squidwardHousePresent = true;
        }
    }

    /**
     * Define se a casa do Squidward está presente.
     */
    public void setSquidwardHousePresent(boolean present) {
        squidwardHousePresent = present;
    }

    /**
     * Evento do Squidward.
     */
    private void SquidwardEvent() {
        if (getObjects(Squidward.class).isEmpty()) {
            addObject(new Squidward(15000), getWidth(), getHeight() / 2);
        }
    }

    /**
     * Gera um Krabby Patty.
     */
    private void gerarKrabbyPatty() {
        int x = Greenfoot.getRandomNumber(getWidth() - 400) + 200;
        addObject(new KrabbyPatty(), x, 0);
    }

    /**
     * Gera um óculos.
     */
    private void gerarOculos() {
        int x = Greenfoot.getRandomNumber(getWidth() - 400) + 200;
        addObject(new Glasses(), x, 0);
    }

    /**
     * Evento do King Jellyfish.
     */
    private void KingJellyfishEvent() {
        if (getObjects(KingJellyfish.class).isEmpty()) {
            addObject(new KingJellyfish(15000), getWidth(), getHeight());
        }
    }

    /**
     * Define o cooldown para gerar Jellyfish.
     */
    private void definirJellyfishCooldown() {
        if (getObjects(KingJellyfish.class).isEmpty()) {
            JellyfishIntervalo = 200;
            BlueJellyfishIntervalo = 600;
        } else {
            JellyfishIntervalo = 50;
            BlueJellyfishIntervalo = 300;
        }
    }
}