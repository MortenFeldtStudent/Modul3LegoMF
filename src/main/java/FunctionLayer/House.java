package FunctionLayer;

import java.util.ArrayList;
import java.util.List;

public class House {
    
    private int height;
    private int wide;
    private int length;
    private Layer layer;
    private List<Layer> listLayers;
    private Door door;
    private Window window;

    public House(int height, int wide, int length, Door door, Window window) {
        this.height = height;
        this.wide = wide;
        this.length = length;
        listLayers = new ArrayList();
        this.door = door;
        this.window = window;
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

    public Layer getLayer() {
        return layer;
    }

    public void setLayer(Layer layer) {
        this.layer = layer;
    }

    public List<Layer> getListLayers() {
        return listLayers;
    }

    public void setListLayers(List<Layer> listLayers) {
        this.listLayers = listLayers;
    }

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }
    
    public void checkSpaceDoorWindow() throws DoorWindowException {
        boolean checkDoor = door.getLength() <= (length - 4) && door.getHeight() < height;
        boolean checkWindow = window.getLength() <= (length - 4) && (window.getHeight() + 1) < height;
        
        if(!checkDoor && !checkWindow){
            throw new DoorWindowException("Der er ikke plads til hverken dør og vindue - tilret størrelse!");
        }
        if(!checkDoor){
            throw new DoorWindowException("Der er ikke plads til dør - tilret størrelse!");
        }
        if(!checkWindow){
            throw new DoorWindowException("Der er ikke plads til vindue - tilret størrelse!");
        }
    }
    
    public void addLayer(Layer layer) {
        listLayers.add(layer);
    }
    
    public Layer calcLayerOfLayers(int layerNumber) {
        layer = new Layer();
        layer.calcLayer(layerNumber, length, wide, door, window);
        return layer;
    }
    
    public void calcLayers() {
        for (int i = 0; i < height; i++) {
            addLayer(calcLayerOfLayers(i));
        }
    }
    
}
