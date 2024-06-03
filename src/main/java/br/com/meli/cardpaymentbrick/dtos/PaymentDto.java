package br.com.meli.cardpaymentbrick.dtos;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class PaymentDto {

  private BigDecimal transaction_amount;

  private String token;

  private int installments;

  private String payment_method_id;

  private int issuer_id;

  private Payer payer;
}
