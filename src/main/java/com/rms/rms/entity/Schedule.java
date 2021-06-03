package com.rms.rms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedule", uniqueConstraints= @UniqueConstraint(columnNames={"work_date", "user_id"}))
@Data
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "work_date")
    private LocalDate workDate;

    private LocalDateTime startWorkHour;

    private LocalDateTime endWorkHour;

    private Boolean isPresent = Boolean.TRUE;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

}
