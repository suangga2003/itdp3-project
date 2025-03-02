package com.bjb.bankmanagement.forextransaction.service;

import com.bjb.bankmanagement.forextransaction.constant.ResponseCode;
import com.bjb.bankmanagement.forextransaction.dto.*;
import com.bjb.bankmanagement.forextransaction.entity.ExchangeRates;
import com.bjb.bankmanagement.forextransaction.repository.ExchangeRateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
public class ExchangeRateService {

    @Autowired
    ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${apiKey.forexRateApi}")
    private String apiKeyForexRateApi;

    @Value("${url.forexRateApi}")
    private String forexRateApiUrl;



    public GetExchangeRateDto getExchangeRates(ReqExchangeRateDto request) {
        ExchangeRates data = new ExchangeRates();
        GetExchangeRateDto response = new GetExchangeRateDto();
        Double forexAmount = 0.0;
        String url;

        try {
            url = forexRateApiUrl + apiKeyForexRateApi + "/pair/" + request.getFromCurrencyCode() + "/" + request.getToCurrencyCode() + "/" + request.getAmount();
            var getForexRates = restTemplate.getForEntity(url, ForexRateCompareDto.class).getBody();
            log.info(getForexRates.toString());

            // forex rate data from external api
            if (!ObjectUtils.isEmpty(getForexRates)) {
                response = GetExchangeRateDto.builder()
                        .exchangeRates(ExchangeRateCustom.builder()
                                .fromCurrencyCode(getForexRates.getBaseCode())
                                .toCurrencyCode(getForexRates.getTargetCode())
                                .exchangeRate(getForexRates.getConversionRate())
                                .rateDate(getForexRates.getTimeLastUpdateUtc())
                                .build())
                        .resultAmount(getForexRates.getConversionResult())
                        .build();
            } else {
                // forex rate data from local database
                data = exchangeRateRepository.findByFromCurrencyCodeAndToCurrencyCode(
                        request.getFromCurrencyCode(),
                        request.getToCurrencyCode()
                );

                if (!ObjectUtils.isEmpty(data)) {
                    forexAmount = request.getAmount() * data.getExchangeRate();

                    response = GetExchangeRateDto.builder()
                            .exchangeRates(ExchangeRateCustom.builder()
                                    .fromCurrencyCode(data.getFromCurrencyCode())
                                    .toCurrencyCode(data.getToCurrencyCode())
                                    .exchangeRate(data.getExchangeRate())
                                    .rateDate(data.getRateDate())
                                    .build())
                            .resultAmount(forexAmount)
                            .build();
                }
            }

            response.setRc(ResponseCode.SUCCESS.getCode());
            response.setRcDescription(ObjectUtils.isEmpty(data) ? "Invalid currency code" : "Successfully");
        }catch (HttpClientErrorException | HttpServerErrorException e) {
            // forex rate data from local database
            data = exchangeRateRepository.findByFromCurrencyCodeAndToCurrencyCode(
                    request.getFromCurrencyCode(),
                    request.getToCurrencyCode()
            );

            if (!ObjectUtils.isEmpty(data)) {
                forexAmount = request.getAmount() * data.getExchangeRate();

                response = GetExchangeRateDto.builder()
                        .exchangeRates(ExchangeRateCustom.builder()
                                .fromCurrencyCode(data.getFromCurrencyCode())
                                .toCurrencyCode(data.getToCurrencyCode())
                                .exchangeRate(data.getExchangeRate())
                                .rateDate(data.getRateDate())
                                .build())
                        .resultAmount(forexAmount)
                        .build();
            }

            response.setRc(ResponseCode.SUCCESS.getCode());
            response.setRcDescription(ObjectUtils.isEmpty(data) ? "Invalid currency code" : "Successfully");
        } catch (Exception e) {
            response.setRc(ResponseCode.GENERAL_ERROR.getCode());
            response.setRcDescription("General Error");
            log.error("Error : {}" + e.getMessage(), e);
        }

        return response;
    }
}
