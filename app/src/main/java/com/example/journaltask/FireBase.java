package com.example.journaltask;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.journaltask.modules.articleWithNum;
import com.example.journaltask.modules.articles;
import com.example.journaltask.modules.articlesWithid;
import com.example.journaltask.modules.users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class FireBase {
       boolean status;
     users user = null;
     private FirebaseFirestore  db= FirebaseFirestore.getInstance(); //create Firebase Firestore object and get instance to establish connection
     private CollectionReference db_collection=db.collection("users"); // CollectionReference to connect to users collection
    private CollectionReference db_articles=db.collection("bendingArticles"); // CollectionReference to connect to bendingArticles collection
    private CollectionReference db_articlesFinal=db.collection("articles");// CollectionReference to connect to articles collection
//all function got callBack objects just to synchronise retriving data speed with app callBacks could return lists or just boolean for isSuccessful or not
      void signIn(String userNamed  , String password, callBack myCallback){
          db_collection.whereEqualTo("userName",userNamed)
                  .whereEqualTo("password",password)
                  .get()
                  .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                      @Override
                      public void onComplete(@NonNull Task<QuerySnapshot> task) {
                          if (task.isSuccessful()) {
                              for (DocumentSnapshot documentSnapshot : task.getResult())
                                  user = new users(documentSnapshot.getBoolean("admin"),
                                          documentSnapshot.getString("password"),
                                          documentSnapshot.getString("userName"));
                              myCallback.onCallback(user);}
                      }
                  });


}
      void signup(users user,callBack myCallback){
          db_collection.document().set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
              @Override
              public void onComplete(@NonNull Task<Void> task) {
                      myCallback.isSent(task.isSuccessful());

              }
          });

}
      void bindingArticles(articles article,callBack mycallBack){
          db_articles.document().set(article).addOnCompleteListener(new OnCompleteListener<Void>() {
              @Override
              public void onComplete(@NonNull Task<Void> task) {
                  mycallBack.isSent(task.isSuccessful());
              }
          });
}
      void getAllBinding(callBack callBack) { List<articlesWithid> RA=new ArrayList<articlesWithid>();
    db_articles.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (!task.isSuccessful()) {
            return;}
            for (DocumentSnapshot documentSnapshot:task.getResult())
            {
                RA.add(new articlesWithid(documentSnapshot.getString("author"),
                       documentSnapshot.getString("body"),
                        documentSnapshot.getString("title"),
                        documentSnapshot.getId()));
            }
            callBack.retriveArticles(RA);
        }
    });
}
      void deleteFromBinding(articlesWithid articles,callBack callBack){
          db_articles.document(articles.getKey()).delete();
          db_articlesFinal.document().set(new articles(articles.getAuthor(),articles.getBody(),articles.getTitle())).addOnCompleteListener(new OnCompleteListener<Void>() {
              @Override
              public void onComplete(@NonNull Task<Void> task) {
                  callBack.isSent(task.isSuccessful());
              }
          });


}
      void getAllArticles(callBack callBack){
          List<articlesWithid> list=new ArrayList<>();
          db_articlesFinal.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
              @Override
              public void onComplete(@NonNull Task<QuerySnapshot> task) {
                  if (!task.isSuccessful())
                      return;
             for (DocumentSnapshot documentSnapshot:task.getResult())
                 list.add(new articlesWithid(documentSnapshot.getString("author"),
                         documentSnapshot.getString("body"),
                         documentSnapshot.getString("title"),
                         documentSnapshot.getId()));
             callBack.retriveAllArticles(list);
              }
          });
}
       void DeleteArtcle(articleWithNum articleWithNum, callBack callBack){
          db_articlesFinal.document(articleWithNum.getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
              @Override
              public void onComplete(@NonNull Task<Void> task) {
                  callBack.isSent(task.isSuccessful());
             Log.e("Deleted status",task.isSuccessful()+"");
              }
          });
       }
}

