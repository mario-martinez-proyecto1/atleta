package com.cenfotec.atleta.service;

import com.cenfotec.atleta.domian.IndiceMasaMuscular;
import com.cenfotec.atleta.repository.IndiceMasaMuscularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class IndicemasaMuscularServiceImpl implements IndiceMasaMuscularService{

    @Autowired
    IndiceMasaMuscularRepository indiceMasaMuscularRepository;

    @Override
    public void saveIMC(IndiceMasaMuscular indiceMasaMuscular) {
        indiceMasaMuscular.setIMC(calculoIMC(indiceMasaMuscular));
        indiceMasaMuscular.setFechaCalculo(new Date());
        indiceMasaMuscularRepository.save(indiceMasaMuscular);
    }

    @Override
    public Optional<IndiceMasaMuscular> getIndiceMasaMuscularById(Long id) {
        return indiceMasaMuscularRepository.findById(id).map(record -> Optional.of(record)).orElse(Optional.empty());
    }

    @Override
    public List<IndiceMasaMuscular> getAllIndiceMasaMuscular() {
        return indiceMasaMuscularRepository.findAll();
    }

    @Override
    public void updateIMC(IndiceMasaMuscular indiceMasaMuscular) {
        Optional<IndiceMasaMuscular> record = indiceMasaMuscularRepository.
                findById(indiceMasaMuscular.getIdIMC());
        if (record.isPresent()) {
            IndiceMasaMuscular data = record.get();
            data.setIdIMC(indiceMasaMuscular.getIdIMC());
            data.setPeso(indiceMasaMuscular.getPeso());
            data.setIMC(calculoIMC(indiceMasaMuscular));
            data.setFechaCalculo(new Date());
            indiceMasaMuscularRepository.save(data);
        }
    }

    private float calculoIMC(IndiceMasaMuscular indiceMasaMuscular){
        return indiceMasaMuscular.getPeso() / indiceMasaMuscular.getEstatura();
    }
}
