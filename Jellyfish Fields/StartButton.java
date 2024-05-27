import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartButton extends Actor {

    public StartButton() {
        GreenfootImage image = new GreenfootImage("Iniciar", 24, Color.WHITE, Color.BLACK);
        setImage(image);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new MyWorld());  // Cria o mundo do jogo
        }
    }
}
