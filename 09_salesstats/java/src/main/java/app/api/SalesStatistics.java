package app.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.jackson.JsonSnakeCase;

import java.util.Objects;

@JsonSnakeCase
public class SalesStatistics {
    private final String totalSalesAmount;
    private final String averageAmountPerOrder;

    @JsonCreator
    public SalesStatistics(@JsonProperty("total_sales_amount") String totalSalesAmount,
                           @JsonProperty("average_amount_per_order") String averageAmountPerOrder) {
        this.totalSalesAmount = totalSalesAmount;
        this.averageAmountPerOrder = averageAmountPerOrder;
    }

    @JsonProperty
    public String getTotalSalesAmount() {
        return totalSalesAmount;
    }

    @JsonProperty
    public String getAverageAmountPerOrder() {
        return averageAmountPerOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SalesStatistics that = (SalesStatistics) o;
        return Objects.equals(totalSalesAmount, that.totalSalesAmount) &&
            Objects.equals(averageAmountPerOrder, that.averageAmountPerOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalSalesAmount, averageAmountPerOrder);
    }
}
