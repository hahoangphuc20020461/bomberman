package uet.oop.bomberman.entities.lifesingame;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Ballom extends LifesInGame{
    public Ballom(int isMove, int swap, String direction, int count, int countToRun) {
        super(4, 1, "up", 0, 0);
    }

    public Ballom(int x, int y, Image img) {
        super(x, y, img);
    }


}
