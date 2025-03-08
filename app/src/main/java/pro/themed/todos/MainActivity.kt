package pro.themed.todos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import pro.themed.todos.ui.theme.ToDosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDosTheme(
                darkTheme = false,
                dynamicColor = false,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeContentPadding()
                ) {
                    ToDoItem()
                    ToDoItem("take a walk")
                    ToDoItem("buy tomatoes")


                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun ToDoItem(label: String = "Go to the doctor") {
    var ischecked by remember { mutableStateOf(true) }
    var showDialog by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = { ischecked = !ischecked },
                onLongClick = { showDialog = true }), verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(checked = ischecked, onCheckedChange = {
            ischecked = !ischecked

        })

        Text(label)
    }


    if (showDialog == true) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Card(
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(
                    text = label, modifier = Modifier.padding(horizontal = 16.dp, vertical = 36.dp)
                )

            }
        }

    }
}
