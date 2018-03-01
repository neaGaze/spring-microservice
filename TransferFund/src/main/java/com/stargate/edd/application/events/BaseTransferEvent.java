package com.stargate.edd.application.events;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import org.springframework.context.ApplicationEvent;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class BaseTransferEvent /*extends ApplicationEvent*/ implements Serializable {

	private static final long serialVersionUID = 5256302746470799059L;
	
	@JsonIgnore
	private final Instant instant;

	protected BaseTransferEvent() {
		super();
		instant = Instant.now();
	}
	
	protected BaseTransferEvent(Object obj) {
		//super(obj);
		instant = Instant.now();
	}

	protected BaseTransferEvent(Object obj, final Instant instant) {
		//super(obj);
		Objects.requireNonNull(instant);
		this.instant = instant;
	}

	public Instant getInstant() {
		return instant;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		final BaseTransferEvent that = (BaseTransferEvent) o;

		return instant.equals(that.instant);
	}

	@Override
	public int hashCode() {
		return instant.hashCode();
	}
}
