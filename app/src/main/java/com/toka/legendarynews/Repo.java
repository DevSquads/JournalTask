package com.toka.legendarynews;

import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Repo {

    private static final String ARTICLES = "articles";
    private static final String USERS = "users";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String IS_ADMIN = "isAdmin";

    private static final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static final FirebaseUser user = mAuth.getCurrentUser();
    private static final DatabaseReference usersTable = FirebaseDatabase.getInstance().getReference().child(USERS),
            currentUserRow = user != null ? usersTable.child(user.getUid()) : null,
            currentUserArticles = user != null ? currentUserRow.child(ARTICLES) : null;

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

        if (user != null) {
            Article article = new Article();
            article.setTitle(title);
            article.setDescription(description);
            article.setPublished(false);

            currentUserRow.get().addOnCompleteListener(task -> {
                if (task.isSuccessful() && task.getResult() != null) {
                    Author author = new Author();
                    author.setId(task.getResult().child(ID).getValue(String.class));
                    author.setName(task.getResult().child(NAME).getValue(String.class));
                    author.setAdmin(task.getResult().child(IS_ADMIN).getValue(Boolean.class));

                    article.setAuthor(author);

                    long noOfArticles = task.getResult().child(ARTICLES).getChildrenCount();
                    String articleId = author.getId() + noOfArticles;
                    article.setId(articleId);

                    currentUserArticles.child(articleId).setValue(article).addOnCompleteListener(articleAddingResultTask::postValue);
                }
            });
        }

        return articleAddingResultTask;
    }

    public static MutableLiveData<List<Article>> getArticles() {
        MutableLiveData<List<Article>> articlesLiveData = new MutableLiveData<>();

        if (user != null) {
            currentUserArticles.get().addOnCompleteListener(task -> {
                if (task.isSuccessful() && task.getResult() != null) {
                    GenericTypeIndicator<Map<String, Article>> genericTypeIndicator = new GenericTypeIndicator<Map<String, Article>>() {};
                    Map<String, Article> hashMap = task.getResult().getValue(genericTypeIndicator);
                    if (hashMap != null) {
                        List<Article> articles = new ArrayList<>();
                        for (Map.Entry<String, Article> entry : hashMap.entrySet())
                            articles.add(entry.getValue());

                        articlesLiveData.postValue(articles);
                    }
                }
            });
        }
        return articlesLiveData;
    }
}
