package com.example.demo.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "teams")
// @Data // Descomentar si usas Lombok
// @NoArgsConstructor // Descomentar si usas Lombok
// @AllArgsConstructor // Descomentar si usas Lombok
public class Teams implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", length = 4, nullable = false)
    private String id;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "number_of_players", nullable = false)
    private Integer numberOfPlayers;

    @Column(name = "discount")
    private Integer discount;

    // Mantén estos constructores y getters/setters/toString si NO USAS Lombok.
    // Si USAS Lombok, puedes eliminarlos.
    public Teams() { super(); }
    public Teams(String id, String name, Integer numberOfPlayers, Integer discount) {
        super();
        this.id = id;
        this.name = name;
        this.numberOfPlayers = numberOfPlayers;
        this.discount = discount;
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getNumberOfPlayers() { return numberOfPlayers; }
    public void setNumberOfPlayers(Integer numberOfPlayers) { this.numberOfPlayers = numberOfPlayers; }
    public Integer getDiscount() { return discount; }
    public void setDiscount(Integer discount) { this.discount = discount; }
    @Override
    public String toString() {
        return "Teams [id=" + id + ", name=" + name + ", numberOfPlayers=" + numberOfPlayers + ", discount=" + discount + "]";
    }
}