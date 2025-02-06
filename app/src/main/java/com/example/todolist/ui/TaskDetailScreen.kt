import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todolist.viewmodel.TaskViewModel

@Composable
fun TaskDetailScreen(navController: NavController, TaskId: Int, viewModel: TaskViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Détail de la tâche") })
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Text(
                text = "Vous avez sélectionné la tâche : $TaskId",
                style = MaterialTheme.typography.body1 // Utilisation de body1 à la place de h6 ou headlineMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Retour")
            }
        }
    }
}
@Composable
fun TaskDetailScreen(taskId: String?) {

    if (taskId != null) {
        // Afficher les détails de la tâche en fonction de l'ID
        Text("Détails de la tâche: $taskId")
    } else {
        // Si taskId est null
        Text("Aucun ID de tâche fourni.")
    }
}
