package app.ecommerce.controller; // Ou onde seus controllers estão

import app.ecommerce.service.SseService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api")
public class SseController {

    private final SseService sseService;

    public SseController(SseService sseService) {
        this.sseService = sseService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/customers/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribeToCustomerEvents() {
        // Cria um emitter com um timeout longo (ex: 30 minutos).
        // O Spring pode fechar a conexão se ficar inativa por muito tempo.
        SseEmitter emitter = new SseEmitter(30 * 60 * 1000L);

        // Adiciona o emitter ao nosso serviço para que ele possa receber eventos.
        sseService.addEmitter(emitter);

        return emitter;
    }
}