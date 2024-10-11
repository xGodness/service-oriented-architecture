package ru.xgodness.endpoint.minimalpoints;

import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import ru.xgodness.orm.DSLContextProvider;

import static ru.xgodness.model.generated.tables.Labwork.LABWORK;

public class MinimalPointService {
    private static final DSLContext context = DSLContextProvider.getContext();

    public static Double sumMinimalPoints() {
        Result<Record1<Double>> result = context.select(LABWORK.MINIMAL_POINT).from(LABWORK).fetch();
        if (result.isEmpty()) return null;
        double sum = 0;
        for (Record1<Double> record : result) {
            sum += record.value1();
        }
        return sum;
    }
}
