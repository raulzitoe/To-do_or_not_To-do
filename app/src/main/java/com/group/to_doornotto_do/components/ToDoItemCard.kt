package com.group.to_doornotto_do.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ToDoItemCard(
    item: String,
    isChecked: Boolean,
    onCheckedChange: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = isChecked, onCheckedChange = { onCheckedChange() })
        Text(text = item)
    }
}

@Preview(showBackground = true)
@Composable
private fun ToDoListCardPreview() {
    ToDoItemCard(
        item = "Ketchup",
        isChecked = true,
        onCheckedChange = {}
    )
}