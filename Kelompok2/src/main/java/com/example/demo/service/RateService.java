package com.example.demo.service;

import com.example.demo.entity.Rate;
import com.example.demo.model.ResponRateUpdateDTO;
import com.example.demo.model.UpdateRateDTO;
import com.example.demo.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RateService {
    @Autowired
    private RateRepository rateRepository;

    public Rate createRate(Rate request) {

        Rate response = request;
        rateRepository.save(response);
        return response;
    }

    public ResponRateUpdateDTO updateRate(UpdateRateDTO request){
        Rate response = new Rate();
        response = rateRepository.findByMataUangAsalAndMataUangTujuan(request.getMataUangAsal(),request.getMataUangTujuan());

        response.setKurs(request.getAmount());
        rateRepository.save(response);
        ResponRateUpdateDTO responRateUpdateDTO = new ResponRateUpdateDTO();
        responRateUpdateDTO.setKurs(response.getKurs());
        responRateUpdateDTO.setMataUangAsal(response.getMataUangAsal());
        responRateUpdateDTO.setMataUangTujuan(response.getMataUangTujuan());

        return responRateUpdateDTO;
    }

    public Double getRateById(Integer request) {
        try {
            Rate response = new Rate();
            response = rateRepository.findById(request).get();

            response.setRc("0000");
            response.setRcDesc("Success");

            return response.getKurs();

        } catch (Exception e){
            Rate response = new Rate();

            response.setRc("4444");
            response.setRcDesc("ID Currency tidak dikenal!");

            return response.getKurs();
        }
    }

}
