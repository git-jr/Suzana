package com.paradoxo.suzana.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import kotlinx.coroutines.delay

@Composable
fun MessageItemUser(value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.End
    ) {
        Row {
            Spacer(Modifier.size(50.dp))
            Text(
                value,
                Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(25.dp, 25.dp, 0.dp, 25.dp)
                    )
                    .padding(16.dp),
                color = Color.White,
            )
        }
    }
}

@Composable
fun MessageItemAi(value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Row {
            Box(modifier = Modifier.weight(5f)) {
                Text(
                    value,
                    Modifier
                        .background(
                            color = MaterialTheme.colorScheme.tertiary,
                            shape = RoundedCornerShape(0.dp, 25.dp, 25.dp, 25.dp)
                        )
                        .padding(16.dp),
                    color = Color.Black,
                )
            }
            Spacer(Modifier.size(50.dp))
        }
    }
}

@Composable
fun MessageItemLoad() {
    val defaultOpacity = 0.1f

    val balls = listOf(
        remember { Animatable(defaultOpacity) },
        remember { Animatable(defaultOpacity) },
        remember { Animatable(defaultOpacity) },
    )

    balls.forEachIndexed { index, animatable ->
        LaunchedEffect(animatable) {

            val interval = 210L
            val minDelay = interval * 2
            val maxDelay = interval * 4
            var delay = minDelay
            when (index) {
                0 -> delay = minDelay
                1 -> delay = interval * 3
                2 -> delay = maxDelay
            }

            delay(delay)
            animatable.animateTo(
                targetValue = 1.0f, animationSpec = infiniteRepeatable(
                    animation = tween(maxDelay.toInt(), easing = FastOutLinearInEasing),
                    repeatMode = RepeatMode.Reverse,
                )
            )
        }
    }

    val ballsMap = balls.map { it.value }

    Row(
        Modifier
            .padding(vertical = 12.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ballsMap.forEachIndexed { index, animatable ->
            Box(
                Modifier
                    .size(24.dp)
                    .padding(2.dp)
                    .scale(animatable)
                    .alpha(animatable)
                    .background(color = Color("#bbcaed".toColorInt()), shape = CircleShape)
            )
        }
    }
}