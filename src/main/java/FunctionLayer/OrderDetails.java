package FunctionLayer;

public class OrderDetails {

    private int order_id;
    private int height;
    private int wide;
    private int length;
    private String username;
    private String orderDate;
    private String shippedDate;
    private final String ORDER_NOT_SHIPPED = "Ordre ej afsendt!";
    private Door door;
    private Window window;

    public OrderDetails(int order_id, int height, int wide, int length, String username, String orderDate, String shippedDate, Door door, Window window) {
        this.order_id = order_id;
        this.height = height;
        this.wide = wide;
        this.length = length;
        this.username = username;
        this.orderDate = orderDate;
        this.door = door;
        this.window = window;

        if (shippedDate == null) {
            this.shippedDate = ORDER_NOT_SHIPPED;
        } else {
            this.shippedDate = shippedDate;
        }
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(String shippedDate) {
        this.shippedDate = shippedDate;
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

}
