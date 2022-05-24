package com.na.bilabonnement.repositories.interfaces;

import com.na.bilabonnement.models.DamageReportLine;

import java.util.List;

public interface IDamageReportLineRepository extends IRepository<DamageReportLine>
{
    List<DamageReportLine> getAllEntitiesWithDamageReportId(int damageReportId);
}
