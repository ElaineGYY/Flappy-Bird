import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Display Flappybird on the World.
 * 
 * @Yueyao Gong 
 * @version 1
 */
public class Flappybird extends Actor
{
    double dy = 0;
    double g = 0.6;
    double BOOST_SPEED = -6;
    int outter_range = 10;
    int inner_range = 5;
    int middle_point = 0;
    int big_rotat = 15;
    int small_rotat = 13;
    
    /**
     * Act - do whatever the Flappybird wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        rotateBird();
        setLocation( getX(), (int) (getY() + dy) );
        
        // if we are touching a pipe,then Game Over
        if (getOneIntersectingObject (Pipe.class) != null) {
            GameOver();
        }
        
        if (getOneIntersectingObject (bot_Pipe.class) != null) {
            GameOver();
        }
        
        // if user pressed UP arrow, launch Flappy Bird upward
        if (Greenfoot.isKeyDown ("space") == true) {
            dy = BOOST_SPEED;
        }
        
        // if Flappybird drops out of the world, Game Over!
        if (getY() >getWorld().getHeight()) {
            GameOver();
        }
        
        dy = dy + g;   
    }   
    
    private void GameOver() {
           GameOver gameOver = new GameOver();
           // Add GameOver through FlappyWorld
           getWorld().addObject(gameOver, getWorld().getWidth()/2, getWorld().getHeight()/2);
           Greenfoot.stop();
    }
    
    private void rotateBird() {
        if ((outter_range >= dy) && (dy > inner_range)) {
             setRotation(big_rotat);
        }
        else if ((inner_range >= dy) && (dy > middle_point)) {
            setRotation(small_rotat);
        }
        
        else if (dy == middle_point) {
            setRotation(middle_point);
        }
        
        else if ((middle_point > dy) && (dy >= -inner_range)) {
            setRotation(-small_rotat);
        }
        
        else if ((-inner_range > dy) && (dy >= -outter_range)) {
            setRotation(-big_rotat);
        }
    }
}
