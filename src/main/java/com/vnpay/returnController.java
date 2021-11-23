package com.vnpay;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DAOs.*;
import com.model.*;


@WebServlet("/return")
public class returnController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CartDAO cartDao;
	private OrderDAO orderDao;
	private UserDAO userDao;
	private ProductDAO productDao;
    public returnController() {
        cartDao = new CartDAO();
		orderDao = new OrderDAO();
		userDao = new UserDAO();
		productDao = new ProductDAO();
    }
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String nextUrl = "WEB-INF/vnpay/vnpay_return.jsp";
	String vnp_TransactionStatus = "";
	//hash attribute
	   Map fields = new HashMap();
	   for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
	       String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
	       String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
	       if ((fieldValue != null) && (fieldValue.length() > 0)) {
	           fields.put(fieldName, fieldValue);
	       }
	   }
	   //get attribute
	   float amount = Float.parseFloat((request.getParameter("vnp_Amount"))) / 100;
	   String code = request.getParameter("vnp_TransactionNo");
	   String day = request.getParameter("vnp_PayDate");
	   String info = request.getParameter("vnp_OrderInfo");
	   String bank = request.getParameter("vnp_BankCode");
	   String transId = request.getParameter("vnp_TxnRef");
	   String vnp_SecureHash = request.getParameter("vnp_SecureHash");
	   if (fields.containsKey("vnp_SecureHashType")) {
	       fields.remove("vnp_SecureHashType");
	   }
	   if (fields.containsKey("vnp_SecureHash")) {
	       fields.remove("vnp_SecureHash");
	   }
	   
	   String signValue = Config.hashAllFields(fields);

	   Object userId = request.getSession().getAttribute("userId");
	   int uid;
	   
	   if(userId == null) {
		   uid = 0;
	   }
	   
	   else {
		   uid = Integer.parseInt(userId.toString());
	   }

	   if (signValue.equals(vnp_SecureHash)) {
	       if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
	    	   //get user
	    	   User user = userDao.getUser(userId.toString());
	    	   Order order = new Order(day, code, amount, user);
	    	   orderDao.CreateOrderForUser(order, user);  
	    	   
	    	   LineItem line_item = null;
	    	   try {
	    		   //get all item in cart by userId
	    		   List<LineItem> items = cartDao.getListItemForUser(uid);
	    		   for (Iterator<LineItem> i = items.iterator(); i.hasNext();) {
	    			   line_item = i.next();
	    			   Product product = productDao.getProduct(line_item.getProduct().getId());
	    			   Item item = new Item(1, product.price, order, product);
	    			   
	    			   userDao.addItemToMyProduct(item);
	    		   }
	    		   
	    		   // create order
		    	   
		    	     	
		    	   
		    	   // remove cart list of user when payment dones
		    	   cartDao.RemoveListItemByUserId(uid);
	    	   } 
	    	   catch (Exception e) {
				e.printStackTrace();
	    	   }

	
	    	   vnp_TransactionStatus = "Success";
	       } 
	       else {
	    	   	vnp_TransactionStatus = "Failed";
	       }
	   } else {
	   		vnp_TransactionStatus = "Invalid signature" + uid;
	   }
	   request.setAttribute("status", vnp_TransactionStatus);
	request.getRequestDispatcher(nextUrl).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(request, response);
	
	}
}
