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

import com.compasso.ecommerce_app.app.dto.address.AddressCepDTO;
import com.compasso.ecommerce_app.app.dto.address.AddressDTO;
import com.compasso.ecommerce_app.app.dto.address.AddressDisplayDTO;
import com.compasso.ecommerce_app.app.service.AddressService;
import com.compasso.ecommerce_app.core.exception.address.AddressException;


@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping("get-cep/{cep}")
    public ResponseEntity<AddressCepDTO> buscarCep(@PathVariable String cep) {
        return ResponseEntity.ok(addressService.getCep(cep));
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody AddressDTO addressDTO){
        return ResponseEntity.ok(addressService.save(addressDTO));
    }

    @GetMapping("/list")
    public ResponseEntity<List<AddressDisplayDTO>> allList(){
        return ResponseEntity.ok(addressService.getAll());
    }

    @GetMapping("/get/{idAddress}")
    public ResponseEntity<AddressDisplayDTO> getById(@PathVariable Integer id) throws AddressException{
        return ResponseEntity.ok(addressService.getById(id));
    }

    @PutMapping("/update/{idEndereco}")
    public ResponseEntity<String> atualizar(@PathVariable Integer id, @RequestBody AddressDisplayDTO addressDisplayDTO) throws AddressException {
        return ResponseEntity.ok(addressService.update(id, addressDisplayDTO));
    }

    @DeleteMapping("delete/{idEndereco}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        addressService.delete(id);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}