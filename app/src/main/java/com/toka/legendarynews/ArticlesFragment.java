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

    public ArticlesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ArticlesFragment.
     */
    public static ArticlesFragment newInstance() {
        return new ArticlesFragment();
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
        ArticlesViewModel articlesViewModel = new ViewModelProvider(this).get(ArticlesViewModel.class);
        articlesViewModel.startFetchingArticles(getViewLifecycleOwner());
        articlesViewModel.getStatus().observe(getViewLifecycleOwner(), this::renderStatus);

        ArticleAdapter adapter = new ArticleAdapter(articlesViewModel.isCurrentUserAdmin(), new ArticleAdapter.OnArticleActionsListener() {
            @Override
            public void onArticleClick(Article article) {
                NavHostFragment.findNavController(ArticlesFragment.this).navigate(ArticlesFragmentDirections.actionArticlesFragment2ToViewArticleFragment(article.getTitle(), article.getDescription(), articlesViewModel.getCurrentUserName()));
            }

            @Override
            public void onArticlePublishClick(Article article) {
                articlesViewModel.publishArticle(article);
            }

            @Override
            public void onArticleDeleteClick(Article article) {
                articlesViewModel.deleteArticle(article);
            }
        });
        binding.rvArticles.setAdapter(adapter);

        articlesViewModel.getArticles().observe(getViewLifecycleOwner(), adapter::submitList);

        binding.fabCreateArticle.setOnClickListener(v -> NavHostFragment.findNavController(this).navigate(R.id.action_articlesFragment2_to_newArticleFragment));
    }

    private void renderStatus(UIStatus UIStatus) {
        if (UIStatus == com.toka.legendarynews.UIStatus.LOADING) {
            binding.cpi.setVisibility(View.VISIBLE);
        } else binding.cpi.setVisibility(View.GONE);
    }
}