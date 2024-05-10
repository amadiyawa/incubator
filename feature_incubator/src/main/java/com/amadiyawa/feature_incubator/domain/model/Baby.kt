package com.amadiyawa.feature_incubator.domain.model

data class Baby(
    val id: String,
    val name: String,
    val dateOfBirth: Long,
    val gender: String,
    val weight: Double,
    val height: Double,
    val motherName: String,
    val fatherName: String,
    val birthPlace: String,
    val bloodType: String,
    val medicalConditions: List<String>,
    val allergies: List<String>,
    val medications: List<String>,
    val currentTemperature: Double,
    val normalTemperatureRange: Pair<Double, Double>,
    val currentHeartRate: Int,
    val normalHeartRateRange: Pair<Int, Int>,
    val arterialRate: Int,
    val bloodPressure: Int,
    val spo2: Double,
    val normalSpo2Range: Pair<Double, Double>,
    val picture: String
)