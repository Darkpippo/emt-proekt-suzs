package mk.ukim.finki.emt.sharedkernel.domain.base;

import jakarta.persistence.*;

import java.util.Objects;

@Embeddable
public class PatientId {

    private Long id;

    protected PatientId() {}

    public PatientId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientId that = (PatientId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}