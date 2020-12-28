package com.training.tacos.service;

import com.training.tacos.service.dto.TacoDto;
import com.training.tacos.data.model.Ingredient;
import com.training.tacos.data.model.Taco;
import com.training.tacos.data.repository.IngredientRepository;
import com.training.tacos.data.repository.TacoRepository;
import com.training.tacos.service.mapper.TacoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DesignTacoService {

    private final IngredientRepository ingredientRepo;
    private final TacoRepository tacoRepository;
    private final TacoMapper tacoMapper;
    private final List<Ingredient> ingredients = new ArrayList<>();

    @Autowired
    public DesignTacoService(IngredientRepository ingredientRepo, TacoRepository tacoRepository,
                             TacoMapper tacoMapper) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepository = tacoRepository;
        this.tacoMapper = tacoMapper;
    }

    @PostConstruct
    private void initIngredients() {
        ingredientRepo.findAll().forEach(ingredients::add);
    }

    public List<Ingredient> getAllIngredients() {
        return ingredients;
    }

    public TacoDto saveTaco(TacoDto tacoDto) {
        Taco newTaco = tacoMapper.convertToEntity(tacoDto, ingredients);
        Taco savedTaco = tacoRepository.save(newTaco);
        return tacoMapper.convertToDto(savedTaco);
    }

    public List<TacoDto> findRecent(PageRequest pageRequest) {
        List<Taco> page = tacoRepository.findAll(pageRequest).getContent();
        return page.stream().map(tacoMapper::convertToDto).collect(Collectors.toList());
    }

    public TacoDto findById(Long id) {
        Optional<Taco> taco = tacoRepository.findById(id);
        return taco.map(tacoMapper::convertToDto).orElse(null);
    }
}
