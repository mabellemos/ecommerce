package com.compasso.ecommerce_app.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.compasso.ecommerce_app.app.dto.customer.CustomerDTO;
import com.compasso.ecommerce_app.app.dto.customer.CustomerDisplayDTO;
import com.compasso.ecommerce_app.core.exception.customer.CustomerException;
import com.compasso.ecommerce_app.core.interfaces.repository.CustomerRepository;
import com.compasso.ecommerce_app.core.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    // Model para DTO
    private CustomerDTO modelParaDTO(Customer customer, CustomerDTO customerDTO) {
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        //customerDTO.setUserCustomer(customer.getUserCustomer());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setCpf(customer.getCpf());
        customerDTO.setDateNasc(customer.getDateNasc());
        customerDTO.setTelephone(customer.getTelephone());

        return customerDTO;
    }

    // DTO para Model
    private Customer dtoForModel(Customer customer, CustomerDTO customerDTO) {
        customer.setName(customerDTO.getName());
        //customer.setUserCustomer(customerDTO.getUserCustomer());
        customer.setEmail(customerDTO.getEmail());
        customer.setCpf(customerDTO.getCpf());
        customer.setDateNasc(customerDTO.getDateNasc());
        customer.setTelephone(customerDTO.getTelephone());

        return customer;
    }

    // Model Para Exibicao
    private CustomerDisplayDTO modelForDisplay(CustomerDisplayDTO displayCustomer, Customer customer) {

        displayCustomer.setId(customer.getId());
        displayCustomer.setName(customer.getName());
        //displayCustomer.setUserCustomer(customer.getUserCustomer());
        displayCustomer.setEmail(customer.getEmail());
        displayCustomer.setCpf(customer.getCpf());
        displayCustomer.setDateNasc(customer.getDateNasc());
        displayCustomer.setTelephone(customer.getTelephone());
        displayCustomer.setListAddress(customer.getListAddress());
        //displayCustomer.setListOrder(customer.getListOrder());

        return displayCustomer;
    }

    // Salvar
    public String save(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        Customer customerSave = dtoForModel(customer, customerDTO);
        customerRepository.save(customerSave);

        return "O cliente foi criado com o id: " + customer.getId();
    }

    // Salvar Varios
    public void saveAllList(List<CustomerDTO> list) {
        List<Customer> listCustomer = new ArrayList<>();

        for (CustomerDTO customerDTO : list) {
            Customer customer = new Customer();
            dtoForModel(customer, customerDTO);
            listCustomer.add(customer);
        }

        customerRepository.saveAll(listCustomer);
    }

    // Buscar Por Id
    public CustomerDisplayDTO getById(Integer id) throws CustomerException {
        Optional<Customer> customer = customerRepository.findById(id);

        Customer customerData = new Customer();

        CustomerDisplayDTO displayDTO = new CustomerDisplayDTO();

        if (customer.isPresent()) {
            customerData = customer.get();
            displayDTO = modelForDisplay(displayDTO, customerData);

            return displayDTO;
        }

        throw new CustomerException("Cliente não encontrado");
    }

    // Buscar Todos
    public List<CustomerDTO> getAll() {
        List<Customer> listCustomer = customerRepository.findAll();

        List<CustomerDTO> listCustomerDTO = new ArrayList<>();

        for (Customer customer : listCustomer) {

            CustomerDTO customerDTO = new CustomerDTO();

            modelParaDTO(customer, customerDTO);
            listCustomerDTO.add(customerDTO);
        }

        return listCustomerDTO;
    }

    // Atualizar
    public String update(Integer id, CustomerDTO customerDTO) throws CustomerException {

        Optional<Customer> customer = customerRepository.findById(id);
        Customer customerDate = new Customer();

        if (customer.isPresent()) {
            customerDate = customer.get();

            if (customerDTO.getName() != null) {
                customerDate.setName(customerDTO.getName());
            }
            /*if (customerDTO.getUserCustomer() != null) {
                customerDate.setUserCustomer(customerDTO.getUsersCustomer());
            }*/
            if (customerDTO.getEmail() != null) {
                customerDate.setEmail(customerDTO.getEmail());
            }
            if (customerDTO.getCpf() != null) {
                customerDate.setCpf(customerDTO.getCpf());
            }
            if (customerDTO.getDateNasc() != null) {
                customerDate.setDateNasc(customerDTO.getDateNasc());
            }
            if (customerDTO.getTelephone() != null) {
                customerDate.setTelephone(customerDTO.getTelephone());
            }

            customerRepository.save(customerDate);

            return "O cliente com o id " + customerDate.getId() + " foi atualizado";
        }
        throw new CustomerException("O cliente nao foi atualizado");
    }

    // Deletar
    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }

}