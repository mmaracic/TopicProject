package hr.mmaracic.topicproject.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mmaracic
 */
@Getter
@Setter
@Entity
@Table(name = "ACCOUNT_BALANCE")
@SequenceGenerator(name = "sequence_generator", sequenceName = "ACCOUNT_BALANCE_SEQ", allocationSize = 1)
public class AccountBalance extends DatabaseObject implements Serializable {

    @Column(name = "BALANCE")
    private Long balance;

    @Column(name = "ACCOUNT_ID")
    private Long accountId;

    @Column(name = "CLOSED")
    private Boolean closed;

    @OneToMany(mappedBy = "accountBalance")
    private List<AccountEntry> entries = new ArrayList();

}
