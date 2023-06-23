package cat.itacademy.barcelonactiva.VillgrasaDebon.Miquel.s05.t01.n01.S05T01N01DebonMiquel.model.services;

import cat.itacademy.barcelonactiva.VillgrasaDebon.Miquel.s05.t01.n01.S05T01N01DebonMiquel.model.domainEntity.Sucursal;
import cat.itacademy.barcelonactiva.VillgrasaDebon.Miquel.s05.t01.n01.S05T01N01DebonMiquel.model.dto.SucursalDTO;

import java.util.List;
import java.util.Optional;

public interface ISucursalService {
    List<SucursalDTO> getAllDTO();
    Sucursal saveOne(Sucursal sucursal);
    Optional<SucursalDTO> findSucursalDTOById(int id);
    void deleteById(int id);
    boolean existById(int id);



//    Optional<Sucursal> findOneById(int id);
    /**
     *
     * Not necessary
     *
     */

    //Optional<Sucursal> getSucursalByName(String name);
}
