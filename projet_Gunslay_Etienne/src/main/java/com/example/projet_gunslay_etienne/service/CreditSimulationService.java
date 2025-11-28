package com.example.projet_gunslay_etienne.service;

import com.example.projet_gunslay_etienne.dto.creditSimulation.CreditSimulationRequestDTO;
import com.example.projet_gunslay_etienne.dto.creditSimulation.CreditSimulationResultDTO;
import com.example.projet_gunslay_etienne.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CreditSimulationService {

    private final ClientRepository clientRepository;

    public CreditSimulationResultDTO simuler(CreditSimulationRequestDTO request) {

        // Optionnel : vérifier que le client existe si un id est fourni
        if (request.getClientId() != null) {
            clientRepository.findById(request.getClientId())
                    .orElseThrow(() -> new RuntimeException("Client non trouvé : " + request.getClientId()));
        }

        BigDecimal montant = request.getMontant();
        int dureeMois = request.getDureeMois();
        BigDecimal tauxInteretAnnuel = request.getTauxInteretAnnuel();
        BigDecimal tauxAssuranceAnnuel = request.getTauxAssuranceAnnuel();

        if (dureeMois <= 0) {
            throw new RuntimeException("La durée du prêt doit être strictement positive.");
        }

        // Taux mensuel (intérêt)
        BigDecimal tauxMensuel = tauxInteretAnnuel
                .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
                .divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);

        // mensualité hors assurance
        BigDecimal mensualiteHorsAssurance = calculerMensualite(montant, tauxMensuel, dureeMois);

        // mensualité assurance (calcul simple, proportionnel au capital)
        BigDecimal tauxMensuelAssurance = tauxAssuranceAnnuel
                .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
                .divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);

        BigDecimal mensualiteAssurance = montant
                .multiply(tauxMensuelAssurance)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal mensualiteTotale = mensualiteHorsAssurance.add(mensualiteAssurance);

        // Coûts globaux
        BigDecimal totalVerseHorsAssurance = mensualiteHorsAssurance
                .multiply(BigDecimal.valueOf(dureeMois))
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal totalAssurance = mensualiteAssurance
                .multiply(BigDecimal.valueOf(dureeMois))
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal coutTotalInterets = totalVerseHorsAssurance
                .subtract(montant)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal coutTotal = totalVerseHorsAssurance
                .add(totalAssurance)
                .setScale(2, RoundingMode.HALF_UP);

        return CreditSimulationResultDTO.builder()
                .montant(montant)
                .dureeMois(dureeMois)
                .tauxInteretAnnuel(tauxInteretAnnuel)
                .tauxAssuranceAnnuel(tauxAssuranceAnnuel)
                .clientId(request.getClientId())
                .mensualiteHorsAssurance(mensualiteHorsAssurance)
                .mensualiteAssurance(mensualiteAssurance)
                .mensualiteTotale(mensualiteTotale)
                .coutTotalInterets(coutTotalInterets)
                .coutTotalAssurance(totalAssurance)
                .coutTotal(coutTotal)
                .build();
    }

    private BigDecimal calculerMensualite(BigDecimal capital,
                                          BigDecimal tauxMensuel,
                                          int dureeMois) {

        if (tauxMensuel.compareTo(BigDecimal.ZERO) == 0) {
            return capital
                    .divide(BigDecimal.valueOf(dureeMois), 2, RoundingMode.HALF_UP);
        }

        double i = tauxMensuel.doubleValue();
        double c = capital.doubleValue();
        double n = dureeMois;

        double facteur = Math.pow(1 + i, -n);
        double mensualiteDouble = c * (i / (1 - facteur));

        return BigDecimal.valueOf(mensualiteDouble)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
