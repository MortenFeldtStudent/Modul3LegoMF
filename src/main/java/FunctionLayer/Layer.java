package FunctionLayer;

import java.util.ArrayList;
import java.util.List;

public class Layer {

    private Side side;
    private List<Side> listSides;

    public Layer() {
        listSides = new ArrayList();
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }
    
    public Layer(List<Side> listSides) {
        this.listSides = listSides;
    }

    public List<Side> getListSides() {
        return listSides;
    }

    public void setListSides(List<Side> listSides) {
        this.listSides = listSides;
    }

    public void addSides(Side side) {
        listSides.add(side);
    }

    public Side calcSideOfLayer(int count) {
        side = new Side();
        side.calcSide(count);
        return side;
    }

    public void calcLayer(int layerNumber, int length, int wide, Door door, Window window) {
        if ((layerNumber % 2) == 0) {
            wide -= 4;
        } else {
            length -= 4;
        }

        int lengthDoor = length;
        int lengthWindow = length;

        if (layerNumber < door.getHeight()) {
            lengthDoor -= door.getLength();
        }
        if (layerNumber < window.getHeight()) {
            lengthWindow -= window.getLength();
        }

        addSides(calcSideOfLayer(lengthDoor)); //For
        addSides(calcSideOfLayer(lengthWindow)); //Bag
        addSides(calcSideOfLayer(wide)); //Side
        addSides(calcSideOfLayer(wide)); //Side
    }

}
