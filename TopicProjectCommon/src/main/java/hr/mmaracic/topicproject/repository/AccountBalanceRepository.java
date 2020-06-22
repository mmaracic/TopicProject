/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.mmaracic.topicproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hr.mmaracic.topicproject.model.AccountBalance;
import java.util.Optional;

/**
 *
 * @author mmaracic
 */
public interface AccountBalanceRepository extends JpaRepository<AccountBalance, Long> {
    
    public Optional<AccountBalance> findByIdAndClosed(long id, boolean closed);
}
