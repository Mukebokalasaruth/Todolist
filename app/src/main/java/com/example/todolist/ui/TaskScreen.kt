import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.* // Assurez-vous que tous les imports sont là
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text // Assurez-vous que c'est bien importé depuis material3
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todolist.data.Task
import com.example.todolist.viewmodel.TaskViewModel

// Définition de la classe Task
data class Task(val name: String, var isDone: Boolean)

@Composable
fun TaskItem(task: Task, onToggle: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        // Utilisation correcte de la Checkbox de Material3
        Checkbox(
            checked = task.isDone,
            onCheckedChange = { onToggle() }
        )
        // Affichage du texte de la tâche
        text(
            text = task.name,
            modifier = Modifier.weight(1f).padding(start = 8.dp).align(Alignment.CenterVertically)
        )
    }
}

fun <string> text(text: string, modifier: Modifier) {

}

@Composable
fun TaskScreen(viewModel: TaskViewModel = viewModel()) {
    // Récupérer la liste des tâches depuis le ViewModel
    val tasks by viewModel.tasks.collectAsState()

    // Column pour organiser les éléments
    Column(modifier = Modifier.fillMaxSize().padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        var taskName by remember { mutableStateOf("") }

        // Row pour la saisie du nom de la tâche
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = taskName,
                onValueChange = { taskName = it },
                modifier = Modifier.weight(1f).padding(16.dp),
                singleLine = true,
                label = { Text("Nom de la tâche") }
            )

            // Bouton pour ajouter une tâche
            Button(
                onClick = {
                    if (taskName.isNotBlank()) {
                        viewModel.addTask(taskName)
                        taskName = "" // Réinitialiser le champ après l'ajout
                    }
                },
                modifier = Modifier.align(Alignment.CenterVertically).padding(16.dp)
            ) {
                Text("Ajouter")
            }
        }

        // Affichage des tâches dans une LazyColumn
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            // Utilisation correcte de `items`
            items(tasks) { task ->
                TaskItem(task, onToggle = { viewModel.updateTask(task) })
            }
        }
    }
}
@Composable
fun TaskItem(task: Task,onToggle:()->Unit,onClick:()->Unit) {
    Row (modifier=Modifier.fillMaxSize().padding(8.dp)){
        Checkbox(checked = task.isDone, onCheckedChange = { onToggle() })
        Text(text = task.name, modifier = Modifier.weight(1f).
        padding(start = 8.dp)
            .align(Alignment.CenterVertically)
            .clickable { onClick() }
        )

    }
}

@Composable
fun TaskScreen(navController: NavController, viewModel: TaskViewModel= viewModel()) {
    val tasks by viewModel.tasks.collectAsState()
    Column(modifier = Modifier.fillMaxSize().padding(top=32.dp).padding(all = 16.dp)) {
        var taskName by remember { mutableStateOf("") }

        Row {
            OutlinedTextField(
                value = taskName,
                onValueChange = { taskName = it },
                modifier = Modifier.weight(1f).padding(16.dp),
                singleLine = true,
                label = { Text("Nom de la tâche") },
            )
            Button(onClick = {
                if (taskName.isNotBlank()) {
                    viewModel.addTask(taskName)
                    taskName = ""
                }
            }, modifier = Modifier.align(Alignment.CenterVertically)) {
                Text("Ajouter")
            }
        }

        LazyColumn {
            items(tasks) { task ->
                TaskItem(task,
                    onToggle = {
                        viewModel.updateTask(task)
                    },
                    onClick = {navController.navigate("task_detail/${task.id}")}


                )
            }
        }
    }
}
