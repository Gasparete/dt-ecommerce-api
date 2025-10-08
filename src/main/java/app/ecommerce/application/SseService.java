package app.ecommerce.application;

import app.ecommerce.dto.CustomerResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SseService {

    private static final Logger log = LoggerFactory.getLogger(SseService.class);

    // Usamos CopyOnWriteArrayList para segurança em ambientes com múltiplas threads.
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    /**
     * Adiciona um novo cliente (emitter) à lista de notificações.
     * @param emitter O SseEmitter do novo cliente.
     */
    public void addEmitter(SseEmitter emitter) {
        emitters.add(emitter);
        log.info("Novo cliente conectado para SSE. Total de conexões: {}", emitters.size());

        // Remove o emitter da lista quando a conexão for fechada ou der erro.
        emitter.onCompletion(() -> {
            emitters.remove(emitter);
            log.info("Cliente desconectado de SSE. Total de conexões: {}", emitters.size());
        });
        emitter.onTimeout(() -> {
            emitters.remove(emitter);
            log.warn("Conexão SSE expirou (timeout). Total de conexões: {}", emitters.size());
        });
    }

    /**
     * Envia uma atualização de cliente para todos os clientes conectados.
     * @param customer O DTO do cliente que foi atualizado.
     */
    public void sendCustomerUpdate(CustomerResponseDTO customer) {
        log.info("Enviando atualização para {} clientes conectados via SSE.", emitters.size());

        for (SseEmitter emitter : emitters) {
            try {
                // O nome do evento ('customer-update') é importante para o front-end.
                emitter.send(SseEmitter.event().name("customer-update").data(customer));
            } catch (IOException e) {
                log.error("Erro ao enviar evento SSE para um cliente. Removendo da lista.", e);
                emitters.remove(emitter);
            }
        }
    }
}