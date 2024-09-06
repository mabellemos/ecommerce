package com.compasso.ecommerce_app.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.compasso.ecommerce_app.app.dto.product.ProductDTO;
import com.compasso.ecommerce_app.app.dto.product.ProductDisplayDTO;
import com.compasso.ecommerce_app.core.exception.product.ProductException;
import com.compasso.ecommerce_app.core.interfaces.repository.ProductRepository;
import com.compasso.ecommerce_app.core.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    /*@Autowired
    SaleRepository saleRepository;*/

    //camada DTO
    public ProductDTO modelToDTO(Product product, ProductDTO productDTO) {

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setDateExpiration(product.getDateExpiration());
        productDTO.setAmount(product.getAmount());
        productDTO.setIdCategory(product.getCategory().getIdCategory());

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
        productDisplayDTO.setIdCategory(product.getCategory().getIdCategory());
        //productDisplayDTO.setListSale(product.getListSale());

        return productDisplayDTO;
    }

    //buscar lista de produtos
    public List<ProductDTO> buscarTodos(){
        List<Product> listProducts = productRepository.findAll();
        List<ProductDTO> listProdutDTO = new ArrayList<>();

        for (Product product : listProducts) {
            ProductDTO productDTO = new ProductDTO();
            modelToDTO(product, productDTO);
            listProdutDTO.add(productDTO);
        }

        return listProdutDTO;

    }

    //buscar produto por Id
    //vem junto a lista de itens
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

    //salvar um produto
    public String saveProduct(ProductDTO productDTO) {
        Product product = new Product();
        dtoToModel(productDTO, product);
        productRepository.save(product);

        return "Produto salvo com sucesso com id " + product.getId();
    }


    //salvar lista de produtos
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

    //editar um produto
    public String alterProduct(Integer id, ProductDTO productDTO) throws ProductException {
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

    //deletar um produto
    public String deleteProductId(Integer id) {
        productRepository.deleteById(id);
        return "Produto deletado com sucesso";
    }

    /*public List<ReportDTO> report(){
        return saleRepository.getSale();
    }*/
}
