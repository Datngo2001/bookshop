package com.data;

import java.util.List;

import com.DTOs.BusinessDtos.RegisterDTO;
import com.data.DAOs.CategoryDAO;
import com.data.DAOs.ProductDAO;
import com.data.DAOs.RoleDAO;
import com.data.DAOs.UserDAO;
import com.model.Category;
import com.model.Product;
import com.model.Role;
import com.model.User;
import com.services.HashService;

public class Seed {
    public void doSeed() {
        // Add role
    	User user = new User();
        RoleDAO roleDAO = new RoleDAO();
        roleDAO.addRole(new Role("Admin"));
        roleDAO.addRole(new Role("Staff"));
        roleDAO.addRole(new Role("Customer"));

        // Add admin account
        RegisterDTO registerDTO = new RegisterDTO();
        RegisterDTO registerDTO1 = new RegisterDTO();
        registerDTO.setUsername("admin");
        registerDTO.setPassword("522001");
        registerDTO.setReEnter("522001");
        registerDTO.setRole("Admin");
        registerDTO1.setUsername("ngocthien");
        registerDTO1.setPassword("230601");
        registerDTO1.setReEnter("230601");
        registerDTO1.setRole("Customer");
        CreateAccount(registerDTO);
        CreateAccount(registerDTO1);
        

        // Add category
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.addCategory(new Category("Action"));
        categoryDAO.addCategory(new Category("Detective"));
        categoryDAO.addCategory(new Category("Mystery"));

        // Add product and add category for product
        ProductDAO productDAO = new ProductDAO();

        Product product = new Product("B1", "Ramma 1/2", "Thien Nguyen", "Have a nice day", 2000, "Classic");
        Product product1 = new Product("B2", "Inuyasha chap 1", "Dat Ngo", "Go to the moon", 3000, "Romance");
        Product product2 = new Product("B3", "Josee, The Tiger and the Fish", "Duong le", "Meme", 2000, "Comedy");
        Product product3 = new Product("B4", "Cậu Ma Nhà Xí Hanako - Tập 1", "Dat Tran", "Good job", 2500, "Buisiness");
        Product product4 = new Product("B5", "Saiki Kusuo Kẻ Siêu Năng Khốn Kho", "Thuy Nguyen", "Go to the moon", 3500,
                "Romance");
        Product product5 = new Product("KMSNYB22",
                "Thanh Gươm Diệt Quỷ - Kimetsu No Yaiba - Tập 22: Vòng Xoay Vận Mệnh - Bản Đặc Biệt - Tặng Kèm Bộ Huy Hiệu + Phụ Lục Độc Quyền: Báo Cáo Đặc Biệt Của Đội Diệt Quỷ",
                "Koyoharu Gotouge", "description", 111000,
                "https://cdn0.fahasa.com/media/catalog/product/t/g/tgdq_v22_mockup_03.png", "8935244855395",
                "Adventure, Action, Shounen, Fantasy, Drama, Historical, Supernatural");

        productDAO.addProducts(product);
        productDAO.addProducts(product1);
        productDAO.addProducts(product2);
        productDAO.addProducts(product3);
        productDAO.addProducts(product4);
        productDAO.addProducts(product5);
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
        user.setRoles(roles);

        // Save new user to database
        UserDAO userDAO = new UserDAO();
        userDAO.addUser(user);
    }
}
