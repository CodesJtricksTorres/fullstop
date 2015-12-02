package org.zalando.stups.fullstop.violation.entity;

import com.google.common.base.MoreObjects;
import org.zalando.stups.fullstop.violation.domain.AbstractModifiableEntity;

import javax.persistence.*;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by gkneitschel.
 */

@Table(name = "application", schema = "fullstop_data")
@Entity
public class ApplicationEntity extends AbstractModifiableEntity {

    @Column(unique = true)
    private String name;

    @ManyToMany
    private List<VersionEntity> versionEntities;

    public ApplicationEntity() {
    }

    public ApplicationEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VersionEntity> getVersionEntities() {

        if (versionEntities == null) {
            versionEntities = newArrayList();
        }

        return versionEntities;
    }

    public void setVersionEntities(
            List<VersionEntity> versionEntities) {
        this.versionEntities = versionEntities;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ApplicationEntity that = (ApplicationEntity) o;

        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        return !(versionEntities != null ?
                !versionEntities.equals(that.versionEntities) :
                that.versionEntities != null);

    }

    @Override public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (versionEntities != null ? versionEntities.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                          .add("name", name)
                          .toString();
    }
}
