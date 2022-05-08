package br.com.releasesolutions.dao;

import br.com.releasesolutions.models.Lease;
import java.util.List;

public interface LeaseDAO {

    void save(Lease lease);

    List<Lease> getPendingLeases();
}
