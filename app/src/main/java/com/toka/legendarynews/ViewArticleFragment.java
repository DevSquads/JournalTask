package com.toka.legendarynews;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toka.legendarynews.databinding.ViewArticleFragmentBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewArticleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewArticleFragment extends Fragment {

    private ViewArticleFragmentBinding binding;

    public ViewArticleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ViewArticleFragment.
     */
    public static ViewArticleFragment newInstance() {
        return new ViewArticleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ViewArticleFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        binding.tvTitle.setText(title);
//        binding.tvDesc.setText(desc);
//        binding.tvAuthor.setText(getString(R.string.by_author_name, authorName));
    }
}