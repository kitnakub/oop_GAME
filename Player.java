import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    private int spriteCounter = 0; 
    private int spriteNum = 1;     

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(new File("player/boy_up.png"));
            up2 = ImageIO.read(new File("player/boy_up_2.png"));

            down1 = ImageIO.read(new File("player/boy_down_1.png"));
            down2 = ImageIO.read(new File("player/boy_down_2.png"));
            down3 = ImageIO.read(new File("player/boy_down_3.png"));

            left1 = ImageIO.read(new File("player/boy_left.png"));
            left2 = ImageIO.read(new File("player/boy_left_2.png"));
            left3 = ImageIO.read(new File("player/boy_left_3.png"));

            right1 = ImageIO.read(new File("player/boy_right.png"));
            right2 = ImageIO.read(new File("player/boy_right_2.png"));
            right3 = ImageIO.read(new File("player/boy_right_3.png"));

            
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void update(){
        boolean moving = false;

        if(keyH.upPressed){
            direction = "up";
            y -= speed;
            moving = true;
        } 
        else if(keyH.downPressed){
            direction = "down";
            y += speed;
            moving = true;
        } 
        else if(keyH.leftPressed){
            direction = "left";
            x -= speed;
            moving = true;
        }
        else if(keyH.rightPressed){
            direction = "right";
            x += speed;
            moving = true;
        } 

        if(moving){
            spriteCounter++;
            if(spriteCounter > 10){ 
                spriteNum++;
                if(spriteNum > 3) spriteNum = 1;
                spriteCounter = 0;
            }
        } else {
            spriteNum = 1; 
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch(direction){
            case "up":
                if(spriteNum == 1) image = up1;
                if(spriteNum == 2) image = up2;
                break;

            case "down":
                if(spriteNum == 1) image = down1;
                if(spriteNum == 2) image = down2;
                if(spriteNum == 3) image = down3;
                break;

            case "left":
                if(spriteNum == 1) image = left1;
                if(spriteNum == 2) image = left2;
                if(spriteNum == 3) image = left3;
                break;

            case "right":
                if(spriteNum == 1) image = right1;
                if(spriteNum == 2) image = right2;
                if(spriteNum == 3) image = right3;
                break;
        }
         
        g2.drawImage(image, x, y, gp.tileSize+100, gp.tileSize+100, null);
    }
}
