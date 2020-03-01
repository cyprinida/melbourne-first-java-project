/**
 * Sample solution for SWEN20003 Object Oriented Software Development
 * Project 1, Semester 2, 2019
 *
 * @author Eleanor McMurtry
 */

import bagel.*;
import bagel.util.Point;

import java.util.Random;

public class ShadowBounce extends AbstractGame {
    private Peg[] pegs = new Peg[50];
    private Ball ball;
    private static final Point BALL_POSITION = new Point(512, 32);
    private static final double PEG_OFFSET = 100;

    public ShadowBounce() {
        Random rand = new Random();
        for (int i = 0; i < pegs.length; ++i) {
            Point p = new Point(Window.getWidth() * rand.nextDouble(),
                             PEG_OFFSET + (Window.getHeight() - PEG_OFFSET) * rand.nextDouble());
            pegs[i] = new Peg(p);
        }
    }

    @Override
    protected void update(Input input) {
        // Check all non-deleted pegs for intersection with the ball
        for (int i = 0; i < pegs.length; ++i) {
            if (pegs[i] != null) {
                if (ball != null && ball.intersects(pegs[i])) {
                    pegs[i] = null;
                } else {
                    pegs[i].update();
                }
            }
        }

        // If we don't have a ball and the mouse button was clicked, create one
        if (input.wasPressed(MouseButtons.LEFT) && ball == null) {
            ball = new Ball(BALL_POSITION, input.directionToMouse(BALL_POSITION));
        }

        if (ball != null) {
            ball.update();

            // Delete the ball when it leaves the screen
            if (ball.outOfScreen()) {
                ball = null;
            }
        }
    }

    public static void main(String[] args) {
        new ShadowBounce().run();
    }
}
