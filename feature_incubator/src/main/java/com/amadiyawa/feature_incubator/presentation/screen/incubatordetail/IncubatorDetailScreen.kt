package com.amadiyawa.feature_incubator.presentation.screen.incubatordetail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandCircleDown
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.outlined.MonitorHeart
import androidx.compose.material.icons.outlined.MonitorWeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.amadiyawa.feature_base.common.res.Dimen
import com.amadiyawa.feature_base.common.util.formatDate
import com.amadiyawa.feature_base.presentation.theme.AppColor
import com.amadiyawa.feature_base.presentation.compose.composable.DrawHorizontalDottedLine
import com.amadiyawa.feature_base.presentation.compose.composable.ExpandableRow
import com.amadiyawa.feature_base.presentation.compose.composable.PlaceholderImage
import com.amadiyawa.feature_base.presentation.compose.composable.TextTitleMedium
import com.amadiyawa.feature_base.presentation.compose.composable.TextTitleSmall
import com.amadiyawa.feature_incubator.R
import com.amadiyawa.feature_incubator.domain.model.Baby
import com.amadiyawa.feature_incubator.domain.model.Incubator
import com.amadiyawa.feature_incubator.presentation.compose.composable.Toolbar

@Composable
fun IncubatorDetailScreen(
    onBackClick: () -> Unit,
    incubatorId: String
) {
    Scaffold(
        contentColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            SetUpToolbar(
                incubatorId = incubatorId,
                onBackClick = onBackClick
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { paddingValues ->
        SetupContent(
            paddingValues = paddingValues,
        )
    }
}

@Composable
private fun SetUpToolbar(
    incubatorId: String,
    onBackClick: () -> Unit
) {
    Toolbar(
        centered = true,
        title = stringResource(R.string.incubator) + " " + incubatorId,
        onBackClick = onBackClick
    )
}

@Composable
private fun SetupContent(
    paddingValues: PaddingValues,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ){
        HandleUiState()
    }
}

@Composable
private fun HandleUiState() {
    val incubator = Incubator(
        id = "1",
        model = "Model X",
        manufacturer = "Manufacturer Y",
        manufactureDate = 1633046400000L,
        lastMaintenanceDate = 1635638400000L,
        nextMaintenanceDate = 1638220800000L,
        location = "Location Z",
        status = "active",
        currentTemperature = 36.5,
        normalTemperatureRange = Pair(36.0, 37.0),
        currentHumidity = 45.0,
        normalHumidityRange = Pair(40.0, 50.0),
        currentOxygenLevel = 21.0,
        normalOxygenLevelRange = Pair(20.0, 22.0),
        baby = Baby(
            id = "1",
            name = "Erwin Smith",
            dateOfBirth = 1633046400000L,
            gender = "male",
            weight = 3.5,
            height = 50.0,
            motherName = "Mother A",
            fatherName = "Father A",
            birthPlace = "Place A",
            bloodType = "O+",
            medicalConditions = listOf("Condition A", "Condition B"),
            allergies = listOf("Allergy A", "Allergy B"),
            medications = listOf("Medication A", "Medication B"),
            currentTemperature = 33.6,
            normalTemperatureRange = Pair(36.0, 37.0),
            currentHeartRate = 120,
            normalHeartRateRange = Pair(110, 130),
            arterialRate = 80,
            bloodPressure = 120,
            spo2 = 46.0,
            normalSpo2Range = Pair(40.0, 50.0),
            picture = "https://www.example.com/picture.jpg",
            currentHumidity = 55.0,
            normalHumidityRange = Pair(40.0, 50.0),
        )
    )

    TemperatureOverview(incubator = incubator)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Dimen.Spacing.medium),
        verticalArrangement = Arrangement.spacedBy(Dimen.Spacing.small)
    ) {
        Column {
            IncubatorVideoStream()
            DesktopIconBottom()
        }
        BabyDetails(baby = incubator.baby)
        IncubatorDetails(incubator = incubator)
    }
}

@Composable
private fun TemperatureOverview(incubator: Incubator) {
    val incubatorTemperatureString = "${incubator.currentTemperature} \u00B0C"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(Dimen.Spacing.large),
        horizontalArrangement = Arrangement.spacedBy(Dimen.Spacing.large),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                Dimen.Spacing.medium,
                Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id =R.drawable.thermometer),
                contentDescription = incubator.currentTemperature.toString(),
                tint = AppColor.DodgerBlue
            )

            Column {
                TextTitleSmall(text = stringResource(id = R.string.temperature))
                TextTitleSmall(
                    text = incubatorTemperatureString,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.spacedBy(
                Dimen.Spacing.medium,
                Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                TextTitleSmall(text = stringResource(id = R.string.humidity))
                TextTitleSmall(
                    text = "${incubator.currentHumidity} RH",
                    fontWeight = FontWeight.Bold
                )
            }

            Icon(
                painter = painterResource(id =R.drawable.humidity_high),
                contentDescription = incubator.currentHumidity.toString(),
                tint = AppColor.SkyBlue
            )
        }
    }
}

