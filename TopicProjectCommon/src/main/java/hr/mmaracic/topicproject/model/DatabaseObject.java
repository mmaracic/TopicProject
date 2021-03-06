/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.mmaracic.topicproject.model;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Marijo
 */
@Getter
@Setter
@MappedSuperclass
public class DatabaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    //@SequenceGenerator(name = "sequence_generator", sequenceName = "seq_name", allocationSize = 50)
    //Override SequenceGenerator in subclasses
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "CREATION_TIMESTAMP")
    OffsetDateTime creationTimestamp;
}
