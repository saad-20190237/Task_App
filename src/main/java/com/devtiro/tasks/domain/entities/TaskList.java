package com.devtiro.tasks.domain.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "task_list")
public class TaskList {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private String title;


    private String description;

    @OneToMany(mappedBy = "taskList" , cascade = {CascadeType.REMOVE , CascadeType.PERSIST})
    private List<Task> tasks  ;

    private LocalDate creationDate;

    private LocalDate updateDate;
}
