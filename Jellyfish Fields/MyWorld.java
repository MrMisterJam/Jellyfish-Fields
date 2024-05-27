import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    int JellyfishIntervalo = 200;  // Intervalo em ticks para gerar novos atores
    int BlueJellyfishIntervalo = 600;
    int timerJ = 0;  // Contador de ticks
    int timerBJ = 0;
    int contador = 0;
    Counter PontosBob, PontosPatrick, Contagem;
    GreenfootSound JogoSom = new GreenfootSound("Jogo.mp3");
    private boolean squidwardHousePresent = false;
    private int krabbyPattyIntervalo = 1000;  // Intervalo em atos para gerar novos Krabby Patties
    private int krabbyPattyTimer = 0;  // Contador de atos
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1366, 768, 1);
        prepare();
        JogoSom.setVolume(55);
        JogoSom.playLoop();
        PontosBob = new Counter("Pontos: ", Color.YELLOW);  // Inicializa o contador com o texto "Score: "
        addObject(PontosBob, 1200, 50);  // Adiciona o contador ao mundo na posição desejada
        PontosPatrick = new Counter("Pontos: ", Color.PINK);  // Inicializa o contador com o texto "Score: "
        addObject(PontosPatrick, 100, 50);  // Adiciona o contador ao mundo na posição desejada
        Contagem = new Counter("", Color.WHITE);
        Contagem.setFontSize(100);
        Contagem.setValue(180);
        addObject(Contagem, 683, 50);
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Jellyfish jellyfish = new Jellyfish();
        addObject(jellyfish,680,339);
        SpongeBob spongeBob = new SpongeBob();
        addObject(spongeBob,131,638);
        Patrick patrick = new Patrick();
        addObject(patrick,1234,614);
        patrick.setLocation(435,657);
        spongeBob.setLocation(1227,670);
        patrick.setLocation(106,683);
        patrick.setLocation(114,634);
        spongeBob.setLocation(1199,632);
    }
    
    public void act() {
        timerJ++;
        timerBJ++;
        contador++;
        
        if (timerJ >= JellyfishIntervalo) {
            Jellyfish jellyfish = new Jellyfish();
            generateActor(jellyfish);
            timerJ = 0;  // Reseta o contador
        }
        if (timerBJ >= BlueJellyfishIntervalo) {
            BlueJellyfish jellyfish = new BlueJellyfish();
            generateActor(jellyfish);
            timerBJ = 0;  // Reseta o contador
        }
        // Código para adicionar o SpecialActor
        if (Greenfoot.getRandomNumber(10000) < 5) { // Chance aleatória de aparecer (ajuste conforme necessário)
    SquidwardHouseEvent();
}

            if (Greenfoot.getRandomNumber(10000) < 5) { // Ajuste a probabilidade conforme necessário
                SquidwardEvent();
                
            }
        
        // Verifica se é hora de gerar um novo Krabby Patty
        if (krabbyPattyTimer >= krabbyPattyIntervalo) {
            gerarKrabbyPatty();
            krabbyPattyTimer = 0;  // Reinicia o contador
        } else {
            krabbyPattyTimer++;  // Incrementa o contador
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
    void AumentarPontosBob(int valor) {
        PontosBob.aumentar(valor);
    }
    void AumentarPontosPatrick(int valor) {
        PontosPatrick.aumentar(valor);
    }

    // Método para gerar atores sem colisão
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

    // Método para verificar colisões em uma posição específica
    private boolean checkCollisionAt(Actor ator, int x, int y) {
        List<Actor> objects = getObjects(Actor.class);
        for (Actor obj : objects) {
            if (obj != ator && areActorsColliding(obj, ator, x, y)) {
                return true;
            }
        }
        return false;
    }

    // Método para verificar se dois atores estão colidindo, com o segundo ator em uma posição específica
    private boolean areActorsColliding(Actor a, Actor b, int bX, int bY) {
        int aX = a.getX();
        int aY = a.getY();

        int aWidth = a.getImage().getWidth();
        int aHeight = a.getImage().getHeight();
        int bWidth = b.getImage().getWidth();
        int bHeight = b.getImage().getHeight();

        return Math.abs(aX - bX) < (aWidth + bWidth) / 2 &&
               Math.abs(aY - bY) < (aHeight + bHeight) / 2;
    }
    
    Actor checarVencendor(int pontosBob, int pontosPatrick) {
        Actor vencedor = null;
        if (pontosBob > pontosPatrick) {
            vencedor = new SpongeBob();
        } else if (pontosPatrick > pontosBob) {
            vencedor = new Patrick();
        }
        return vencedor;
    }
    private void SquidwardHouseEvent() {
    if (!squidwardHousePresent) {
        SquidwardHouse specialActor = new SquidwardHouse(10000); // Ator que fica visível por 10 segundos
        addObject(specialActor, getWidth() / 2, 0); // Adiciona no topo do mundo
        squidwardHousePresent = true;
    }
}
public void setSquidwardHousePresent(boolean present) {
    squidwardHousePresent = present;
}
private void SquidwardEvent() {
        // Verifica se já existe um Squidward no mundo
        if (getObjects(Squidward.class).isEmpty()) {
            Squidward squidward = new Squidward(15000);
            addObject(squidward, getWidth(), getHeight() / 2); // Adiciona no lado direito do mundo
        }
    }
    private void gerarKrabbyPatty() {
        KrabbyPatty krabbyPatty = new KrabbyPatty();
        int x = Greenfoot.getRandomNumber(getWidth() - 400) + 200;  // Posição aleatória no eixo X
        int y = 0;  // Começa no topo do mundo
        addObject(krabbyPatty, x, y);  // Adiciona o Krabby Patty ao mundo
    }
}
