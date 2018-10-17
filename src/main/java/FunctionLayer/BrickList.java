package FunctionLayer;

public class BrickList {
    
    private int height;
    private int wide;
    private int length;
    private int[] listBricks2x4;
    private int[] listBricks2x2;
    private int[] listBricks2x1;
    private House house;

    public BrickList(House house) {
        this.height = house.getHeight();
        this.wide = house.getWide();
        this.length = house.getLength();
        this.house = house;
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

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
    
    public int calcLayerSideBricks(int brick, int listValue) {
        return listValue += brick;
    }

    public int[] calcTotalBricks(int[] list) {
        int countTotalBricks = 0;

        for (int i = 0; i < list.length - 1; i++) {
            countTotalBricks += list[i];
        }
        list[4] = countTotalBricks;

        return list;
    }
    
    public BrickList calcBrickList() {
        int[] listCountBrick2x4 = {0, 0, 0, 0, 0}; //side1 (For), side2 (Bag), side3 (Side), side4 (Side), total (alle lag)
        int[] listCountBrick2x2 = {0, 0, 0, 0, 0}; //side1 (For), side2 (Bag), side3 (Side), side4 (Side), total (alle lag)
        int[] listCountBrick2x1 = {0, 0, 0, 0, 0}; //side1 (For), side2 (Bag), side3 (Side), side4 (Side), total (alle lag)
        
        house.calcLayers();

        for (int i = 0; i < house.getListLayers().size(); i++) {
            Layer layer = house.getListLayers().get(i);

            for (int j = 0; j < layer.getListSides().size(); j++) {
                Side side = layer.getListSides().get(j);

                listCountBrick2x4[j] = calcLayerSideBricks(side.getBrick2x4(), listCountBrick2x4[j]);
                listCountBrick2x2[j] = calcLayerSideBricks(side.getBrick2x2(), listCountBrick2x2[j]);
                listCountBrick2x1[j] = calcLayerSideBricks(side.getBrick2x1(), listCountBrick2x1[j]);
            }

        }
        listCountBrick2x4 = calcTotalBricks(listCountBrick2x4);
        listCountBrick2x2 = calcTotalBricks(listCountBrick2x2);
        listCountBrick2x1 = calcTotalBricks(listCountBrick2x1);

        setListBricks2x4(listCountBrick2x4);
        setListBricks2x2(listCountBrick2x2);
        setListBricks2x1(listCountBrick2x1);

        return this;
    }

}
