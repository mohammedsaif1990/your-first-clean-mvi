package com.kordia.yourfirstcleanmvi.data.mapper

import com.kordia.yourfirstcleanmvi.data.db.name.NameData
import com.kordia.yourfirstcleanmvi.domain.entity.NameEntity

fun NameData.toNameEntity() = NameEntity(
    id = id,
    name = name
)

fun NameEntity.toNameData() = NameData(
    id = id,
    name = name
)

fun List<NameData>.toNameEntityList() = this.map { it.toNameEntity() }

fun List<NameEntity>.toNameDataList() = this.map { it.toNameData() }