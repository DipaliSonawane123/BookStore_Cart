package com.example.cartapplication.controller;

import com.example.cartapplication.dto.CartDto;
import com.example.cartapplication.dto.ResponseDto;
import com.example.cartapplication.model.Cart;
import com.example.cartapplication.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ICartService cartservice;
    /**
     * Post Api for insert card details
     */
    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addBook(@Valid @RequestBody CartDto cartDto) {
        Cart cart = cartservice.addCart(cartDto);
        ResponseDto responseDTO = new ResponseDto("cart details added", cart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    /**
     * Get Api to get All list of cart
     */
    @GetMapping("/getall")
    public ResponseEntity<ResponseDto> GetAllBookDetails() {
        List<Cart> response = cartservice.getall();
        ResponseDto responseDto = new ResponseDto(" All Book with Details", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    /**
     * Get Api for get Al  card details of particular id
     */
    @GetMapping("/get/{Id}")
    public ResponseEntity<ResponseDto> FindById(@PathVariable int Id) {
        Cart response = cartservice.FindById(Id);
        ResponseDto responseDto = new ResponseDto("***All Details of Book using Id***", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    /**
     * Delete particular card details if given id
     */
    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable int Id) {
        cartservice.deleteById(Id);
        ResponseDto reponseDTO = new ResponseDto("** Cart Data deleted successfully ** ", "Id:" + Id + " is deleted");
        return new ResponseEntity(reponseDTO, HttpStatus.ACCEPTED);
    }
    /**
     * Put Api to update particular Data by id
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> editData(@PathVariable int id, @Valid @RequestBody CartDto cardDto) {
        Cart response = cartservice.editById(id, cardDto);
        ResponseDto responseDTO = new ResponseDto("Updated Book Details Successfully", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    /**
     * Post Api to update Quantity
     */
    @PostMapping("/update-qty")
    public ResponseEntity<ResponseDto> changeBookQuantity(@RequestParam int cartId, @RequestParam int quantity) {
        Cart cart = cartservice.changeCartQty(cartId, quantity);
        ResponseDto responseDTO = new ResponseDto("Cart quantity changed successfully", cart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


}
