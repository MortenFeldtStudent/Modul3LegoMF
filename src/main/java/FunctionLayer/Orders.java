
package FunctionLayer;

import java.util.ArrayList;
import java.util.List;

public class Orders {
    
    private List<Integer> listOrders;

    public Orders() {
        listOrders = new ArrayList();
    }

    public List<Integer> getListOrders() {
        return listOrders;
    }

    public void setListOrders(List<Integer> listOrders) {
        this.listOrders = listOrders;
    }
    
    public void addIntToList(int order_id){
        listOrders.add(order_id);
    }
}
