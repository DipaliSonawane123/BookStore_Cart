package com.example.cartapplication.model;

import com.example.cartapplication.dto.CartDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cartId", nullable = false)
    int cartId;
//    @OneToOne
//    @JoinColumn(name = "userId")
    int userid;

//    @ManyToOne
//    @JoinColumn(name = "id")
    int bookid;
    int quantity;

   public Cart(CartDto cartDto) {
        this.userid=cartDto.getUserid();
        this.bookid= cartDto.getBookId();
        this.quantity=cartDto.getQuantity();
    }
}

