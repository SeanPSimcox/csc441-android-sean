package edu.lemoyne.campusapp

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import edu.lemoyne.campusapp.ui.theme.CampusAppTheme

const val MAX_NAME_LENGTH = 30
// --- Class 8 ~ Step 1: one rule book for trail names ---
fun validatePortName(input: String, existingPorts: List<String>): String? {
    val name = input.trim()
    return when {
        name.isEmpty() -> "Enter a Port name"
        // --- Lab 8 ~ Task 1: minimum length rule ---
        name.length < 3 -> "Port name must be at least 3 characters"
        name.length > MAX_NAME_LENGTH -> "Keep it less than or equal to $MAX_NAME_LENGTH characters"
        // --- Lab 8 ~ Task 2: custom validation rule ---
        !name.any { it.isLetter() } -> "A port entry must contain at least one service name"
        existingPorts.any { it.equals(name, ignoreCase = true) } -> "$name already exists"
        else -> null
    }
}

// ---Class 7: Step 1: a counter that remembers
@Composable
fun CounterDemo() {
    var count by remember { mutableStateOf(0) }

    Button(onClick = {
        count++
        println("count is now $count")
    }) {
        Text("Tapped $count times")
    }
}

// --- Class 9 ~ Step 2: one owner for the data
@Composable
fun CampusAppScreen(modifier: Modifier = Modifier){
    // ---Class 7: Step 2: The list lives in state ---
    val ports = remember {
        mutableStateListOf(
            "*FTP - Port 20/21 - TCP",
            "*SSH - Port 22 - TCP",
            "*Telnet - Port 23 - TCP",
            "*SMTP - Port 25 - TCP",
            "*SMB - Port 445 - TCP",
            "*DNS - Port 53 - TCP/UDP",
            "*DHCP - Port 67/68 - UDP",
            "*TFTP - Port 69 - UDP",
            "*HTTP - Port 80 - TCP",
            "*POP3 - Port 110 - TCP",
            "*NTP - Port 123 - UDP",
            "*IMAP4 - Port 143 - TCP",
            "*SNMP - Port 161/162 - UDP",
            "*LDAP - Port 389 - TCP/UDP",
            "*HTTPS - Port 443 - TCP",
            "*LDAPS - Port 636 - TCP",
            "*NFS - Port 2049 - TCP/UDP",
            "*RDP - Port 3389 - TCP")

    }

    // --- Class 9 ~ Step 4: which screen is showing is just state ---
    var currentScreen by rememberSaveable { mutableStateOf("home")}

    when (currentScreen) {
        "home" -> HomeScreen(
            ports = ports,
            onAddPort = { ports.add(it) },
            onSeeAll = { currentScreen = "list" },
            // --- Lab 9 ~ Task 2: HomeScreen can request the About screen ---
            onAbout = { currentScreen = "about"}
        )
        "list" -> ListScreen(
            ports = ports,
            onBack = { currentScreen = "home" },
            // --- Class 10 ~ Step 4: only the owner changes the list ---
            onRemove = { ports.remove(it) },
            modifier = modifier
        )
        // --- Lab 9 ~ Task 2: add the About screen to navigation ---
        "about" -> AboutScreen(
            onBack = { currentScreen = "home" },
            modifier = modifier
        )
    }

}

