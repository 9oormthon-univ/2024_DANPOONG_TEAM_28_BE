package com.be.mission.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MissionDifficulty {
    EASY("easy"),
    NORMAL("normal"),
    HARD("hard");

    private final String value;

    @JsonCreator
    public static MissionDifficulty from(String value) {
        for (MissionDifficulty missionDifficulty: MissionDifficulty.values()) {
            if (missionDifficulty.value.equals(value)) {
                return missionDifficulty;
            }
        }
        return null;
    }
}
