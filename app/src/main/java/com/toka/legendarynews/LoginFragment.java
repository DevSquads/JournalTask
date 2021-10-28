package com.toka.legendarynews;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.toka.legendarynews.databinding.FragmentLoginBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    private LoginViewModel loginViewModel;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.getStatus().observe(getViewLifecycleOwner(), this::renderStatus);

        binding.setViewModel(loginViewModel);
        binding.executePendingBindings();

        binding.btnLogin.setOnClickListener(v -> {
            if (areInputsValid())
                onLoginClicked();
            else {
                loginViewModel.setError(getString(R.string.empty_fields_error));
                loginViewModel.setStatus(Status.ERROR);
            }
        });
    }

    private boolean areInputsValid() {
        Editable usernameEditable = binding.tietUsername.getText(), passwordEditable = binding.tietPass.getText();
        return usernameEditable != null && passwordEditable != null && usernameEditable.toString().trim().length() > 0 && passwordEditable.toString().trim().length() > 0;
    }

    public void onLoginClicked() {
        loginViewModel.onLogin(this);
    }

    private void renderStatus(Status status) {
        switch (status) {
            case LOADING:
                binding.lpi.setVisibility(View.VISIBLE);
                binding.btnLogin.setEnabled(false);
                binding.tfPassword.setEnabled(false);
                binding.tfUserName.setEnabled(false);
                break;
            case ERROR:
                Toast.makeText(getContext(), loginViewModel.getError() != null? loginViewModel.getError(): getString(R.string.error), Toast.LENGTH_SHORT).show();
            case IDLE:
                binding.lpi.setVisibility(View.GONE);
                binding.btnLogin.setEnabled(true);
                binding.tfPassword.setEnabled(true);
                binding.tfUserName.setEnabled(true);
                break;
            case SUCCESS:
                NavHostFragment.findNavController(this).navigate(LoginFragmentDirections.actionLoginFragmentToArticlesFragment2());
        }
    }
}