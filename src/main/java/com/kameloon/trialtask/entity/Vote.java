package com.kameloon.trialtask.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vote_sequence")
    @SequenceGenerator(name = "vote_sequence", sequenceName = "vote_seq", allocationSize = 1)
    private Long id;

    @Column(name = "likes")
    private int likes;

    @Column(name = "dislikes")
    private int dislikes;

}
