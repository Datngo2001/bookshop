package com.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;
import javax.persistence.*;
import javax.servlet.ServletContext;

import com.DTOs.BusinessDtos.LoginDTO;
import com.DTOs.BusinessDtos.RegisterDTO;
import com.DTOs.BusinessDtos.UserDTO;
import com.data.DAOs.CartDao;
import com.data.DAOs.RoleDAO;
import com.data.DAOs.UserDAO;
import com.services.EmailService;
import com.services.HashService;

@Entity
@Table(name = "app_user")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	private String username;
	private byte[] passwordHash;
	private byte[] passwordSalt;
	private Date bdate;
	private String fname;
	private String lname;
	private String email;

	// Relation
	@OneToOne
	private Role roles;
	@OneToMany(mappedBy = "user")
	private List<Order> orders = new ArrayList<Order>();
	@OneToOne(mappedBy = "user")
	private Cart cart;

	public User() {
	}

	public User(String username) {
		this.username = username;
	}

	public User(String username, String email) {
		this.username = username;
		this.email = email;
	}

	public User(String username, byte[] passwordHash, byte[] passwordSalt) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.passwordSalt = passwordSalt;
	}

	public User(Role roles, int id, String username, byte[] passwordHash, byte[] passwordSalt, Date bdate, String fname,
			String lname, String email, String gender) {
		// this.orders = orders;
		this.roles = roles;
		this.id = id;
		this.username = username;
		this.passwordHash = passwordHash;
		this.passwordSalt = passwordSalt;
		this.bdate = bdate;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.gender = gender;
	}

	public User(int id, String username, byte[] passwordHash, byte[] passwordSalt, Date bdate, String fname,
			String lname, String email, Role roles, List<Order> orders, Cart cart, String gender) {
		this.id = id;
		this.username = username;
		this.passwordHash = passwordHash;
		this.passwordSalt = passwordSalt;
		this.bdate = bdate;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.roles = roles;
		this.orders = orders;
		this.cart = cart;
		this.gender = gender;
	}

	// BUSINESS LOGIC ----------------------------------------------------

	public Boolean login(LoginDTO loginDTO) {
		UserDAO userDAO = new UserDAO();

		userDAO.getPasswordHashAndSalt(loginDTO);
		User user = userDAO.getUserByUserName(loginDTO.getUsername());
		int rid = user.getRoles().getId();
		int id = user.getId();
		loginDTO.setRoleId(rid);
		loginDTO.setId(id);
		if (loginDTO.getPasswordHash() == null)
			return false;

		// compute hash
		HashService hashService = new HashService();
		byte[] hashedInputPass = hashService.doHash(loginDTO.getPassword().getBytes(), loginDTO.getPasswordSalt());

		// compare hash result with the hash from database
		return Arrays.equals(hashedInputPass, loginDTO.getPasswordHash());
	}
	public Boolean PayerInfor(String username) {
		try {
			UserDAO userDao = new UserDAO();
			UserDTO userDto = new UserDTO();
			User user = userDao.getUserByUserName(username);
			
			userDto.setFirstName(user.getFname());
			userDto.setLastName(user.getLname());
			userDto.setEmail(user.getEmail());
			
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public Boolean register(RegisterDTO registerDTO, ServletContext context) {
		UserDAO userDAO = new UserDAO();

		// Check not match password
		if (!registerDTO.getPassword().equals(registerDTO.getReEnter())) {
			registerDTO.setErrorMessage("Password not match");
			return false;
		}

		// Check existed user name
		if (userDAO.isUsernameExisted(registerDTO.getUsername())) {
			registerDTO.setErrorMessage("Username is existed");
			return false;
		}

		// Check existed email
		if (userDAO.isEmailExisted(registerDTO.getUsername())) {
			registerDTO.setErrorMessage("This email alreadly used");
			return false;
		}

		if (registerDTO.getRole() == null || registerDTO.getRole().equals("")) {
			registerDTO.setRole("Customer");
		}

		// Seed verify mail
		try {
			registerDTO.setCode(EmailService.getRandom());
			String host = context.getInitParameter("host");
			String port = context.getInitParameter("port");
			String seeder = context.getInitParameter("c");
			String pass = context.getInitParameter("pass");
			EmailService.sendEmail(host, port, seeder, pass, registerDTO.getEmail(), "Email Verification",
					"Registered successfully.Please verify your account using this code: " + registerDTO.getCode());
		} catch (MessagingException e) {
			e.printStackTrace();
			registerDTO.setErrorMessage("Unable to seed mail!");
			return false;
		}

		return true;
	}
	
	public Boolean verify(RegisterDTO registerDTO, String code) {

		if (!registerDTO.getCode().equals(code)) {
			registerDTO.setErrorMessage("Verify code incorrect!");
			return false;
		}

		// Compute hash
		HashService hashService = new HashService();
		byte[] salt = hashService.generateSalt();
		byte[] hash = null;
		hash = hashService.doHash(registerDTO.getPassword().getBytes(), salt);

		// Add role
		Role roles = new RoleDAO().getRoleByName(registerDTO.getRole());

		// Creat user entity
		User user = new User();
		user.setPasswordHash(hash);
		user.setPasswordSalt(salt);
		user.setUsername(registerDTO.getUsername());
		user.setRoles(roles);
		Cart cart = new CartDao().CreateCart(new Cart());
		user.setCart(cart);

		// Save new user to database
		UserDAO userDAO = new UserDAO();
		userDAO.addUser(user);

		return true;
	}

	// INPUT OUTPUT LOGIC ----------------------------------------------------
	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String gender;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte[] getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(byte[] passwordHash) {
		this.passwordHash = passwordHash;
	}

	public byte[] getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(byte[] passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

}