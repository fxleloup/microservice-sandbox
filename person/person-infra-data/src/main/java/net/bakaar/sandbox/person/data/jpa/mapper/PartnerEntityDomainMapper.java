package net.bakaar.sandbox.person.data.jpa.mapper;

import net.bakaar.sandbox.person.data.jpa.entity.PersonEntity;
import net.bakaar.sandbox.person.domain.entity.Partner;

public class PartnerEntityDomainMapper {
    public Partner mapToDomain(PersonEntity entity) {
        return Partner.of(entity.getPnummer(), entity.getName(), entity.getForename(), entity.getBirthDate());
    }

    public PersonEntity mapToEntity(Partner partner) {
        PersonEntity entity = new PersonEntity();
        entity.setName(partner.getName());
        entity.setForename(partner.getForename());
        entity.setBirthDate(partner.getBirthDate());
        entity.setPnummer(partner.getId().getValue());
        return entity;
    }
}
