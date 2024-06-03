package br.com.meli.cardpaymentbrick.dtos;

import lombok.Data;

@Data
public class Payer {

  private String email;
  private Identification identification;
}
