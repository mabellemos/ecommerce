package com.compasso.ecommerce_app.core.interfaces.services.controller;
import java.util.List;

import com.compasso.ecommerce_app.app.dto.product.ProductDTO;
import com.compasso.ecommerce_app.app.dto.product.ProductDisplayDTO;
import com.compasso.ecommerce_app.app.dto.report.ReportDTO;
import com.compasso.ecommerce_app.app.service.ProductService;
import com.compasso.ecommerce_app.core.exception.product.ProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

        @Autowired
        ProductService productService;

        @GetMapping("/list-product")
        public ResponseEntity<List<ProductDTO>> getAll(){
            return ResponseEntity.ok(productService.getAll());
        }

        @GetMapping("/get/{idProduct}")
        public ResponseEntity<ProductDisplayDTO> getById(@PathVariable Integer id) throws ProductException {
            return ResponseEntity.ok(productService.getById(id));
        }

        @PostMapping("/save")
        public ResponseEntity<String> saveProduct(@RequestBody ProductDTO productDTO){
            return ResponseEntity.ok(productService.saveProduct(productDTO));
        }

        @PostMapping("/save-list")
        public ResponseEntity<String>saveListProducts(@RequestBody List<ProductDTO> listProductDTO){
            return ResponseEntity.ok(productService.saveListProducts(listProductDTO));

        }

        @PutMapping("/alter/{idProduct}")
        public ResponseEntity<String> alterProductById(@PathVariable Integer id, @RequestBody ProductDTO productDTO) throws ProductException{
            return ResponseEntity.ok(productService.editProduct(id, productDTO));
        }

        @DeleteMapping("/delete/{idProduct}")
        public ResponseEntity<String> deleteProductById(@PathVariable Integer id){
            return ResponseEntity.ok(productService.deleteProduct(id));
        }

        /*@GetMapping("/report")
        public ResponseEntity<List<ReportDTO>> reports(){
            return ResponseEntity.ok(productService.reports());
        }*/

}