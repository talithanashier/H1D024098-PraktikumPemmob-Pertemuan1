package com.pemmob.tita.ui.screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pemmob.tita.R
import com.pemmob.tita.data.dummy.DummyData
import com.pemmob.tita.data.model.Category
import com.pemmob.tita.data.model.Product
import com.pemmob.tita.ui.theme.JualanTheme


@Composable
fun ProductItemCard(
    product: Product,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        border = null
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.dummy_product
                    ),
                    contentDescription = product.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )

                if (product.category != null) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .background(
                                color = MaterialTheme.colorScheme.secondary,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .padding(
                                horizontal = 8.dp,
                                vertical = 4.dp
                            )
                    ) {
                        Text(
                            text = product.category.name,
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "Rp ${product.price}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}


@Composable
fun CategoryItem(
    category: Category,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val containerColor =
        if (isSelected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.surfaceVariant
        }

    val contentColor =
        if (isSelected) {
            MaterialTheme.colorScheme.onPrimary
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        }

    Card(
        modifier = Modifier.clickable {
            onClick()
        },
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {

        Text(
            text = category.name,
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
            fontWeight = FontWeight.Medium
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaftarProductScreen() {

    var selectedCategoryId by remember {
        mutableStateOf(
            DummyData.categories.firstOrNull()?.id
        )
    }

    val filteredProducts =
        if (selectedCategoryId != null) {

            DummyData.products.filter {
                it.category_id == selectedCategoryId
            }

        } else {

            DummyData.products

        }

    val context = LocalContext.current

    Scaffold(

        containerColor = MaterialTheme.colorScheme.background,

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "Daftar Produk UMKM"
                    )
                },

                actions = {

                    Text(
                        text = "🛒",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(
                            end = 16.dp
                        )
                    )
                },

                colors = TopAppBarDefaults.topAppBarColors(

                    containerColor =
                    MaterialTheme.colorScheme.primary,

                    titleContentColor =
                    MaterialTheme.colorScheme.onPrimary,

                    actionIconContentColor =
                    MaterialTheme.colorScheme.onPrimary
                )
            )
        }

    ) { paddingValues ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)

        ) {

            Text(

                text = "Kategori Produk",

                style =
                MaterialTheme.typography.titleLarge,

                color = MaterialTheme.colorScheme.onBackground,

                modifier = Modifier.padding(
                    all = 16.dp
                )
            )


            LazyRow(

                contentPadding =
                PaddingValues(
                    horizontal = 16.dp
                ),

                horizontalArrangement =
                Arrangement.spacedBy(
                    8.dp
                )

            ) {

                items(
                    DummyData.categories
                ) { category ->

                    CategoryItem(

                        category = category,

                        isSelected =
                        category.id ==
                                selectedCategoryId,

                        onClick = {

                            selectedCategoryId =
                                category.id
                        }
                    )
                }
            }


            Spacer(
                modifier = Modifier.height(16.dp)
            )


            Text(

                text = "Daftar Produk",

                style =
                MaterialTheme.typography.titleLarge,

                color = MaterialTheme.colorScheme.onBackground,

                modifier = Modifier.padding(

                    horizontal = 16.dp,

                    vertical = 8.dp
                )
            )


            LazyVerticalGrid(

                columns =
                GridCells.Fixed(
                    count = 2
                ),

                contentPadding =
                PaddingValues(
                    all = 16.dp
                ),

                horizontalArrangement =
                Arrangement.spacedBy(
                    16.dp
                ),

                verticalArrangement =
                Arrangement.spacedBy(
                    16.dp
                ),

                modifier =
                Modifier.fillMaxSize()

            ) {

                items(
                    filteredProducts
                ) { product ->

                    ProductItemCard(

                        product = product,

                        onClick = {

                            Toast.makeText(

                                context,

                                "Clicked: ${product.name}",

                                Toast.LENGTH_SHORT

                            ).show()
                        }
                    )
                }
            }
        }
    }
}


/*
 * PREVIEW PRODUCT
 */

@Preview(
    showBackground = true,
    name = "Light Theme"
)
@Composable
fun PreviewDaftarProductLight() {

    JualanTheme(
        darkTheme = false
    ) {

        DaftarProductScreen()
    }
}


@Preview(
    showBackground = true,
    name = "Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewDaftarProductDark() {

    JualanTheme(
        darkTheme = true
    ) {

        DaftarProductScreen()
    }
}