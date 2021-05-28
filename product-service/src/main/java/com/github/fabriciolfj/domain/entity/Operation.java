package com.github.fabriciolfj.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "operation")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operation {

    @Id
    @SequenceGenerator(
            name = "operationSequence",
            allocationSize = 1,
            initialValue = 1,
            sequenceName = "operationId_seq"
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operationSequence")
    private Long id;
    @Column(name = "date_mov")
    private LocalDateTime dateMov;
    @Enumerated(EnumType.STRING)
    private TypeOperation type;
    private Long qtde;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}