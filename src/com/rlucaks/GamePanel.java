package com.rlucaks;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import java.util.Timer;
import javax.sound.sampled.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    static boolean collisioncooldown = true;
    static boolean paused = false;
    static Thread gameThread;
    static boolean unpausing = false;
    static int unpausingnum = 3;
    static boolean hardmode = false;
    static boolean insanemode = false;
    static Ball ball;
    static Random random;
    static int interval;
    static Timer timer;
    boolean raley;

    //Removing all the statics eventually... o-o
    Image image;
    Graphics graphics;
    Winner winner1;

    Paddle paddle1;
    Paddle paddle2;

    Paused pausedclass;

    Score score;
    Mute mute;
    Exit exit;
    Boolean winner;

    Menu menu;
    SettingsMenu settingsMenu;
    DifficultyMenu difficultyMenu;
    GeneralMenu generalMenu;
    ColorMenu colorMenu;

    boolean running = true;
    int player;

    SoundEffect paddlesoundfile = new SoundEffect();
    SoundEffect scoresoundfile = new SoundEffect();
    SoundEffect wallhitsoundfile = new SoundEffect();
    CoolDownCollision cooldown = new CoolDownCollision();

    public static enum STATE {
        MENU,
        SETTINGS,
        DIFFICULTY,
        GENERAL,
        COLOURMENU,
        RUNNING,
        PAUSED,
        RESTART;
    }
    static STATE state = STATE.MENU;

    GamePanel() {
        newPaddles();
        newBall();
        mute = new Mute(GAME_WIDTH, GAME_HEIGHT);
        exit = new Exit(GAME_WIDTH, GAME_HEIGHT);
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        pausedclass = new Paused(GAME_WIDTH, GAME_HEIGHT);
        winner1 = new Winner(GAME_WIDTH, GAME_HEIGHT);
        winner = false;

        menu = new Menu();
        settingsMenu = new SettingsMenu();
        difficultyMenu = new DifficultyMenu();
        generalMenu = new GeneralMenu();
        colorMenu = new ColorMenu();

        this.addMouseListener(new MouseInput());

        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        //Location of paddle sound file
        String pathpaddle = "src/res/paddle.wav";
        File filepaddle = new File(pathpaddle);
        String absolutePathpaddle = filepaddle.getAbsolutePath();

        System.out.println(absolutePathpaddle);
        // Set file to this location
        paddlesoundfile.setFile(absolutePathpaddle);

        //Location of score sound file
        String pathscore = "src/res/score.wav";
        File filescore = new File(pathscore);
        String absolutePathscore = filescore.getAbsolutePath();

        System.out.println(absolutePathscore);
        // Set file to this location
        scoresoundfile.setFile(absolutePathscore);

        //Location of wallhit sound file
        String pathwallhit = "src/res/wallhit.wav";
        File filewallhit = new File(pathwallhit);
        String absolutePathwallhit = filewallhit.getAbsolutePath();

        System.out.println(absolutePathwallhit);
        // Set file to this location
        wallhitsoundfile.setFile(absolutePathwallhit);

        thread();
    }
    public void thread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    public static void newBall() {
        random = new Random();
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
    }


    public void newPaddles() {
        paddle1 = new Paddle(0, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }
    public void draw(Graphics g) {
        if (state == STATE.RUNNING || state == STATE.PAUSED) {
            paddle1.draw(g);
            paddle2.draw(g);
            ball.draw(g);
            score.draw(g);
            winner1.draw(g, player);
            pausedclass.draw(g);
            mute.draw(g);
            exit.draw(g);
            Toolkit.getDefaultToolkit().sync();
        } else {
            switch (state) {
                case MENU -> menu.menu(g);
                case SETTINGS -> settingsMenu.menu(g);
                case DIFFICULTY -> difficultyMenu.menu(g);
                case GENERAL -> generalMenu.menu(g);
                case COLOURMENU -> colorMenu.menu(g);
            }
        }
        Toolkit.getDefaultToolkit().sync();

    }
    public void move() {
        paddle1.move();
        paddle2.move();
        ball.move();

    }
    public void checkCollision() {
        //stops paddles at edge of the window

        //paddle 1
        if (paddle1.y <= 0) {
            paddle1.y = 1;
        }
        if (paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;

        //paddle 2
        if (paddle2.y <= 0)
            paddle2.y = 1;
        if (paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;

        //bounces ball off paddles
        if (ball.intersects(paddle1)) {
            if (collisioncooldown) {
                if (!Mute.muted) {
                    paddlesoundfile.play();
                }
                // Random int, if its 1 or more it will flip if its less it will reverse direction
                Random rand = new Random();
                ball.xVelocity = Math.abs(ball.xVelocity);
                ball.xVelocity++;
                if (ball.yVelocity > 0) {
                    ball.yVelocity++;
                } else {
                    ball.yVelocity--;
                }
                int noreverse = rand.nextInt(2);
                if ((noreverse >= 1) && (collisioncooldown)) {
                    ball.setYDirection(-ball.yVelocity);
                } else {
                    ball.setYDirection(ball.yVelocity);
                }
                ball.setXDirection(ball.xVelocity);
                cooldown.cooldown();
                if (raley) {
                    score.raleynum++;
                    raley = false;
                } else if (!raley)
                    raley = true;
            }
        }

        if (ball.intersects(paddle2)) {
            if (collisioncooldown) {
                if (!Mute.muted) {
                    paddlesoundfile.play();
                }
                // Random int, if its 1 or more it will flip if its less it will reverse direction
                Random rand = new Random();
                ball.xVelocity = Math.abs(ball.xVelocity);
                ball.xVelocity++;
                if (ball.yVelocity > 0) {
                    ball.yVelocity++;
                } else {
                    ball.yVelocity--;
                }
                int noreverse = rand.nextInt(2);
                if ((noreverse >= 1) && (collisioncooldown)) {
                    ball.setYDirection(ball.yVelocity);
                } else {
                    ball.setYDirection(ball.yVelocity);
                }
                ball.setXDirection(-ball.xVelocity);
                cooldown.cooldown();
                if (raley) {
                    score.raleynum++;
                    raley = false;
                } else if (!raley)
                    raley = true;
            }
        }

        //bounce the ball ball of the top of the window and bottom

        if (ball.y <= 0) {
            if (!Mute.muted) {
                wallhitsoundfile.play();
            }
            ball.setYDirection(-ball.yVelocity);
        }
        if (ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
            if (!Mute.muted) {
                wallhitsoundfile.play();
            }
            ball.setYDirection(-ball.yVelocity);
        }

        //give player 1 point and creates new paddles & ball if goes off screen left/right
        if (ball.x <= 0) {
            score.player2++;
            score.raleynum = 0;
            raley = false;
            if (!Mute.muted) {
                scoresoundfile.play();
            }
            if (score.player2 == 10) {
                player = 2;
                state = STATE.PAUSED;
            }
            newPaddles();
            newBall();
        }

        if (ball.x >= GAME_WIDTH-BALL_DIAMETER) {
            score.player1++;
            score.raleynum = 0;
            raley = false;
            if (!Mute.muted) {
                scoresoundfile.play();
            }
            if (score.player1 == 10) {
                player = 1;
                state = STATE.PAUSED;
            }
            newPaddles();
            newBall();
            }
        }

    public void run() {
        // game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now -lastTime)/ns;
            lastTime = now;
            if (delta >= 1) {
                repaint();
                tick();
                delta--;

            }
        }

    }
    public void tick() {
        // Only do tick if it is in the running state
        if (state == STATE.RUNNING) {
            move();
            checkCollision();
        }
        if (state == STATE.RESTART) {
            state = STATE.MENU;
            newBall();
            newPaddles();
            score = new Score(GAME_WIDTH, GAME_HEIGHT);
            paused = false;
        }
    }

    //Al = action listener
    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {

            //If game is not paused move paddles

            if (state == STATE.RUNNING) {
                //Checking if its currently unpausing
                if (!unpausing) {
                    paddle1.keyPressed(e);
                    paddle2.keyPressed(e);
                }
            }

            // restart game once key is pressed if game has ended

            if (state == STATE.PAUSED) {
                if (e.getKeyCode()==KeyEvent.VK_SPACE) {
                    if (!paused) {
                        if (!unpausing) {
                            state = STATE.MENU;
                            player = 0;
                            score = new Score(GAME_WIDTH, GAME_HEIGHT);
                            newPaddles();
                            newBall();
                        }
                    }
                }
            }

            //Pause game if esc key is pressed

            if (state == STATE.RUNNING) {
                if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
                    if (!unpausing) {
                        state = STATE.PAUSED;
                        paused = true;
                        pausedclass.draw(graphics);
                        repaint();
                        return;
                    }
                }
            }

            //Unpause if paused

            if (state == STATE.PAUSED) {
                if (e.getKeyCode()==KeyEvent.VK_SPACE || e.getKeyCode()==KeyEvent.VK_ESCAPE) {
                    if (paused) {
                        paused = false;
                        unpausing = true;

                        String secs = "3";
                        int delay = 1000;
                        int period = 1000;
                        timer = new Timer();
                        interval = Integer.parseInt(secs);
                        pausedclass.draw(graphics);
                        repaint();
                        timer.scheduleAtFixedRate(new TimerTask() {

                            //Loops 3 times with a delay of one second per time and at the beggining for the numbers to count down
                            //Once its reached 0 (1 on screen) it will resume the game
                            public void run() {
                                repaint();
                                Toolkit.getDefaultToolkit().sync();
                                unpausingnum--;
                                setInterval();
                                pausedclass.draw(graphics);
                                repaint();
                                if (unpausingnum == 0) {
                                    unpausingnum = 3;
                                    unpausing = false;
                                    repaint();
                                    Toolkit.getDefaultToolkit().sync();
                                    state = STATE.RUNNING;

                                }
                            }
                        }, delay, period);


                    }
                }
            }

        }
        // Checking if its reached 1
        private final int setInterval() {
            if (interval == 1)
                timer.cancel();
            return --interval;
        }

        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);

        }
    }

    public class SoundEffect {
        Clip clip;

        public void setFile(String soundFileName) {
            try {
                File file = new File(soundFileName);
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                AudioFormat format = sound.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                this.clip = (Clip) AudioSystem.getLine(info);
                clip.open(sound);
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }

        public void play() {
            clip.setFramePosition(0);
            clip.start();
        }
    }
    public class CoolDownCollision implements Runnable {

        public void cooldown() {
            thread();
            GamePanel.collisioncooldown = false;

        }

        @Override
        public void run() {
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            GamePanel.collisioncooldown = true;

        }

        public void thread() {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }
}