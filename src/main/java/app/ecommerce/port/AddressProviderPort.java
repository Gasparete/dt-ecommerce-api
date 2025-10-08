package app.ecommerce.port;

import app.ecommerce.dto.AddressDTO;

import java.util.Optional;

public interface AddressProviderPort {
    Optional<AddressDTO> findByZipCode(String zipCode);
}