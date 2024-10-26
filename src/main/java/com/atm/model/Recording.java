package com.atm.model;

import com.atm.enums.MediaType;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "recordings")
public class Recording {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "atm_id")
    private Long atmId;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "start_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime startTime;

    @Column(name = "end_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime endTime;

    @Column(name = "media_type", columnDefinition = "SMALLINT")
    private MediaType mediaType;
}
