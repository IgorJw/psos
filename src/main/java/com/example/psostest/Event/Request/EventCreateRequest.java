package com.example.psostest.Event.Request;

import com.example.psostest.Event.Enum.EventPriority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventCreateRequest {
    String content;
    LocalDate date;
    EventPriority priority;
    Integer subjectId;
}
