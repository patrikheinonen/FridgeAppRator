package com.example.fridgeapprator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fridgeapprator.fragment.FridgeProductListFragment;
import com.example.fridgeapprator.fragment.InstructionsFragment;
import com.example.fridgeapprator.fragment.ShoppingListFragment;
import com.example.fridgeapprator.fragment.WelcomePageFragment;
import com.example.fridgeapprator.model.ShoppingList;
import com.example.fridgeapprator.viewModel.ProductTypeViewModel;
import com.example.fridgeapprator.viewModel.ProductViewModel;
import com.example.fridgeapprator.viewModel.ShoppingListProductViewModel;
import com.example.fridgeapprator.viewModel.ShoppingListViewModel;

public class MainActivity extends AppCompatActivity {

    ProductTypeViewModel productTypeViewModel;
    ProductViewModel productViewModel;
    ShoppingListProductViewModel shoppingListProductViewModel;
    ShoppingListViewModel shoppingListViewModel;
    ShoppingListFragment shoppingListFragment;
    WelcomePageFragment welcomePageFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*productTypeViewModel = new ViewModelProvider(this).get(ProductTypeViewModel.class);
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productTypeViewModel.insert(new ProductType("Maito", 1));
        ProductType productTypeLiha = new ProductType("Liha", 1);
        int lihaid = (int)productTypeViewModel.insert(productTypeLiha);
        productViewModel.insert(new Product(1, new Date(2020-11-28)));
        shoppingListViewModel = new ViewModelProvider(this).get(ShoppingListViewModel.class);
        shoppingListViewModel.insert(new ShoppingList("Joonatananin kassit"));
        shoppingListProductViewModel = new ViewModelProvider(this).get(ShoppingListProductViewModel.class);
        shoppingListProductViewModel.insert(new ShoppingListProduct("Maito", 2, 1));
        shoppingListProductViewModel.insert(new ShoppingListProduct("Kokkare", 2, 1));
        ProductType productTypeTesti = new ProductType("Testi", 1);
        int testiid = (int)productTypeViewModel.insert(productTypeTesti);
        productTypeTesti.setProductTypeID(testiid);
        productTypeViewModel.delete(productTypeTesti);

         */

        shoppingListViewModel = new ViewModelProvider(this).get(ShoppingListViewModel.class);
        shoppingListViewModel.insert(new ShoppingList("Ostoslista"));


        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }
            //TODO: If fridge is empty open welcome page fragment. If not open the fridge fragment.
            welcomePageFragment = new WelcomePageFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.add(R.id.fragment_container, welcomePageFragment, "welcomePageFragment");
            //transaction.addToBackStack(null); // to get back from ListFragment
            // Commit the transaction
            transaction.commit();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_shoppingList) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            //transaction.remove(fragment).commit(); // wipe the old
            ShoppingListFragment shoppingListFragment = new ShoppingListFragment();
            transaction.addToBackStack(null);
            transaction.replace(R.id.fragment_container, shoppingListFragment)
                    .commit();
            return true;
        }
        else if (id == R.id.menu_fridge) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            //transaction.remove(fragment).commit(); // wipe the old
            FridgeProductListFragment fridgeProductListFragment = new FridgeProductListFragment();
            transaction.addToBackStack(null);
            transaction.replace(R.id.fragment_container, fridgeProductListFragment)
                    .commit();
            return true;
        }
        else if (id == R.id.menu_instructions) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            //transaction.remove(fragment).commit(); // wipe the old
            InstructionsFragment instructionsFragment = new InstructionsFragment();
            transaction.addToBackStack(null);
            transaction.replace(R.id.fragment_container, instructionsFragment)
                    .commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}