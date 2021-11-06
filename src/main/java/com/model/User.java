package com.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

import com.DTOs.BusinessDtos.LoginDTO;
import com.DTOs.BusinessDtos.RegisterDTO;
import com.data.UserDAO;
import com.services.HashService;

@Entity
@Table(name = "user")
public class User implements Serializable {

	@OneToMany(mappedBy = "user")
	private List<Order> orders;

	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"))
	private List<Role> roles;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int id;
	public String username;
	public byte[] passwordHash;
	public byte[] passwordSalt;
	public Date bdate;
	public String fname;
	public String lname;

	public User() {
	}

	public User(String username, byte[] passwordHash, byte[] passwordSalt, Date bdate, String fname, String lname,
			String gender) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.passwordSalt = passwordSalt;
		this.bdate = bdate;
		this.fname = fname;
		this.lname = lname;
		this.gender = gender;
	}

	// BUSINESS LOGIC ----------------------------------------------------
	public Boolean login(LoginDTO loginDTO) {
		UserDAO userDAO = new UserDAO();

		userDAO.getPasswordHashAndSalt(loginDTO);

		if (loginDTO.getPasswordHash().length == 0)
			return false;

		// compute hash
		HashService hashService = new HashService();
		byte[] hashedInputPass = hashService.doHash(loginDTO.getPassword().getBytes(), loginDTO.getPasswordSalt());

		// compare hash result with the hash from database
		return Arrays.equals(hashedInputPass, loginDTO.getPasswordHash());
	}

	public Boolean register(RegisterDTO registerDTO) {
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

		HashService hashService = new HashService();

		byte[] salt = hashService.generateSalt();
		byte[] hash = null;

		// Compute hash
		hash = hashService.doHash(registerDTO.getPassword().getBytes(), salt);

		User user = new User();
		user.setPasswordHash(hash);
		user.setPasswordSalt(salt);
		user.setUsername(registerDTO.getUsername());

		// Save new user to database
		userDAO.addUser(user);

		return true;
	}

	// INPUT OUTPUT LOGIC ----------------------------------------------------
	public void addRole(Role role) {
		this.roles.add(role);
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
