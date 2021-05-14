package com.github.fabriciolfj.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stock")
public class Stock {

    @Id
    @SequenceGenerator(
            name = "stockSequence",
            allocationSize = 1,
            initialValue = 1,
            sequenceName = "stockId_seq"
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stockSequence")
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "date_mov")
    private LocalDateTime dateMov;
    private Integer exit;
    private Integer entrance;
    private Integer balance;
}
