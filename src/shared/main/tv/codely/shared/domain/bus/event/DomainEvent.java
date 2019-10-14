package tv.codely.shared.domain.bus.event;

import tv.codely.shared.domain.Utils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

public abstract class DomainEvent<T extends DomainEvent<?>> {
    private String aggregateId;
    private String eventId;
    private String occurredOn;

    public DomainEvent(String aggregateId) {
        this.aggregateId = aggregateId;
        this.eventId     = UUID.randomUUID().toString();
        this.occurredOn  = Utils.dateToString(LocalDateTime.now());
    }

    public DomainEvent(String aggregateId, String eventId, String occurredOn) {
        this.aggregateId = aggregateId;
        this.eventId     = eventId;
        this.occurredOn  = occurredOn;
    }

    protected abstract String eventName();

    protected abstract HashMap<String, Serializable> toPrimitives();

    protected abstract T fromPrimitives(
        String aggregateId,
        HashMap<String, Serializable> body,
        String eventId,
        String occurredOn
    );
}
