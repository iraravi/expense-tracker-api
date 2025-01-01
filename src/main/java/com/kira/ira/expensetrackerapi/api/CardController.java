package com.kira.ira.expensetrackerapi.api;

import com.kira.ira.expensetrackerapi.api.model.Card;
import com.kira.ira.expensetrackerapi.repo.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @GetMapping
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<String> addCard(@RequestBody Card card) {
        cardRepository.save(card);
        return ResponseEntity.ok("Card added successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCard(@PathVariable Long id) {
        cardRepository.deleteById(id);
        return ResponseEntity.ok("Card deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCard(@PathVariable Long id, @RequestBody Card card) {
        card.setId(id);
        cardRepository.update(card);
        return ResponseEntity.ok("Card updated successfully");
    }
}