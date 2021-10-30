package com.toka.legendarynews;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class Repo {

    private static final String ARTICLES = "articles";
    private static final String USERS = "users";
    private static final String NAME = "name";
    private static final String IS_ADMIN = "isAdmin";

    private static final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static final List<Article> articlesList = new ArrayList<>();
    private static final MutableLiveData<List<Article>> articlesLiveData = new MutableLiveData<>();
    private static Boolean isCurrentUserAdmin;
    private static String currentUserName;


    private Repo() {
    }

    public static MutableLiveData<Task<AuthResult>> login(String username, String password) {
        MutableLiveData<Task<AuthResult>> authResultTask = new MutableLiveData<>();
        if (mAuth.getCurrentUser() != null)
            mAuth.signOut();

        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(authResultTask::postValue);
        return authResultTask;
    }

    public static MutableLiveData<Task<Void>> addArticle(String title, String description) {
        MutableLiveData<Task<Void>> articleAddingResultTask = new MutableLiveData<>();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Article article = new Article();
            article.setTitle(title);
            article.setDescription(description);

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child(ARTICLES).get().addOnCompleteListener(task -> {
                if (task.isSuccessful() && task.getResult() != null) {
                    GenericTypeIndicator<Map<String, Article>> genericTypeIndicator = new GenericTypeIndicator<Map<String, Article>>() {};
                    Map<String, Article> articlesMap = task.getResult().getValue(genericTypeIndicator);
                    AtomicLong noOfArticles = new AtomicLong();
                    if (articlesMap != null) {
                        articlesMap.forEach((key, val) -> {
                            if (key.startsWith(user.getUid()))
                                noOfArticles.getAndIncrement();
                        });
                    }
                    String articleId = user.getUid() + '-' + noOfArticles.get();
                    article.setId(articleId);

                    databaseReference.child(ARTICLES).child(articleId).setValue(article).addOnCompleteListener(articleAddingResultTask::postValue);
                }
            });
        }

        return articleAddingResultTask;
    }

    public static MutableLiveData<List<Article>> listenForCurrentUserArticleChanges() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            FirebaseDatabase.getInstance().getReference().child(ARTICLES)
            .addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String previousChildName) {
                    // A new article has been added, add it to the beginning of the displayed list
                    Article article = dataSnapshot.getValue(Article.class);
                    if (!articlesList.contains(article) && article != null) {
                        if (article.getId().startsWith(user.getUid()))
                            articlesList.add(0, article);
                        else {
                            articlesList.add(article);
                            reOrderArticlesList();
                        }
                        articlesLiveData.postValue(articlesList);
                    }
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String previousChildName) {
                    // currently there is no need to watch for changes in articles as they shouldn't happen hence there's no UI mechanism to update the articles
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    articlesList.remove(dataSnapshot.getValue(Article.class));
                    articlesLiveData.postValue(articlesList);
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String previousChildName) {
                    // currently there is no need to watch for changes in articles positions as they shouldn't happen hence there's no UI mechanism to do so
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    articlesList.add(null);
                    articlesLiveData.postValue(articlesList);
                }
            });
        }
        return articlesLiveData;
    }

    private static void reOrderArticlesList() {
        Map<String, List<Article>> authorArticlesMap = new HashMap<>();
        articlesList.forEach(article -> {
            String articleId = article.getId(), authorId = articleId.substring(0, articleId.indexOf('-'));
            if (authorArticlesMap.get(authorId) != null)
                authorArticlesMap.get(authorId).add(article);
            else {
                List<Article> authorArticles = new ArrayList<>();
                authorArticles.add(article);
                authorArticlesMap.put(authorId, authorArticles);
            }
        });

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            articlesList.clear();
            List<Article> currentUserArticles = authorArticlesMap.remove(user.getUid());
            if (currentUserArticles != null)
                articlesList.addAll(currentUserArticles);
        } else throw new IllegalStateException("current user is null while trying to order articles list");

        articlesList.addAll(authorArticlesMap.values()
                .stream()
                .sorted(Collections.reverseOrder(Comparator.comparingInt(List::size)))
                .flatMap(Collection::stream)
                .collect(Collectors.toList()));
    }

    public static void publishArticle(Article article) {

    }

    public static void deleteArticle(Article article) {

    }

    public static MutableLiveData<Boolean> setCurrentUserInfo() {
        FirebaseUser user = mAuth.getCurrentUser();
        MutableLiveData<Boolean> isSuccessful = new MutableLiveData<>();
        isSuccessful.postValue(false);
        if (user != null) {
            FirebaseDatabase.getInstance().getReference().child(USERS).child(user.getUid()).get().addOnCompleteListener(task -> {
                DataSnapshot dataSnapshot = task.getResult();
                if (task.isSuccessful() && dataSnapshot != null) {
                    isCurrentUserAdmin = dataSnapshot.child(IS_ADMIN).getValue(Boolean.class);
                    currentUserName = dataSnapshot.child(NAME).getValue(String.class);
                    isSuccessful.postValue(true);
                }
            });
        }

        return isSuccessful;
    }

    public static boolean isCurrentUserAdmin() {
        return isCurrentUserAdmin;
    }

    public static String getCurrentUserName() {
        return currentUserName;
    }
}
