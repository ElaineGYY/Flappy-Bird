import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Game FlappyBird! Press Space to play!
 * 
 * @Yueyao Gong 
 * @version 1
 */
public class FlappyWorld extends World
{
    int pipe_counter = 0;
    int PIPE_SPACE = 120;
    int GroundLevel = 20;
    int score = 0;
    int FIRST_PIPE = 240;
    int win_counter = 0;
    Score scoreObj = null;
    
    /**
     * Constructor for objects of class FlappyWorld.
     * 
     */
    public FlappyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false); 
        
        // Set paint order
        setPaintOrder(GameOver.class, Score.class, Flappybird.class, Pipe.class, bot_Pipe.class, Ground.class);
        
        // Creat a Flappy Bird Object
        Flappybird flappy = new Flappybird();
        
        // Add flappy to our world
        addObject (flappy, 100, getHeight()/2);
        
        // Create Ground object
        Ground ground = new Ground();
        
        // Add Ground object
        addObject (ground, getWidth()/2, getHeight() - GroundLevel);
        
        // Create a Score object
        scoreObj = new Score();
        scoreObj.setScore (0);
        
        // Add a Score object
        addObject(scoreObj, 290, 80);
        
    }
    
    public void act() {
        createPipe();
        
        if (pipe_counter >= FIRST_PIPE) {
            if (win_counter % 100 == 0) {
               score++;
               scoreObj.setScore(score);
            }
            win_counter++;
        }
    }
    
    private void createPipe() {
           pipe_counter++;
        int random = Greenfoot.getRandomNumber(150);
        if (pipe_counter % 100 == 0) {
            // Create a pipe object
            Pipe pipe = new Pipe();
            bot_Pipe bot_pipe = new bot_Pipe();
            GreenfootImage pipe_image = pipe.getImage();
            GreenfootImage bot_pipe_image = bot_pipe.getImage();
            addObject(pipe, getWidth(), getHeight()/2 - pipe_image.getHeight() + random);
            addObject(bot_pipe, getWidth(), getHeight()/2 - pipe_image.getHeight() + random + bot_pipe_image.getHeight() + PIPE_SPACE);
        }
        
    }
}
