package br.com.meli.cardpaymentbrick.controller;

import br.com.meli.cardpaymentbrick.dtos.PaymentDto;
import br.com.meli.cardpaymentbrick.dtos.ResponsePaymentDto;
import br.com.meli.cardpaymentbrick.service.CardPaymentBrickService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:63342")
@RequestMapping
public class CardPaymentBrickController {

  @Autowired
  private CardPaymentBrickService cardPaymentBrickService;

  @PostMapping("/process_payment")
  ResponseEntity<ResponsePaymentDto> CardPaymentBrickRequest(@RequestBody @Valid PaymentDto paymentDto){
    ResponsePaymentDto payment = cardPaymentBrickService.responsePaymentBrick(paymentDto);
    return ResponseEntity.status(HttpStatus.OK).body(payment);
  }
}
