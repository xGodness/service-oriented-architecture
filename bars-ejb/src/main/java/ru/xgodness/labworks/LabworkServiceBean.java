package ru.xgodness.labworks;

import jakarta.ejb.Stateless;
import ru.xgodness.labworks.dto.Difficulty;
import ru.xgodness.labworks.dto.Labwork;
import ru.xgodness.http.ExternalApiCaller;
import ru.xgodness.http.LabworkRequestBody;

@Stateless(name = "LabworkService")
public class LabworkServiceBean implements LabworkService  {
    public Labwork increaseDifficultyByStepCount(long id, int stepCount) {
        Labwork labwork = ExternalApiCaller.getLabworkById(id);
        labwork.setDifficulty(
                Difficulty.increase(labwork.getDifficulty(), stepCount)
        );
        return ExternalApiCaller.updateLabworkById(id, new LabworkRequestBody(labwork));
    }
}
