package endpoint.minimalpoints;

import model.generated.tables.Labwork;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import orm.DSLContextProvider;

public class MinimalPointService {
    private static final DSLContext context = DSLContextProvider.getContext();

    public static Double sumMinimalPoints() {
        Result<Record1<Double>> result = context.select(Labwork.LABWORK.MINIMAL_POINT).from(Labwork.LABWORK).fetch();
        if (result.isEmpty()) return null;
        double sum = 0;
        for (Record1<Double> record : result) {
            sum += record.value1();
        }
        return sum;
    }
}
