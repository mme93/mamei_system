package com.gamesmanager.game.pixelquest.account;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private final PixelQuestAccountRepository accountRepository;

    public AccountService(PixelQuestAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public PixelQuestAccountDto getAccountById(Long id) {
        Optional<PixelQuestAccountEntity> accountEntityOpt = accountRepository.findById(id);
        if (accountEntityOpt.isPresent()) {
            PixelQuestAccountEntity accountEntity = accountEntityOpt.get();
            return new PixelQuestAccountDto(
                    accountEntity.getUserName(), accountEntity.getCurrentWorldId(), accountEntity.getCurrentMapId(), accountEntity.getMapColIndex(), accountEntity.getMapRowIndex(), accountEntity.getPixelQuestMap()
            );
        }
        throw new EntityNotFoundException(String.format("No account entity found by id %s.", id));
    }
}
