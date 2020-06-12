package controller;

import dao.ProductDaoImpl;
import model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/home")
public class ProductServlet extends HttpServlet {
    private ProductDaoImpl productDao;
    @Override
    public void init() throws ServletException {
        productDao = new ProductDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action= request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                createProduct(request,response);
                break;
            case "edit":
                updateProduct(request,response);
                break;
            case "delete":
                deleteProduct(request,response);
                break;
            case "search":
                searchProduct(request,response);
                break;
            default:
                break;
        }

    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response) {
        String productname= request.getParameter("search");
        String address= request.getParameter("address");
        List<Product> productList  = productDao.searchByName(productname);
        request.setAttribute("products",productList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(address);
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDao.selectById(id);
        RequestDispatcher dispatcher;
        if(product==null) {
            dispatcher = request.getRequestDispatcher("404-error.jsp");
        }else {
            productDao.removeProduct(id);
            try {
                response.sendRedirect("./home");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String name = request.getParameter("newName");
        Double price = Double.parseDouble(request.getParameter("newPrice"));
        int quantity= Integer.parseInt(request.getParameter("newQuantity"));
        String color= request.getParameter("newColor");
        String category = request.getParameter("newCategory");
        int id= Integer.parseInt(request.getParameter("id"));
       Product product = productDao.selectById(id);
        RequestDispatcher dispatcher;
        if(product==null) {
            dispatcher = request.getRequestDispatcher("404-error.jsp");
        } else {
            dispatcher= request.getRequestDispatcher("view/editproduct.jsp");
            request.setAttribute("message","Update product success!!!");
            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setColor(color);
            product.setCategory(category);
            productDao.updateProduct(product);
        }
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String name = request.getParameter("productName");
        double price = Double.parseDouble(request.getParameter("productPrice"));
        int quantity = Integer.parseInt(request.getParameter("productQuantity"));
        String color = request.getParameter("productColor");
        String category = request.getParameter("productCategory");
        Product product = new Product(name,price,quantity,color,category);
        productDao.saveProduct(product);
        RequestDispatcher dispatcher= request.getRequestDispatcher("view/createproduct.jsp");
        request.setAttribute("message","Create new product success!!!");
        dispatcher.forward(request,response);

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action= request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreatForm(request, response);
                break;
            case "edit":
                showEditForm(request,response);
                break;
            case "delete":
                deleteform(request,response);
                break;
            default:
                listProduct(request, response);
                break;
        }
    }

    private void deleteform(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        Product product=productDao.selectById(id);
        RequestDispatcher dispatcher;
        if(product==null){
            dispatcher=request.getRequestDispatcher("404-error.jsp");
        }else {
            dispatcher=request.getRequestDispatcher("view/delete.jsp");
            request.setAttribute("product", product);
        }
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDao.selectById(id);
        RequestDispatcher dispatcher;
            dispatcher=request.getRequestDispatcher("view/editproduct.jsp");
            request.setAttribute("product", product);
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private void showCreatForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/createproduct.jsp");
        requestDispatcher.forward(request,response);
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList  = productDao.findAll();
        request.setAttribute("products",productList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/home.jsp");
        requestDispatcher.forward(request,response);
    }
}
