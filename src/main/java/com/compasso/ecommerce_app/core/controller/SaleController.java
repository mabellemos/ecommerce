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

import com.compasso.ecommerce_app.app.dto.sale.SaleDTO;
import com.compasso.ecommerce_app.app.service.SaleService;
import com.compasso.ecommerce_app.core.exception.EmailException;
import com.compasso.ecommerce_app.core.exception.sale.SaleException;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    SaleService saleService;

    @GetMapping("/list")
    public ResponseEntity<List<SaleDTO>> allList(){
        return ResponseEntity.ok(saleService.allList());
    }

    @GetMapping("/get/{invoice}")
    public ResponseEntity<List<SaleDTO>> getByInvoice(@PathVariable String invoive) throws SaleException {
        return ResponseEntity.ok(saleService.getByInvoice(invoive));
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveSale(@RequestBody SaleDTO saleDTO) throws SaleException, EmailException, MessagingException{
        return ResponseEntity.ok(saleService.save(saleDTO));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editSale(@PathVariable Integer id, @RequestBody SaleDTO saleDTO) throws SaleException{
        return ResponseEntity.ok(saleService.editSale(id, saleDTO));
    }

    @DeleteMapping("delete/{idSale}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        saleService.delete(id);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}