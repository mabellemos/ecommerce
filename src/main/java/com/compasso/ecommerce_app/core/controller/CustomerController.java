package com.compasso.ecommerce_app.core.controller;

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

import com.compasso.ecommerce_app.app.dto.customer.CustomerDTO;
import com.compasso.ecommerce_app.app.dto.customer.CustomerDisplayDTO;
import com.compasso.ecommerce_app.app.service.CustomerService;
import com.compasso.ecommerce_app.core.exception.customer.CustomerException;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok(customerService.save(customerDTO));

    }

    @GetMapping("/get/{idCustomer}")
    public ResponseEntity<CustomerDisplayDTO> getById(@PathVariable Integer id) throws CustomerException {
        return ResponseEntity.ok(customerService.getById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<CustomerDTO>> listAll(){
        return ResponseEntity.ok(customerService.getAll());
    }

    @PutMapping("/update/{idCustomer}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody CustomerDTO customerDTO) throws CustomerException{
        return ResponseEntity.ok(customerService.update(id, customerDTO));
    }

    @PostMapping("/save-list")
    public ResponseEntity<Void> saveList(@RequestBody List<CustomerDTO> listCustomerDTO){
        customerService.saveAllList(listCustomerDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{idCustomer}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        customerService.delete(id);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}