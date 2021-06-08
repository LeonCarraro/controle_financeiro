package com.leoncarraro.controlefinanceiro.api.config;

import com.leoncarraro.controlefinanceiro.api.dto.TransactionRequest;
import com.leoncarraro.controlefinanceiro.api.dto.TransactionResponse;
import com.leoncarraro.controlefinanceiro.api.model.Transaction;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.typeMap(Transaction.class, TransactionResponse.class).addMappings(mapper -> {
           mapper.map(src -> src.getWallet().getName(), TransactionResponse::setWalletName);
           mapper.map(src -> src.getCategory().getName(), TransactionResponse::setCategoryName);
        });

        modelMapper.typeMap(TransactionRequest.class, Transaction.class).addMappings(mapper -> {
            mapper.skip(Transaction::setCategory);
            mapper.skip(Transaction::setWallet);
        });

        return modelMapper;
    }

}
