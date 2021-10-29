package com.toka.legendarynews;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.toka.legendarynews.databinding.FragmentArticlesBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArticlesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticlesFragment extends Fragment {

    private FragmentArticlesBinding binding;

    // the fragment initialization parameters
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ArticlesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ArticlesFragment.
     */
    public static ArticlesFragment newInstance(String param1, String param2) {
        ArticlesFragment fragment = new ArticlesFragment();
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
        binding = FragmentArticlesBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArticleAdapter adapter = new ArticleAdapter(article -> NavHostFragment.findNavController(this).navigate(ArticlesFragmentDirections.actionArticlesFragment2ToViewArticleFragment(article.getTitle(), article.getDescription(), article.getAuthor().getName())));
        binding.rvArticles.setAdapter(adapter);

        ArticlesViewModel articlesViewModel = new ViewModelProvider(this).get(ArticlesViewModel.class);
        articlesViewModel.startFetchingArticles(getViewLifecycleOwner());
        articlesViewModel.getStatus().observe(getViewLifecycleOwner(), this::renderStatus);
        articlesViewModel.getArticles().observe(getViewLifecycleOwner(), adapter::submitList);

        binding.fabCreateArticle.setOnClickListener(v -> NavHostFragment.findNavController(this).navigate(R.id.action_articlesFragment2_to_newArticleFragment));
    }

    private void renderStatus(UIStatus UIStatus) {
        switch (UIStatus) {
            case LOADING:
                binding.cpi.setVisibility(View.VISIBLE);
                break;
            case ERROR:
                Toast.makeText(getContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
            case IDLE:
                binding.cpi.setVisibility(View.GONE);
                break;
        }
    }
}