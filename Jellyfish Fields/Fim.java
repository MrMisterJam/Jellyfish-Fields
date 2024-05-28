import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fim here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fim extends World
{
    GifImage GIF;
    /**
     * Constructor for objects of class Fim.
     * 
     */
    public Fim(Actor vencedor)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
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
    
    public void act() {
        GreenfootImage imagem = new GreenfootImage(GIF.getCurrentImage());
        imagem.scale(1366, 768);
        setBackground(imagem);
    }
    
    void bobganhou() {
        GIF = new GifImage("VitoriaBob.gif");
    }
    
    void patrickganhou() {
        GIF = new GifImage("VitoriaPatrick.gif");
    }
    void empate() {
        GIF = new GifImage("Empate.gif");
    }
}
