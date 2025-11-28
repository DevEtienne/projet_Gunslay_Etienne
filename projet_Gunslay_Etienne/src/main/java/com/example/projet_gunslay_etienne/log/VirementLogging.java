package com.example.projet_gunslay_etienne.log;

import com.example.projet_gunslay_etienne.dto.VirementRequestDTO;
import com.example.projet_gunslay_etienne.dto.virement.VirementResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j(topic = "VIREMENT_LOG")
@Aspect
@Component
public class VirementLogging {

    @Pointcut("execution(* com.example.projet_gunslay_etienne.service.VirementService.effectuerVirement(..))")
    public void virementOperation() {}

    @Before("virementOperation() && args(request)")
    public void logBefore(VirementRequestDTO request) {
        log.info("DEBUT_VIREMENT - debiteur={}, receveur={}, montant={}",
                request.getCompteDebiteurNumero(),
                request.getCompteReceveurNumero(),
                request.getMontant());
    }

    @AfterReturning(pointcut = "virementOperation()", returning = "response")
    public void logAfterSuccess(VirementResponseDTO response) {
        log.info("SUCCES_VIREMENT - debiteur={}, receveur={}, montant={}, message={}",
                response.getCompteDebiteurNumero(),
                response.getCompteReceveurNumero(),
                response.getMontant(),
                response.getMessage());
    }

    @AfterThrowing(pointcut = "virementOperation()", throwing = "ex")
    public void logAfterError(JoinPoint jp, Throwable ex) {
        Object[] args = jp.getArgs();
        if (args.length > 0 && args[0] instanceof VirementRequestDTO request) {
            log.error("ECHEC_VIREMENT - debiteur={}, receveur={}, montant={}, erreur={}",
                    request.getCompteDebiteurNumero(),
                    request.getCompteReceveurNumero(),
                    request.getMontant(),
                    ex.getMessage());
        } else {
            log.error("ECHEC_VIREMENT - Erreur={}", ex.getMessage());
        }
    }
}

