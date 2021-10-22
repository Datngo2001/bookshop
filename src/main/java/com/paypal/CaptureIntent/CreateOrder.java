package com.paypal.CaptureIntent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.paypal.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paypal.PayPalClient;
import com.paypal.orders.AddressPortable;
import com.paypal.orders.AmountBreakdown;
import com.paypal.orders.AmountWithBreakdown;
import com.paypal.orders.ApplicationContext;
import com.paypal.orders.Item;
import com.paypal.orders.LinkDescription;
import com.paypal.orders.Money;
import com.paypal.orders.Name;
import com.paypal.orders.Order;
import com.paypal.orders.OrderRequest;
import com.paypal.orders.OrdersCreateRequest;
import com.paypal.orders.PurchaseUnitRequest;
import com.paypal.orders.ShippingDetail;

import org.json.JSONArray;
import org.json.JSONObject;

/*
*
*1. Import the PayPal SDK client that was created in `Set up Server-Side SDK`.
*This step extends the SDK client. It's not mandatory to extend the client, you can also inject it.
*/
public class CreateOrder extends PayPalClient {

  // 2. Set up your server to receive a call from the client
  /**
   * Method to create order
   *
   * @param debug true = print response data
   * @return HttpResponse<Order> response received from API
   * @throws IOException Exceptions from API if any
   */
  public String createOrder(boolean debug, JSONObject json) throws IOException {
    OrdersCreateRequest request = new OrdersCreateRequest();
    request.requestBody(buildRequestBody(json));
    // 3. Call PayPal to set up a transaction
    request.prefer("return=representation");
    HttpResponse<Order> response = client().execute(request);
    if (debug) {
      if (response.statusCode() == 201) {
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Status: " + response.result().status());
        System.out.println("Order ID: " + response.result().id());
        System.out.println("Intent: " + response.result().checkoutPaymentIntent());
        System.out.println("Links: ");
        for (LinkDescription link : response.result().links()) {
          System.out.println("\t" + link.rel() + ": " + link.href() + "\tCall Type: " + link.method());
        }
        System.out
            .println("Total Amount: " + response.result().purchaseUnits().get(0).amountWithBreakdown().currencyCode()
                + " " + response.result().purchaseUnits().get(0).amountWithBreakdown().value());
      }
    }
    return response.result().id();
  }

  /**
   * Method to generate sample create order body with CAPTURE intent
   *
   * @return OrderRequest with created order request
   */
  private OrderRequest buildRequestBody(JSONObject json) {
    OrderRequest orderRequest = new OrderRequest();
    // checkoutPaymentIntent not intent
    orderRequest.checkoutPaymentIntent("CAPTURE");

    ApplicationContext applicationContext = new ApplicationContext().brandName("BookShop INC").landingPage("BILLING")
        .shippingPreference("SET_PROVIDED_ADDRESS");
    orderRequest.applicationContext(applicationContext);

    
    //Problem 
    // không thể convert sang java objec
    List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<PurchaseUnitRequest>();
    JSONArray purchase_units = json.getJSONArray("purchase_units");
    for (int i = 0; i < purchase_units.length(); i++) {
      PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest();
      purchaseUnitRequest.amountWithBreakdown(new AmountWithBreakdown().value("100").currencyCode("USD"));
      //tui thử cách này nhưng các feild của purchaseUnitRequest đều bi null
      purchaseUnitRequests.add(purchaseUnitRequest);
    }
    orderRequest.purchaseUnits(purchaseUnitRequests);
    return orderRequest;
  }
}