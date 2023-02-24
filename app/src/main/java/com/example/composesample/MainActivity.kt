package com.example.composesample

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composesample.ui.theme.ComposeSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //PreviewMessage()
                    //ShowProfile()
                    Conversion(getMessages())
                }
            }
        }
    }

    @Composable
    fun getMessages() = listOf(
        Message(
          profile = painterResource(id = R.drawable.test),
          name = "someBody",
          description = "HI"
        ),
        Message(
            profile = painterResource(id = R.drawable.test),
            name = "AnyBody",
            description = "HELLO"
        ),
        Message(
            profile = painterResource(id = R.drawable.test),
            name = "Android",
            description = "IYA~ ㅁㄴㅇㄹㄴㅇㅁㄹㄴㅇㅁㄹㅇㄴㅁㄹㅇㄴㅁㄹㅇㄴㅁㄹㅁㄴㄹ"
        ),
        Message(
            profile = painterResource(id = R.drawable.test),
            name = "iOS",
            description = "KIA~"
        ),
        Message(
            profile = painterResource(id = R.drawable.test),
            name = "Mac",
            description = "WOW"
        )
    )

    @Composable
    fun Conversion(messages: List<Message>) {
        LazyColumn {
            items(messages) {
                FirstProfile(it)
            }
        }
    }

    @Preview(name = "Night Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun ShowProfile() {
        ComposeSampleTheme {
            Surface {
                FirstProfile(
                    message = Message(
                        profile = painterResource(id = R.drawable.test),
                        name = "PARK",
                        description = "Android Developer"
                    )
                )
            }
        }
    }

    @Composable
    fun FirstProfile(message: Message) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = message.profile,
                contentDescription = "profile Image",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(1.dp, MaterialTheme.colors.secondary, CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            var isExpand by remember { mutableStateOf(false) }
            val surfaceColor by animateColorAsState(
                if (isExpand) MaterialTheme.colors.primary else MaterialTheme.colors.secondary
            )

            Column(modifier = Modifier.clickable { isExpand = !isExpand }) {
                Text(
                    text = message.name,
                    color = MaterialTheme.colors.secondary
                )

                Spacer(modifier = Modifier.height(3.dp))

                Surface(
                    shape = MaterialTheme.shapes.medium,
                    elevation = 5.dp,
                    color = surfaceColor,
                    modifier = Modifier.animateContentSize().padding(all = 1.dp)
                ) {
                    Text(
                        text = message.description,
                        modifier = Modifier.padding(all = 3.dp),
                        style = MaterialTheme.typography.h6,
                        maxLines = if (isExpand) Int.MAX_VALUE else 1
                    )
                }
            }
        }
    }

    @Composable
    fun PreviewMessage() {
        FirstCard(letter = Letter("PARK", "내용 1"))
        SecondCard(letter = Letter("PARK 2", "내용 2"))
        ThirdCard(letter = Letter("PARK 3", "내용 3"))
    }

    @Composable
    fun FirstCard(letter: Letter) {
        Column {
            Text(text = "first Column")
            Text(text = letter.authorName)
            Text(text = letter.content)
        }
    }

    @Composable
    fun SecondCard(letter: Letter) {
        Row {
            Text(text = "second Row")
            Text(text = letter.authorName)
            Text(text = letter.content)
        }
    }

    @Composable
    fun ThirdCard(letter: Letter) {
        Box {
            Text(text = "third Box")
            Text(text = letter.authorName)
            Text(text = letter.content)
        }
    }

    @Composable
    fun NextLine() {
        Text(text = "\n")
    }

    data class Letter(val authorName: String, val content: String)
    data class Message(val profile: Painter, val name: String, val description: String)
}