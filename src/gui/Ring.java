package gui;

import game.object.GameObject;
import game.object.UpdatableObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Ring extends GameObject implements UpdatableObject {
    private Icon[] icons;
    private int upgrade;

    public Ring(int posX, int posY, int tag) {
        super(posX, posY, new Image("file:resources/gui_ring.png"));
        scale = 0;
        upgrade = -1;
        int iconAmount;
        if (tag == 0) iconAmount = 4;
        else iconAmount = 2;
        icons = new Icon[iconAmount];
        for (int i = 0; i < iconAmount; i++) icons[i] = new Icon(0, 0, i);
    }

    public void onClick(int mouseX, int mouseY) {
        for (Icon icon : icons) {
            upgrade = icon.onClick(mouseX, mouseY);
            if (upgrade >= 0) break;
        }
    }

    public void hover(int mouseX, int mouseY) {
        for (Icon icon : icons) icon.hover(mouseX, mouseY);
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        for (Icon icon : icons) icon.draw(gc);
    }

    // Zoom vào cái vòng tròn chọn Icon
    public void update() {
        if (scale < 1) {
            scale += 0.1;
            setPosX(getPosX() + (int) getImage().getWidth() / 2);
            setPosY(getPosY() + (int) getImage().getHeight() / 2);
            setImage(new Image("file:resources/gui_ring.png", getWidth() * scale, getHeight() * scale, false, true));
            setPosX(getPosX() - (int) getImage().getWidth() / 2);
            setPosY(getPosY() - (int) getImage().getHeight() / 2);
        }

        for (Icon icon : icons) icon.update();

        int x_left = getPosX();
        int x_right = getPosX() + (int) getImage().getWidth() - (int) icons[1].getImage().getWidth();
        int y_up = getPosY();
        int y_down = getPosY() + (int) getImage().getHeight() - (int) icons[2].getImage().getHeight();
        icons[0].setPosX(x_left);
        icons[0].setPosY(y_up);
        icons[1].setPosX(x_right);
        icons[1].setPosY(y_up);
        icons[2].setPosX(x_left);
        icons[2].setPosY(y_down);
        icons[3].setPosX(x_right);
        icons[3].setPosY(y_down);
    }

    public int getUpgrade() {
        return upgrade;
    }
}
