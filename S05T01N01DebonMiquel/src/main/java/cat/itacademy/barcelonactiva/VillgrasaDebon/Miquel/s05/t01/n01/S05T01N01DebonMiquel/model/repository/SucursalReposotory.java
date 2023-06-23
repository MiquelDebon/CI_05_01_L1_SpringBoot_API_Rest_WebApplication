package cat.itacademy.barcelonactiva.VillgrasaDebon.Miquel.s05.t01.n01.S05T01N01DebonMiquel.model.repository;

import cat.itacademy.barcelonactiva.VillgrasaDebon.Miquel.s05.t01.n01.S05T01N01DebonMiquel.model.domainEntity.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SucursalReposotory extends JpaRepository<Sucursal, Integer> {

    //Optional<Sucursal> findByNomSucursal(String name);
}
