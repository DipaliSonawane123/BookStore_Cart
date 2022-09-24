package com.example.cartapplication.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BookData {
    int id;
    String bookName;
    String autherName;
    String  bookDescription;
    String bookImg;
    int price;
    int quantity;
}
