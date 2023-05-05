package com.javarush.jira.bugtracking.internal.model;

import com.javarush.jira.common.HasId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tag")
@Getter
@Setter
public class Tag implements HasId {

    @Setter
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Long id;
}
