import ca.bc.gov.gba.model.GbaTables;
import ca.bc.gov.gba.ui.BatchUpdateDialog;
import ca.bc.gov.gba.ui.StatisticsDialog;

import com.revolsys.io.Reader;
import com.revolsys.record.Record;
import com.revolsys.record.query.Query;
import com.revolsys.record.schema.RecordStore;
import com.revolsys.transaction.Transaction;

public class IntegrationSessionPolyCount extends BatchUpdateDialog {
  public static void main(final String[] args) {
    StatisticsDialog.start(IntegrationSessionPolyCount.class);
  }

  public IntegrationSessionPolyCount() {
    super("Integration Session Poly Count");
    newStatistics("Read", GbaTables.INTEGRATION_SESSION_POLY);
    autoSize();
  }

  @Override
  public boolean batchUpdate(final Transaction transaction) {
    final Query query = new Query(GbaTables.INTEGRATION_SESSION_POLY);
    final RecordStore recordStore = getRecordStore();
    try (
      Reader<Record> roadReader = recordStore.getRecords(query)) {
      for (final Record record : roadReader) {
        addStatistic("Read", GbaTables.INTEGRATION_SESSION_POLY);
      }
    }
    return true;
  }
}
