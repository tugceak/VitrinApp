package com.tugceaksoy.vitrinapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.bumptech.glide.util.Util
import com.google.android.material.navigation.NavigationView
import com.tugceaksoy.vitrinapp.R
import com.tugceaksoy.vitrinapp.data.model.discover.CollectionList
import com.tugceaksoy.vitrinapp.data.model.discover.Product
import com.tugceaksoy.vitrinapp.data.model.discover.ProductList
import com.tugceaksoy.vitrinapp.data.model.discover.ShopList
import com.tugceaksoy.vitrinapp.databinding.ActivityHomeBinding
import com.tugceaksoy.vitrinapp.ui.discover.DiscoverFragmentDirections
import com.tugceaksoy.vitrinapp.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initNavigation()
        binding.navView.setNavigationItemSelectedListener(this)


    }

        private fun initNavigation() {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHostFragment.navController
            NavigationUI.setupWithNavController(binding.toolbar, navController)
            appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)
            setupActionBarWithNavController(navController, appBarConfiguration)
        }

        override fun onSupportNavigateUp(): Boolean {
            val navController = findNavController(R.id.nav_host_fragment)
            return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
        }

        fun showLoading() {
            binding.lottieAnimationView.isVisible = true
        }

        fun hideLoading() {
            binding.lottieAnimationView.isVisible = false
        }

        override fun onBackPressed() {
            if (supportFragmentManager.backStackEntryCount == 0) {
                val builder = AlertDialog.Builder(this)
                    .setTitle("Çıkış yap")
                    .setMessage("Uygulamayı kapatmak istiyor musunuz?")
                    .setIcon(android.R.drawable.ic_delete)
                    .setPositiveButton("İptal") { dialogInterface, _ ->
                        dialogInterface.cancel()
                    }
                    .setNegativeButton("Çıkış") { dialogInterface, _ ->
                        dialogInterface.cancel()
                        android.os.Process.killProcess(android.os.Process.myPid())
                    }

                val alertDialog: AlertDialog = builder.create()
                alertDialog.show()
            } else {
                supportFragmentManager.popBackStackImmediate()
            }
        }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.navigationProductDetail -> {
               homeViewModel.getProductList()?.let { products ->
                    val actionProduct =
                        DiscoverFragmentDirections.toProductsDetailFragment(ProductList(products))
                   findNavController(R.id.nav_host_fragment).navigate(actionProduct)
                }

            }

        }
        return true
    }

}
