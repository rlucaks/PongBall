package com.rlucaks;

import java.awt.*;

import static com.rlucaks.GamePanel.GAME_WIDTH;

public class GeneralMenu {

    public Rectangle normal = new Rectangle(GAME_WIDTH / 2 - 5- 200, 250, 121 ,50);
    public Rectangle color = new Rectangle(GAME_WIDTH / 2 - 5- 50, 250, 121 ,50);
    public Rectangle back = new Rectangle(GAME_WIDTH / 2 - 5+100, 250, 121 ,50);

    public void menu(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        Font font = new Font("Consolas", Font.BOLD, 80);
        g.setFont(font);
        g.setColor(Color.WHITE);
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int titleLen = fontMetrics.stringWidth("General");

        g.drawString("General", (GAME_WIDTH / 2 - 4) - (titleLen / 2 - 5), 100);

        Font font1 = new Font("Consolas", Font.PLAIN, 18);
        g.setFont(font1);
        g2d.draw(color);
        g.drawString("Background", color.x + 12, color.y+18);
        g.drawString("Color", color.x + 34, color.y+42);
        g2d.draw(back);
        Font font2 = new Font("Consolas", Font.PLAIN, 21);
        g.setFont(font2);
        g.drawString("Back", back.x + 34, back.y + 33);
    }
}
