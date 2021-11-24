package com.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DTOs.BusinessDtos.UploadKeyDTO;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.google.gson.Gson;

@WebServlet("/service/uploadSignature")
public class CloudinaryAuthController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String apiKey = "";
        String cloud_name = "";
        String api_secret = "";

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", cloud_name, "api_key", apiKey,
                "api_secret", api_secret, "secure", true));

        String type = request.getParameter("type");
        String folder = "";

        if (type.equals("photo")) {
            folder = "bookshop/product/photos";
        } else if (type.equals("video")) {
            folder = "bookshop/product/videos";
        } else if (type.equals("file")) {
            folder = "bookshop/product/files";
        }

        Long timestamp = System.currentTimeMillis() / 1000;

        Map<String, Object> paramsToSign = new HashMap<String, Object>();
        paramsToSign.put("timestamp", timestamp);
        paramsToSign.put("source", "uw");
        paramsToSign.put("folder", folder);

        String signature = cloudinary.apiSignRequest(paramsToSign, api_secret);

        // Write upload key in json format
        UploadKeyDTO uploadKeyDTO = new UploadKeyDTO(apiKey, cloud_name, folder, signature, timestamp);
        Gson gson = new Gson();
        String json = gson.toJson(uploadKeyDTO);
        response.getWriter().write(json);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