@Composable
private fun IncubatorVideoStream() {
    var expanded by remember { mutableStateOf (true) }

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .background(color = AppColor.Charcoal)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimen.Spacing.large)
                ) {
                    TextTitleMedium(
                        text = stringResource(id = R.string.live_incubator),
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.weight(1f))

                    Icon(
                        imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandCircleDown,
                        contentDescription = "left arrow",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            AnimatedVisibility(visible = expanded) {
                LiveStream()
            }
        }
    }
}

@Composable
fun LiveStream() {
    Box(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .background(color = AppColor.DimGray)
            .border(
                width = Dimen.Spacing.small,
                color = AppColor.Charcoal,
                shape = RoundedCornerShape(
                    bottomStart = Dimen.Spacing.medium,
                    bottomEnd = Dimen.Spacing.medium
                )
            )
    ) {
        TextTitleSmall(
            text = stringResource(id = R.string.video_player),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun DesktopIconBottom() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(Dimen.Size.extraSmall)
                .background(color = AppColor.Charcoal)
        )

        Box(
            modifier = Modifier
                .width(Dimen.Size.extraLarge)
                .height(Dimen.Spacing.medium)
                .background(color = AppColor.Charcoal)
        )
    }
}

@Composable
private fun IncubatorDetails(
    incubator: Incubator
) {
    var expandedIncubator by remember { mutableStateOf (false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimen.Spacing.large),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(Dimen.Size.small),
                    painter = painterResource(id = R.drawable.cameroon_flag_icon),
                    contentDescription = stringResource(id = R.string.country_flag),
                    tint = Color.Unspecified
                )

                Spacer(modifier = Modifier.weight(1f))

                TextTitleMedium(
                    text = stringResource(id = R.string.incubator_settings),
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    modifier = Modifier
                        .size(Dimen.Size.large),
                    painter = painterResource(id = R.drawable.aui),
                    contentDescription = stringResource(id = R.string.country_flag),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            DrawHorizontalDottedLine(
                color = MaterialTheme.colorScheme.onSurface,
                startPadding = 0.dp,
                endPadding = 0.dp
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimen.Spacing.large),
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(Dimen.Spacing.large)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(Dimen.Spacing.medium, Alignment.CenterHorizontally),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                TextTitleSmall(text = stringResource(id = R.string.humidity))
                                TextTitleSmall(
                                    text = incubator.currentHumidity.toString() + " %",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(Dimen.Spacing.medium, Alignment.CenterHorizontally),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                TextTitleSmall(text = stringResource(id = R.string.temperature))
                                TextTitleSmall(
                                    text = "${incubator.currentTemperature} \u00B0C" ,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Column(
                        verticalArrangement = Arrangement.spacedBy(Dimen.Spacing.large)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(Dimen.Spacing.medium, Alignment.CenterHorizontally),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                TextTitleSmall(text = stringResource(id = R.string.normal_humidity))
                                TextTitleSmall(
                                    text = incubator.normalHumidityRange.toString() + " %",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(Dimen.Spacing.medium, Alignment.CenterHorizontally),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                TextTitleSmall(text = stringResource(id = R.string.normal_temperature))
                                TextTitleSmall(
                                    text = incubator.normalTemperatureRange.toString() + " \u00B0C",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.background)

            ExpandableRow(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
                expanded = expandedIncubator
            ) {
                expandedIncubator = !expandedIncubator
            }

            AnimatedVisibility(visible = expandedIncubator) {
                MoreIncubatorInfo(incubator = incubator)
            }
        }
    }
}

@Composable
private fun MoreIncubatorInfo(incubator: Incubator) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimen.Spacing.large),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Column(
                verticalArrangement = Arrangement.spacedBy(Dimen.Spacing.large)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Dimen.Spacing.medium, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        TextTitleSmall(text = stringResource(id = R.string.model))
                        TextTitleSmall(
                            text = incubator.model,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(Dimen.Spacing.medium, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        TextTitleSmall(text = stringResource(id = R.string.location))
                        TextTitleSmall(
                            text = incubator.location,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(Dimen.Spacing.medium, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        TextTitleSmall(text = stringResource(id = R.string.last_maintenance))
                        TextTitleSmall(
                            text = formatDate(incubator.lastMaintenanceDate),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                verticalArrangement = Arrangement.spacedBy(Dimen.Spacing.large)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Dimen.Spacing.medium, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        TextTitleSmall(text = stringResource(id = R.string.manufacturer))
                        TextTitleSmall(
                            text = incubator.manufacturer,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(Dimen.Spacing.medium, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        TextTitleSmall(text = stringResource(id = R.string.status))
                        TextTitleSmall(
                            text = incubator.status,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(Dimen.Spacing.medium, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        TextTitleSmall(text = stringResource(id = R.string.next_maintenance))
                        TextTitleSmall(
                            text = formatDate(incubator.nextMaintenanceDate),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BabyDetails(
    baby: Baby
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimen.Spacing.large),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(Dimen.Size.small),
                    painter = painterResource(id = R.drawable.cameroon_flag_icon),
                    contentDescription = stringResource(id = R.string.country_flag),
                    tint = Color.Unspecified
                )

                Spacer(modifier = Modifier.weight(1f))

                TextTitleMedium(
                    text = stringResource(id = R.string.baby_parameters),
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    modifier = Modifier
                        .size(Dimen.Size.large),
                    painter = painterResource(id = R.drawable.aui),
                    contentDescription = stringResource(id = R.string.country_flag),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            DrawHorizontalDottedLine(
                color = MaterialTheme.colorScheme.onSurface,
                startPadding = 0.dp,
                endPadding = 0.dp
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimen.Spacing.large),
                verticalArrangement = Arrangement.spacedBy(Dimen.Spacing.medium, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BabyOverview(baby = baby)

                Row {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(Dimen.Spacing.large)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                Dimen.Spacing.medium,
                                Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.MonitorWeight,
                                contentDescription = baby.weight.toString(),
                                tint = AppColor.MintCream,
                                modifier = Modifier.size(Dimen.Size.medium)
                            )

                            Column {
                                TextTitleSmall(text = stringResource(id = R.string.weight))
                                TextTitleSmall(
                                    text = baby.weight.toString() + " kg",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                Dimen.Spacing.medium,
                                Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.blood_pressure),
                                contentDescription = baby.weight.toString(),
                                tint = AppColor.Tomato,
                                modifier = Modifier.size(Dimen.Size.medium)
                            )

                            Column {
                                TextTitleSmall(text = stringResource(id = R.string.blood_pressure))
                                TextTitleSmall(
                                    text = baby.bloodPressure.toString() + " mmHg",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                Dimen.Spacing.medium,
                                Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.humidity_high),
                                contentDescription = baby.weight.toString(),
                                tint = AppColor.SkyBlue,
                                modifier = Modifier.size(Dimen.Size.medium)
                            )

                            Column {
                                TextTitleSmall(text = stringResource(id = R.string.humidity))
                                TextTitleSmall(
                                    text = baby.currentHumidity.toString() + " %",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                Dimen.Spacing.medium,
                                Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id =R.drawable.spo2),
                                contentDescription = baby.weight.toString(),
                                tint = AppColor.DeepSkyBlue,
                                modifier = Modifier.size(Dimen.Size.medium)
                            )

                            Column {
                                TextTitleSmall(text = stringResource(id = R.string.spo2))
                                TextTitleSmall(
                                    text = baby.spo2.toString() + " %",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Column(
                        verticalArrangement = Arrangement.spacedBy(Dimen.Spacing.large)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                Dimen.Spacing.medium,
                                Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id =R.drawable.thermometer),
                                contentDescription = baby.currentTemperature.toString(),
                                tint = AppColor.DodgerBlue,
                                modifier = Modifier.size(Dimen.Size.medium)
                            )

                            Column {
                                TextTitleSmall(text = stringResource(id = R.string.temperature))
                                TextTitleSmall(
                                    text = "${baby.currentTemperature} \u00B0C",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                Dimen.Spacing.medium,
                                Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.MonitorHeart,
                                contentDescription = baby.weight.toString(),
                                tint = AppColor.Tomato,
                                modifier = Modifier.size(Dimen.Size.medium)
                            )

                            Column {
                                TextTitleSmall(text = stringResource(id = R.string.heart_rate))
                                TextTitleSmall(
                                    text = baby.currentHeartRate.toString() + " Bpm",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                Dimen.Spacing.medium,
                                Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.humidity_mid),
                                contentDescription = baby.weight.toString(),
                                tint = AppColor.SkyBlue,
                                modifier = Modifier.size(Dimen.Size.medium)
                            )

                            Column {
                                TextTitleSmall(text = stringResource(id = R.string.normal_humidity))
                                TextTitleSmall(
                                    text = baby.normalHumidityRange.toString() + " %",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                Dimen.Spacing.medium,
                                Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id =R.drawable.thermometer),
                                contentDescription = baby.currentTemperature.toString(),
                                tint = AppColor.SkyBlue,
                                modifier = Modifier.size(Dimen.Size.medium)
                            )

                            Column {
                                TextTitleSmall(text = stringResource(id = R.string.normal_temperature))
                                TextTitleSmall(
                                    text = "${baby.normalTemperatureRange} \u00B0C",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BabyOverview(baby: Baby) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface
            )
            .padding(start = Dimen.Spacing.large, bottom = Dimen.Spacing.large),
        horizontalArrangement = Arrangement.spacedBy(
            Dimen.Spacing.small,
            Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Row(modifier = Modifier.size(Dimen.Picture.mediumSize)) {
            PlaceholderImage(
                url = baby.picture,
                contentDescription = baby.picture,
                gender = baby.gender,
                size = Dimen.Picture.mediumSize
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(
                Dimen.Spacing.extraSmall,
                Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.Start,
        ) {
            TextTitleMedium(
                text = baby.name,
                fontWeight = FontWeight.Bold
            )

            TextTitleSmall(formatDate(baby.dateOfBirth))
        }
    }
}
