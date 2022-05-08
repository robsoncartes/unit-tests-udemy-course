package br.com.releasesolutions.dao;

import br.com.releasesolutions.models.Lease;
import java.util.Collections;
import java.util.List;

public class FakeLeaseDAO implements LeaseDAO {

    @Override
    public void save(Lease lease) throws UnsupportedOperationException {

    }

    @Override
    public List<Lease> getPendingLeases() {
        return Collections.emptyList();
    }
}
