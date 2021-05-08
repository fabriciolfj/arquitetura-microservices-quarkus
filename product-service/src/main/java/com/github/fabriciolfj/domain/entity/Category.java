package com.github.fabriciolfj.domain.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "category")
@Entity
@NamedQuery(name = "Category.findDescription", query = "Select p From Category p where p.description = :parDescription")
public class Category extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "categorySequence",
            allocationSize = 1,
            initialValue = 1,
            sequenceName = "categoryId_seq"
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorySequence")
    public Long id;

    @Column(name = "description", length = 40, nullable = false)
    public String description;

    @OneToMany(mappedBy = "category")
    public List<Product> products;
}
