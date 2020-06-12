package dao;

import model.Product;
import uitils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements IProductDao {
    private  static final String SELECT_ALL_FROM_PRODUCTS= "SELECT * FROM PHONES;";
    private static final  String INSERT_NEW_PRODUCT= "insert into phones(name,price,quantity,color,category)"+
            " values " +"(?,?,?,?,?);";
    private static final String UPDATE_PRODUCTS= "update phones set name=?,price=?,quantity=?,color=?,category=?"+
            " where id=?";
    private static final String SELECT_PRODUCTS_BY_ID="select name,price,quantity,color,category from phones"+
            " where id=?";
    private  static final String DELETE_PRODUCT_BY_ID="delete from phones where id=?";
    private static final String FIND_PRODUCT_BY_NAME ="SELECT * FROM products where name like ?;";
    private  static final String GROUP_PRODUCTS_BY_MAKER = "select * from products where maker= ?;";

    private List<Product> products = new ArrayList<>();

    @Override
    public List<Product> findAll() {
        products.clear();

        try {
            Connection connection= JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_PRODUCTS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                String category = resultSet.getString("category");
                products.add(new Product(id,name,price,quantity,color,category));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    @Override
    public void saveProduct(Product product) {
        try {
            Connection connection = JDBCUtils.getConnection();

            PreparedStatement preparedStatement =connection.prepareStatement(INSERT_NEW_PRODUCT);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setInt(3,product.getQuantity());
            preparedStatement.setString(4,product.getColor());
            preparedStatement.setString(5,product.getCategory());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public Product selectById(int id) {
        Product product = null;
        try {
            Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTS_BY_ID);
            preparedStatement.setInt(1,id);
           ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()){
               String name = resultSet.getString("name");
               double price = resultSet.getDouble("price");
               int quantity = resultSet.getInt("quantity");
               String color = resultSet.getString("color");
               String category = resultSet.getString("category");
             product = new Product(id,name,price,quantity,color,category);

           }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    @Override
    public boolean removeProduct(int id) {
       boolean rowDeleted=false;

        try {
            Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_BY_ID);
            preparedStatement.setInt(1,id);
            rowDeleted = preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowDeleted;

    }

    @Override
    public boolean updateProduct(Product product) {
        boolean rowUpdated =false;
        Connection connection = JDBCUtils.getConnection();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(UPDATE_PRODUCTS);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setInt(3,product.getQuantity());
            preparedStatement.setString(4,product.getColor());
            preparedStatement.setString(5,product.getCategory());
            preparedStatement.setInt(6,product.getId());
            rowUpdated = preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  rowUpdated;
    }

    @Override
    public List<Product> searchByName(String productName) {
        products.clear();
        Connection connection= JDBCUtils.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_NAME);
            preparedStatement.setString(1,"%"+productName+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price= resultSet.getDouble("price");
                int quantity= resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                String category = resultSet.getString("category");
                products.add(new Product(id,name,price,quantity,color,category));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> groupByMaker(String maker) {
        return null;
    }
}
