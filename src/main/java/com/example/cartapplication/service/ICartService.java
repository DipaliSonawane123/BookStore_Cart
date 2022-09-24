package com.example.cartapplication.service;

import com.example.cartapplication.dto.CartDto;
import com.example.cartapplication.model.Cart;

import java.util.List;

public interface ICartService {
    Cart addCart(CartDto cartDto);

    List<Cart> getall();

    Cart FindById(int id);

    void deleteById(int id);

    Cart editById(int id, CartDto cardDto);

    Cart changeCartQty(int cardId, int quantity);
}

