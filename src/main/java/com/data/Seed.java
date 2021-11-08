package com.data;

import com.DTOs.BusinessDtos.RegisterDTO;
import com.data.DAOs.CategoryDAO;
import com.data.DAOs.ProductDAO;
import com.data.DAOs.RoleDAO;
import com.model.Category;
import com.model.Product;
import com.model.Role;
import com.model.User;

public class Seed {
    public void doSeed() {
        // Add role
        RoleDAO roleDAO = new RoleDAO();
        roleDAO.addRole(new Role("Admin"));
        roleDAO.addRole(new Role("Staff"));
        roleDAO.addRole(new Role("Customer"));

        // Add admin account
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setUsername("admin");
        registerDTO.setPassword("522001");
        registerDTO.setReEnter("522001");
        registerDTO.setRole("Admin");
        User user = new User();
        user.register(registerDTO);

        // Add category
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.addCategory(new Category("Action"));
        categoryDAO.addCategory(new Category("Detective"));
        categoryDAO.addCategory(new Category("Mystery"));

        // Add product and add category for product
        ProductDAO productDAO = new ProductDAO();
        Product product = new Product("B1","Thien Nguyen", "Have a nice day", 2000, "Classic");
        Product product1 = new Product("B2","Dat Ngo", "Go to the moon", 3000, "Romance");
        Product product2 = new Product("B3","Duong le", "Meme", 2000, "Comedy");
        Product product3 = new Product("B4","Dat Tran", "Good job", 2500, "Buisiness");
        Product product4 = new Product("B5","Thuy Nguyen", "Go to the moon", 3500, "Romance");
        productDAO.addProducts(product);
        productDAO.addProducts(product1);
        productDAO.addProducts(product2);
        productDAO.addProducts(product3);
        productDAO.addProducts(product4); 
    }
}
