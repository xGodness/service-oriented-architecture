package ru.xgodness.endpoint.labworks;

import ru.xgodness.endpoint.labworks.dto.Difficulty;
import ru.xgodness.endpoint.labworks.dto.Labwork;
import ru.xgodness.http.ExternalApiCaller;
import ru.xgodness.http.LabworkRequestBody;

public class LabworkService {
    public static Labwork increaseDifficultyByStepCount(long id, int stepCount) {
        Labwork labwork = ExternalApiCaller.getLabworkById(id);
        labwork.setDifficulty(
                Difficulty.increase(labwork.getDifficulty(), stepCount)
        );
        return ExternalApiCaller.updateLabworkById(id, new LabworkRequestBody(labwork));
    }
}
