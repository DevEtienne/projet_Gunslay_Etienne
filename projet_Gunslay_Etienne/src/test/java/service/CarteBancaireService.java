package service;

import com.example.projet_gunslay_etienne.domain.CarteBancaire;
import com.example.projet_gunslay_etienne.domain.CompteBancaire;
import com.example.projet_gunslay_etienne.domain.CompteCourant;
import com.example.projet_gunslay_etienne.dto.carte.CarteCreateDTO;
import com.example.projet_gunslay_etienne.dto.carte.CarteDTO;
import com.example.projet_gunslay_etienne.mapper.CarteMapper;
import com.example.projet_gunslay_etienne.repository.CarteBancaireRepository;
import com.example.projet_gunslay_etienne.repository.CompteBancaireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CarteBancaireService {

    private final CarteBancaireRepository carteRepository;
    private final CompteBancaireRepository compteRepository;
    private final CarteMapper carteMapper;

    public CarteDTO create(CarteCreateDTO dto) {
        CompteBancaire compte = compteRepository.findByNumeroCompte(dto.getNumeroCompte())
                .orElseThrow(() -> new RuntimeException("Compte non trouvé : " + dto.getNumeroCompte()));

        if (!(compte instanceof CompteCourant cc)) {
            throw new RuntimeException("Seuls les comptes courants peuvent avoir une carte bancaire.");
        }

        CarteBancaire carte = carteMapper.fromCreateDto(dto, cc);

        cc.getCartes().add(carte);

        CarteBancaire saved = carteRepository.save(carte);

        return carteMapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public List<CarteDTO> findAll() {
        return carteRepository.findAll()
                .stream()
                .map(carteMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CarteDTO> findByNumeroCompte(String numeroCompte) {
        return carteRepository.findByCompteCourant_NumeroCompte(numeroCompte)
                .stream()
                .map(carteMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public CarteDTO findById(Long id) {
        CarteBancaire carte = carteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carte non trouvée : " + id));
        return carteMapper.toDto(carte);
    }

    public CarteDTO activer(Long id) {
        CarteBancaire carte = carteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carte non trouvée : " + id));
        carte.setActive(true);
        return carteMapper.toDto(carteRepository.save(carte));
    }

    public CarteDTO desactiver(Long id) {
        CarteBancaire carte = carteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carte non trouvée : " + id));
        carte.setActive(false);
        return carteMapper.toDto(carteRepository.save(carte));
    }

    public void delete(Long id) {
        carteRepository.deleteById(id);
    }
}
