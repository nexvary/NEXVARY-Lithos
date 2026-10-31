package com.nexvary.lithos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
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
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val deepBlack = Color(0xFF0A0A0A)
private val metallic = Color(0xFFC7D0DC)
private val panel = Color(0xFF191C22)
private val accent = Color(0xFF66C8F3)

private data class Mineral(val ar: String, val en: String, val formula: String, val mohs: String, val categoryAr: String, val categoryEn: String)
private val referenceSamples = listOf(
    Mineral("الكوارتز", "Quartz", "SiO₂", "7", "معدن", "Mineral"),
    Mineral("الجمشت", "Amethyst", "SiO₂", "7", "كوارتز بنفسجي", "Purple quartz"),
    Mineral("الكالسيت", "Calcite", "CaCO₃", "3", "معدن", "Mineral"),
    Mineral("الفلوريت", "Fluorite", "CaF₂", "4", "معدن", "Mineral"),
    Mineral("الكوراندوم", "Corundum", "Al₂O₃", "9", "معدن", "Mineral"),
    Mineral("البيريت", "Pyrite", "FeS₂", "6–6.5", "معدن", "Mineral")
)
private enum class Page { HOME, DISCOVER, CATALOG, VALUE, COLLECTION, SETTINGS, ABOUT }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { LithosApp() }
    }
}

@Composable
private fun LithosApp() {
    var page by remember { mutableStateOf(Page.HOME) }
    var arabic by remember { mutableStateOf(true) }
    var query by remember { mutableStateOf("") }
    var selected by remember { mutableStateOf<Mineral?>(null) }
    BackHandler(page != Page.HOME || selected != null) {
        if (selected != null) selected = null else page = Page.HOME
    }
    val t: (String, String) -> String = { ar, en -> if (arabic) ar else en }
    val entries = listOf(
        Triple(Page.DISCOVER, t("اكتشاف عينة", "Identify specimen"), t("تحليل الصور: قيد التطوير", "Image analysis: in development")),
        Triple(Page.CATALOG, t("الموسوعة العلمية", "Encyclopedia"), t("معلومات معدنية محلية", "Offline mineral references")),
        Triple(Page.VALUE, t("التقييم التجاري", "Valuation"), t("التقديرات تتطلب أدلة", "Evidence-based estimates only")),
        Triple(Page.COLLECTION, t("مجموعتي", "My collection"), t("التخزين المحلي قيد التطوير", "Local storage in development")),
        Triple(Page.SETTINGS, t("الإعدادات", "Settings"), t("اللغة والخصوصية", "Language and privacy")),
        Triple(Page.ABOUT, t("عن NEXVARY", "About NEXVARY"), "nexvary.com")
    )
    CompositionLocalProvider(LocalLayoutDirection provides if (arabic) LayoutDirection.Rtl else LayoutDirection.Ltr) {
        MaterialTheme(colorScheme = darkColorScheme(background = deepBlack, surface = panel, primary = accent, onBackground = Color.White)) {
            Column(Modifier.fillMaxSize().background(deepBlack).padding(20.dp)) {
                Text(if (page == Page.HOME) "NEXVARY  /  LITHOS" else t("رجوع  ‹", "‹  Back"),
                    modifier = Modifier.clickable { selected = null; page = Page.HOME }.padding(vertical = 18.dp),
                    color = metallic, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text("Discover • Identify • Evaluate", color = accent, fontSize = 13.sp)
                Spacer(Modifier.height(22.dp))
                when {
                    selected != null -> {
                        val item = selected!!
                        Text(if (arabic) item.ar else item.en, color = Color.White, fontSize = 26.sp)
                        Spacer(Modifier.height(12.dp))
                        Text("Formula: ${item.formula}", color = metallic)
                        Text("Mohs: ${item.mohs}", color = metallic)
                        Text(t("التصنيف: ${item.categoryAr}", "Category: ${item.categoryEn}"), color = metallic)
                        Spacer(Modifier.height(12.dp))
                        Text(t("هذه معلومات مرجعية وليست تحديدًا لعينة مصورة.", "Reference facts, not an identification of a photographed specimen."), color = accent)
                    }
                    page == Page.HOME -> LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(entries) { entry ->
                            Card(onClick = { page = entry.first }, shape = RoundedCornerShape(18.dp),
                                colors = CardDefaults.cardColors(containerColor = panel), modifier = Modifier.fillMaxWidth()) {
                                Column(Modifier.padding(20.dp)) {
                                    Text(entry.second, color = Color.White, fontSize = 19.sp, fontWeight = FontWeight.SemiBold)
                                    Spacer(Modifier.height(5.dp))
                                    Text(entry.third, color = metallic, fontSize = 13.sp)
                                }
                            }
                        }
                    }
                    page == Page.CATALOG -> {
                        OutlinedTextField(value = query, onValueChange = { query = it }, singleLine = true,
                            label = { Text(t("ابحث عن معدن", "Search minerals")) }, modifier = Modifier.fillMaxWidth())
                        Spacer(Modifier.height(12.dp))
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(referenceSamples.filter { it.ar.contains(query, true) || it.en.contains(query, true) }) { mineral ->
                                Card(onClick = { selected = mineral }, colors = CardDefaults.cardColors(containerColor = panel)) {
                                    Column(Modifier.fillMaxWidth().padding(16.dp)) {
                                        Text(if (arabic) mineral.ar else mineral.en, color = Color.White)
                                        Text("${mineral.formula}  •  Mohs ${mineral.mohs}", color = metallic)
                                    }
                                }
                            }
                        }
                    }
                    page == Page.SETTINGS -> {
                        Text(t("اللغة", "Language"), color = Color.White, fontSize = 20.sp)
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Button(onClick = { arabic = true }) { Text("العربية") }
                            Button(onClick = { arabic = false }) { Text("English") }
                        }
                        Text(t("لا يتطلب التطبيق حسابًا أو اتصالًا بالخادم.", "No account or server connection required."), color = metallic)
                    }
                    page == Page.ABOUT -> {
                        Text("NEXVARY Inc.", color = Color.White, fontSize = 24.sp)
                        Text("https://nexvary.com", color = accent)
                        Text(t("تطبيق مستقل يعمل محليًا لحماية خصوصية المستخدم.", "Private, on-device field reference."), color = metallic)
                    }
                    else -> Text(when (page) {
                        Page.DISCOVER -> t("التعرف البصري غير متاح حتى اختبار النموذج علميًا.", "Visual identification is unavailable pending model validation.")
                        Page.VALUE -> t("لا توجد أسعار مرجعية موثقة محملة. لا يمكن إصدار تقدير حاليًا.", "No verified reference prices installed; valuation unavailable.")
                        else -> t("حفظ العينات قيد التنفيذ.", "Specimen storage is in development.")
                    }, color = Color.White, fontSize = 17.sp)
                }
            }
        }
    }
}
