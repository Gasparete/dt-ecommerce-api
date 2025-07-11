package app.ecommerce.service;

import app.ecommerce.model.Address;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Service
public class AddressService {

    private final RestTemplate restTemplate;

    public AddressService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Address getAddressByZipCode(String zipCode) {
        String url = "http://viacep.com.br/ws/" + zipCode + "/json/";

        try {
            Map<String, String> response = restTemplate.getForObject(url, Map.class);

            // resposta válida, mas CEP não encontrado
            if (response == null || response.containsKey("erro")) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CEP não existe");
            }

            return new Address(
                    zipCode,
                    response.get("logradouro"),
                    response.get("bairro"),
                    response.get("localidade"),
                    response.get("uf")
            );
        } catch (HttpClientErrorException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao consultar o CEP: " + e.getStatusText());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro inesperado ao consultar o CEP");
        }
    }
}
