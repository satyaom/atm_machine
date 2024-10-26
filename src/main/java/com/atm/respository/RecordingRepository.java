package com.atm.respository;

import com.atm.model.Recording;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface RecordingRepository extends JpaRepository<Recording, Long> {

    @Query(nativeQuery = true, value = "select * from recordings where media_type=?1 and start_time >= ?2 and end_time <= ?3")
    public List<Recording> getRecordingsByTypeAndTime(short mediaType, OffsetDateTime startTime, OffsetDateTime endTime);
}
