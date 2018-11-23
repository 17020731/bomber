package bomb.actor;

import javax.swing.*;
import java.awt.*;

public class Bomb extends Actor {
    protected int size, timeline;

    public Bomb(int x, int y, int size, int timeline){
        this.x=x;
        this.y=y;
        this.size=size;
        this.orient=0;
        this.timeline = timeline;   // Thời gian bom bắt đầu cho đến khi nổ
        this.type = Actor.BOMB;
        img= new ImageIcon(getClass().getResource("/Images/bomb.png")).getImage();
        this.width= img.getWidth(null);
        this.height= img.getHeight(null);
    }

    public Bomb(int x, int y,int orient, int speed, int size, int timeline){
        this.x=x;
        this.y=y;
        this.orient = orient;
        this.speed = 5;
        this.size=size;
        this.timeline = timeline;
        this.type = Actor.BOMB;
        img= new ImageIcon(getClass().getResource("/Images/bomb.png")).getImage();
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }
    public int getTimeline() {
        return timeline;
    }
    public void setTimeline(int timeline) {
        this.timeline = timeline;
    }
    public int getSize() {
        return size;
    }

    //cài đặt thời gian bom nổ
    public void deadlineBomb(){
        this.timeline--;
    }



    //Kiểm tra khi bomber vừa đặt bomb thì có đi tiếp hay bị kẹt lại
    public boolean setRun(Bomber bomber){
        Rectangle rec2 = new Rectangle(x, y, 45, 45);
        Rectangle rec3 = new Rectangle(bomber.getX(), bomber.getY(), bomber.getWidth(), bomber.getHeight());
        return rec2.intersects(rec3);
    }

    //Kiểm tra 2 quả bom có xảy ra va chạm không? - (true nếu có)!
    public boolean isImpact(int xNewBomb, int yNewBomb){
        Rectangle rec1 = new Rectangle(x, y, 45, 45);
        Rectangle rec2 = new Rectangle(xNewBomb, yNewBomb, 45, 45);
        return rec1.intersects(rec2);
    }

    //Kiểm tra Bomb có va chạm với nhân vật(enemy) hay không?
    public int isImpactBombvsActor(Actor actor){
        if(actor.getRunBomb()==Bomber.ALLOW_RUN) {
            return 0;
        }
        Rectangle rec2 = new Rectangle(x, y, 45, 45);
        Rectangle rec3 = new Rectangle(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
        if(rec2.intersects(rec3)){
            if(actor.getType()==Bomber.BOSS){
                return 2;
            }
            return 1;
        };
        return 0;
    }
}
