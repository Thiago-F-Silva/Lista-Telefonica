package com.company.listaTelefonica.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.company.listaTelefonica.dto.PhoneBookDTO;
import com.company.listaTelefonica.model.PhoneBook;
import com.company.listaTelefonica.repository.PhoneBookRepository;

@Service
public class PhoneBookService {
    //
    @Autowired
    private PhoneBookRepository phoneBookRepository;

    public PhoneBook addPhone(PhoneBookDTO phoneBookDTO) {
        
        PhoneBook phoneBook = new PhoneBook();
        BeanUtils.copyProperties(phoneBookDTO, phoneBook);
        phoneBook = phoneBookRepository.save(phoneBook);

        return phoneBook;
    }

    public Boolean deletePhone(Long id){

        Optional<PhoneBook> phone = phoneBookRepository.findById(id);
        if (phone.isEmpty()) {
            return false;
        }
        phoneBookRepository.deleteById(id);
        return true;
        
    }

    public ResponseEntity<Object> updatePhone(Long id, PhoneBookDTO phoneBookDTO){
        Optional<PhoneBook> phone = phoneBookRepository.findById(id);
        if (phone.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Phone number doesn't exist");
        }

        PhoneBook phoneBook = phone.get();
        BeanUtils.copyProperties(phoneBookDTO, phoneBook);
        phoneBook = phoneBookRepository.save(phoneBook);

        return ResponseEntity.status(HttpStatus.OK).body(phoneBook);
    }

}
