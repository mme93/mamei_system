package com.user.account.repository;

import com.user.account.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing account entities.
 */
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    /**
     * Retrieves an account by its ID and user ID.
     *
     * @param id     The ID of the account.
     * @param userId The ID of the user associated with the account.
     * @return An optional containing the account entity, if found.
     */
    Optional<AccountEntity>findByIdAndUserId(Long id, Long userId);

    /**
     * Retrieves an account by its username.
     *
     * @param userName The username of the account.
     * @return An optional containing the account entity, if found.
     */
    Optional<AccountEntity>findByUsername(String userName);

    /**
     * Retrieves an account by its user ID.
     *
     * @param userId The ID of the user associated with the account.
     * @return An optional containing the account entity, if found.
     */
    Optional<AccountEntity>findByUserId(Long userId);


    /**
     * Checks if an account exists with the given user ID.
     *
     * @param userId The ID of the user associated with the account.
     * @return True if an account exists with the given user ID, false otherwise.
     */
    boolean existsByUserId( Long userId);
}
