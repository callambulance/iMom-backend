package com.el_proyecte_grande.imom.tips.service;

import com.el_proyecte_grande.imom.tips.model.Tip;
import com.el_proyecte_grande.imom.tips.repository.TipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TipService {
    private final TipRepository tipRepository;

    @Autowired
    public TipService(TipRepository tipRepository) {
        this.tipRepository = tipRepository;
    }

    public Tip getTipByDate() {
        LocalDate date = LocalDate.now();
        return tipRepository.findTipByDate(date);
    }
}
