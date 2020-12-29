package com.training.tacos.web.controller;

import com.training.tacos.service.DesignTacoService;
import com.training.tacos.service.dto.IngredientList;
import com.training.tacos.service.dto.TacoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoRestController {

    private final DesignTacoService designTacoService;

    @Autowired
    public DesignTacoRestController(DesignTacoService designTacoService) {
        this.designTacoService = designTacoService;
    }

    @GetMapping
    public List<TacoDto> recentTacos(@RequestParam(required = false, defaultValue = "0") int offset,
                                     @RequestParam(required = false, defaultValue = "10") int limit) {
        PageRequest page = PageRequest.of(offset, limit, Sort.by("createdAt").descending());
        return designTacoService.findRecent(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TacoDto> tacoById(@PathVariable("id") Long id) {
        TacoDto taco = designTacoService.findById(id);
        if (taco != null) {
            return new ResponseEntity<>(taco, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public TacoDto saveTaco(@RequestBody TacoDto taco) {
        return designTacoService.saveTaco(taco);
    }

    @GetMapping("/ingredients")
    public IngredientList getAllIngredients() {
        return new IngredientList(designTacoService.getConvertedIngredients());
    }
}
