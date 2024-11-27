package kgarel.isis.fr

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

@Composable
fun ProfilePicture() {
    Image(
        painterResource(R.drawable.bouffon),
        contentDescription = "Profile Picture",
        modifier = Modifier.clip(CircleShape)
    )
}

@Composable
fun ProfileName(){
    Text(
        text = "Kevin GAREL",
        style = MaterialTheme.typography.headlineMedium,
    )
}

@Composable
fun ProfileDescription(){
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Text(
            text = "oui"
        )
        Text(
            text = "Ecole d'ingénieurs ISIS - FIA4"
        )
    }
}


@Composable
fun Contacts(){
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
            Icon(
                painterResource(R.drawable.linkedin),
                contentDescription = "LinkedIn Logo",
                Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.size(15.dp))
            Text(
                text = "https://www.oui.fr/kgarel"
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        Row{
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Email Icon",
                Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.size(15.dp))
            Text(
                text = "kevin.garel@etud.univ-jfc.fr"
            )
        }
    }
}

@Composable
fun StartButton(onClick : () -> Unit){
    Button(onClick = onClick) {
        Text(
            text = "Démarrer"
        )
    }
}

@Composable
fun ProfilePage(sizeClasses: WindowSizeClass, onStartClick: () -> Unit){
    val sizeClass = sizeClasses.windowWidthSizeClass
    when (sizeClass) {
        WindowWidthSizeClass.EXPANDED -> {
            ProfileAssetsExpanded(onStartClick)
        }
        else -> {
            ProfileAssets(onStartClick)
        }
    }
}

@Composable
fun ProfileAssets(onStartClick: () -> Unit){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        ProfilePicture()
        ProfileName()
        Spacer(modifier = Modifier.size(25.dp))
        ProfileDescription()
        Spacer(modifier = Modifier.size(25.dp))
        Contacts()
        Spacer(modifier = Modifier.size(25.dp))
        StartButton(onStartClick)
    }
}

@Composable
fun ProfileAssetsExpanded(onStartClick: () -> Unit){
    Row (modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            ProfilePicture()
            ProfileName()
            ProfileDescription()
        }
        Spacer(modifier = Modifier.size(50.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
            Contacts()
            StartButton(onStartClick)
        }
    }
}