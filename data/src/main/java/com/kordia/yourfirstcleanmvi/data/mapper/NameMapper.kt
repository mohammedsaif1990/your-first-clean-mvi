package com.kordia.yourfirstcleanmvi.data.mapper

import com.kordia.yourfirstcleanmvi.data.db.name.NameData
import com.kordia.yourfirstcleanmvi.domain.model.NameDto

fun NameData.toNameDto() = NameDto(
    id = id,
    name = name
)

fun NameDto.toNameData() = NameData(
    id = id,
    name = name
)

fun List<NameData>.toNameDtoList() = this.map { it.toNameDto() }

fun List<NameDto>.toNameDataList() = this.map { it.toNameData() }