// --- Class 6 ~ Step 1: my own screen --
@Composable
fun HomeScreen(
    ports: MutableList<String>,
    onAddPort: (String) -> Unit,
    onSeeAll: () -> Unit,
    // --- Lab 9 ~ Task 2: a way to the About screen ---
    onAbout: () -> Unit,
    modifier: Modifier = Modifier
) {
    // --- Class 7 ~ Step 3: what's typed lives in state ---
    var newPort by remember { mutableStateOf("") }
    // --- Class 8 ~ Step 2: the error message lives in state 2
    var error by remember {mutableStateOf<String?>(null)}
    // --- Class 6 ~ Step 3: a column, so things stack ---
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
//        CounterDemo()
        // --- Lab 6 ~ task 3: a picture of my own ---
        Image(
            painter = painterResource(id = R.drawable.koenkayakesmall),
            contentDescription = "Kayak to your proper port",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- Class 6 ~ Step 4: real styling (subtitle)---
        Text(
            text = "Network Port Reference",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // --- Class 7 ~ Step 3: the text field ---
        OutlinedTextField(
            value = newPort,
            // --- Class 8 ~ Step 3: the field itself pushes back ---
            onValueChange = {
                newPort = it.take(n = MAX_NAME_LENGTH)
                error = null
            },
            label = { Text("Port name") },
            singleLine = true,
            isError = error != null,
            modifier = Modifier.fillMaxWidth()
        )
        error?.let { message ->
            Text(
                text = message,
                // --- Lab 8 ~ Task 3: show errors in red ---
                color = Color.Red
            )
        }

        // --- Lab 7 ~ Task 4: a live character counter ---
        Text(
            // --- Lab 8 ~ Task 4: change length to MAX ---
            text = "${newPort.length} / $MAX_NAME_LENGTH",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(8.dp))
        // --- Class 7 ~ Step 4: the button changes the state ---
        Button(onClick = {
            // --- Class 8 ~ Step 3: check before you add ---
            val problem = validatePortName(newPort, ports)
            if (problem == null) {
                // --- Class 9 ~ Step 2: ask the owner to add it ---
                onAddPort(newPort.trim())
                newPort = ""
            } else {
                error = problem
            }
        },
            // --- Class 8 ~ Step 4: the sign on the door, not the lock ---
            enabled = newPort.isNotBlank()
        ) {
            Text("Add Port")
        }
        // --- Lab 7 ~ Task 1: remove the last item ---
        Button(onClick = {
            if (ports.isNotEmpty()) {
                ports.removeAt(ports.lastIndex)
            }
        }) {
            Text("Remove last")
        }
        // --- Lab 7 ~ Task 3: clear all ---
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Documented Ports",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        // --- Class 7 ~ Step 2: draw whatever is in the list ---
        Text(
            // --- Lab 7 ~ Task 2: singular and plural ---
            text = if (ports.size != 1) "${ports.size} ports" else "1 port",
            fontWeight = FontWeight.Bold
        )
        // --- Class 9 ~ Step 5: a way to the second screen ---
        Button(onClick = onSeeAll) {
            Text(text = "See all ports")
        }
        // --- Lab 6 ~ Task 2: Footer ---
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Last updated September 2026",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        // --- Lab 9 ~ Task 2: the About button ---
        Button(onClick = onAbout) {
            Text(text = "About")
        }

    }
}

// --- Class 9 Step 3: second screen ---
@Composable
fun ListScreen(
    ports: List<String>,
    onBack: () -> Unit,
    onRemove: (String) -> Unit,
    modifier:Modifier = Modifier
) {

    // --- Class 9 ~ Step 6: the phone's back button goes home too ---
    BackHandler { onBack() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        TextButton(onClick = onBack) {
            Text(text = "back")
        }

        Text(
            text = "Ports List:",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Text( text = "Well-known ports: *",
            color = MaterialTheme.colorScheme.onSurfaceVariant)

        // --- Lab 9 ~ Task 1: count on the list screen ---
        Text( text = "Total: ${ports.size}",
            color = MaterialTheme.colorScheme.onSurfaceVariant)

        Spacer(modifier = Modifier.height(16.dp))

//        for (port in ports) {
//            Text(text = port, fontSize = 18.sp)
        // --- Class 10 ~ Step 5: the empty case ---
        if(ports.isEmpty()) {
            Text(
                text = "No ports yet. Add one on the home screen",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            } else {
        // --- Class 10 ~ Step 2: a list that scrolls ---
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
                items(ports) { port ->
                    PortRow(
                        name = port,
                        onRemove = { onRemove(port) }
                    )
                }
            }
        }
    }
}

// --- Class 10 ~ Step 3: one row, as its own Composable ---
@Composable
fun PortRow(name: String, onRemove: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                fontSize = 18.sp,
                modifier = Modifier.weight(1f)

            )

            // --- Class 10 ~ Step 4: a remove button on every row ---
            TextButton(onClick = onRemove) {
                Text("Remove")
            }
        }
    }
}

