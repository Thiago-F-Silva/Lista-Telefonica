package com.company.listaTelefonica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.listaTelefonica.dto.PhoneBookDTO;
import com.company.listaTelefonica.model.PhoneBook;
import com.company.listaTelefonica.repository.PhoneBookRepository;
import com.company.listaTelefonica.service.PhoneBookService;

@RestController
@RequestMapping("/phone_book")
public class PhoneBookController {
    
    @Autowired
    private PhoneBookRepository phoneBookRepository;

    @Autowired
    private PhoneBookService phoneBookService;

    @PostMapping("/add")
    public ResponseEntity<PhoneBook> addPhoneBook(@RequestBody PhoneBookDTO phoneBookDTO){
        PhoneBook phoneBook = phoneBookService.addPhone(phoneBookDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(phoneBook);
    }

    @GetMapping
    public ResponseEntity<List<PhoneBook>> getPhoneBooks(){
        List<PhoneBook> phoneBooks = phoneBookRepository.findAll();
        
        return ResponseEntity.status(HttpStatus.OK).body(phoneBooks);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePhoneBook(@PathVariable Long id){
        Boolean response = phoneBookService.deletePhone(id);

        if (response) {
            return ResponseEntity.status(HttpStatus.OK).body("Phone number deleted successfully!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Phone number doesn't exist");
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Object> updatePhoneBook(@PathVariable Long id, PhoneBookDTO phoneBookDTO){
        ResponseEntity<Object> response = phoneBookService.updatePhone(id, phoneBookDTO);
        
        return response;
    }



}
