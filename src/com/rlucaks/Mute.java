package com.rlucaks;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Mute extends Rectangle {

    static boolean muted = false;
    static int GAME_WIDTH;
    static int GAME_HEIGHT;

    Mute(int GAME_WIDTH, int GAME_HEIGHT) {
        Mute.GAME_WIDTH = GAME_WIDTH;
        Mute.GAME_HEIGHT = GAME_HEIGHT;
    }

    public void draw(Graphics g) {

        if (GamePanel.state == GamePanel.STATE.PAUSED) {
            g.setFont(new Font("Consolas", Font.PLAIN, 30));
            Font font = new Font("Consolas", Font.BOLD, 30);
            FontMetrics fontMetrics = g.getFontMetrics(font);
            String mute = "Mute";
            Rectangle2D titleLen = fontMetrics.getStringBounds(mute, g);
            String hieght = String.valueOf(titleLen.getHeight());

            if (!muted) {
                g.drawString("Mute", GAME_WIDTH - 100, GAME_HEIGHT - 20);

            } else if (muted) {
                g.drawString("UnMute", GAME_WIDTH - 120, GAME_HEIGHT - 20);
            }
        }
    }

}
