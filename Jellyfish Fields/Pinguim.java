import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pinguim here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pinguim extends Actor
{
    GifImage GIF = new GifImage("2375092_2f794.gif");
    /**
     * Act - do whatever the Pinguim wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setImage( GIF.getCurrentImage() );
    }
}
