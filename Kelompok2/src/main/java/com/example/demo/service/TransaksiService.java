package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Rate;
import com.example.demo.entity.Transaksi;
import com.example.demo.model.GetRateDTO;
import com.example.demo.model.GetTransferDTO;
import com.example.demo.model.ReqRateDTO;

import com.example.demo.model.ReqTransferDTO;
import com.example.demo.repository.AccountsRepository;
import com.example.demo.repository.RateRepository;
import com.example.demo.repository.TransaksiRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransaksiService {

    @Autowired
    TransaksiRepository transaksiRepository;

    @Autowired
    RateRepository rateRepository;

    @Autowired
    AccountsRepository accountsRepository;


    public GetRateDTO getExchange(ReqRateDTO reqRateDTO){
        Double result = 0.0;

        Rate data = rateRepository.findByMataUangAsalAndMataUangTujuan(reqRateDTO.getMataUangAsal(),reqRateDTO.getMataUangTujuan());

        result = data.getKurs() * reqRateDTO.getAmount();

        GetRateDTO getRateDTO = new GetRateDTO();
        getRateDTO.setRateId(data.getId());
        getRateDTO.setResultAmount(result);
        getRateDTO.setExchangeRate(data.getKurs());
        return getRateDTO;
    }

    @Transactional
    public GetTransferDTO transferValas(ReqTransferDTO reqTransferDTO){
        Account rekeningAsal = new Account();
        Account rekeningTujuan = new Account();
        GetRateDTO hasilRate = new GetRateDTO();
        ReqRateDTO reqRate = new ReqRateDTO();
        GetTransferDTO getTransferDTO = new GetTransferDTO();
        Transaksi transaksi = new Transaksi();

        rekeningAsal = accountsRepository.findAllByNoRek(reqTransferDTO.getNoRekAsal());
        rekeningTujuan = accountsRepository.findAllByNoRek(reqTransferDTO.getNoRekTujuan());

        reqRate.setMataUangAsal(rekeningAsal.getMataUang());
        reqRate.setMataUangTujuan(rekeningTujuan.getMataUang());
        reqRate.setAmount(reqTransferDTO.getJumlah());

        hasilRate = getExchange(reqRate);

        rekeningAsal.setSaldo(rekeningAsal.getSaldo() - reqTransferDTO.getJumlah());
        rekeningTujuan.setSaldo(rekeningTujuan.getSaldo() + hasilRate.getResultAmount());

        accountsRepository.save(rekeningAsal);
        accountsRepository.save(rekeningTujuan);

        transaksi.setWaktuTransaksi(LocalDateTime.now());
        transaksi.setAkunId(rekeningAsal.getId());
        transaksi.setUserId(rekeningAsal.getUsersId());
        transaksi.setRateId(hasilRate.getRateId());
        transaksi.setJumlahIn(hasilRate.getResultAmount());
        transaksi.setJumlahOut(reqTransferDTO.getJumlah());

        transaksiRepository.save(transaksi);

        getTransferDTO.setJumlahTransfer(reqTransferDTO.getJumlah());
        getTransferDTO.setWaktuTransaksi(transaksi.getWaktuTransaksi());
        getTransferDTO.setHasilPembelianValas(hasilRate.getResultAmount());

        return getTransferDTO;
    }
}
