package com.rlucaks;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Paused extends JPanel {

    static int GAME_WIDTH;
    static int GAME_HEIGHT;

    Paused(int GAME_WIDTH, int GAME_HEIGHT) {
        Paused.GAME_WIDTH = GAME_WIDTH;
        Paused.GAME_HEIGHT = GAME_HEIGHT;
    }

    public void draw(Graphics g) {

        Font font = new Font("Consolas", Font.BOLD, 40);
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int titleLen = fontMetrics.stringWidth("PAUSED");
        int five = fontMetrics.stringWidth("5");

        Rectangle2D bounds = fontMetrics.getStringBounds("PAUSED", g);
        int textHeight = (int) bounds.getHeight();
        int titleLen2 = fontMetrics.stringWidth("PAUSED");

        g.setFont(font);
        g.setColor(Color.WHITE);

        if (GamePanel.paused) {
            g.drawString("PAUSED", (GAME_WIDTH / 2 - 5) - (titleLen / 2 - 5), GAME_HEIGHT / 2);
            font = new Font("Consolas", Font.PLAIN, 20);
            g.setFont(font);
            g.drawString("Press space to resume", (GAME_WIDTH / 2 - 60) - (titleLen2 / 2 - 5), GAME_HEIGHT / 2 + textHeight);
        }
        if (GamePanel.unpausing) {
            switch (GamePanel.unpausingnum) {
                case 3:
                    g.drawString("3", (GAME_WIDTH / 2 - 5) - (five / 2 - 5), GAME_HEIGHT / 2);
                    break;
                case 2:
                    g.drawString("2", (GAME_WIDTH / 2 - 5) - (five / 2 - 5), GAME_HEIGHT / 2);
                    break;
                case 1:
                    g.drawString("1", (GAME_WIDTH / 2 - 5) - (five / 2 - 5), GAME_HEIGHT / 2);
                    break;
            }
        }
    }

}
