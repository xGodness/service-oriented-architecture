package ru.xgodness.labworks;

import jakarta.ejb.Stateless;
import org.jboss.ejb3.annotation.Pool;
import ru.xgodness.endpoint.labworks.model.dto.Difficulty;
import ru.xgodness.endpoint.labworks.model.dto.Labwork;
import ru.xgodness.http.ExternalApiCaller;
import ru.xgodness.http.LabworkRequestBody;

@Stateless(name = "LabworkService")
@Pool("slsb-strict-max-pool")
public class LabworkServiceBean implements LabworkService {
    public Labwork increaseDifficultyByStepCount(long id, int stepCount) {
        Labwork labwork = ExternalApiCaller.getLabworkById(id);
        labwork.setDifficulty(
                Difficulty.increase(labwork.getDifficulty(), stepCount)
        );
        return ExternalApiCaller.updateLabworkById(id, new LabworkRequestBody(labwork));
    }
}
