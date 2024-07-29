package com.example.foodapp.fragments

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.example.foodapp.R
import com.example.foodapp.modalClass.FoodItem
import com.example.foodapp.ui.theme.FoodAppTheme


class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ComposeView>(R.id.composeView).setContent {
            FoodAppTheme {
                HomeFragmentScreen(::onItemAdded)
            }

        }
    }

    private fun onItemAdded(foodItem: FoodItem)
    {

    }

}

@Composable
fun HomeFragmentScreen(onAddClicked: (foodItem: FoodItem) -> Unit) {

    val context = LocalContext.current

    val foodItems = getFoodItems()


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(26.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .padding(vertical = 6.dp, horizontal = 16.dp),
        ) {
            Text(
                text = context.getString(R.string.app_title),
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {
            items(foodItems.size) { index ->
                FoodItemCard(foodItems[index], onAddClicked)
            }
        }

    }
}

@Composable
fun FoodItemCard(foodItem: FoodItem, onAddClicked: (foodItem: FoodItem) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onAddClicked.invoke(foodItem) },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = foodItem.image),
                contentDescription = "Food Image",
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = foodItem.name,
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = foodItem.price,
                    style = MaterialTheme.typography.bodyLarge
                )

            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                },
                modifier = Modifier,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red // Set the background color to red
                )
            ) {
                Text(
                    text = "Add",
                    color = Color.White // Set the text color to white
                )
            }

        }
    }
}

fun getFoodItems(): List<FoodItem> {
    return listOf(
        FoodItem(
            id = 1,
            name = "Pizza",
            price = "$10",
            image = R.drawable.breakfast
        ),
        FoodItem(
            id = 2,
            name = "Burger",
            price = "$8",
            image = R.drawable.breakfast
        ),
        FoodItem(
            id = 3,
            name = "Burger",
            price = "$6",
            image = R.drawable.breakfast
        )
    )
}