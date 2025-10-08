package app.ecommerce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CpfValidationService {

    private static final Logger log = LoggerFactory.getLogger(CpfValidationService.class);
    private final Random random = new Random();

    /**
     * @param cpf O CPF a ser validado.
     * @return true se o CPF for considerado válido, false caso contrário.
     */
    public boolean isCpfValid(String cpf) {
        log.info("Iniciando validação externa para o CPF: {}", cpf);

        try {
            // 1. Simula a latência da rede (delay de 1 a 7 segundos)
            int delay = random.nextInt(6000) + 1000;
            Thread.sleep(delay);

            // 2. Simula o resultado da validação (50% de chance de sucesso)
            return random.nextDouble() > 0.5;

        } catch (InterruptedException e) {
            log.error("A thread de validação foi interrompida.", e);
            Thread.currentThread().interrupt();
            return false;
        }
    }
}