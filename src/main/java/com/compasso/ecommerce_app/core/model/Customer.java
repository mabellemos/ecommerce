package com.compasso.ecommerce_app.core.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	@NotNull
	private String name;

	/*@Column(name = "userCustomer")
	@NotNull
	private String userCustomer;*/

	@Column(name = "email")
	@NotNull
	private String email;

	@Column(name = "cpf")
	@NotNull
	private String cpf;

	@Column(name = "dateNasc")
	@NotNull
	private LocalDate dateNasc;

	@Column(name = "telephone")
	@NotNull
	private String telephone;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
	private List<Address> listAddress;

	/*@OneToMany(mappedBy="customer", cascade = CascadeType.REMOVE)
	private List<Movimentacao> listOrder;*/

	// Constructor
	public Customer() {
	}

	// Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public String getUserCustomer() {
		return userCustomer;
	}*/

	/*public void setUserCustomer(String userCustomer) {
		this.userCustomer = userCustomer;
	}*/

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDateNasc() {
		return dateNasc;
	}

	public void setDateNasc(LocalDate dateNasc) {
		this.dateNasc = dateNasc;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/*public String getTelefoneSec() {
		return telefoneSec;
	}*/

	/*public void setTelefoneSec(String telefoneSec) {
		this.telefoneSec = telefoneSec;
	}*/

	public List<Address> getListAddress() {
		return listAddress;
	}

	public void setListAddress(List<Address> listAddress) {
		this.listAddress = listAddress;
	}

	/*public List<Movimentacao> getListaPedidos() {
		return listaPedidos;
	}*/

	/*public void setListaPedidos(List<Movimentacao> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}*/

}