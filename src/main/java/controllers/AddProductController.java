package controllers;

import beans.ProductBean;
import dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet("/add-product")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class AddProductController extends HttpServlet {
    public AddProductController() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        String productName = req.getParameter("name");
        String productDescription = req.getParameter("description");
        String productBrand = req.getParameter("brand");
        String productSize = req.getParameter("size");
        int productPrice = Integer.parseInt(req.getParameter("price"));

        try {
            String appPath = req.getServletContext().getRealPath("");
            appPath = appPath.replace("\\", "/");

            String savePath;
            if (appPath.endsWith("/")) savePath = appPath + "img/product-img";
            else savePath = appPath + "/img/product-img";

            File fileSavePath = new File(savePath);
            if (!fileSavePath.exists()) {
                fileSavePath.mkdir();
            }

            for (Part part : req.getParts()) {
                String productImage = extractFileName(part);

                if (productImage != null && !productImage.isEmpty()) {
                    UUID uuid = UUID.randomUUID();
                    productImage = savePath + "/" + uuid + "_" + productImage;

                    System.out.println("Write attachment to file: " + productImage);
                    int i =  new ProductDao().addProduct(categoryId, productName, productImage, productDescription, productBrand, productSize, productPrice);
                    if (i > 0) {
                        part.write(productImage);
                        break;
                    }
                    else {
                        req.setAttribute("message", "Thao tác không thành công. Hãy thử lại lần sau.");
                        req.getRequestDispatcher("product-management").forward(req, resp);
                    }
                }
            }

            resp.sendRedirect("product-management");
        } catch (ClassNotFoundException | SQLException e) {
            req.setAttribute("message", "Thao tác không thành công. Hãy thử lại lần sau.");
            req.getRequestDispatcher("product-management").forward(req, resp);
        }
    }
    
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");

        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String clientFilename = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFilename = clientFilename.replace("\\", "/");
                int i = clientFilename.lastIndexOf("/");
                return clientFilename.substring(i + 1);
            }
        }

        return null;
    }
}
