package com.example.ekzamen// Убедись, что пакет совпадает с твоим

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Устанавливаем тему Material 3 (она будет базовой оболочкой)
            MaterialTheme {
                // Вызываем наш основной composable-экран
                ThemeSwitcherScreen()
            }
        }
    }
}

// Определяем наши кастомные цвета для светлой и темной тем
private val LightBackground = Color(0xFFF5F5F5) // Светло-серый фон
private val LightText = Color(0xFF212121)       // Почти черный текст

private val DarkBackground = Color(0xFF212121)  // Темно-серый фон
private val DarkText = Color(0xFFF5F5F5)        // Светлый текст

@Composable
fun ThemeSwitcherScreen() {
    // Состояние переключателя. false = светлая тема, true = темная.
    var isDarkTheme by remember { mutableStateOf(false) }

    // Выбираем текущие цвета в зависимости от состояния переключателя
    val backgroundColor = if (isDarkTheme) DarkBackground else LightBackground
    val textColor = if (isDarkTheme) DarkText else LightText
    val switchLabelColor = if (isDarkTheme) Color.White else Color.Black

    // Контейнер экрана с динамическим фоном
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor), // <-- Вот где меняется фон!
        contentAlignment = Alignment.Center
    ) {
        // Карточка в центре, чтобы контент выглядел аккуратно
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isDarkTheme) Color.DarkGray else Color.White
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Заголовок, цвет которого меняется
                Text(
                    text = "Настройки темы",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor // <-- Меняем цвет текста
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Демонстрационный текст, чтобы увидеть эффект смены цвета
                Text(
                    text = "Этот текст иллюстрирует текущую цветовую схему.",
                    fontSize = 16.sp,
                    color = textColor.copy(alpha = 0.7f) // <-- Меняем цвет и делаем полупрозрачным
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Сам переключатель и его подпись
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Тёмная тема",
                        fontSize = 18.sp,
                        color = switchLabelColor // Цвет подписи тоже зависит от темы
                    )
                    Switch(
                        checked = isDarkTheme,
                        onCheckedChange = { isDarkTheme = it }, // Переключаем состояние
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = Color(0xFF6200EE), // Material Purple
                            uncheckedThumbColor = Color.Gray,
                            uncheckedTrackColor = Color.LightGray
                        )
                    )
                }
            }
        }
    }
}