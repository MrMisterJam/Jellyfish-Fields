import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe Menu representa o menu inicial do jogo.
 * Contém o botão para iniciar o jogo.
 * 
 * @autor SeuNome
 * @versão DataOuVersão
 */
public class Menu extends World {

    /**
     * Construtor para objetos da classe Menu.
     */
    public Menu() {    
        super(1366, 768, 1);
        getBackground().scale(1366, 768);
        prepare();
    }

    /**
     * Prepara o menu inicial, adicionando os objetos necessários.
     */
    private void prepare() {
        StartButton startButton = new StartButton();
        addObject(startButton, 674, 653);
        startButton.setLocation(688, 650);
    }
}