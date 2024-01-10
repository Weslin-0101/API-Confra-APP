package com.confra.api.infra.gateways.util;

public interface EntityMapper<Entity, Domain> {
    Entity toEntity(Domain domain);
    Domain toDomainObject(Entity entity);
}
