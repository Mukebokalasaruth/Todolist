package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist.ui.theme.TodolistTheme

// Classe principale de l'application
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()  // Pour des bords d'écran étendus
        setContent {
            TodolistTheme {
                TaskScreen()
                NavigationGraph()
            }
        }
    }
}

// Données de la tâche
data class Task(val name: String, var isDone: Boolean)

// Composant pour afficher une tâche
@Composable
fun TaskItem(task: Task, onToggle: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        // Affichage de la Checkbox pour marquer la tâche
        Checkbox(
            checked = task.isDone,
            onCheckedChange = { onToggle() }
        )
        // Affichage du texte de la tâche
        Text(
            text = task.name,
            modifier = Modifier.weight(1f).padding(start = 8.dp).align(Alignment.CenterVertically)
        )
    }
}

// Composant principal qui gère la liste des tâches
@Composable
fun TaskScreen() {
    // Liste des tâches (state)
    var tasks by remember { mutableStateOf<List<Task>>(emptyList()) }
    var taskName by remember { mutableStateOf("") }

    // Column pour la disposition verticale
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Row pour le champ de saisie et le bouton
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = taskName,
                onValueChange = { taskName = it },
                modifier = Modifier.weight(1f).padding(end = 8.dp),
                singleLine = true,
                label = { Text("Nom de la tâche") }
            )

            // Bouton pour ajouter une tâche
            Button(
                onClick = {
                    if (taskName.isNotBlank()) {
                        val newTask = Task(name = taskName, isDone = false)
                        tasks = tasks + newTask  // Ajout de la tâche à la liste
                        taskName = ""  // Réinitialiser le champ de saisie
                    }
                }
            ) {
                Text("Ajouter")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))  // Espacement entre le formulaire et la liste

        // LazyColumn pour afficher la liste des tâches
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(tasks) { task ->
                TaskItem(task, onToggle = {
                    tasks = tasks.map {
                        if (it == task) it.copy(isDone = !it.isDone) else it
                    }
                })
            }
        }
    }
}

// Prévisualisation de l'écran des tâches
@Preview(showBackground = true)
@Composable
fun PreviewTaskScreen() {
    TodolistTheme {
        TaskScreen()
    }
}
@Preview(showBackground = true)
@Composable
fun TaskPreview() {
    NavigationGraph()
}

@Composable
fun NavigationGraph() {
    TODO("Not yet implemented")
}
