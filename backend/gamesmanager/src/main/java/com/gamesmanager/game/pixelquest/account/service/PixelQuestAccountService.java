package com.gamesmanager.game.pixelquest.account.service;

import com.gamesmanager.game.pixelquest.account.dto.PixelQuestAccountDto;
import com.gamesmanager.game.pixelquest.account.repository.PixelQuestAccountRepository;
import com.gamesmanager.game.pixelquest.exception.PixelQuestNotFoundException;
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
        throw new PixelQuestNotFoundException(String.format("No account entity found by username %s.", userName));
    }
}
