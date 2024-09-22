package mk.ukim.finki.emt.sharedkernel.domain.base;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class AppointmentId {

    private Long id;

    protected AppointmentId() {}

    public AppointmentId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentId that = (AppointmentId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}