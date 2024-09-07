package com.nowni.sqlbasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.nowni.sqlbasics.ui.theme.SqlBasicsTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SqlBasicsTheme {
                SqlBasicApp()
                LaunchedEffect(Unit) {
                    lifecycleScope.launch {
                        AppDatabase.getDatabase(applicationContext).delhiParkDao().getAll()
                    }

                }
            }
        }
    }
}

/** Separated database call*/
/*@Composable
fun DatabaseCall() {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            AppDatabase.getDatabase(context).delhiParkDao().getAll()
        }
    }
}*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SqlBasicApp() {

    Surface {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        Scaffold(modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = { SqlBasicTopAppBar(scrollBehavior = scrollBehavior) }) { innerPadding ->
            InstructionText(
                instruction = stringResource(R.string.instruction),
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}

@Composable
fun InstructionText(instruction: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = instruction,
            modifier = modifier,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SqlBasicTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineMedium,
            )
        },
        modifier = modifier,
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            scrolledContainerColor = Color.Unspecified,
            navigationIconContentColor = Color.Unspecified,
            titleContentColor = Color.Unspecified,
            actionIconContentColor = Color.Unspecified
        )
    )

}

@Preview(showBackground = true, name = "instructionPreview", showSystemUi = true)
@Composable
fun InstructionPreview() {
    SqlBasicsTheme {
        Surface(modifier = Modifier) {
            InstructionText(instruction = stringResource(R.string.instruction))
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "topAppbarPreview", showSystemUi = true)
@Composable
fun TopAppBarPreview() {
    SqlBasicsTheme {
        Surface(modifier = Modifier) {
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
            SqlBasicTopAppBar(scrollBehavior = scrollBehavior)
        }

    }
}


@Preview(showBackground = true, name = "sqlBasicAppPreview", showSystemUi = true)
@Composable
fun SqlBasicAppPreview() {
    SqlBasicsTheme {
        Surface(modifier = Modifier) {
            SqlBasicApp()
        }

    }
}