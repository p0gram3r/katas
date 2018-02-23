package app.core;

import jersey.repackaged.com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class SalesAmountSnapshotTest {

    private static final Collection<BigDecimal> EMTPY_LIST = ImmutableList.of();

    private static final Collection<BigDecimal> NON_EMPTY_LIST = ImmutableList.of(
        new BigDecimal(15),
        new BigDecimal(16),
        new BigDecimal(17),
        new BigDecimal(18),
        new BigDecimal(19),
        new BigDecimal(20)
    );

    @Test
    public void getSumReturnsZeroForEmptyCollection() {
        SalesAmountSnapshot snap = new SalesAmountSnapshot(EMTPY_LIST);
        assertThat(snap.getSum()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void getSumWorksForNonEmptyCollection() {
        SalesAmountSnapshot snap = new SalesAmountSnapshot(NON_EMPTY_LIST);
        assertThat(snap.getSum()).isEqualTo(BigDecimal.valueOf(105));
    }

    @Test
    public void getMeanReturnsZeroForEmptyCollection() {
        SalesAmountSnapshot snap = new SalesAmountSnapshot(EMTPY_LIST);
        assertThat(snap.getMean()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void getMeanWorksForNonEmptyCollection() {
        SalesAmountSnapshot snap = new SalesAmountSnapshot(NON_EMPTY_LIST);
        assertThat(snap.getMean()).isEqualTo(BigDecimal.valueOf(18));
    }
}