// --- Lab 9 ~ Task 2: a third screen ---
@Composable
fun AboutScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onBack() }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ){
        TextButton(onClick = onBack) {
            Text("Back")
        }

        Text(
            text = "About",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Common Network Ports keeps track of common networking ports as well as any" +
                " additional ports worth mentioning.")
        Text(text = "Built for CSC 441 by Sean P Simcox")
    }

}

// --- Class 10 ~ Step 5: preview the empty case too ---
@Preview(showBackground = true)
@Composable
fun ListScreenEmptyPreview() {
    CampusAppTheme {
        ListScreen(
            ports = emptyList(),
            onBack = {},
            onRemove = {}
        )
    }
}

// --- Class 6 ~ Step 2: preview, no build required ---
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CampusAppTheme {
        HomeScreen( ports = remember {
            mutableStateListOf(
                "*FTP - Port 20/21 - TCP",
                "*SSH - Port 22 - TCP",
                "*Telnet - Port 23 - TCP",
                "*SMTP - Port 25 - TCP",
                "*SMB - Port 445 - TCP",
                "*DNS - Port 53 - TCP/UDP",
                "*DHCP - Port 67/68 - UDP",
                "*TFTP - Port 69 - UDP",
                "*HTTP - Port 80 - TCP",
                "*POP3 - Port 110 - TCP",
                "*NTP - Port 123 - UDP",
                "*IMAP4 - Port 143 - TCP",
                "*SNMP - Port 161/162 - UDP",
                "*LDAP - Port 389 - TCP/UDP",
                "*HTTPS - Port 443 - TCP",
                "*LDAPS - Port 636 - TCP",
                "*NFS - Port 2049 - TCP/UDP",
                "*RDP - Port 3389 - TCP")
        },
            onAddPort = {},
            onSeeAll = {},
            onAbout = {}

        )
    }
}

// --- Class 9 ~ Step 7: preview the list screen ---
@Preview(showBackground = true)
@Composable
fun ListScreenPreview(){
    CampusAppTheme {
        ListScreen(
            ports = remember {
                mutableStateListOf(
                    "*FTP - Port 20/21 - TCP",
                    "*SSH - Port 22 - TCP",
                    "*Telnet - Port 23 - TCP",
                    "*SMTP - Port 25 - TCP",
                    "*SMB - Port 445 - TCP",
                    "*DNS - Port 53 - TCP/UDP",
                    "*DHCP - Port 67/68 - UDP",
                    "*TFTP - Port 69 - UDP",
                    "*HTTP - Port 80 - TCP",
                    "*POP3 - Port 110 - TCP",
                    "*NTP - Port 123 - UDP",
                    "*IMAP4 - Port 143 - TCP",
                    "*SNMP - Port 161/162 - UDP",
                    "*LDAP - Port 389 - TCP/UDP",
                    "*HTTPS - Port 443 - TCP",
                    "*LDAPS - Port 636 - TCP",
                    "*NFS - Port 2049 - TCP/UDP",
                    "*RDP - Port 3389 - TCP")
            },
            onBack = {},
            onRemove = {}
        )
    }
}

// --- Class 6 ~ Task: 4 dark mode preview ---
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenDarkPreview() {
    CampusAppTheme {
        Surface {
            HomeScreen( ports = remember {
                mutableStateListOf(
                    "*FTP - Port 20/21 - TCP",
                    "*SSH - Port 22 - TCP",
                    "*Telnet - Port 23 - TCP",
                    "*SMTP - Port 25 - TCP",
                    "*SMB - Port 445 - TCP",
                    "*DNS - Port 53 - TCP/UDP",
                    "*DHCP - Port 67/68 - UDP",
                    "*TFTP - Port 69 - UDP",
                    "*HTTP - Port 80 - TCP",
                    "*POP3 - Port 110 - TCP",
                    "*NTP - Port 123 - UDP",
                    "*IMAP4 - Port 143 - TCP",
                    "*SNMP - Port 161/162 - UDP",
                    "*LDAP - Port 389 - TCP/UDP",
                    "*HTTPS - Port 443 - TCP",
                    "*LDAPS - Port 636 - TCP",
                    "*NFS - Port 2049 - TCP/UDP",
                    "*RDP - Port 3389 - TCP")
            },
                onAddPort = {},
                onSeeAll = {},
                onAbout = {}

            )
        }
    }
}