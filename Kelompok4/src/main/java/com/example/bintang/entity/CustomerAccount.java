package com.example.bintang.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "customer_accounts")
public class CustomerAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "foreign_exchange_market_seq")
    @SequenceGenerator(name = "foreign_exchange_market_seq", sequenceName = "foreign_exchange_markets_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Column(name = "account")
    private String account;

    @Column(name = "balance")
    private String balance;

    @Column(name = "type")
    private String type;

    @Transient
    @JsonIgnore
    private String rc;

    @Transient
    @JsonIgnore
    private String rcDesc;
}
