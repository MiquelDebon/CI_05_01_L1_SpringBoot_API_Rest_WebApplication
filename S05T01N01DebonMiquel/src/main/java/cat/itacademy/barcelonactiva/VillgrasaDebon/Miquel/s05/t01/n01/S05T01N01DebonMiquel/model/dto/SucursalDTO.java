package cat.itacademy.barcelonactiva.VillgrasaDebon.Miquel.s05.t01.n01.S05T01N01DebonMiquel.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class SucursalDTO {
    private final int id;
    private final String name;
    private final String country;
    private final String tipusSucursal;

    public SucursalDTO(int id, String name, String country){
        this.id = id;
        this.name = name;
        this.country = country;
        this.tipusSucursal = (UECountries.UEcountriesList.stream()
                .anyMatch(x -> x.equalsIgnoreCase(country)))? "UE" : "Fora UE";
    }

}
