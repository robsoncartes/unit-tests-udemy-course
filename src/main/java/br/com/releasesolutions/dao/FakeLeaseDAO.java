package br.com.releasesolutions.dao;

import br.com.releasesolutions.models.Lease;
import java.util.List;

public class FakeLeaseDAO implements LeaseDAO {

    @Override
    public void save(Lease lease) {

    }

    @Override
    public List<Lease> getPendingLeases() {
        return null;
    }
}
