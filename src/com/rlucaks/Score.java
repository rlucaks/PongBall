package com.rlucaks;

import java.awt.*;

public class Score extends Rectangle{

    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1;
    int player2;
    int raleynum;
    Color player1color = Color.WHITE;
    Color player2color = Color.WHITE;

    Score(int GAME_WIDTH, int GAME_HEIGHT) {
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }

    public void draw(Graphics g) {
        if (player1 == 10) {
            player1color = Color.GREEN;
        } else if (player2 == 10) {
            player2color = Color.GREEN;
        }
        g.setFont(new Font("Consolas", Font.PLAIN, 60));

        g.drawLine(GAME_WIDTH/2, 30, GAME_WIDTH/2, GAME_HEIGHT/12+30);
        g.drawLine(GAME_WIDTH/2, GAME_HEIGHT/12+GAME_HEIGHT/12+30, GAME_WIDTH/2, GAME_HEIGHT/12 * 3+30);
        g.drawLine(GAME_WIDTH/2, GAME_HEIGHT/12 * 4+30, GAME_WIDTH/2, GAME_HEIGHT/12 * 5+30);
        g.drawLine(GAME_WIDTH/2, GAME_HEIGHT/12 * 6+30, GAME_WIDTH/2, GAME_HEIGHT/12 * 7+30);
        g.drawLine(GAME_WIDTH/2, GAME_HEIGHT/12 * 8+30, GAME_WIDTH/2, GAME_HEIGHT/12 * 9+30);
        g.drawLine(GAME_WIDTH/2, GAME_HEIGHT/12 * 10+30, GAME_WIDTH/2, GAME_HEIGHT/12 * 11+30);

        g.setColor(player1color);
        g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), GAME_WIDTH/2-85, 50);
        g.setColor(player2color);
        g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), GAME_WIDTH/2+20, 50);
        g.drawString(String.valueOf(raleynum), GAME_WIDTH-50, 50);
    }

}
