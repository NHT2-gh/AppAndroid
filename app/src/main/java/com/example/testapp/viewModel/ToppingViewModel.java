package com.example.testapp.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testapp.model.Topping;

public class ToppingViewModel extends ViewModel {
    private MutableLiveData<Topping> selectedTopping = new MutableLiveData<>();
    public void setSelectedTopping(Topping topping) {
        selectedTopping.setValue(topping);
    }

    public LiveData<Topping> getSelectedTopping() {
        return selectedTopping;
    }
}
