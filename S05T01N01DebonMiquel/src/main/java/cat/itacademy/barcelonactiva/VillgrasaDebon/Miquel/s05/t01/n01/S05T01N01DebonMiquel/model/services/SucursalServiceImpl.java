package cat.itacademy.barcelonactiva.VillgrasaDebon.Miquel.s05.t01.n01.S05T01N01DebonMiquel.model.services;

import cat.itacademy.barcelonactiva.VillgrasaDebon.Miquel.s05.t01.n01.S05T01N01DebonMiquel.model.domainEntity.Sucursal;
import cat.itacademy.barcelonactiva.VillgrasaDebon.Miquel.s05.t01.n01.S05T01N01DebonMiquel.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.VillgrasaDebon.Miquel.s05.t01.n01.S05T01N01DebonMiquel.model.repository.SucursalReposotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SucursalServiceImpl implements ISucursalService {
    @Autowired
    private SucursalReposotory sucRepository;


    @Override
    public Sucursal saveOne(Sucursal su) {
        return sucRepository.save(su);
    }
    @Override
    public void deleteById(int id) {
        sucRepository.deleteById(id);
    }

//    @Override
//    public Optional<Sucursal> findOneById(int id) {
//        return sucRepository.findById(id);
//    }


    @Override
    public List<SucursalDTO> getAllDTO() {
        List<Sucursal> sucursals = sucRepository.findAll();
        List<SucursalDTO> listDTO = new ArrayList<>();
        for(Sucursal suc : sucursals){
            listDTO.add(new SucursalDTO(suc.getPk_SucursalID(), suc.getNomSucursal(), suc.getPaisSucursal()));
        }
        return listDTO;
    }
    @Override
    public Optional<SucursalDTO> findSucursalDTOById(int id) {
        Sucursal sucursal = sucRepository.findById(id).get();
        return Optional.of( new SucursalDTO(sucursal.getPk_SucursalID(),sucursal.getNomSucursal(), sucursal.getPaisSucursal()));
    }


    public Sucursal sucursalFromDTO(SucursalDTO DTO){
        int id = DTO.getId();
        String name = DTO.getName();
        String country = DTO.getCountry();
        return new Sucursal(id, name, country);
    }

    @Override
    public boolean existById(int id) {
        return sucRepository.existsById(id);
    }


    //    @Override
//    public Optional<Sucursal> getSucursalByName(String name) {
//        return sucRepository.findByNomSucursal(name);
//    }

}
