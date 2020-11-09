package id.putraprima.mvvmlogin.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import id.putraprima.mvvmlogin.models.LoggedIn;

public class LoginViewModelFactory implements ViewModelProvider.Factory  {
    private LoggedIn loggedIn;

    public LoginViewModelFactory(LoggedIn loggedIn) {
        this.loggedIn = loggedIn;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(LoginViewModel.class)){
            return (T) new LoginViewModel(loggedIn);
        }
        throw new IllegalArgumentException("Viewmodel Yang Diminta LoginViewModel");
    }
}
