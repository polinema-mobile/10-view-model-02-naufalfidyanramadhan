package id.putraprima.mvvmlogin.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import id.putraprima.mvvmlogin.R;
import id.putraprima.mvvmlogin.databinding.FragmentLoginBinding;
import id.putraprima.mvvmlogin.models.LoggedIn;
import id.putraprima.mvvmlogin.viewmodels.LoginViewModel;
import id.putraprima.mvvmlogin.viewmodels.LoginViewModelFactory;

public class LoginFragment extends Fragment {
    public static String LOGIN_SUCCESSFUL = "LOGIN_SUCCESSFUL";

    private LoginViewModel loginViewModel;
    private SavedStateHandle savedStateHandle;
    FragmentLoginBinding binding;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentLoginBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        binding.setLifecycleOwner(this);
        LoginViewModelFactory loginViewModelFactory = new LoginViewModelFactory(new LoggedIn("naufalfidyanr@gmail.com", "1234567"));
        loginViewModel = new ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel.class);
        binding.setLoginViewModel(loginViewModel);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        savedStateHandle = Navigation.findNavController(view)
                .getPreviousBackStackEntry()
                .getSavedStateHandle();
        savedStateHandle.set(LOGIN_SUCCESSFUL, false);
        loginViewModel.loggedLiveData().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (loginViewModel.loggedLiveData().getValue() == true){
                    Bundle bundle = new Bundle();
                    bundle.putString("email", loginViewModel.getLogin().getValue().email);
                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment,bundle);
                    savedStateHandle.set(LOGIN_SUCCESSFUL, true);
                }
            }
        });
    }
}