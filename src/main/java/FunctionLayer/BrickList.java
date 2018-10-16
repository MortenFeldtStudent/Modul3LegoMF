package FunctionLayer;

public class BrickList {
    
    private int height;
    private int wide;
    private int length;
    private int[] listBricks2x4;
    private int[] listBricks2x2;
    private int[] listBricks2x1;
    private boolean door;
    private boolean window;

    public BrickList(int height, int wide, int length) {
        this.height = height;
        this.wide = wide;
        this.length = length;
        this.door = false;
        this.window = false;
    }

    public BrickList(int height, int wide, int length, int[] listBricks2x4, int[] listBricks2x2, int[] listBricks2x1) {
        this.height = height;
        this.wide = wide;
        this.length = length;
        this.listBricks2x4 = listBricks2x4;
        this.listBricks2x2 = listBricks2x2;
        this.listBricks2x1 = listBricks2x1;
        this.door = false;
        this.window = false;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWide() {
        return wide;
    }

    public void setWide(int wide) {
        this.wide = wide;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int[] getListBricks2x4() {
        return listBricks2x4;
    }

    public void setListBricks2x4(int[] listBricks2x4) {
        this.listBricks2x4 = listBricks2x4;
    }

    public int[] getListBricks2x2() {
        return listBricks2x2;
    }

    public void setListBricks2x2(int[] listBricks2x2) {
        this.listBricks2x2 = listBricks2x2;
    }

    public int[] getListBricks2x1() {
        return listBricks2x1;
    }

    public void setListBricks2x1(int[] listBricks2x1) {
        this.listBricks2x1 = listBricks2x1;
    }

    public boolean isDoor() {
        return door;
    }

    public void setDoor(boolean door) {
        this.door = door;
    }

    public boolean isWindow() {
        return window;
    }

    public void setWindow(boolean window) {
        this.window = window;
    }

}
