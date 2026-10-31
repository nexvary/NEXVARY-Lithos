package com.nexvary.lithos

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext

private val black = Color(0xFF0A0A0A)
private val silver = Color(0xFFCCD3DC)
private val panels = Color(0xFF181A1E)
private data class Section(val title: String, val subtitle: String)
private val sections = listOf(
    Section("اكتشاف عينة", "التعرف البصري: قيد التطوير"),
    Section("موسوعة الطبيعة", "الأحجار والمعادن والصخور والأحافير"),
    Section("التقييم التجاري", "تقدير استرشادي عند توفر بيانات موثوقة"),
    Section("مجموعتي", "حفظ العينات محليًا: قيد التطوير"),
    Section("الإعدادات", "اللغة والخصوصية"),
    Section("عن NEXVARY", "nexvary.com")
)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); setContent { LithosApp() } }
}
@Composable
private fun LithosApp() {
    var current by remember { mutableStateOf<String?>(null) }
    BackHandler(current != null) { current = null }
    MaterialTheme(colorScheme = darkColorScheme(background = black, surface = panels, primary = silver, onBackground = Color.White)) {
        Column(Modifier.fillMaxSize().background(black).padding(20.dp)) {
            Text(if (current == null) "NEXVARY  /  LITHOS" else "‹  $current", color = silver, fontSize = 23.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { current = null }.padding(vertical = 18.dp))
            Text("Discover • Identify • Evaluate", color = Color(0xFF8A949F), fontSize = 13.sp)
            Spacer(Modifier.height(26.dp))
            if (current == null) {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(sections) { section ->
                        Card(onClick = { current = section.title }, shape = RoundedCornerShape(18.dp), colors = CardDefaults.cardColors(containerColor = panels), modifier = Modifier.fillMaxWidth()) {
                            Column(Modifier.padding(20.dp)) {
                                Text(section.title, color = Color.White, fontSize = 19.sp, fontWeight = FontWeight.SemiBold)
                                Spacer(Modifier.height(6.dp))
                                Text(section.subtitle, color = silver, fontSize = 13.sp)
                            }
                        }
                    }
                }
            } else {
                Text(when (current) {
                    "عن NEXVARY" -> "NEXVARY Inc. — https://nexvary.com"
                    "التقييم التجاري" -> "لا يمكن تحديد قيمة حجر من الصورة وحدها. لا توجد أسعار موثقة محملة بعد."
                    "اكتشاف عينة" -> "لن نعرض نتيجة تصنيف قبل تدريب نموذج واختبار دقته."
                    "موسوعة الطبيعة" -> "قاعدة البيانات العلمية قيد الإعداد والتحقق من تراخيصها."
                    "مجموعتي" -> "التخزين المحلي قيد التنفيذ؛ لا يتم رفع الصور أو البيانات."
                    else -> "اللغة العربية مبدئيًا. لا توجد حسابات أو خوادم أو تحليلات."
                }, color = Color.White, fontSize = 17.sp)
            }
        }
    }
}
