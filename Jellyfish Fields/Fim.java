import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe Fim representa a tela de fim de jogo.
 * Mostra o resultado do jogo e uma animação correspondente.
 * 
 * @autor SeuNome
 * @versão DataOuVersão
 */
public class Fim extends World {
    GifImage GIF;

    /**
     * Construtor para objetos da classe Fim.
     * 
     * @param vencedor O ator vencedor do jogo.
     */
    public Fim(Actor vencedor) {    
        super(1366, 768, 1);
        if (vencedor != null) {
            if (vencedor instanceof SpongeBob) {
                bobganhou();
            } else if (vencedor instanceof Patrick) {
                patrickganhou();
            } 
        } else {
            empate();
        }
    }

    /**
     * Método chamado em cada ato do jogo.
     * Atualiza a imagem de fundo com a animação.
     */
    public void act() {
        GreenfootImage imagem = new GreenfootImage(GIF.getCurrentImage());
        imagem.scale(1366, 768);
        setBackground(imagem);
    }

    /**
     * Define a animação para a vitória de SpongeBob.
     */
    void bobganhou() {
        GIF = new GifImage("VitoriaBob.gif");
    }

    /**
     * Define a animação para a vitória de Patrick.
     */
    void patrickganhou() {
        GIF = new GifImage("VitoriaPatrick.gif");
    }

    /**
     * Define a animação para empate.
     */
    void empate() {
        GIF = new GifImage("Empate.gif");
    }
}