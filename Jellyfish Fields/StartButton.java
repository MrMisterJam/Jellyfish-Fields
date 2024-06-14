import greenfoot.*;

public class StartButton extends Actor {

    /**
     * Cria um novo StartButton com o texto "Iniciar".
     */
    public StartButton() {
        // Configura a imagem do botão com o texto "Iniciar"
        GreenfootImage image = new GreenfootImage("Iniciar", 48, Color.WHITE, Color.BLACK);
        setImage(image); // Define a imagem do botão
    }

    /**
     * Verifica se o botão foi clicado a cada ato.
     * Se clicado, cria um novo mundo do jogo.
     */
    public void act() {
        if (Greenfoot.mouseClicked(this)) { // Verifica se o botão foi clicado
            Greenfoot.setWorld(new MyWorld());  // Cria um novo mundo do jogo
        }
    }
}