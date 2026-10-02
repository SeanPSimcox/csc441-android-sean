package edu.lemoyne.campusapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.lemoyne.campusapp.ui.theme.CampusAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CampusAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// ---Class 7: Step 1: a counter that remembers
@Composable
fun CounterDemo() {
    var count by remember { mutableStateOf(0)}

    Button(onClick = {
        count++
        println("count is now $count")
    }) {
        Text("Tapped $count times")
    }
}
// --- Class 6 ~ Step 1: my own screen --
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
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
            "*RDP - Port 3389 - TCP"
        )
    }
    // --- Class 7 ~ Step 3: what's typed lives in state ---
    var newPort by remember { mutableStateOf("") }

    // --- Class 6 ~ Step 3: a column, so things stack ---
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        // --- Lab 6 ~ task 3: a picture of my own ---
        Image(
            painter = painterResource(id = R.drawable.koenkayakesmall),
            contentDescription = "Kayake to your proper port",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- Class 6 ~ Step 4: real styling (subtitle)---
        Text(
            text = "Common Network Ports",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // --- Class 7 ~ Step 3: the text field ---
        OutlinedTextField(
            value = newPort,
            onValueChange = { newPort = it },
            label = { Text("Port name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        // --- Class 7~ Step 4: the button changes the state ---
        Button(onClick = {
            ports.add(newPort)
            newPort = ""
        }) {
            Text("Add Port")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Well-known network services",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        // --- Class 7 ~ Step 2: draw whatever is in the list ---
        Text(
            text = "${ports.size} Ports:",
            fontWeight = FontWeight.Bold
        )
        for (port in ports) {
            Text(text = port, fontSize = 18.sp)
        }
        // --- Lab 6 ~ Task 2: Footer ---
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Last updated September 2026",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

    }
}

// --- Class 6 ~ Step 2: preview, no build required ---
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CampusAppTheme {
        HomeScreen()
    }
}

// --- Class 6 ~ Task: 4 dark mode preview ---
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenDarkPreview() {
    CampusAppTheme {
        Surface {
            HomeScreen()
        }
    }
}

