
package FunctionLayer;

import java.util.ArrayList;
import java.util.List;

public class House {
    
    private int height;
    private int wide;
    private int length;
    private List<Layer> listLayers;

    public House(int height, int wide, int length) {
        this.height = height;
        this.wide = wide;
        this.length = length;
        listLayers = new ArrayList();
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

    public List<Layer> getListLayers() {
        return listLayers;
    }

    public void setListLayers(List<Layer> listLayers) {
        this.listLayers = listLayers;
    }
    
}
