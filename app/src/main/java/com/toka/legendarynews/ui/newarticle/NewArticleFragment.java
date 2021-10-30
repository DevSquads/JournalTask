package com.toka.legendarynews.ui.newarticle;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.toka.legendarynews.R;
import com.toka.legendarynews.databinding.FragmentNewArticleBinding;
import com.toka.legendarynews.ui.base.BasicUIStatus;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewArticleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewArticleFragment extends Fragment {

    private FragmentNewArticleBinding binding;

    public NewArticleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NewArticleFragment.
     */
    public static NewArticleFragment newInstance() {
        return new NewArticleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewArticleBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NewArticleViewModel newArticleViewModel = new ViewModelProvider(this).get(NewArticleViewModel.class);
        newArticleViewModel.getStatus().observe(getViewLifecycleOwner(), this::renderStatus);

        binding.btnSendForPublishing.setOnClickListener(v -> {
            Editable titleEditable = binding.tietTitle.getText(), descEditable = binding.tietDesc.getText();
            if (titleEditable != null && descEditable != null && areInputsValid(titleEditable, descEditable))
                newArticleViewModel.addArticle(titleEditable.toString().trim(), descEditable.toString().trim(), this);
            else Toast.makeText(getContext(), getString(R.string.empty_article_fields), Toast.LENGTH_LONG).show();
        });
    }

    private boolean areInputsValid(@NonNull Editable titleEditable, @NonNull Editable descEditable) {
        return titleEditable.toString().trim().length() > 0 && descEditable.toString().trim().length() > 0;
    }

    private void renderStatus(BasicUIStatus status) {
        switch (status) {
            case LOADING:
                binding.lpi.setVisibility(View.VISIBLE);
                binding.btnSendForPublishing.setEnabled(false);
                binding.tfTitle.setEnabled(false);
                binding.tfDesc.setEnabled(false);
                break;
            case ERROR:
                Toast.makeText(getContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
            case IDLE:
                reActivateScreen();
                break;
            case SUCCESS:
                reActivateScreen();
                binding.tietDesc.setText("");
                binding.tietTitle.setText("");
                Toast.makeText(getContext(), getString(R.string.article_sent), Toast.LENGTH_SHORT).show();
        }
    }

    public void reActivateScreen() {
        binding.lpi.setVisibility(View.GONE);
        binding.btnSendForPublishing.setEnabled(true);
        binding.tfTitle.setEnabled(true);
        binding.tfDesc.setEnabled(true);
    }
}