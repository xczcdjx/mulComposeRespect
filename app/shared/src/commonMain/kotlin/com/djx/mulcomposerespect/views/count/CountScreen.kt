package com.djx.mulcomposerespect.views.count

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.djx.i18n.runtime.tr
import com.djx.mulcomposerespect.i18n.I18nKeys
import com.djx.mulcomposerespect.layout.LayoutCom
import com.djx.mulcomposerespect.viewmodels.CountVM
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CountScreen(
    vm: CountVM = koinViewModel(),
    back: () -> Unit = {}
) {
    val count by vm.count.collectAsState()
    LayoutCom(tr(I18nKeys.router_count), back) {
        Text(tr(I18nKeys.count, count), fontSize = 25.sp, fontWeight = FontWeight.Bold)
        TextButton({
            vm.add(1)
        }) {
            Text("Count++")
        }
    }
}