package bomb.actor;

import javax.swing.*;
import java.awt.*;

public class Box {
    public static int ALLROW_BANG = 0;  //Box có thể Nổ
    public static int DISALLROW_BANG = 1;   //Box khồng được nổ
    private int x,y;    //Tọa độ của Box
    private int width,height;   //Chiều rộng và chiều dài của Box
    private int type;   //Loại Box
    private Image img;
    public Box(int x, int y, int type,String images) {
        super();
        this.x = x;
        this.y = y;
        this.type = type;
        this.img = new ImageIcon(getClass().getResource(images)).getImage();
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }

    public void drawBox(Graphics2D g2d){
        g2d.drawImage(img, x, y, null);
    }

    //todo : Hàm kiểm tra va chạm của box với nhân vật
    public int isImpactBoxvsActor(Actor actor){
        if(actor.getType() == Actor.BOSS){
            return 0;
        }
        Rectangle rec1 = new Rectangle(x, y, width, height);
        Rectangle rec2 = new Rectangle(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
        Rectangle rec3 = new Rectangle();

        if(rec1.intersects(rec2)){  //nếu rec1 giao nhau với rec2
            rec1.intersect(rec1, rec2, rec3);
            if(rec3.getHeight()==1 && (actor.getOrient()== Actor.UP || actor.getOrient()== Actor.DOWN)){
                if(actor.getX()==rec3.getX()){
                    return (int)rec3.getWidth();
                }else{
                    return (int)-rec3.getWidth();
                }
            }else{
                if(actor.getY()==rec3.getY()){
                    return (int)rec3.getHeight();
                }else{
                    return (int)-rec3.getHeight();
                }
            }
        }
        return 0;
    }

    public int getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
