package com.itdp.arnd.service;

// import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itdp.arnd.dto.BalanceData;
import com.itdp.arnd.dto.FormatBalance;
import com.itdp.arnd.entity.BankBalances;
import com.itdp.arnd.entity.BankUsers;
import com.itdp.arnd.entity.Currencies;
import com.itdp.arnd.entity.Users;
import com.itdp.arnd.repository.BalanceRepository;
import com.itdp.arnd.repository.BankUserRepository;
import com.itdp.arnd.repository.CurrencyRepository;
import com.itdp.arnd.repository.UsersRepository;

@Service
public class BankUserService {

    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    BankUserRepository bankUserRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    public BalanceData getBalance(String param){
        BankUsers response = new BankUsers();
        Users dataUser = new Users();
        Currencies dataCurrencies = new Currencies();
        try {
            response = bankUserRepository.findById(Integer.valueOf(param)).get();
            int userId = response.getUserId();
            String accountNumber = response.getAccountNumber();
            Instant updatenya = response.getUpdatedAt();
            
            dataUser = usersRepository.findAllById(Integer.valueOf(userId));
            String namanya = dataUser.getName();

            List<BankBalances> bankBalances = balanceRepository.findAllByBankUserId(Integer.valueOf(param));
            List<FormatBalance> balances = new ArrayList<>();
            for (BankBalances balancesnya : bankBalances) {
                Double balance = balancesnya.getBalance();
                Integer currencyId = balancesnya.getCurrencyId();
                dataCurrencies = currencyRepository.findAllById(currencyId);
                String name = dataCurrencies.getName();

                balances.add(new FormatBalance(currencyId, name, balance));
            }

            BalanceData responseData = new BalanceData(Integer.valueOf(param) , namanya, accountNumber, balances, String.valueOf(updatenya));

            return responseData;
        } catch (Exception e) {
            System.err.println("Error fetching balance: " + e.getMessage());
            e.printStackTrace();
            
            // Mengembalikan response default jika terjadi error
            return new BalanceData(0, "Unknown", "000000", new ArrayList<>(), "N/A");
        }
    }

}
