package com.compasso.ecommerce_app.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compasso.ecommerce_app.app.dto.product.ProductDTO;
import com.compasso.ecommerce_app.app.dto.product.ProductDisplayDTO;
import com.compasso.ecommerce_app.core.exception.product.ProductException;
import com.compasso.ecommerce_app.core.model.Product;
import com.compasso.ecommerce_app.core.repository.CategoryRepository;
import com.compasso.ecommerce_app.core.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;


    public ProductDTO modelToDTO(Product product, ProductDTO productDTO) {

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setDateExpiration(product.getDateExpiration());
        productDTO.setAmount(product.getAmount());
        productDTO.setIdCategory(product.getCategory().getId());

        return productDTO;
    }

    public Product dtoToModel(ProductDTO productDTO, Product product) {

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setDateExpiration(productDTO.getDateExpiration());
        product.setAmount(productDTO.getAmount());

        if(productDTO.getIdCategory() != null) {
            product.setCategory(categoryRepository.findById(productDTO.getIdCategory()).get());
        }

        return product;
    }

    public ProductDisplayDTO modelToDTODisplay(Product product, ProductDisplayDTO productDisplayDTO) {

        productDisplayDTO.setId(product.getId());
        productDisplayDTO.setName(product.getName());
        productDisplayDTO.setDescription(product.getDescription());
        productDisplayDTO.setPrice(product.getPrice());
        productDisplayDTO.setDateExpiration(product.getDateExpiration());
        productDisplayDTO.setAmount(product.getAmount());
        productDisplayDTO.setIdCategory(product.getCategory().getId());

        return productDisplayDTO;
    }

    public List<ProductDTO> getAll(){
        List<Product> listProducts = productRepository.findAll();
        List<ProductDTO> listProdutDTO = new ArrayList<>();

        for (Product product : listProducts) {
            ProductDTO productDTO = new ProductDTO();
            modelToDTO(product, productDTO);
            listProdutDTO.add(productDTO);
        }

        return listProdutDTO;

    }

    public ProductDisplayDTO getById(Integer id) throws ProductException {
        Optional<Product> productSearch = productRepository.findById(id);
        ProductDisplayDTO productDisplayDTO = new ProductDisplayDTO();

        if(productSearch.isPresent()) {
            Product product = productSearch.get();
            modelToDTODisplay(product, productDisplayDTO);
            return productDisplayDTO;
        }

        throw new ProductException("O produto com id informado não foi encontrado.");

    }

    public String saveProduct(ProductDTO productDTO) {
        Product product = new Product();
        dtoToModel(productDTO, product);
        productRepository.save(product);

        return "Produto salvo com sucesso com id " + product.getId();
    }

    public String saveListProducts(List<ProductDTO> listProductsDTO) {
        List<Product> listProducts = new ArrayList<>();

        for(ProductDTO productDTO : listProductsDTO) {
            Product product = new Product();
            dtoToModel(productDTO, product);
            listProducts.add(product);
        }

        productRepository.saveAll(listProducts);

        return "Todos os produtos foram salvos!";
    }

    public String editProduct(Integer id, ProductDTO productDTO) throws ProductException {
        Optional<Product> productSearch = productRepository.findById(id);

        if(productSearch.isPresent()) {
            Product product = productSearch.get();

            if(productDTO.getName() != null) {
                product.setName(productDTO.getName());
            }

            if(productDTO.getDescription() != null) {
                product.setDescription(productDTO.getDescription());
            }

            if(productDTO.getPrice() != 0) {
                product.setPrice(productDTO.getPrice());
            }

            if(productDTO.getDateExpiration()!= null) {
                product.setDateExpiration(productDTO.getDateExpiration());
            }

            if(productDTO.getAmount() != null) {
                product.setAmount(productDTO.getAmount());
            }

            if(productDTO.getIdCategory() != null) {
                product.setCategory(categoryRepository.findById(productDTO.getIdCategory()).get());
            }

            productRepository.save(product);

            return "Produto atualizado com sucesso!";
        }

        throw new ProductException("O id " + productDTO.getId() + " não foi encontrado.");

    }

    public String deleteProduct(Integer id) {
        productRepository.deleteById(id);
        return "Produto deletado com sucesso";
    }

    /*public List<ReportDTO> reports(){
        return saleRepository.getSale();
    }*/
}
