package edu.lemoyne.campusapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
// --- Class 6 ~ Step 1: my own screen --
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    // --- Class 6 ~ Step 3: a column, so things stack ---
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        // --- Class 6 ~ Step 4: real styling ---
        Text(
            text = "Common Network Ports",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "18 well-known network services",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "FTP - Port 20/21 - TCP", fontSize = 18.sp)
        Text(text = "SSH - Port 22 - TCP", fontSize = 18.sp)
        Text(text = "Telnet - Port 23 - TCP", fontSize = 18.sp)
        Text(text = "SMTP - Port 25 - TCP", fontSize = 18.sp)
        Text(text = "DNS - Port 53 - TCP/UDP", fontSize = 18.sp)
        Text(text = "DHCP - Port 67/68 - UDP", fontSize = 18.sp)
        Text(text = "TFTP - Port 69 - UDP", fontSize = 18.sp)
        Text(text = "HTTP - Port 80 - TCP", fontSize = 18.sp)
        Text(text = "POP3 - Port 110 - TCP", fontSize = 18.sp)
        Text(text = "NTP - Port 123 - UDP", fontSize = 18.sp)
        Text(text = "IMAP4 - Port 143 - TCP", fontSize = 18.sp)
        Text(text = "SNMP - Port 161/162 - UDP", fontSize = 18.sp)
        Text(text = "LDAP - Port 389 - TCP/UDP", fontSize = 18.sp)
        Text(text = "HTTPS - Port 443 - TCP", fontSize = 18.sp)
        Text(text = "SMB - Port 445 - TCP", fontSize = 18.sp)
        Text(text = "LDAPS - Port 636 - TCP", fontSize = 18.sp)
        Text(text = "NFS - Port 2049 - TCP/UDP", fontSize = 18.sp)
        Text(text = "RDP - Port 3389 - TCP", fontSize = 18.sp)

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