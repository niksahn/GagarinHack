package com.niksah.gagarin.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Response(
    val file: ByteArray,
    val fields: Map<String, String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Response

        if (!file.contentEquals(other.file)) return false
        if (fields != other.fields) return false

        return true
    }

    override fun hashCode(): Int {
        var result = file.contentHashCode()
        result = 31 * result + fields.hashCode()
        return result
    }
}

@Serializable
data class History(
    @SerialName("file_id")
    val fileId: String,
    val data: Map<Data, String>,
    @SerialName("user_id")
    val userId: String?,
    @SerialName("guid")
    val id: String?,
    val status: Int,
    val type: DocumentType?,
    val series: Int?,
    val number: Int?,
    val page_number: Int?
) {
    @Serializable
    enum class DocumentType {
        @SerialName("personal_passport")
        PERSONAL_PASSPORT,

        @SerialName("vehicle_passport")
        VEHICLE_PASSPORT,

        @SerialName("driver_license")
        DRIVER_LICENSE,

        @SerialName("vehicle_certificate")
        VEHICLE_CERTIFICATE
    }

}

@Serializable
data class Image(
    val image: String
)

enum class Data {
    address_en,
    address_ru,
    birth_date,
    birth_place_en,
    birth_place_ru,
    car_number,
    car_series,
    categories,
    driving_exp,
    expiration_date,
    initial_names_en,
    initial_names_ru,
    issue_date,
    issue_place_ru,
    number_front,
    series_front,
    surname_en,
    surname_ru,
    car,
    car_category,
    car_color,
    car_issue_date,
    car_max_weight,
    car_type,
    ecology_class,
    number,
    engine_type,
    @SerialName("pts-segmentation")
    pts_segmentation,
    series,
    vin,
    apartment_number,
    area,
    car_model_en,
    car_model_ru,
    car_plate,
    car_raw_weight,
    chassis_number,
    country,
    engine_capacity,
    engine_model,
    engine_number,
    engine_power,
    fathersname,
    home_number,
    issue_day,
    issue_month,
    issue_place,
    issue_year,
    locality,
    name_en,
    name_ru,
    region_en,
    region_ru,
    special_marks,
    street,
    temporary_expiration_date,
    temporary_pts_number,
    issue_place_en
}

fun Data.toStr() =
    when (this) {
        Data.address_en -> "Место жительства"
        Data.address_ru -> "Место жительства"
        Data.birth_date -> "Дата рождения"
        Data.birth_place_en -> "Место рождения"
        Data.birth_place_ru -> "Место рождения"
        Data.car_number -> "Номер авто"
        Data.car_series -> "Серия авто"
        Data.categories -> "Категория"
        Data.driving_exp -> "Опыт"
        Data.expiration_date -> "Дата окончания"
        Data.initial_names_en -> "Имя"
        Data.initial_names_ru -> "Имя"
        Data.issue_date -> "Дата выдачи"
        Data.issue_place_ru -> "Место выдачи прав"
        Data.number_front -> "Номер"
        Data.series_front -> "Cерия"
        Data.surname_en -> "Фамилия и отчество"
        Data.surname_ru -> "Фамилия и отчество"
        Data.car -> "Серия и номер ТС"
        Data.car_category -> "Категория ТС"
        Data.car_color -> "Цвет"
        Data.car_issue_date -> "Дата выдачи свидетельства"
        Data.car_max_weight ->"Разрешённая max масса"
        Data.car_type -> "Тип ТС"
        Data.ecology_class -> "Экологический класс"
        Data.number -> "Номер"
        Data.pts_segmentation -> "Категория птс"
        Data.series -> "Серия"
        Data.vin -> "VIN"
        Data.apartment_number -> "Номер квартиры"
        Data.area -> "Нас.пункт"
        Data.car_model_en -> "Модель"
        Data.car_model_ru ->"Модель"
        Data.car_plate -> "Регистрационный знак"
        Data.car_raw_weight -> "Масса без нагрузки"
        Data.chassis_number -> "Номер кузова"
        Data.country -> "Страна"
        Data.engine_capacity -> "Объём двигателя"
        Data.engine_model -> "Модель двигателя"
        Data.engine_number -> "Номер двигателя"
        Data.engine_power -> "Мощность двигателя"
        Data.fathersname ->  "Отчество"
        Data.home_number ->"Дом"
        Data.issue_day -> "День выдачи свидетельства"
        Data.issue_month -> "Месяц выдачи свидетельства"
        Data.issue_place ->"Место выдачи свидетельства"
        Data.issue_year -> "Год выдачи свидетельства"
        Data.locality -> "Регион"
        Data.name_en -> "Имя"
        Data.name_ru -> "Имя"
        Data.region_en -> "Регион"
        Data.region_ru -> "Регион"
        Data.special_marks ->"Особые отметки"
        Data.street -> "Улица"
        Data.temporary_expiration_date -> "Дата окончания действия"
        Data.temporary_pts_number -> "Серия и номер удостоверения"
        Data.engine_type -> "Тип двигателя"
        Data.issue_place_en -> "Место выдачи "
    }
