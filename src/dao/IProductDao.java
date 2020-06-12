package dao;

import model.Product;

import java.util.List;

public interface IProductDao {
    List<Product> findAll();
    void saveProduct (Product product);
    Product selectById(int id);
    boolean removeProduct(int id);
    boolean updateProduct(Product product);
    List<Product> searchByName(String productName);
    List<Product> groupByMaker(String maker);
}
