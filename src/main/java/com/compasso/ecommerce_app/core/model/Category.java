package com.compasso.ecommerce_app.core.model;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="categoria")
public class Category implements Serializable{

        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        @Column(name="id")
        private Integer id;

        @Column(name="name")
        @NotNull
        private String name;

        @Column(name="description")
        @NotNull
        private String description;

        @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
        private List<Product> listProduct;

        public Category() {}

        public List<Product> getListProduct() {
            return listProduct;
        }

        public void setListProduct(List<Product> listProduct) {
            this.listProduct = listProduct;
        }

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
}
