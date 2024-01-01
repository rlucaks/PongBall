package com.rlucaks;

import java.awt.*;

import static com.rlucaks.GamePanel.GAME_WIDTH;

public class SettingsMenu {

    public Rectangle general = new Rectangle(GAME_WIDTH / 2 - 5- 200, 250, 121 ,50);
    public Rectangle difficulty = new Rectangle(GAME_WIDTH / 2 - 5- 50, 250, 121 ,50);
    public Rectangle back = new Rectangle(GAME_WIDTH / 2 - 5+100, 250, 121 ,50);

    public void menu(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        Font font = new Font("Consolas", Font.BOLD, 80);
        g.setFont(font);
        g.setColor(Color.WHITE);
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int titleLen = fontMetrics.stringWidth("Settings");

        g.drawString("SETTINGS", (GAME_WIDTH / 2 - 4) - (titleLen / 2 - 5), 100);

        Font font1 = new Font("Consolas", Font.PLAIN, 21);
        g.setFont(font1);
        g2d.draw(difficulty);
        g.drawString("Difficulty", difficulty.x, difficulty.y+33);
        g2d.draw(general);
        g.drawString("General", general.x+ 18, general.y + 33);
        g2d.draw(back);
        g.drawString("Back", back.x + 34, back.y + 33);

    }
}
