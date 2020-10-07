package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class ProductEvent {
    @Setter
    @Getter
    private Long eventId;

    @Setter
    @Getter
    private String eventType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEvent that = (ProductEvent) o;
        return Objects.equals(eventId, that.eventId) &&
                Objects.equals(eventType, that.eventType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventType);
    }

    @Override
    public String toString() {
        return "model.ProductEvent{" +
                "eventId='" + eventId + '\'' +
                ", eventType='" + eventType + '\'' +
                '}';
    }
}
