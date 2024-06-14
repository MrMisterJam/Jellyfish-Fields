import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A classe KrabbyPatty representa um hambúrguer que cai no jogo.
 * Ele aumenta a velocidade do jogador temporariamente quando colidido.
 * 
 * @autor SeuNome
 * @versão DataOuVersão
 */
public class KrabbyPatty extends Actor {
    GifImage GIF = new GifImage("KrabbyPatty.gif");
    private int speed = 2; // Velocidade de queda
    private int speedBoost = 3; // Aumento de velocidade para o jogador
    int duracao = 500;

    /**
     * Método chamado em cada ato do jogo.
     * Atualiza a imagem e movimenta o hambúrguer.
     */
    public void act() {
        GreenfootImage image = GIF.getCurrentImage();
        image.scale(60, 60); // Ajuste conforme necessário
        setImage(image);

        // Move para baixo
        setLocation(getX(), getY() + speed);

        // Verifica se colidiu com um jogador
        if (isTouching(Jogador.class)) {
            Jogador jogador = (Jogador) getOneIntersectingObject(Jogador.class);
            jogador.aumentarVelocidade(speedBoost, duracao);
            getWorld().removeObject(this);
        } else if (getY() >= getWorld().getHeight() - 1) { // Verifica se chegou na base do mundo
            getWorld().removeObject(this);
        }
    }
}