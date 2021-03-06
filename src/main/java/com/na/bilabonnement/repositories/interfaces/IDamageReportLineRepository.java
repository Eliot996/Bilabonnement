package com.na.bilabonnement.repositories.interfaces;

import com.na.bilabonnement.models.DamageReportLine;

import java.util.List;

public interface IDamageReportLineRepository extends IRepository<DamageReportLine>
{
    DamageReportLine getSingleEntityByLinenumberAndDamageReportId(int lineNumber, int damageReportId);
    List<DamageReportLine> getAllEntitiesWithDamageReportId(int damageReportId);
    boolean deleteById(int lineNumber, int damageReportId);
}
