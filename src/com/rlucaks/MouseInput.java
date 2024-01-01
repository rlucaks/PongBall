package com.rlucaks;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.rlucaks.GamePanel.*;

public class MouseInput implements MouseListener {

    boolean clickcooldown;
    CoolDownClick cooldownclick = new CoolDownClick();

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

        if (GamePanel.state == GamePanel.STATE.MENU) {

            int mx = e.getX();
            int my = e.getY();

            //public Rectangle settings = new Rectangle(GAME_WIDTH / 2 - 5- 200, 250, 121 ,50);
            //public Rectangle play = new Rectangle(GAME_WIDTH / 2 - 5- 50, 250, 121 ,50);
            //public Rectangle quit = new Rectangle(GAME_WIDTH / 2 - 5+100, 250, 121 ,50);
            //start button
            if (mx >= GAME_WIDTH / 2 - 5 - 50 && mx <= GAME_WIDTH / 2 + 63) {
                if (my >= 250 && my <= 300) {

                    System.out.println("play");
                    GamePanel.state = GamePanel.STATE.RUNNING;

                }
            }

            //settings button
            if (mx >= GAME_WIDTH / 2 - 5 - 200 && mx <= GAME_WIDTH / 2 - 87) {
                if (my >= 250 && my <= 300) {

                    System.out.println("settings");
                    GamePanel.state = GamePanel.STATE.SETTINGS;
                    cooldownclick.cooldown();
                }
            }

            //quit button
            if (mx >= GAME_WIDTH / 2 - 5 + 100 && mx <= GAME_WIDTH / 2 + 205) {
                if (my >= 250 && my <= 300) {

                    System.out.println("quit");
                    System.exit(1);
                }
            }

        }


        if (GamePanel.state == GamePanel.STATE.SETTINGS) {
            if (clickcooldown) {

                int mx = e.getX();
                int my = e.getY();


                //start button
                if (mx >= GAME_WIDTH / 2 - 5 - 50 && mx <= GAME_WIDTH / 2 + 63) {
                    if (my >= 250 && my <= 300) {

                        System.out.println("difficulty");
                        GamePanel.state = GamePanel.STATE.DIFFICULTY;
                        cooldownclick.cooldown();

                    }
                }

                //settings button
                if (mx >= GAME_WIDTH / 2 - 5 - 200 && mx <= GAME_WIDTH / 2 - 87) {
                    if (my >= 250 && my <= 300) {

                        System.out.println("general");
                        GamePanel.state = GamePanel.STATE.GENERAL;
                    }
                }

                //quit button
                if (mx >= GAME_WIDTH / 2 - 5 + 100 && mx <= GAME_WIDTH / 2 + 205) {
                    if (my >= 250 && my <= 300) {

                        System.out.println("back");
                        GamePanel.state = GamePanel.STATE.MENU;
                    }
                }

            }
        }

        if (GamePanel.state == GamePanel.STATE.DIFFICULTY) {
            if (clickcooldown) {

                int mx = e.getX();
                int my = e.getY();

                //start button
                if (mx >= GAME_WIDTH / 2 - 5 - 50 && mx <= GAME_WIDTH / 2 + 63) {
                    if (my >= 250 && my <= 300) {

                        System.out.println("Hard");
                        GamePanel.state = GamePanel.STATE.SETTINGS;
                        GamePanel.insanemode = false;
                        GamePanel.hardmode = true;
                        newBall();
                        cooldownclick.cooldown();

                    }
                }

                //settings button
                if (mx >= GAME_WIDTH / 2 - 5 - 200 && mx <= GAME_WIDTH / 2 - 87) {
                    if (my >= 250 && my <= 300) {

                        System.out.println("Normal");
                        GamePanel.state = GamePanel.STATE.SETTINGS;
                        GamePanel.hardmode = false;
                        GamePanel.insanemode = false;
                        newBall();
                        cooldownclick.cooldown();
                    }
                }

                //quit button
                if (mx >= GAME_WIDTH / 2 - 5 + 100 && mx <= GAME_WIDTH / 2 + 205) {
                    if (my >= 250 && my <= 300) {

                        System.out.println("Insane");
                        GamePanel.state = GamePanel.STATE.SETTINGS;
                        GamePanel.hardmode = false;
                        GamePanel.insanemode = true;
                        newBall();
                        cooldownclick.cooldown();
                    }
                }

            }
        }

        if (GamePanel.state == GamePanel.STATE.GENERAL) {
            if (clickcooldown) {

                int mx = e.getX();
                int my = e.getY();

                //start button
                if (mx >= GAME_WIDTH / 2 - 5 - 50 && mx <= GAME_WIDTH / 2 + 63) {
                    if (my >= 250 && my <= 300) {

                        System.out.println("Background colour");
                        GamePanel.state = GamePanel.STATE.COLOURMENU;
                        cooldownclick.cooldown();

                    }
                }

                //quit button
                if (mx >= GAME_WIDTH / 2 - 5 + 100 && mx <= GAME_WIDTH / 2 + 205) {
                    if (my >= 250 && my <= 300) {

                        System.out.println("Back");
                        GamePanel.state = GamePanel.STATE.SETTINGS;
                        cooldownclick.cooldown();
                    }
                }

            }
        }

        if (GamePanel.state == GamePanel.STATE.COLOURMENU) {
            if (clickcooldown) {

                int mx = e.getX();
                int my = e.getY();

                if (mx >= GAME_WIDTH / 2 - 5 - 50 && mx <= GAME_WIDTH / 2 + 63) {
                    if (my >= 250 && my <= 300) {

                        System.out.println("White");
                        GamePanel.state = GamePanel.STATE.GENERAL;

                    }
                }

                if (mx >= GAME_WIDTH / 2 - 5 - 200 && mx <= GAME_WIDTH / 2 - 87) {
                    if (my >= 250 && my <= 300) {

                        System.out.println("Black");
                        GamePanel.state = GamePanel.STATE.GENERAL;
                    }
                }

                if (mx >= GAME_WIDTH / 2 - 5 + 100 && mx <= GAME_WIDTH / 2 + 205) {
                    if (my >= 250 && my <= 300) {

                        System.out.println("GRAY");
                        GamePanel.state = GamePanel.STATE.GENERAL;
                    }
                }

            }
        }
        if (GamePanel.state == STATE.PAUSED) {
                int mx = e.getX();
                int my = e.getY();

                //            g.drawString("Muted", GAME_WIDTH - 100, GAME_HEIGHT-20);
                if (mx >= GAME_WIDTH - 100 && mx <= GAME_WIDTH + 100) {
                    if (my >= GAME_HEIGHT - 50 && my <= GAME_HEIGHT + 55) {

                        if (!Mute.muted) {
                            Mute.muted = true;
                        } else if (Mute.muted) {
                            Mute.muted = false;
                        }
                    }
                }



            if (mx >= 10 && mx <= + 80) {
                if (my >= -80 && my <= 60) {

                    state = STATE.RESTART;
                }
            }
        }

        //g.drawString("Exit", 10, 40);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public class CoolDownClick implements Runnable {
        Thread test;

        public void cooldown() {
            thread();
            clickcooldown = false;

        }

        @Override
        public void run() {
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clickcooldown = true;

        }

        public void thread() {
            test = new Thread(this);
            test.start();
        }
    }


}
