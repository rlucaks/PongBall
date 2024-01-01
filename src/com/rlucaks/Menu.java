package com.rlucaks;

import java.awt.*;

import static com.rlucaks.GamePanel.GAME_WIDTH;

public class Menu {


    public Rectangle settings = new Rectangle(GAME_WIDTH / 2 - 5- 200, 250, 121 ,50);
    public Rectangle play = new Rectangle(GAME_WIDTH / 2 - 5- 50, 250, 121 ,50);
    public Rectangle quit = new Rectangle(GAME_WIDTH / 2 - 5+100, 250, 121 ,50);

    public void menu(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        Font font = new Font("Consolas", Font.BOLD, 80);
        g.setFont(font);
        g.setColor(Color.WHITE);
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int titleLen = fontMetrics.stringWidth("PONG BALL");

        g.drawString("PONG BALL", (GAME_WIDTH / 2 - 4) - (titleLen / 2 - 5), 100);

        Font font1 = new Font("Consolas", Font.BOLD, 28);
        g.setFont(font1);
        g2d.draw(play);
        g.drawString("Play", play.x + 29, play.y+33);
        g2d.draw(settings);
        g.drawString("Settings", settings.x, settings.y + 33);
        g2d.draw(quit);
        g.drawString("Quit", quit.x + 29, quit.y + 33);

    }
}
