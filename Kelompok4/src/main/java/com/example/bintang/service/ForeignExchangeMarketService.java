package com.example.bintang.service;

import com.example.bintang.entity.Customer;
import com.example.bintang.entity.CustomerAccount;
import com.example.bintang.entity.ForeignExchangeMarket;
import com.example.bintang.repository.ForeignExchangeMarketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForeignExchangeMarketService {
    @Autowired
    ForeignExchangeMarketRepository foreignExchangeMarketRepository;

    public ForeignExchangeMarket getExchangePrice(String currencyTo) {
        return foreignExchangeMarketRepository.findByCurrencyTo(currencyTo);
    }

    public ForeignExchangeMarket addExchangeMarket(ForeignExchangeMarket market) {
        return foreignExchangeMarketRepository.save(market);
    }

    @Transactional
    public ForeignExchangeMarket updateForeignExchangeMarket(ForeignExchangeMarket param) {
        ForeignExchangeMarket response = new ForeignExchangeMarket();

        //get user data
        response = foreignExchangeMarketRepository.findById(param.getId()).get();

        //set user address
        response.setExchangePrice(param.getExchangePrice());
        response.setCurrencyFrom(param.getCurrencyFrom());
        response.setCurrencyTo(param.getCurrencyTo());

        //save to db
        foreignExchangeMarketRepository.save(response);
        return response;
    }

    public ForeignExchangeMarket deleteUserById(String param) {

        try{
            foreignExchangeMarketRepository.deleteById(Integer.valueOf(param));

            if(foreignExchangeMarketRepository.findById(Integer.valueOf(param)).isPresent()){
                ForeignExchangeMarket response = new ForeignExchangeMarket();
                response.setRc("0005");
                response.setRcDesc("No data with user id " + param);
                return response;
            }

            ForeignExchangeMarket response = new ForeignExchangeMarket();
            response.setRc("0000");
            response.setRcDesc("Successfully delete user id");

            return response;

        } catch (Exception e) {
            ForeignExchangeMarket response = new ForeignExchangeMarket();
            response.setRc("0005");
            response.setRcDesc("Failed to delete user id");
            return response;
        }
    }
}
