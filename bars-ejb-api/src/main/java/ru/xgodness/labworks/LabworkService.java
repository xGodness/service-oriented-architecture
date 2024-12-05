package ru.xgodness.labworks;

import jakarta.ejb.Remote;
import ru.xgodness.endpoint.labworks.model.dto.Labwork;

@Remote
public interface LabworkService {
    Labwork increaseDifficultyByStepCount(long id, int stepCount);
}
