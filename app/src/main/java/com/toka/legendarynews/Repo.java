package com.toka.legendarynews;

import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Repo {

    private static final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private Repo() {}
    public static MutableLiveData<Task<AuthResult>> login(String username, String password) {
        MutableLiveData<Task<AuthResult>> authResultTask = new MutableLiveData<>();
        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(authResultTask::postValue);
        return authResultTask;
    }

    public static MutableLiveData<Task<Void>> addArticle(String title, String description) {
        FirebaseUser user = mAuth.getCurrentUser();
        DatabaseReference  usersTable = FirebaseDatabase.getInstance().getReference().child("users");
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
}
