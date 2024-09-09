package com.compasso.ecommerce_app.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.compasso.ecommerce_app.app.dto.address.AddressCepDTO;
import com.compasso.ecommerce_app.app.dto.address.AddressDTO;
import com.compasso.ecommerce_app.app.dto.address.AddressDisplayDTO;
import com.compasso.ecommerce_app.core.exception.address.AddressException;
import com.compasso.ecommerce_app.core.model.Address;
import com.compasso.ecommerce_app.core.repository.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public AddressDTO modelToDTO(Address address, AddressDTO addressDTO){

        addressDTO.setId(address.getId());
        addressDTO.setCep(address.getCep());
        addressDTO.setComplement(address.getComplement());

        return addressDTO;
    }

    public Address dtoToModel(Address address, AddressDTO addressDTO){

        address.setComplement(addressDTO.getComplement());
        address.setCep(addressDTO.getCep());

        return address;
    }

    public Address viaCepToModel(AddressCepDTO addressCepDTO, Address address) {

        address.setDistrict(addressCepDTO.getDistrict());
        address.setState(addressCepDTO.getCity());
        address.setState(addressCepDTO.getState());

        return address;
    }

    public AddressDisplayDTO modelToDTO(AddressDisplayDTO addressDisplayDTO, Address address) {

        addressDisplayDTO.setId(address.getId());
        addressDisplayDTO.setComplement(address.getComplement());
        addressDisplayDTO.setDistrict(address.getDistrict());
        addressDisplayDTO.setState(address.getState());
        addressDisplayDTO.setCep(address.getCep());

        return addressDisplayDTO;
    }

    public AddressCepDTO getCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://viacep.com.br/ws/{cep}/json/";
        Map<String, String> params = new HashMap<String, String>();
        params.put("cep", cep);
        AddressCepDTO addressCepDTO = restTemplate.getForObject(url, AddressCepDTO.class, params);

        return addressCepDTO;
    }

    public String save(AddressDTO addressDTO) {
        Address address = new Address();
        dtoToModel(address, addressDTO);

        AddressCepDTO addressCepDTO = getCep(addressDTO.getCep());
        viaCepToModel(addressCepDTO, address);

        addressRepository.save(address);

        return "Endereço salvo com sucesso com id " + address.getId();
    }

    public AddressDisplayDTO getById(Integer idEndereco) throws AddressException {
        Optional<Address> addressSearch = addressRepository.findById(idEndereco);
        Address address = new Address();

        AddressDisplayDTO addressDisplayDTO = new AddressDisplayDTO();

        if (addressSearch.isPresent()) {
            address = addressSearch.get();

            modelToDTO(addressDisplayDTO,  address);
            return addressDisplayDTO;
        }

        throw new AddressException("Endereço com o id informado não foi encontrado");
    }

    public List<AddressDisplayDTO> getAll() {
        List<Address> listAddressModel = addressRepository.findAll();
        List<AddressDisplayDTO> listAddressDTO = new ArrayList<>();

        for (Address address : listAddressModel) {
            AddressDisplayDTO enderecoExibicaoDTO = new AddressDisplayDTO();
            modelToDTO(enderecoExibicaoDTO, address);
            listAddressDTO.add(enderecoExibicaoDTO);

        }
        return listAddressDTO;

    }

    public String update(Integer id, AddressDisplayDTO addresDisplayDTO) throws AddressException {
        Optional<Address> address = addressRepository.findById(id);

        Address updateAddress = new Address();

        if (address.isPresent()) {
            updateAddress = address.get();

            if(addresDisplayDTO.getComplement() != null) {
                updateAddress.setComplement(addresDisplayDTO.getComplement());
            }

            if(addresDisplayDTO.getDistrict() != null) {
                updateAddress.setDistrict(addresDisplayDTO.getDistrict());
            }

            if(addresDisplayDTO.getState() != null) {
                updateAddress.setState(addresDisplayDTO.getState());
            }

            if(addresDisplayDTO.getCep() != null) {
                updateAddress.setCep(addresDisplayDTO.getCep());
            }

            addressRepository.save(updateAddress);
            return "O endereço com o id " + updateAddress.getId() + " foi atualizado";
        }
        throw new AddressException("O endereco não foi atualizado");
    }

    public void delete(Integer id) {
        addressRepository.deleteById(id);
    }

}