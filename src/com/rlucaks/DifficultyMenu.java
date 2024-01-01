package com.rlucaks;

import java.awt.*;

import static com.rlucaks.GamePanel.GAME_WIDTH;

public class DifficultyMenu {

    public Rectangle normal = new Rectangle(GAME_WIDTH / 2 - 5- 200, 250, 121 ,50);
    public Rectangle hard = new Rectangle(GAME_WIDTH / 2 - 5- 50, 250, 121 ,50);
    public Rectangle insane = new Rectangle(GAME_WIDTH / 2 - 5+100, 250, 121 ,50);

    public void menu(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        Font font = new Font("Consolas", Font.BOLD, 80);
        g.setFont(font);
        g.setColor(Color.WHITE);
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int titleLen = fontMetrics.stringWidth("Difficulty");

        g.drawString("Difficulty", (GAME_WIDTH / 2 - 4) - (titleLen / 2 - 5), 100);

        Font font1 = new Font("Consolas", Font.PLAIN, 28);
        g.setFont(font1);
        g2d.draw(hard);
        g.drawString("Hard", hard.x + 29, hard.y+33);
        g2d.draw(normal);
        g.drawString("Normal", normal.x + 15 , normal.y + 33);
        g2d.draw(insane);
        g.drawString("Insane", insane.x + 15, insane.y + 33);

    }
}
