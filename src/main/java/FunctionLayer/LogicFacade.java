package FunctionLayer;

import DBAccess.DataMapper;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LogicFacade {

    private static final String CUSTOMER = "customer";
    private static final String ENCODING_SHA256 = "SHA-256";

    public static String getCUSTOMER() {
        return CUSTOMER;
    }

//SHA256 AF PASSWORD
    public static String encodePasswordSHA256(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(ENCODING_SHA256);
        byte[] encodedPassword = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder passwordSHA256 = new StringBuilder();
        for (int i : encodedPassword) {
            if (Integer.toHexString(0xFF & i).length() == 2) {
                passwordSHA256.append(Integer.toHexString(0xFF & i));
            } else {
                passwordSHA256.append(0x00).append(Integer.toHexString(0xFF & i));
            }
        }
        return new String(passwordSHA256);
    }

//DATAMAPPER CRUD
    public static User createUser(String email, String password) throws CreateUserException, NoSuchAlgorithmException {
        User user = new User(email, encodePasswordSHA256(password), CUSTOMER);
        DataMapper.createUser(user);
        return user;
    }

    public static User login(String email, String password) throws LoginUserException, NoSuchAlgorithmException {
        return DataMapper.login(email, encodePasswordSHA256(password));
    }

    public static OrderDetails createOrder(User user, int length, int wide, int heigth, int doorHeight, int doorLength, int windowHeight, int windowLength) throws OrderException {
        return DataMapper.createOrder(user, length, wide, heigth, doorHeight, doorLength, windowHeight, windowLength);
    }

    public static OrderDetails getOrderFromDB(User user, int order_id) throws OrderException {
        if (user.getRole().equals(CUSTOMER)) {
            return DataMapper.getOrderDetails(user, order_id);
        } else {
            return DataMapper.getOrderDetailsEmployee(order_id);
        }
    }

    public static Orders getOrders(User user) throws OrderException {
        if (user.getRole().equals(CUSTOMER)) {
            return DataMapper.getOrders(user);
        } else {
            return DataMapper.getOrdersEmployee();
        }
    }

    public static Orders getOrdersNotShipped() throws OrderException {
        return DataMapper.getOrdersNotShippedEmployee();
    }

    public static void orderShipped(int order_id) throws OrderShipException {
        DataMapper.orderToShip(order_id);
    }

//BUSINESS LOGIC   
    public static House createHouse(int length, int wide, int height, int doorLength, int doorHeight, int windowLength, int windowHeight) {
        return new House(height, wide, length, new Door(doorHeight, doorLength), new Window(windowHeight, windowLength));
    }

    public static void houseCheckDoorWindow(House house) throws DoorWindowException {
        house.checkSpaceDoorWindow();
    }

    public static Orders isThisFirstOrder(Orders orders) {
        if (orders == null) {
            return new Orders();
        }
        return orders;
    }

    public static Orders addOrderIdToOrders(Orders orders, int order_id) {
        orders.addIntToList(order_id);
        return orders;
    }

    public static BrickList calcBrickList(House house) {
        BrickList brickList = new BrickList(house);
        brickList.calcBrickList();
        return brickList;
    }
}
