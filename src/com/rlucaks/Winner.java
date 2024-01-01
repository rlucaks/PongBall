package com.rlucaks;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class Winner extends JPanel {

    static int GAME_WIDTH;
    static int GAME_HEIGHT;

    Winner(int GAME_WIDTH, int GAME_HEIGHT) {
        Winner.GAME_WIDTH = GAME_WIDTH;
        Winner.GAME_HEIGHT = GAME_HEIGHT;
    }

    public void draw(Graphics g, int player) {

        Font font = new Font("Consolas", Font.PLAIN, 40);
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int titleLen = fontMetrics.stringWidth("Player 2 wins!");

        Rectangle2D bounds = fontMetrics.getStringBounds("Player 2 wins!", g);
        int textHeight = (int)bounds.getHeight();
        int titleLen2 = fontMetrics.stringWidth("Player 2 wins!");

        g.setFont(font);
        g.setColor(Color.WHITE);

        if (player == 2) {
            g.drawString("Player 2 wins!", (GAME_WIDTH / 2+7) - (titleLen / 2 - 5), GAME_HEIGHT / 2);
            font = new Font("Consolas", Font.PLAIN, 20);
            g.setFont(font);
            g.drawString("Press space to continue", (GAME_WIDTH / 2+25) - (titleLen2 / 2 - 5), GAME_HEIGHT / 2+textHeight);
        }

        if ((player == 1)) {
            g.drawString("Player 1 wins!", (GAME_WIDTH / 2+7) - (titleLen / 2 - 5), GAME_HEIGHT / 2);
            font = new Font("Consolas", Font.PLAIN, 20);
            g.setFont(font);
            g.drawString("Press space to continue", (GAME_WIDTH / 2+25) - (titleLen2 / 2 - 5), GAME_HEIGHT / 2+textHeight);
        }

    }

}
