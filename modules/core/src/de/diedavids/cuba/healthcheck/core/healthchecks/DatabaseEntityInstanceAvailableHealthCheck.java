package de.diedavids.cuba.healthcheck.core.healthchecks;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import de.diedavids.cuba.healthcheck.entity.HealthCheckReportDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * Abstract superclass for defining a check against the database for a particular entity instance in-place
 *
 * This is normally useful if you want to enforce some database entries to exists, like
 * - reports with certain codes
 * - security groups or constraints
 * - bpm definitions
 */
public abstract class DatabaseEntityInstanceAvailableHealthCheck<T extends Entity> extends DefaultHealthCheck {

    private Logger log = LoggerFactory.getLogger(DatabaseEntityInstanceAvailableHealthCheck.class);

    @Inject
    protected DataManager dataManager;

    @Override
    public HealthCheckReportDetail check() {
        LoadContext<T> loadContext = createSingleEntityLoadContext();

        try {
            T item = dataManager.load(loadContext);
            if (item != null) {
                return success("Entity instance found");
            } else {
                return error("Entity instance was not found in the database");
            }
        }
        catch (Exception e) {
            log.error("Error while executing check (" + e.getMessage() + ")", e);
            return error("Error while executing check", e.getMessage());
        }

    }

    /**
     * defines the LoadContext for the query for the single entity instance
     * that should get loaded from the database
     *
     * @return the loadContext describing the load of the single entity instance
     */
    protected abstract LoadContext<T> createSingleEntityLoadContext();

}
