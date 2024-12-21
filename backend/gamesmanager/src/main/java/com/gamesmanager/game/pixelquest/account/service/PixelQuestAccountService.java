package com.gamesmanager.game.pixelquest.account.service;

import com.gamesmanager.game.pixelquest.account.dto.PixelQuestAccountDto;
import com.gamesmanager.game.pixelquest.account.entity.PixelQuestAccountEntity;
import com.gamesmanager.game.pixelquest.account.repository.PixelQuestAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PixelQuestAccountService {

    private final PixelQuestAccountRepository accountRepository;

    public PixelQuestAccountService(PixelQuestAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public PixelQuestAccountDto getAccountByUserName(String userName) {
        Optional<PixelQuestAccountDto>accountDtoOpt= accountRepository.findAll().stream().filter(account -> account.getUserName().equals(userName)).map(account ->
                new PixelQuestAccountDto(account.getUserName(), account.getCurrentWorldId(), account.getCurrentMapId(),
                        account.getMapColIndex(), account.getMapRowIndex(), account.getPixelQuestMap())).findAny();
        if(accountDtoOpt.isPresent()){
            return accountDtoOpt.get();
        }
        throw new EntityNotFoundException(String.format("No account entity found by username %s.", userName));
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
