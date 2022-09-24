package com.example.cartapplication.service;

import com.example.cartapplication.dto.CartDto;

import com.example.cartapplication.exception.CartException;
import com.example.cartapplication.model.BookData;
import com.example.cartapplication.model.Cart;
import com.example.cartapplication.model.UserData;
import com.example.cartapplication.repo.CartRepo;
import com.example.cartapplication.util.EmailSenderService;
import com.example.cartapplication.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    CartRepo cartRepo;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    EmailSenderService emailSender;
    @Autowired
    RestTemplate restTemplate;

//    @Autowired
//    UserRepo userRepo;
//    @Autowired
//    BookRepo bookRepo;

    @Override
    public Cart addCart(CartDto cartDto) {
        UserData userDetails = restTemplate.getForObject("http://localhost:8081/user/gets/"+cartDto.getUserid(), UserData.class);
        System.out.println(userDetails);
        System.out.println(userDetails.getEmail());
        BookData bookDetails = restTemplate.getForObject("http://localhost:8082/book/gets/"+cartDto.getBookId(), BookData.class);
        System.out.println(bookDetails);
        if (userDetails.equals(null) &&bookDetails.equals(null)) {
          throw new CartException(" userid and bookid is invalid");
      }
            Cart cartDetails = new Cart(cartDto);
            cartRepo.save(cartDetails);
            emailSender.sendEmail(userDetails.getEmail(), "Added Your Details", ""+cartDetails )  ;
            return cartDetails;

    }

    @Override
    public List<Cart> getall() {
        List<Cart> cart = cartRepo.findAll();
        return cart;
    }

    @Override
    public Cart FindById(int id) {
        Optional<Cart> cart = cartRepo.findById(id);
        if(cart.isPresent())
        return cart.get();
        else
            throw new CartException("Id is not present");
    }

    @Override
    public void deleteById(int id) {
        Optional<Cart> findById = cartRepo.findById(id);
        if (findById.isPresent()){
            cartRepo.deleteById(id);
        } else throw new CartException("Id:"+id+" not present");

    }
    @Override
    public Cart editById(int id, CartDto cartDto) {
        UserData userDetails = restTemplate.getForObject("http://localhost:8081/user/gets/"+cartDto.getUserid(), UserData.class);
        System.out.println(userDetails);
        BookData bookDetails = restTemplate.getForObject("http://localhost:8082/book/gets/"+cartDto.getBookId(), BookData.class);
        System.out.println(bookDetails);
        Cart editdata = cartRepo.findById(id).orElse(null);
        if (userDetails.equals(null)&&bookDetails.equals(null)&&editdata.equals(null)) {
            throw new CartException(" id is invalid");
        }else
            editdata.setBookid(cartDto.getBookId());
            editdata.setUserid(cartDto.getUserid());
        editdata.setQuantity(cartDto.getQuantity());
        return cartRepo.save(editdata);
    }

    @Override
    public Cart changeCartQty(int cartId, int quantity) {
        Cart cart = cartRepo.findById(cartId).orElse(null);
        if(cart == null){
            throw new CartException("id is not found");
        }
        cart.setQuantity(quantity);
        return cartRepo.save(cart);
    }
}

