import ca.bc.gov.gba.model.GbaTables;
import ca.bc.gov.gba.ui.BatchUpdateDialog;
import ca.bc.gov.gba.ui.StatisticsDialog;

import com.revolsys.record.Record;
import com.revolsys.record.io.RecordReader;
import com.revolsys.record.query.Query;
import com.revolsys.record.schema.RecordStore;
import com.revolsys.transaction.Transaction;

public class IntegrationSessionPolyCount extends BatchUpdateDialog {
  public static void main(final String[] args) {
    StatisticsDialog.start(IntegrationSessionPolyCount.class);
  }

  public IntegrationSessionPolyCount() {
    super("Integration Session Poly Count");
    newLabelCount(COUNTS, "Read", GbaTables.INTEGRATION_SESSION_POLY);
  }

  @Override
  public boolean batchUpdate(final Transaction transaction) {
    final Query query = new Query(GbaTables.INTEGRATION_SESSION_POLY);
    final RecordStore recordStore = getRecordStore();
    try (
      RecordReader roadReader = recordStore.getRecords(query)) {
      for (final Record record : roadReader) {
        addLabelCount(COUNTS, GbaTables.INTEGRATION_SESSION_POLY, "Read");
      }
    }
    return true;
  }
}
