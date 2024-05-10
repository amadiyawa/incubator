package com.amadiyawa.feature_incubator.domain.model

data class Incubator(
    val id: String,
    val model: String,
    val manufacturer: String,
    val manufactureDate: Long,
    val lastMaintenanceDate: Long,
    val nextMaintenanceDate: Long,
    val location: String,
    val status: String, // can be "active", "inactive", "maintenance", etc.
    val currentTemperature: Double,
    val normalTemperatureRange: Pair<Double, Double>,
    val currentHumidity: Double,
    val normalHumidityRange: Pair<Double, Double>,
    val currentOxygenLevel: Double,
    val normalOxygenLevelRange: Pair<Double, Double>,
    val baby: Baby
)