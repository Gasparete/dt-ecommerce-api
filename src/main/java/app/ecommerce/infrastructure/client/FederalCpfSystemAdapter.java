package app.ecommerce.infrastructure.client;

import app.ecommerce.port.CpfValidationPort;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FederalCpfSystemAdapter implements CpfValidationPort {

    private final Random random = new Random();

    @Override
    public boolean isCpfRegistered(String cpf) {
        try {
            // 1. Simula a latência da rede (delay de 1 a 3 segundos)
            int delay = random.nextInt(2000) + 1000;
            Thread.sleep(delay);

            // 2. Simula o resultado da validação (85% de chance de sucesso)
            return random.nextDouble() > 0.15;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    @Override
    public boolean hasClearLegalRecord(String cpf) {
        try {
            // 1. Simula a latência da rede (delay de 1 a 5 segundos)
            int delay = random.nextInt(4000) + 1000;
            Thread.sleep(delay);

            // 2. Simula o resultado da validação (70% de chance de sucesso)
            return random.nextDouble() > 0.30;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}