package com.github.fabriciolfj.domain.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product")
@NamedQuery(name = "Product.findCode", query = "Select p From Product p where p.code = :parCode")
public class Product extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "productSequence",
            allocationSize = 1,
            initialValue = 1,
            sequenceName = "productId_seq"
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productSequence")
    public Long id;

    @Column(length = 40)
    public String description;
    public BigDecimal price;
    public String code;
}
