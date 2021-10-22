package com.controller.api;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paypal.CaptureIntent.CreateOrder;
import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/api/order/create")
public class OderCreateAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BufferedReader bufferedReader = request.getReader();
        JSONTokener tokener = new JSONTokener(bufferedReader);
        JSONObject json = new JSONObject(tokener);

        CreateOrder createOrder = new CreateOrder();
        String orderID = createOrder.createOrder(true, json);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("{'id': '" + orderID + "'}");
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
