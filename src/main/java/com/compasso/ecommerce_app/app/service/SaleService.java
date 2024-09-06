package com.compasso.ecommerce_app.app.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.mail.MessagingException;

import com.compasso.ecommerce_app.app.dto.product.ProductDTO;
import com.compasso.ecommerce_app.app.dto.sale.SaleDTO;
import com.compasso.ecommerce_app.app.dto.sale.SaleProductDTO;
import com.compasso.ecommerce_app.core.exception.EmailException;
import com.compasso.ecommerce_app.core.exception.sale.SaleException;
import com.compasso.ecommerce_app.core.interfaces.repository.CustomerRepository;
import com.compasso.ecommerce_app.core.interfaces.repository.ProductRepository;
import com.compasso.ecommerce_app.core.interfaces.repository.SaleRepository;
import com.compasso.ecommerce_app.core.model.Product;
import com.compasso.ecommerce_app.core.model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Autowired
    EmailService emailService;

    //camada DTO
    public SaleDTO modelToDTO(Sale sale, SaleDTO saleDTO) {

        saleDTO.setId(sale.getId());
        saleDTO.setInvoice(sale.getInvoice());

        List<Sale> listSale= saleRepository.findAll();
        List<Sale> listInvoice = saleRepository.findByInvoice(saleDTO.getInvoice(), listSale);
        List<SaleProductDTO> listProduct = new ArrayList<>();

        for (Sale sale2 : listInvoice) {
            SaleProductDTO buyDTO = new SaleProductDTO();

            buyDTO.setAmount(sale2.getAmount());
            buyDTO.setAmount(sale2.getAmount());
            buyDTO.setIdProduct(sale2.getProduct().getId());

            listProduct.add(buyDTO);

        }

        saleDTO.setListProduct(listProduct);
        return saleDTO;

    }

    /*public Sale DTOToModel(SaleDTO saleDTO, Sale sale) throws SaleException {

        sale.setInvoice(saleDTO.getInvoice());

        if(saleDTO.getIdCustomer() != null) {

            sale.setCustomer(customerRepository.findById(saleDTO.getIdCustomer()).get());
        }

        return sale;
    }*/

    //buscar lista de vendas
    public List<SaleDTO> allList(){
        List<Sale> listSale = saleRepository.findAll();
        List<SaleDTO> listSaleDTO = new ArrayList<>();

        for (Sale sale : listSale) {
            SaleDTO saleDTO = new SaleDTO();
            modelToDTO(sale, saleDTO);

            listSaleDTO.add(saleDTO);
        }

        return listSaleDTO;

    }

    //buscar por nota fiscal
    public List<SaleDTO> getByInvoice(String invoice) throws SaleException{

        List<Sale> listSale = saleRepository.findAll();
        List<Sale> listInvoice = saleRepository.findByInvoice(invoice, listSale);
        List<SaleDTO> listSaleDTO = new ArrayList<>();

        if(listInvoice.isEmpty()) {
            throw new SaleException("Nota fiscal não encontrada!");
        }

        for (Sale sale : listInvoice) {
            SaleDTO saleDTO = new SaleDTO();
            modelToDTO(sale, saleDTO);

            listSaleDTO.add(saleDTO);
        }

        return listSaleDTO;

    }

    public void missingProduct(SaleDTO saleDTO)  {
        saleDTO.getListProduct().forEach(product -> {

            Product productEstoque = productRepository.findById(product.getIdProduct()).get();

            productEstoque.setAmount(productEstoque.getAmount() - product.getAmount());
            productRepository.save(productEstoque);

            if(productEstoque.getAmount() < 2) {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setName(productEstoque.getName());

                try {
                    emailService.emailProductStock(productDTO);
                } catch (EmailException e) {

                    e.printStackTrace();
                } catch (MessagingException e) {

                    e.printStackTrace();
                }

            }
        });

    }

    //salvar uma venda
    public String save(SaleDTO saleDTO) throws SaleException, EmailException, MessagingException {

        for (SaleProductDTO saleProductDTO : saleDTO.getListProduct()) {

            Sale sale = new Sale();
            sale.setProduto(productRepository.findById(saleProductDTO.getIdProduct()).get());
            sale.setValue(saleProductDTO.getValue());
            sale.setAmount(saleProductDTO.getAmount());

            if(saleProductDTO.getAmount() > sale.getProduct().getAmount()) {
                throw new SaleException("Falta de estoque!");
            }

            missingProduct(saleDTO);

            //DTOToModel(saleDTO, sale);
            saleRepository.save(sale);

        }

        emailService.sendEmail(saleDTO);
        return "Venda salva com sucesso!";

    }

    //editar uma movimentação
    public String editSale(Integer id, SaleDTO saleDTO) throws SaleException {
        Optional<Sale> saleSearch = saleRepository.findById(id);

        if(saleSearch.isPresent()) {
            Sale sale =  saleSearch.get();

            /*if(saleDTO.getIdCustomer() != null) {
                sale.setCustomer(customerRepository.findById(saleDTO.getIdCustomer()).get());
            }*/

            if(saleDTO.getInvoice() != null) {
                sale.setInvoice(saleDTO.getInvoice());
            }

            if(saleDTO.getListProduct() != null) {
                for (SaleProductDTO saleProductDTO : saleDTO.getListProduct()) {
                    sale.setProduto(productRepository.findById(saleProductDTO.getIdProduct()).get());
                    sale.setValue(saleProductDTO.getValue());

                    sale.setAmount(saleProductDTO.getAmount());
                }
            }

            saleRepository.save(sale);

            return "Categoria atualizada com sucesso!";
        }

        throw new SaleException("O id " + saleDTO.getId() + " não foi encontrado.");

    }

    //deletar venda
    public String delete(Integer id) {
       saleRepository.deleteById(id);
        return "Venda deletada com sucesso!";
    }

}