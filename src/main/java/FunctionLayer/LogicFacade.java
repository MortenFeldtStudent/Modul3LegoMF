package FunctionLayer;

import DBAccess.DataMapper;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class LogicFacade {
    
    private static final String CUSTOMER = "customer";
    private static final String ENCODING_SHA256 = "SHA-256";

    public static String getCUSTOMER() {
        return CUSTOMER;
    }

    public static User login(String email, String password) throws LoginUserException, NoSuchAlgorithmException {
        return DataMapper.login(email, encodePasswordSHA256(password));
    }

    public static User createUser(String email, String password) throws CreateUserException, NoSuchAlgorithmException {
        User user = new User(email, encodePasswordSHA256(password), CUSTOMER);
        DataMapper.createUser(user);
        return user;
    }

    public static int createOrder(User user, int length, int wide, int heigth) throws OrderException {
        return DataMapper.createOrder(user, length, wide, heigth);
    }

    public static Orders getOrders(User user) throws OrderException {
        if (user.getRole().equals(CUSTOMER)) {
            return DataMapper.getOrders(user);
        }
        else {
            return DataMapper.getOrdersEmployee();
        }
    }

    public static Orders getOrdersNotShipped() throws OrderException {
        return DataMapper.getOrdersNotShippedEmployee();
    }
    
    public static void orderShipped(int order_id) throws OrderShipException {
        DataMapper.orderToShip(order_id);
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

    public static OrderDetails getOrderFromDB(User user, int order_id) throws OrderException {
        if (user.getRole().equals(CUSTOMER)) {
            return DataMapper.getOrderDetails(user, order_id);
        }
        else {
            return DataMapper.getOrderDetailsEmployee(order_id);
        }
    }

    public static BrickList calcBrickList(int length, int wide, int heigth) {
        int[] listCountBrick2x4 = {0, 0, 0, 0, 0}; //side1 (For), side2 (Bag), side3 (Side), side4 (Side), total (alle lag)
        int[] listCountBrick2x2 = {0, 0, 0, 0, 0}; //side1 (For), side2 (Bag), side3 (Side), side4 (Side), total (alle lag)
        int[] listCountBrick2x1 = {0, 0, 0, 0, 0}; //side1 (For), side2 (Bag), side3 (Side), side4 (Side), total (alle lag)

        House house = calcHouse(length, wide, heigth);

        for (int i = 0; i < house.getListLayers().size(); i++) {
            Layer layer = house.getListLayers().get(i);

            for (int j = 0; j < layer.getListSides().size(); j++) {
                Side side = layer.getListSides().get(j);

                listCountBrick2x4[j] = calcLayerSide(side.getBrick2x4(), listCountBrick2x4[j]);
                listCountBrick2x2[j] = calcLayerSide(side.getBrick2x2(), listCountBrick2x2[j]);
                listCountBrick2x1[j] = calcLayerSide(side.getBrick2x1(), listCountBrick2x1[j]);
            }

        }
        listCountBrick2x4 = calcTotalBricks(listCountBrick2x4);
        listCountBrick2x2 = calcTotalBricks(listCountBrick2x2);
        listCountBrick2x1 = calcTotalBricks(listCountBrick2x1);

        return new BrickList(heigth, wide, length, listCountBrick2x4, listCountBrick2x2, listCountBrick2x1);
    }

    public static int calcLayerSide(int brick, int listValue) {
        return listValue += brick;
    }

    public static int[] calcTotalBricks(int[] list) {
        int countTotalBricks = 0;

        for (int i = 0; i < list.length - 1; i++) {
            countTotalBricks += list[i];
        }
        list[4] = countTotalBricks;

        return list;
    }

    public static House calcHouse(int length, int wide, int heigth) {
        House house;

        house = new House(heigth, wide, length);
        house.setListLayers(calcLayers(house.getLength(), house.getWide(), house.getHeight()));

        return house;
    }

    public static List<Layer> calcLayers(int length, int wide, int heigth) {
        Layer layer = null;
        List<Layer> listLayers = new ArrayList();

        for (int i = 0; i < heigth; i++) {
            if ((i % 2) == 0) {
                listLayers.add(calcLayerEven(layer, length, wide));
            } else {
                listLayers.add(calcLayerNotEven(layer, length, wide));
            }

        }

        return listLayers;
    }

    public static Layer calcLayerEven(Layer layer, int length, int wide) {
        layer = new Layer();
        layer.addSides(calcSide(length)); //For
        layer.addSides(calcSide(length)); //Bag
        layer.addSides(calcSide(wide - 4)); //Side
        layer.addSides(calcSide(wide - 4)); //Side
        return layer;
    }

    public static Layer calcLayerNotEven(Layer layer, int length, int wide) {
        layer = new Layer();
        layer.addSides(calcSide(length - 4)); //For
        layer.addSides(calcSide(length - 4)); //Bag
        layer.addSides(calcSide(wide)); //Side
        layer.addSides(calcSide(wide)); //Side
        return layer;
    }

    public static Side calcSide(int count) {
        int brick2x4 = 0;
        int brick2x2 = 0;
        int brick2x1 = 0;

        brick2x4 = calcBrick2x4(count);
        count = count - (brick2x4 * 4);

        return calcSideBricks(count, brick2x4, brick2x2, brick2x1);
    }

    public static int calcBrick2x4(int count) {
        if (count < 4) {
            return 0;
        }
        if ((count % 4) == 0) {
            return count / 4;
        }
        return (count - (count % 4)) / 4;
    }

    public static Side calcSideBricks(int count, int brick2x4, int brick2x2, int brick2x1) {
        switch (count) {
            case 0:
                return new Side(brick2x4, brick2x2, brick2x1);
            case 1:
                brick2x1 = 1;
                return new Side(brick2x4, brick2x2, brick2x1);
            case 2:
                brick2x2 = 1;
                return new Side(brick2x4, brick2x2, brick2x1);
            case 3:
                brick2x1 = 1;
                brick2x2 = 1;
                return new Side(brick2x4, brick2x2, brick2x1);
            default:
                return null;
        }
    }
    
    public static String encodePasswordSHA256(String password) throws NoSuchAlgorithmException{
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
}
