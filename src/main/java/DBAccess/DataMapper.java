package DBAccess;

import FunctionLayer.OrderException;
import FunctionLayer.CreateUserException;
import FunctionLayer.LoginUserException;
import FunctionLayer.OrderDetails;
import FunctionLayer.Orders;
import FunctionLayer.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The purpose of DataMapper is to...
 *
 * @author kasper
 */
public class DataMapper {

    private static final String SQL_USER_CREATE = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";
    private static final String SQL_USER_LOGIN = "SELECT user_id, role FROM users WHERE email=? AND password=?";
//    private static final String SQL_USER_GET_ORDERS_NOT_SHIPPED = "SELECT orders.order_id, email, orderdate, shipped, length, wide, height FROM orders JOIN order_details ON orders.order_id = order_details.order_id JOIN users ON users.user_id = orders.user_id WHERE users.user_id = ? AND orders.shipped IS NULL ORDER BY order_id ASC";
//    private static final String SQL_USER_GET_ORDERS_SHIPPED = "SELECT orders.order_id, email, orderdate, shipped, length, wide, height FROM orders JOIN order_details ON orders.order_id = order_details.order_id JOIN users ON users.user_id = orders.user_id WHERE users.user_id = ? AND orders.shipped IS NOT NULL ORDER BY order_id ASC";
    private static final String SQL_USER_GET_ALL_ORDERS = "SELECT order_id FROM orders WHERE user_id = ? ORDER BY order_id ASC";
    private static final String SQL_USER_GET_ORDER_DETAILS = "SELECT orders.order_id, email, orderdate, shipped, length, wide, height FROM orders JOIN order_details ON orders.order_id = order_details.order_id JOIN users ON users.user_id = orders.user_id WHERE users.user_id = ? AND orders.order_id = ?";
    private static final String SQL_EMPLOYEE_GET_ALL_ORDERS = "SELECT order_id FROM orders ORDER BY order_id ASC";
    private static final String SQL_EMPLOYEE_UPDATE_SHIPPED_ON_ORDER = "UPDATE orders SET orders.shipped = current_timestamp() WHERE order_id=?";
    private static final String SQL_INSERT_ORDER = "INSERT INTO orders (user_id) VALUES (?)";
    private static final String SQL_INSERT_ORDER_DETAILS = "INSERT INTO order_details (order_id, length, wide, height) VALUES (?, ?, ?, ?);";

    public static void createUser(User user) throws CreateUserException {
        try {
            Connection con = DBConnector.connection();
            String SQL = SQL_USER_CREATE;
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            user.setId(id);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CreateUserException(ex.getMessage());
        }
    }

    public static User login(String email, String password) throws LoginUserException {
        try {
            Connection con = DBConnector.connection();
            String SQL = SQL_USER_LOGIN;
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                int user_id = rs.getInt("user_id");
                User user = new User(email, password, role);
                user.setId(user_id);
                return user;
            } else {
                throw new LoginUserException("Email findes ikke!");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginUserException(ex.getMessage());
        }
    }

    public static int createOrder(User user, int length, int wide, int height) throws OrderException {
        try {
            Connection con = DBConnector.connection();
            PreparedStatement orderPstmt = con.prepareStatement(SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement orderDetailsPstmt = con.prepareStatement(SQL_INSERT_ORDER_DETAILS);
            ResultSet rs = null;
            int orderId = 0;
            try {
                orderPstmt.setInt(1, user.getId());
                con.setAutoCommit(false);
                int resultOrder = orderPstmt.executeUpdate();
                rs = orderPstmt.getGeneratedKeys();
                rs.next();
                orderId = rs.getInt(1);
                if (resultOrder == 1) {
                    orderDetailsPstmt.setInt(1, orderId);
                    orderDetailsPstmt.setInt(2, length);
                    orderDetailsPstmt.setInt(3, wide);
                    orderDetailsPstmt.setInt(4, height);
                    orderDetailsPstmt.executeUpdate();
                    con.commit();
                } else {
                    con.rollback();
                }
                return orderId;
            } catch (SQLException ex) {
                con.rollback();
                throw new OrderException(ex.getMessage());
            } finally {
                con.setAutoCommit(true);
                if (orderPstmt != null) {
                    orderPstmt.close();
                }
                if (orderDetailsPstmt != null) {
                    orderDetailsPstmt.close();
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new OrderException(ex.getMessage());
        }
    }

    public static Orders getOrders(User user) throws OrderException {
        try {
            Connection con = DBConnector.connection();
            String SQL = SQL_USER_GET_ALL_ORDERS;
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            Orders orders = new Orders();
            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                orders.addIntToList(order_id);
            }
            return orders;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new OrderException(ex.getMessage());
        }
    }

    public static OrderDetails getOrderDetails(User user, int order_id) throws OrderException {
        try {
            Connection con = DBConnector.connection();
            String SQL = SQL_USER_GET_ORDER_DETAILS;
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, user.getId());
            ps.setInt(2, order_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int orderid = rs.getInt("order_id");
                int height = rs.getInt("height");
                int wide = rs.getInt("wide");
                int length = rs.getInt("length");
                String username = rs.getString("email");
                String orderDate = rs.getString("orderdate");
                String shippedDate = rs.getString("shipped");
                OrderDetails orderDetails = new OrderDetails(orderid, height, wide, length, username, orderDate, shippedDate);
                return orderDetails;
            } else {
                throw new OrderException("Email findes ikke!");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new OrderException(ex.getMessage());
        }
    }
}
