package com.kameloon.trialtask.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "quotes")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quote_sequence")
    @SequenceGenerator(name = "quote_sequence", sequenceName = "quote_seq", allocationSize = 1)
    private Long id;

    private String content;
    private LocalDateTime dateOfCreation;
    private LocalDateTime dateOfUpdate;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "votes_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Vote votes;

}
