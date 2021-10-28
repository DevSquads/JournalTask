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

import java.util.ArrayList;
import java.util.List;

public class Repo {

    private static final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static final FirebaseUser user = mAuth.getCurrentUser();
    private static final DatabaseReference usersTable = FirebaseDatabase.getInstance().getReference().child("users"),
            currentUserRow = user != null ? usersTable.child(user.getUid()) : null,
            currentUserArticles = user != null ? currentUserRow.child("articles") : null;

    private Repo() {
    }

    public static MutableLiveData<Task<AuthResult>> login(String username, String password) {
        MutableLiveData<Task<AuthResult>> authResultTask = new MutableLiveData<>();
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

            Author author = new Author();
            usersTable.child(user.getUid()).get().addOnCompleteListener(task -> {
                if (task.isSuccessful() && task.getResult() != null) {
                    author.setId(task.getResult().child("id").getValue(String.class));
                    author.setName(task.getResult().child("name").getValue(String.class));
                    author.setAdmin(task.getResult().child("isAdmin").getValue(boolean.class));

                    article.setAuthor(author);

                    String articleId = author.getId() + author.getNoOfArticles();
                    article.setId(articleId);

                    usersTable.child(user.getUid()).child("articles").child(articleId).setValue(article).addOnCompleteListener(articleAddingResultTask::postValue);
                }
            });
        }

        return articleAddingResultTask;
    }

    public static MutableLiveData<List<Article>> getArticles() {
        MutableLiveData<List<Article>> articlesLiveData = new MutableLiveData<>();

        if (user != null) {
            List<Article> articles = new ArrayList<>();
            ChildEventListener childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                    // A new data item has been added, add it to the list
                    Article article = dataSnapshot.getValue(Article.class);
                    articles.add(article);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                    // A data item has changed
                    Article article = dataSnapshot.getValue(Article.class);
                    if (articles.contains(article))
                        articles.set(articles.indexOf(article), article);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    // A data item has been removed
                    Article article = dataSnapshot.getValue(Article.class);
                    articles.remove(article);
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String previousChildName) {
                    // A data item has changed position; doesn't affect us hence we don't care about the current user's articles order
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    //Toast.makeText(mContext, "Failed to load data.", Toast.LENGTH_SHORT).show();
                }
            };

            currentUserArticles.addChildEventListener(childEventListener);
            articlesLiveData.postValue(articles);
        }
        return articlesLiveData;
    }
}
