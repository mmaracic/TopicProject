package hr.mmaracic.topicproject.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ACCOUNT_ENTRY")
@SequenceGenerator(name = "sequence_generator", sequenceName = "ACCOUNT_ENTRY_SEQ", allocationSize = 1)
public class AccountEntry extends DatabaseObject implements Serializable {

    @Column(name = "AMOUNT")
    private Long amount;

    @Column(name = "ACCOUNT_ID")
    private Long accountId;

    @Column(name = "PROCESSING_TIMESTAMP")
    private OffsetDateTime processingTimestamp;

    @Column(name = "IS_ERROR")
    private Boolean isError;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ACCOUNT_BALANCE_ID")
    private AccountBalance accountBalance;
}
