package cat.itacademy.barcelonactiva.VillgrasaDebon.Miquel.s05.t01.n01.S05T01N01DebonMiquel.model.domainEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data //Getter Setter ReqConstructor ToString EqualHasCode
@Entity
@NoArgsConstructor // IMPORTANT!!
@AllArgsConstructor
@Table(name = "sucursal")
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int pk_SucursalID;
    @Column(unique=true)
    @NotEmpty(message = "El Nombre es requerido")
    @Size(min = 3, max =50 , message = "El Nombre debe tener entre 3 y 50 caracteres")
    private String nomSucursal;
    @NotEmpty(message = "El pais es requerido")
    private String paisSucursal;

    public Sucursal(String name, String country){
        this.nomSucursal = name;
        this.paisSucursal = country;
    }
}
