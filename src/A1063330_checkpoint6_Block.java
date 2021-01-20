import java.awt.Image;
public class A1063330_checkpoint6_Block{
    //Description : The grid X location of this block.
    private int X;
    //Description : The grid Y location of this block.
    private int Y;
    //Description : The type of this block. Ex. obstacle, space
    private String type;
    //Description : If the type of this block is not space, it should get a image of it.
    private Image img;
    public A1063330_checkpoint6_Block(int X, int Y, String type, Image img){
        this.X = X;
        this.Y = Y;
        this.type = type;
        this.img = img;
    }
    public int getX(){
        return this.X;
    }
    public void setX(int X){
        this.X = X;
    }
    public int getY(){
        return this.Y;
    }
    public void setY(int Y){
        this.Y = Y;
    }
    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }
    public Image getImg(){
        return this.img;
    }
    public void setImg(Image img){
        this.img = img;
    }
}
