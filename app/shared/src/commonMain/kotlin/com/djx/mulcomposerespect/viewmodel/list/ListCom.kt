package com.djx.mulcomposerespect.viewmodel.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.djx.mulcomposerespect.viewmodel.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ListCom(
    vm: HomeViewModel = koinViewModel(),
    goDetail: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        vm.toast.collect { msg -> snackbarHostState.showSnackbar(msg) }
    }
    val title by vm.title.collectAsState()
    val list by vm.list.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(title)
                }
            }, actions = {
                IconButton({
                    vm.loadList(true)
                }) {
                    Icon(Icons.Default.Refresh, null)
                }
            })
        },
        floatingActionButton = {
            IconButton({
                vm.upData()
            }) {
                Icon(Icons.Default.Add, null)
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }

    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            if (list.isEmpty() || vm.loading) {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth().height(200.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        if (vm.loading) {
                            LoadingIndicator()
                        } else {
                            Text("No Data")
                        }
                    }
                }
            }
            if (!vm.loading) {
                items(list.size) { index ->
                    val todo = list[index]
                    ListItem(modifier = Modifier.clickable {
                        vm.upData(todo)
                    }, leadingContent = {
                        Text("${index + 1}.")
                    }, headlineContent = {
                        Text(todo.title)
                    }, supportingContent = {
                        Text(todo.content)
                    }, trailingContent = {
                        Row() {
                            Checkbox(todo.done, {
                                vm.toggleChecked(todo, it)
                            })
                            IconButton({
                                vm.delItem(todo.id)
                            }) {
                                Icon(Icons.Default.Remove, null, tint = Color.Red)
                            }
                        }
                    })
                }
            }
        }
    }
    if (vm.showDialog) {
        Dialog(onDismissRequest = {
            vm.toggleDialog(false)
        }) {
            Surface(
                modifier = Modifier
                    .width(340.dp),
                shape = RoundedCornerShape(16.dp),
                tonalElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                ) {
                    val todo = vm.todoItem
                    Text(
                        text = if (todo.id == "-1") "新增 Todo" else "修改 ToDo",
                        style = MaterialTheme.typography.titleLarge
                    )

                    OutlinedTextField(
                        value = vm.todoItem.title,
                        onValueChange = {
                            vm.updateTodoItem {
                                copy(title = it)
                            }
                        },
                        label = {
                            Text("标题")
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = vm.todoItem.content,
                        onValueChange = {
                            vm.updateTodoItem {
                                copy(content = it)
                            }
                        },
                        label = {
                            Text("内容")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        maxLines = 4
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = todo.done ?: false,
                            onCheckedChange = {
                                vm.updateTodoItem {
                                    copy(done = it)
                                }
                            }
                        )

                        Text("是否完成")
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = {
                                vm.toggleDialog(false)
                            }
                        ) {
                            Text("取消")
                        }

                        Spacer(Modifier.width(8.dp))

                        Button(
                            onClick = {
                                vm.submit(todo.id)
                            },
                            enabled = todo.title.isNotBlank() && todo.content.isNotBlank()
                        ) {
                            Text("保存")
                        }
                    }
                }
            }
        }
    }
}