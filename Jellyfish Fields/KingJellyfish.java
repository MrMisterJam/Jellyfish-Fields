import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class KingJellyfish here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class KingJellyfish extends Actor
{
    long startTime;
    int duration;
    boolean sumir = false;
    public KingJellyfish(int duration) {
        setImage("King.png"); // Substitua pelo nome correto da sua imagem
        getImage().scale(500, 600); // Ajuste os valores de escala conforme necessário
        getImage().setTransparency(0);
        this.duration = duration;
    }
    protected void addedToWorld(World world) {
        startTime = System.currentTimeMillis();
        setLocation(world.getWidth() / 2, world.getHeight() / 2);
    }
    /**
     * Act - do whatever the KingJellyfish wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (getImage().getTransparency() < 255 && sumir == false) {
            getImage().setTransparency(getImage().getTransparency() + 1);
            return;
        }
        if (System.currentTimeMillis() - startTime >= duration) {
                sumir = true;
        }
        if (sumir) {
            if (getImage().getTransparency() > 0) {
                getImage().setTransparency(getImage().getTransparency() - 1);
            } else {
                getWorld().removeObject(this);
            }
        }
    }
}
