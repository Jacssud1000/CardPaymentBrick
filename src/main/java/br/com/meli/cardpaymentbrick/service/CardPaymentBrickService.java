package br.com.meli.cardpaymentbrick.service;

import br.com.meli.cardpaymentbrick.dtos.PaymentDto;
import br.com.meli.cardpaymentbrick.dtos.ResponsePaymentDto;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class CardPaymentBrickService {

  public ResponsePaymentDto responsePaymentBrick(PaymentDto paymentDto){
    try{
      Map<String, String> customHeaders = new HashMap<>();
      customHeaders.put("x-idempotency-key", UUID.randomUUID().toString());

      MPRequestOptions requestOptions = MPRequestOptions.builder()
          .customHeaders(customHeaders)
          .accessToken("APP_USR-5879859661431869-050816-63fe2574c50c3369bae624b038a5c0c4-1259153998")
          .build();

      MercadoPagoConfig.setAccessToken("APP_USR-5879859661431869-050816-63fe2574c50c3369bae624b038a5c0c4-1259153998");

      PaymentClient client = new PaymentClient();

      PaymentCreateRequest paymentCreateRequest =
          PaymentCreateRequest.builder()
              .transactionAmount(paymentDto.getTransaction_amount())
              .token(paymentDto.getToken())
              .installments(paymentDto.getInstallments())
              .paymentMethodId(paymentDto.getPayment_method_id())
              .payer(
                  PaymentPayerRequest.builder()
                      .email(paymentDto.getPayer().getEmail())
                      .identification(
                          IdentificationRequest.builder()
                              .type(paymentDto.getPayer().getIdentification().getType())
                              .number(paymentDto.getPayer().getIdentification().getNumber())
                              .build())
                      .build())
              .build();

      Payment createdPayment = client.create(paymentCreateRequest, requestOptions);

      return new ResponsePaymentDto(
          createdPayment.getStatus(),
          createdPayment.getStatusDetail(),
          createdPayment.getId(),
          createdPayment.getDateApproved()
      );

    } catch (MPException | MPApiException e) {
      throw new RuntimeException(e);
    }
  }
}