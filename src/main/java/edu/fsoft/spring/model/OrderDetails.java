package edu.fsoft.spring.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "[order_details]")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column
    private int quantity;

    @Column
    private float unitPrice;
}
