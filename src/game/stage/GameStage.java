package game.stage;

import game.enemy.BaseEnemy;
import game.object.UpdatableObject;
import game.tower.Tower;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import game.object.GameObject;
import game.tower.BaseTower;

import java.util.ArrayList;

public abstract class GameStage {
    public ArrayList<GameObject> ornament = new ArrayList<>();
    public ArrayList<Tower> towers = new ArrayList<>();
    public ArrayList<BaseEnemy> enemies = new ArrayList<>();
    private Image map;

    // Load map của level tương ứng
    public GameStage(int k) {
        map = new Image("file:resources/Stage_" + k + ".png");
    }

    // Thêm vật trang trí
    public abstract void addOrnament();

    /**
     * Vẽ màn chơi
     * Vẽ kẻ địch
     * Vẽ các tháp
     * Vẽ vật trang trí
     * Vẽ các layout
     */
    public void draw(GraphicsContext gc) {
        gc.drawImage(map, 0, -50);
        for (BaseEnemy enemy : enemies) enemy.draw(gc);
        for (Tower tower : towers) tower.draw(gc);
        for (GameObject i : ornament) i.draw(gc);
        for (Tower tower : towers) tower.drawLayout(gc);
    }

    /**
     * Cập nhật màn chơi
     */

//  TODO sửa enemy để tận dụng tính đa hình khi dùng update()
    public void update() {
        for (Tower tower : towers) {
            tower.update();
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
            if (enemies.get(i).getPosX() < -30) enemies.remove(i);
        }
    }

    /** Đọc các sự kiện bàn phím chuột
     *  key = 0: Mouse Position When Clicking
     *  key = 1: Mouse Position
     */
    public void input(int key, double mouseX, double mouseY) {
        if (key == 0) for (BaseTower tower: towers) tower.onClick((int) mouseX, (int) mouseY);
        if (key == 1) for (BaseTower tower: towers) tower.hover((int) mouseX, (int) mouseY);
    }
}
