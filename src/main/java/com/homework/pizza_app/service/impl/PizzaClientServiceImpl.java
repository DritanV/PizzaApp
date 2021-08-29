/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.homework.pizza_app.service.impl;

import com.homework.pizza_app.service.PizzaClientService;
import com.homework.pizza_app.service.impl.dtoexternalservice.BearerToken;
import com.homework.pizza_app.service.impl.dtoexternalservice.DeleteOrder;
import com.homework.pizza_app.service.impl.dtoexternalservice.ReceivedOrder;
import com.homework.pizza_app.service.impl.dtoexternalservice.SendOrder;
import com.homework.pizza_app.service.impl.dtoexternalservice.UserPass;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author dritan
 */
//@Component
@Service
public class PizzaClientServiceImpl implements PizzaClientService {

    public static String https_url = "https://order-pizza-api.herokuapp.com/api";

    private String token;

    public static void main(String[] args) {
        PizzaClientServiceImpl pizza = new PizzaClientServiceImpl();
        pizza.authenticate();
        pizza.getOrders();
        System.out.println(pizza.token);
    //  pizza.makeOrder(new SendOrder("THIN", "HAWAII", "L", 3));
        pizza.makeOrder(new SendOrder("Thin", "HAWAII", "L", 10003));
    //  pizza.deleteOrder(new DeleteOrder(2));

    }

    @Override
    public synchronized void authenticate() {
        if (token != null) {
            return;
        }

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserPass> request = new HttpEntity<>(new UserPass("test", "test"), headers);
        BearerToken response = restTemplate.postForObject(PizzaClientServiceImpl.https_url + "/auth", request, BearerToken.class);
        this.token = response.access_token;
        System.out.println(token);
    }

    @Override
    public void getOrders() {
        this.authenticate();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> response = restTemplate.getForEntity(PizzaClientServiceImpl.https_url + "/orders", String.class);
        System.out.println(response);
    }

    @Override
    public ReceivedOrder makeOrder(SendOrder order) {
        this.authenticate();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + this.token);
        HttpEntity<SendOrder> request = new HttpEntity<>(order, headers);
        ReceivedOrder receivedOrder = restTemplate.postForObject(PizzaClientServiceImpl.https_url + "/orders", request, ReceivedOrder.class);
        return receivedOrder;

    }

    @Override
    public void deleteOrder(DeleteOrder deleteOrder) {
        this.authenticate();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + this.token);
        HttpEntity<DeleteOrder> request = new HttpEntity<>(headers);

        restTemplate.delete(PizzaClientServiceImpl.https_url + "/orders/" + deleteOrder.Order_ID, request, String.class);

    }

}
