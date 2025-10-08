package app.ecommerce.infrastructure.client;

import app.ecommerce.dto.AddressDTO;
import app.ecommerce.port.AddressProviderPort;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Component
public class ViaCepAdapter implements AddressProviderPort {

    private final RestTemplate restTemplate;

    public ViaCepAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<AddressDTO> findByZipCode(String zipCode) {
        String url = "http://viacep.com.br/ws/" + zipCode + "/json/";

        try {
            Map<String, String> response = restTemplate.getForObject(url, Map.class);

            if (response == null || response.containsKey("erro")) {
                return Optional.empty();
            }

            AddressDTO addressDTO = new AddressDTO(
                    response.get("cep").replace("-", ""),
                    response.get("logradouro"),
                    response.get("bairro"),
                    response.get("localidade"),
                    response.get("uf")
            );
            return Optional.of(addressDTO);

        } catch (Exception e) {
            return Optional.empty();
        }
    }
}