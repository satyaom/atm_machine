package com.atm.controller;

import com.atm.enums.MediaType;
import com.atm.model.FailureLog;
import com.atm.model.Recording;
import com.atm.service.FailureLogService;
import com.atm.service.RecordingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/recordings")
public class RecordingController {
    @Autowired
    RecordingService recordingService;

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> saveFailureLog(
            @RequestBody Recording recording
    ) {
        Map<String, Object> res = new HashMap<>();
        res.put("recoding", recordingService.saveRecording(recording));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadMedia(
            @RequestParam(value = "start_time_epoch") Long startTimeEpoch,
            @RequestParam(value = "end_time_epoch") Long endTimeEpoch,
            @RequestParam(value = "media_type") MediaType mediaType
    ) throws Exception{

        OffsetDateTime startTime = OffsetDateTime.ofInstant(Instant.ofEpochMilli(startTimeEpoch), ZoneOffset.UTC);
        OffsetDateTime endTime = OffsetDateTime.ofInstant(Instant.ofEpochMilli(endTimeEpoch), ZoneOffset.UTC);

        List<Recording> mediaFiles = recordingService.listRecordings(mediaType,startTime, endTime);

        Path zipFilePath = Files.createTempFile("media_files_", ".zip");
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFilePath.toFile()))) {

            RestTemplate restTemplate = new RestTemplate();
            for (Recording mediaFile : mediaFiles) {
                String link = mediaFile.getFilePath();
                String fileName = Paths.get(new URL(link).getPath()).getFileName().toString();
                ZipEntry zipEntry = new ZipEntry(fileName);
                zos.putNextEntry(zipEntry);

                ResponseEntity<Resource> response = restTemplate.exchange(link, HttpMethod.GET, null, Resource.class);
                Resource resource = response.getBody();

                if (resource != null) {
                    try (InputStream inputStream = resource.getInputStream()) {
                        inputStream.transferTo(zos);
                    }
                }
                zos.closeEntry();
            }
        }

        InputStreamResource resource = new InputStreamResource(new FileInputStream(zipFilePath.toFile()));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=media_files.zip")
                .contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
