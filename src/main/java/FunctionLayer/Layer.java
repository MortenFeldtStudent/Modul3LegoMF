
package FunctionLayer;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    
    private List<Side> listSides;

    public Layer() {
        listSides = new ArrayList();
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
    
    public void addSides(Side side){
        listSides.add(side);
    }
    
}
