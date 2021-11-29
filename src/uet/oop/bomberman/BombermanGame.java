package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.control.collision.Collision;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.item.FlameUp;
import uet.oop.bomberman.entities.item.SpeedUp;
import uet.oop.bomberman.entities.lifesingame.Ballom;
import uet.oop.bomberman.entities.lifesingame.LifesInGame;
import uet.oop.bomberman.graphics.Sprite;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BombermanGame extends Application {

    /*
        W:992px
        H:416px
     */

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static int _level = 1;
    public static int _width = 0;
    public static int _height = 0;
    public static char[][] _map;
    public static char[][] listKill;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    public static  List<Entity> stillObjects = new ArrayList<>();
    public static final List<Entity> obs = new ArrayList<>();
    public static LifesInGame player;
    public static Sprite _sprite;

    public static int speed = 1;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }


    @Override
    public void start(Stage stage) throws IOException {

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);
            player = new Bomber(1, 1, Sprite.player_right_2.getFxImage());
            //Entity bomberman = new Bomber(1, 1, Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animate(_animate), 20).getFxImage());
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.LEFT ) {
                    //bomberman.setX((bomberman.getX() - 1 * speed));
                    Move.Left(player);
                }
                if (event.getCode() == KeyCode.RIGHT ) {
                    //bomberman.setX((bomberman.getX() + 1 * speed));
                    Move.Right(player);
                }
                if (event.getCode() == KeyCode.DOWN ) {
                    //bomberman.setY((bomberman.getY() + 1 * speed));
                    Move.Down(player);
                }
                if (event.getCode() == KeyCode.UP ) {
                    //bomberman.setY((bomberman.getY() - 1 * speed));
                    Move.Up(player);
                }
                if (event.getCode() == KeyCode.SPACE) {
                    Bomb.putBomb(player);
                }
            });
            //entities.add(bomberman);
            entities.add(player);
            player.setLife(false);
        // Them scene vao stage
        stage.setScene(scene);
        stage.setTitle("Bomberman");
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();

    }
    /*
        public void createMap() {
            for (int i = 0; i < WIDTH; i++) {
                for (int j = 0; j < HEIGHT; j++) {
                    Entity object;

                    if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                        object = new Wall(i, j, Sprite.wall.getFxImage());
                    }
                    else {
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                    }

                    stillObjects.add(object);
                }
            }
        }
    */
    public void update() {
        entities.forEach(Entity::update);
        stillObjects.forEach(Entity::update);
        player.update();
        player.setCountToRun(player.getCountToRun() + 1);
        if (player.getCountToRun() == 8) {
            Move.checkMove(player);
            player.setCountToRun(0);
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        //obs.forEach(g -> g.render(gc));
        player.render(gc);
    }

    public void createMap() throws IOException {
        String path = "res/levels/Level" + _level + ".txt";
        try {
            File mapFile = new File(path);
            Scanner sc = new Scanner(mapFile);
            String[] info = sc.nextLine().split(" ");
            _level = Integer.parseInt(info[0]);
            _height = Integer.parseInt(info[1]);
            _width = Integer.parseInt(info[2]);

            _map = new char[_height][_width];
            listKill = new char[_height][_width];
            for (int i = 0; i < _height; i++) {
                String mapRow = sc.nextLine();
                for (int j = 0; j < _width; j++) {
                    _map[i][j] = mapRow.charAt(j);
                    switch (_map[i][j]) {
                        case '#':
                            stillObjects.add(j + i * _width, new Wall(j, i, Sprite.wall.getFxImage()));
                            break;
                        case '*':
                            stillObjects.add(j + i * _width, new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        case 'f' :
                            stillObjects.add(j + i * _width, new FlameUp(j, i, Sprite.brick.getFxImage()));
                            break;
                        case 'b' :
                            stillObjects.add(j + i * _width, new SpeedUp(j, i, Sprite.brick.getFxImage()));
                            break;
                        default:
                            stillObjects.add(j + i * _width, new Grass(j, i, Sprite.grass.getFxImage()));
                    }
                }
            }
        } catch (NullPointerException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

