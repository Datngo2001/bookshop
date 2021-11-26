package com.data;

import java.util.ArrayList;

import com.DTOs.BusinessDtos.RegisterDTO;
import com.data.DAOs.CartDAO;
import com.data.DAOs.CategoryDAO;
import com.data.DAOs.ProductDAO;
import com.data.DAOs.RoleDAO;
import com.data.DAOs.UserDAO;
import com.model.Cart;
import com.model.Category;
import com.model.Product;
import com.model.Role;
import com.model.User;
import com.services.HashService;

public class Seed {
    public void doSeed() {
        // Add role
        RoleDAO roleDAO = new RoleDAO();
        roleDAO.addRole(new Role("Admin"));
        roleDAO.addRole(new Role("Staff"));
        roleDAO.addRole(new Role("Customer"));

        // Add admin account
        RegisterDTO registerDTO = new RegisterDTO();
        RegisterDTO registerDTO1 = new RegisterDTO();
        RegisterDTO registerDTO2 = new RegisterDTO();
        registerDTO.setUsername("admin");
        registerDTO.setPassword("522001");
        registerDTO.setReEnter("522001");
        registerDTO.setRole("Admin");
        registerDTO1.setUsername("ngocthien");
        registerDTO1.setPassword("230601");
        registerDTO1.setReEnter("230601");
        registerDTO1.setRole("Customer");
        
        registerDTO2.setUsername("duongle");
        registerDTO2.setPassword("123456");
        registerDTO2.setReEnter("123456");
        registerDTO2.setRole("Customer");
        CreateAccount(registerDTO);
        CreateAccount(registerDTO1);
        CreateAccount(registerDTO2);

        // Add category
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.addCategory(new Category("Action"));
        categoryDAO.addCategory(new Category("Detective"));
        categoryDAO.addCategory(new Category("Mystery"));

        // Add product and add category for product
        ProductDAO productDAO = new ProductDAO();

        ArrayList<Product> products = DataUtil.getCrawlData();

        for (Product product : products) {
            productDAO.addProducts(product);
        }
    }

    private void CreateAccount(RegisterDTO registerDTO) {
        // Compute hash
        HashService hashService = new HashService();
        byte[] salt = hashService.generateSalt();
        byte[] hash = null;
        hash = hashService.doHash(registerDTO.getPassword().getBytes(), salt);

        // Add role
        if (registerDTO.getRole().equals("")) {
            registerDTO.setRole("Customer");
        }
        Role roles = new RoleDAO().getRoleByName(registerDTO.getRole());

        // Creat user entity
        User user = new User();
        user.setPasswordHash(hash);
        user.setPasswordSalt(salt);
        user.setUsername(registerDTO.getUsername());
        user.setRole(roles);

        // Save new user to database
        UserDAO userDAO = new UserDAO();
        userDAO.addUser(user);
        new CartDAO().CreateCartForUser(new Cart(), user);
    }
}
