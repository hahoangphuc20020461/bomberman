package uet.oop.bomberman.control;

import com.sun.deploy.security.BlockedDialog;
import uet.oop.bomberman.control.collision.Collision;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.lifesingame.LifesInGame;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.speed;
import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public class Move {

    private Bomber bomber;

    public static void setDirect(String direct, LifesInGame lifes, int isMove) {
        switch (direct) {
            case "down" :
                down_step(lifes);
                lifes.setY(lifes.getY() + isMove);
                break;
            case  "up" :
                up_step(lifes);
                lifes.setY(lifes.getY() - isMove);
                break;
            case  "left" :
                left_step(lifes);
                lifes.setX(lifes.getX() - isMove);
                break;
            case  "right" :
                right_step(lifes);
                lifes.setX(lifes.getX() + isMove);
                break;
        }
    }

    public static void checkMove(LifesInGame lifes) {
        if (lifes instanceof Bomber && lifes.getCountStep() > 0) {
            setDirect(lifes.getDirect(), lifes, 8 * speed);
            lifes.setCountStep(lifes.getCountStep() - 1);
        }
    }

    public static void Down(LifesInGame lifes) {
        if (lifes.getY() % SCALED_SIZE == 0 && lifes.getX() % SCALED_SIZE == 0) {
            if (lifes instanceof Bomber && Collision.canMove(lifes.getX(), lifes.getY() + 1)) {
                lifes.setDirect("down");
                lifes.setCountStep(4 / speed);
                checkMove(lifes);
            }
        }
    }

    public static void Up(LifesInGame lifes) {
        if (lifes.getY() % SCALED_SIZE == 0 && lifes.getX() % SCALED_SIZE == 0) {
            if (lifes instanceof Bomber && Collision.canMove(lifes.getX(), lifes.getY() - 1)) {
                lifes.setDirect("up");
                lifes.setCountStep(4 / speed);
                checkMove(lifes);
            }
        }
    }

    public static void Left(LifesInGame lifes) {
        if (lifes.getY() % SCALED_SIZE == 0 && lifes.getX() % SCALED_SIZE == 0) {
            if (lifes instanceof Bomber && Collision.canMove(lifes.getX() - 1, lifes.getY())) {
                lifes.setDirect("left");
                lifes.setCountStep(4 / speed);
                checkMove(lifes);
            }
        }
    }

    public static void Right(LifesInGame lifes) {
        if (lifes.getY() % SCALED_SIZE == 0 && lifes.getX() % SCALED_SIZE == 0) {
            if (lifes instanceof Bomber && Collision.canMove(lifes.getX() + 1, lifes.getY())) {
                lifes.setDirect("right");
                lifes.setCountStep(4 / speed);
                checkMove(lifes);
            }
        }
    }

    private static void down_step(LifesInGame lifes) {
        if (lifes instanceof Bomber && lifes.getY() % 8 == 0) {
            if (lifes.getSwap() == 1) {
                lifes.setImg(Sprite.player_down.getFxImage());
                lifes.setSwap(2);
            } else if (lifes.getSwap() == 2) {
                lifes.setImg(Sprite.player_down_1.getFxImage());
                lifes.setSwap(3);
            } else if (lifes.getSwap() == 3) {
                lifes.setImg(Sprite.player_down.getFxImage());
                lifes.setSwap(4);
            } else {
                lifes.setImg(Sprite.player_down_2.getFxImage());
                lifes.setSwap(1);
            }
        }
    }

    private static void up_step(LifesInGame lifes) {
        if (lifes instanceof Bomber && lifes.getY() % 8 == 0) {
            if (lifes.getSwap() == 1) {
                lifes.setImg(Sprite.player_up.getFxImage());
                lifes.setSwap(2);
            } else if (lifes.getSwap() == 2) {
                lifes.setImg(Sprite.player_up_1.getFxImage());
                lifes.setSwap(3);
            } else if (lifes.getSwap() == 3) {
                lifes.setImg(Sprite.player_up.getFxImage());
                lifes.setSwap(4);
            } else {
                lifes.setImg(Sprite.player_up_2.getFxImage());
                lifes.setSwap(1);
            }
        }
    }

    private static void left_step(LifesInGame lifes) {
        if (lifes instanceof Bomber && lifes.getX() % 8 == 0) {
            if (lifes.getSwap() == 1) {
                lifes.setImg(Sprite.player_left.getFxImage());
                lifes.setSwap(2);
            } else if (lifes.getSwap() == 2) {
                lifes.setImg(Sprite.player_left_1.getFxImage());
                lifes.setSwap(3);
            } else if (lifes.getSwap() == 3) {
                lifes.setImg(Sprite.player_left.getFxImage());
                lifes.setSwap(4);
            } else {
                lifes.setImg(Sprite.player_left_2.getFxImage());
                lifes.setSwap(1);
            }
        }
    }

    private static void right_step(LifesInGame lifes) {
        if (lifes instanceof Bomber && lifes.getX() % 8 == 0) {
            if (lifes.getSwap() == 1) {
                lifes.setImg(Sprite.player_right.getFxImage());
                lifes.setSwap(2);
            } else if (lifes.getSwap() == 2) {
                lifes.setImg(Sprite.player_right_1.getFxImage());
                lifes.setSwap(3);
            } else if (lifes.getSwap() == 3) {
                lifes.setImg(Sprite.player_right.getFxImage());
                lifes.setSwap(4);
            } else {
                lifes.setImg(Sprite.player_right_2.getFxImage());
                lifes.setSwap(1);
            }
        }
    }
}
