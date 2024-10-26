package com.atm.service;

import com.atm.enums.MediaType;
import com.atm.model.Recording;
import com.atm.respository.RecordingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class RecordingService {
    @Autowired
    RecordingRepository recordingRepository;

    public Recording saveRecording(Recording recording) {
        return recordingRepository.save(recording);
    }

    public List<Recording> listRecordings(MediaType mediaType, OffsetDateTime startTime, OffsetDateTime endTime) {
        return recordingRepository.getRecordingsByTypeAndTime((short) mediaType.getVal(), startTime, endTime);
    }
}
