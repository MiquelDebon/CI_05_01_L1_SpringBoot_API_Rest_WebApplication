package cat.itacademy.barcelonactiva.VillgrasaDebon.Miquel.s05.t01.n01.S05T01N01DebonMiquel.controller;

import cat.itacademy.barcelonactiva.VillgrasaDebon.Miquel.s05.t01.n01.S05T01N01DebonMiquel.model.domainEntity.Sucursal;
import cat.itacademy.barcelonactiva.VillgrasaDebon.Miquel.s05.t01.n01.S05T01N01DebonMiquel.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.VillgrasaDebon.Miquel.s05.t01.n01.S05T01N01DebonMiquel.model.services.SucursalServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sucursal")
public class SucursalController {

    private static Logger ConsoleLog = LoggerFactory.getLogger(SucursalController.class);
    //TODO Cada cop que reinicio es generen 48 nous Sucursals xd

    @Autowired
    private SucursalServiceImpl sucService;

    // http://localhost:9000/sucursal/home
    @GetMapping("/home")
    public String homePage(Model model){
        model.addAttribute("title", "We are IT-Branch");
        return "home";
    }


    @GetMapping({"/getAll", "/branches"})  // http://localhost:9000/sucursal/getAll
    public String getAll(Model model){
        List<SucursalDTO> lista = this.getAllDTO().getBody();

        model.addAttribute("title", "Home branch page DTO");
        model.addAttribute("sucursalDTO",lista);
        ConsoleLog.info("Gone to dtoBranch.html page");
        return "dtoBranch";
    }

    @GetMapping("/add")  // http://localhost:9000/sucursal/add
    public String add(Model model){
        Sucursal sucursal = new Sucursal();
        model.addAttribute("sucursal", sucursal);
        model.addAttribute("title", "ADD a new Branch");
        ConsoleLog.info("Gone to add.html page");
        return "addBranch";
    }

    @PostMapping("/addBranch") //Post Action method
    public String addBranch(@Valid @ModelAttribute Sucursal sucursal, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("title", "ADD a CORRECT Branch");
            model.addAttribute("sucursal", sucursal);
            ConsoleLog.warn("Existieron errores en el formulario");
            return "addBranch";
        }
        sucService.saveOne(sucursal);
        ConsoleLog.info("Branch saved CORRECTLY");
        return "redirect:/sucursal/getAll";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable int id){
        if(id>0){
            if(sucService.existById(id)){
                sucService.deleteById(id);
                ConsoleLog.info("Deleted user id '" + id + "' from the database");
            }else{
                ConsoleLog.warn("This user '" + id + "' DOES NOT EXIST");
            }
            return "redirect:/sucursal/branches";
        }else{
            return "error/404";
        }

    }

    @GetMapping("update/{id}")
    public String updateById(@PathVariable int id, Model model){
        model.addAttribute("title", "EDIT a new Branch");
        try{
            Optional<SucursalDTO> dto = sucService.findSucursalDTOById(id);
            Sucursal suc = sucService.sucursalFromDTO(dto.get());

            model.addAttribute("title", "Edit a Branch");
            model.addAttribute("sucursal", suc);
            ConsoleLog.info("Correctly updated branch");
            return "addBranch";
        }catch (Exception e){
            ConsoleLog.warn("An error occurred");
            return "addBranch";
        }
    }

    /*  DTO no hauria de no tenir id??
    @GetMapping("/deleteByName/{name}")  //DRAMA
    public String deleteByName(@PathVariable String name){
        Optional<Sucursal> sucursal = sucService.getSucursalByName(name);
        try{
            sucService.deleteById(sucursal.get().getPk_SucursalID());

        }catch (Exception e){
            return "redirect:/sucursal/branches";
        }
        return "redirect:/sucursal/branches";
    }*/


    /**
     *
     * EXTRAA
     *
     */

    @GetMapping("/getAllEU")  // http://localhost:9000/sucursal/getAllEU
    public String getAllEU(Model model){
        List<SucursalDTO> lista = this.getAllEuropeanDto().getBody();

        model.addAttribute("title", "European Branches!!");
        model.addAttribute("sucursalDTO",lista);
        return "dtoBranch";
    }

    @GetMapping("/getAllNoEU")
    public String getAllNoEU(Model model){
        List<SucursalDTO> listaNoEU = this.getAllNoEuropeanDto().getBody();

        model.addAttribute("title", "NO European Branches!!");
        model.addAttribute("sucursalDTO",listaNoEU);
        return "dtoBranch";
    }


    /**
     *
     * SUPPORT
     *
     */

    @GetMapping("/getAllDTO")
    public ResponseEntity<List<SucursalDTO>> getAllDTO(){
        try{
            List<SucursalDTO> listDTO = sucService.getAllDTO();
            if(!listDTO.isEmpty()){
                return new ResponseEntity<>(listDTO, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getAllEUList")
    public ResponseEntity<List<SucursalDTO>> getAllEuropeanDto(){
        try{
            List<SucursalDTO> EUCotries = this.getAllDTO().getBody()
                    .stream()
                    .filter((s)->s.getTipusSucursal().equalsIgnoreCase("UE"))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(EUCotries, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getAllNoEUList")
    public ResponseEntity<List<SucursalDTO>> getAllNoEuropeanDto(){
        try{
            List<SucursalDTO> EUCotries = this.getAllDTO().getBody()
                    .stream()
                    .filter((s)->!s.getTipusSucursal().equalsIgnoreCase("UE"))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(EUCotries, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    /**
     *
     * Postman
     *
     */


    @PostMapping("/addMember")
    public ResponseEntity<Sucursal> addSucursal(@RequestBody Sucursal sucursal){
        try{
            Sucursal newSucursal = new Sucursal(
                    sucursal.getNomSucursal(),
                    sucursal.getPaisSucursal());
            sucService.saveOne(newSucursal);
            return new ResponseEntity<>(newSucursal, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/update")
    public ResponseEntity<Sucursal> updateSucursal(@RequestBody Sucursal sucursal){
        try{
            int index = sucursal.getPk_SucursalID();
            sucService.saveOne(sucursal);
            return new ResponseEntity<>(sucursal, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getOneDTO/{id}")
    public ResponseEntity<SucursalDTO> getOneSucursalDTO(@PathVariable int id){
        try{
            SucursalDTO suc = sucService.findSucursalDTOById(id).get();
            return new ResponseEntity<>(suc, HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deletePM/{id}")
    public ResponseEntity<HttpStatus> deletePMById(@PathVariable int id){
        try{
            sucService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

