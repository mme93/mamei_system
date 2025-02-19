package com.gamesmanager.game.pixelquest.account.service;

import com.gamesmanager.game.pixelquest.account.dto.PixelQuestAccountDto;
import com.gamesmanager.game.pixelquest.account.dto.PixelQuestUserDto;
import com.gamesmanager.game.pixelquest.account.entity.PixelQuestUserEntity;
import com.gamesmanager.game.pixelquest.account.repository.PixelQuestUserRepository;
import com.gamesmanager.game.pixelquest.exception.PixelQuestInvalidLoginException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PixelQuestUserService {

    private final PixelQuestUserRepository pixelQuestUserRepository;
    private final PixelQuestAccountService accountService;

    public PixelQuestUserService(PixelQuestUserRepository pixelQuestUserRepository, PixelQuestAccountService accountService) {
        this.pixelQuestUserRepository = pixelQuestUserRepository;
        this.accountService = accountService;
    }


    public PixelQuestAccountDto login(PixelQuestUserDto pixelQuestUserDto) {
        List<PixelQuestUserEntity> users = pixelQuestUserRepository.findAll();
        Optional<PixelQuestUserEntity> userOpt = users.stream().filter(user -> (
                user.getUserName().equals(pixelQuestUserDto.getUserName()) &&
                        user.getPassword().equals(pixelQuestUserDto.getPassword()))).findAny();
        if (!userOpt.isPresent()) {
            throw new PixelQuestInvalidLoginException(
                    String.format("No account found by username %s or wrong password", pixelQuestUserDto.getUserName())
            );

        }
        return accountService.getAccountByUserName(userOpt.get().getUserName());
    }
}
