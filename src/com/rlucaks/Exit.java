package com.rlucaks;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Exit extends Rectangle {

    static boolean muted = false;
    static int GAME_WIDTH;
    static int GAME_HEIGHT;

    Exit(int GAME_WIDTH, int GAME_HEIGHT) {
        Exit.GAME_WIDTH = GAME_WIDTH;
        Exit.GAME_HEIGHT = GAME_HEIGHT;
    }

    public void draw(Graphics g) {

        if (GamePanel.state == GamePanel.STATE.PAUSED) {
            g.setFont(new Font("Consolas", Font.PLAIN, 30));
            Font font = new Font("Consolas", Font.BOLD, 30);
            FontMetrics fontMetrics = g.getFontMetrics(font);
            String mute = "Exit";
            Rectangle2D titleLen = fontMetrics.getStringBounds(mute, g);
            String hieght = String.valueOf(titleLen.getHeight());

            g.drawString("Exit", 10, 40);

        }
    }

}
