import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu extends World
{

    /**
     * Constructor for objects of class Menu.
     * 
     */
    public Menu()
    {    
        super(1366, 768, 1);
        getBackground().scale(1366, 768);
        prepare();
    }
    
    private void prepare() {

        StartButton startButton = new StartButton();
        addObject(startButton,674,653);
        startButton.setLocation(688,650);
    }
}
