package com.sermaluc.sermalucbcichallenge.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phone")
public class Phone {
  @Id
  @GeneratedValue
  @Column(name = "phone_id", nullable = false)
  private UUID phoneId;
  private Integer number;
  private Byte citycode;
  private Short countrycode;

}