package com.rlucaks;

import java.awt.*;

import static com.rlucaks.GamePanel.GAME_WIDTH;

public class ColorMenu {

    public Rectangle black = new Rectangle(GAME_WIDTH / 2 - 5- 200, 250, 121 ,50);
    public Rectangle white = new Rectangle(GAME_WIDTH / 2 - 5- 50, 250, 121 ,50);
    public Rectangle gray = new Rectangle(GAME_WIDTH / 2 - 5+100, 250, 121 ,50);

    public void menu(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        Font font = new Font("Consolas", Font.BOLD, 80);
        g.setFont(font);
        g.setColor(Color.WHITE);
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int titleLen = fontMetrics.stringWidth("Background colours");

        g.drawString("Background colours", (GAME_WIDTH / 2 - 4) - (titleLen / 2 - 5), 100);

        Font font1 = new Font("Consolas", Font.PLAIN, 28);
        g.setFont(font1);
        g2d.draw(white);
        g.drawString("WHITE", white.x + 29, white.y+33);
        g2d.draw(black);
        g.drawString("BLACK", black.x + 15 , black.y + 33);
        g2d.draw(gray);
        g.drawString("GRAY", gray.x + 15, gray.y + 33);

    }
}
