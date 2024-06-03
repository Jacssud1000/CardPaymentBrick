package br.com.meli.cardpaymentbrick.dtos;

import java.time.OffsetDateTime;
import lombok.Data;

@Data
public class ResponsePaymentDto {

  private String status;
  private String status_detail;
  private Long id;
  private OffsetDateTime date_approved;

  public ResponsePaymentDto(String status, String status_detail, Long id, OffsetDateTime date_approved) {
    this.status = status;
    this.status_detail = status_detail;
    this.id = id;
    this.date_approved = date_approved;
  }
}
