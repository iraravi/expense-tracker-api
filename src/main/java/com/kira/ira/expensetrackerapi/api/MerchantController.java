package com.kira.ira.expensetrackerapi.api;

import com.kira.ira.expensetrackerapi.api.model.Merchant;
import com.kira.ira.expensetrackerapi.repo.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

    @Autowired
    private MerchantRepository merchantRepository;

    @GetMapping
    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<String> addMerchant(@RequestBody Merchant merchant) {
        merchantRepository.save(merchant);
        return ResponseEntity.ok("Merchant added successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMerchant(@PathVariable Long id) {
        merchantRepository.deleteById(id);
        return ResponseEntity.ok("Merchant deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMerchant(@PathVariable Long id, @RequestBody Merchant merchant) {
        merchant.setId(id);
        merchantRepository.update(merchant);
        return ResponseEntity.ok("Merchant updated successfully");
    }
}