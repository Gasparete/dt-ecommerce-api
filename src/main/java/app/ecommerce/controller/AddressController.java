package app.ecommerce.controller;

import app.ecommerce.model.Address;
import app.ecommerce.service.AddressService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/addresses")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService customerService) {
        this.addressService = customerService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "CEP não existe"),
            @ApiResponse(responseCode = "400", description = "Formato de CEP inválido")
    })
    @GetMapping("/{zipCode}")
    public Address getCustomerById(@PathVariable String zipCode) {
        return addressService.getAddressByZipCode(zipCode);
    }
}