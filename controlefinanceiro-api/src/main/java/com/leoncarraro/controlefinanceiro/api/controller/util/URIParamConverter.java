package com.leoncarraro.controlefinanceiro.api.controller.util;

import com.leoncarraro.controlefinanceiro.api.service.WalletService;
import com.leoncarraro.controlefinanceiro.api.service.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class URIParamConverter {

    private final WalletService walletService;

    public List<Long> getListOfIds(String ids) {
        if (ids.equals("")) {
            return walletService.getAll().stream().map(w -> w.getId()).collect(Collectors.toList());
        }

        List<Long> result = new ArrayList<>();

        try {
            String[] temp = ids.split(",");
            for (String s: temp) {
                result.add(Long.parseLong(s));
            }
        } catch (NumberFormatException e) {
            throw new BadRequestException("Erro ao fazer uma busca pelas carteiras selecionadas! " +
                    "Digite apenas números separados por ',' (vírgula).");
        }

        return result;
    }

    public String decodeParam(String param) {
        try {
            return URLDecoder.decode(param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

}
