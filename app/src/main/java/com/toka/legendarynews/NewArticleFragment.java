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

import com.toka.legendarynews.databinding.FragmentNewArticleBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewArticleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewArticleFragment extends Fragment {

    private FragmentNewArticleBinding binding;

    // the fragment initialization parameters
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public NewArticleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewArticleFragment.
     */
    public static NewArticleFragment newInstance(String param1, String param2) {
        NewArticleFragment fragment = new NewArticleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

    private void renderStatus(Status status) {
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