package app.ecommerce.controller;

import app.ecommerce.dto.AddressDTO;
import app.ecommerce.port.AddressProviderPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    private final AddressProviderPort addressProviderPort;

    public AddressController(AddressProviderPort addressProviderPort) {
        this.addressProviderPort = addressProviderPort;
    }

    @Operation(summary = "Busca um endereço pelo CEP")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "CEP não encontrado")
    })
    @GetMapping("/{zipCode}")
    public ResponseEntity<AddressDTO> getAddressByZipCode(@PathVariable String zipCode) {
        return addressProviderPort.findByZipCode(zipCode)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}