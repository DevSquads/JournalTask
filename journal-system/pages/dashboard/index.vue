

<template>
  <div class="pageWraper">
  <mdb-container class="mt-5 mb-5">

    <h2 class="mb-5 text-left indigo-text" >Articles List</h2>

    <mdb-tbl responsive-sm striped >
      <mdb-tbl-head color="grey" textWhite>
        <tr>
          <th>#</th>
          <th>Title</th>
          <th>Discription</th>
          <th>Author</th>
          <th>Status</th>
          <th v-if="usersRole == 'admin'">Publish</th>
          <th v-if="usersRole == 'admin'">Delete</th>
        </tr>
      </mdb-tbl-head>
      <mdb-tbl-body>
        <tr v-for="(article,index) in articles" v-bind:key="index">
          <th>{{index + 1}}</th>
          <td>{{ article.title }}</td>
          <td>{{ article.disc }}</td>
          <td>{{article.author}}</td>
          <td v-if="article.isPuplished == true"><span style="color:green;">Published</span></td>
          <td v-else><span style="color:red;">Unpublished</span></td>
          <!-- published or not condition -->
            <td v-if="article.isPuplished == true && usersRole == 'admin'" ></td>
            <td v-if="article.isPuplished == false && usersRole == 'admin'" ><a @click="publishArticle(article.id, article.author)"><mdb-icon icon="upload" /></a> </td>
          <!-- published or not condition -->
          <td v-if="usersRole == 'admin'"><a @click="deletArticle(article.id, article.author, article.isPuplished )"> <mdb-icon icon="trash-alt" /></a> </td>
        </tr>
      </mdb-tbl-body>
    </mdb-tbl>

  </mdb-container>
</div>
</template>

<script>
  import Cookies from 'js-cookie'
  import { getUserFromCookie } from '~/helpers'
  import * as firebase from 'firebase/app'
  import 'firebase/auth'
  import { mdbTbl, mdbTblHead, mdbTblBody, mdbContainer, mdbIcon } from 'mdbvue';
  export default {
    name: 'TablePage',
    components: {
      mdbTbl,
      mdbTblHead,
      mdbTblBody,
      mdbContainer,
      mdbIcon
    },
    data() {
      return {
        articles:[],
        signedUserEmail:'',
        signedAuthor:'',
        usersRole:'',
      }
    },
    methods:{
      deletArticle(id, author, status){
        console.log('hi from delete')
        console.log(id)
        this.$axios.post('http://localhost:8080/api/deleteArticle', {"articleId": `${id}`, "author": `${author}`, "status": `${status}` })
        .then(res => {
          console.log('In Delete Article')
        })
        .catch(err => {
          console.log('ERROR:cannot perform the request' + err)
        });
        location.reload();
      },
      publishArticle(id, authorName){
        console.log('hi from publish')
        console.log(id)
        console.log(authorName)
        this.$axios.post('http://localhost:8080/api/puplishArticle', {"articleId": `${id}`, "authorName": `${authorName}`})
        .then(res => {
          console.log('In Delete Article')

        })
        .catch(err => {
          console.log('ERROR:cannot perform the request' + err)
        });
        location.reload();
      }
    },
    mounted() {
      // geting signed Author
      console.log('I am in mounted')
      let userEmail = Cookies.get('usercookie');
      console.log(`userEmail: ${userEmail}`)
      this.signedUserEmail = userEmail
      let usernameArr = this.signedUserEmail.split("@");
      this.signedAuthor = usernameArr[0]

      // geting Author role

      this.$axios.post('http://localhost:8080/api/user', { "signedAuthor": `${usernameArr[0]}` })
      .then(res => {
        console.log(res.data)
        this.usersRole = res.data.user[0].data.role;
        console.log(this.usersRole)
      })
      .catch(err => console.log('ERROR:cannot perform the request' + err));

      // geting Articles
      this.$axios.post('http://localhost:8080/api/articles', { "signedAuthor": `${usernameArr[0]}` })
      .then(res => {
        console.log(res.data)
        this.articles = res.data.articles;
        console.log(this.articles)
      })
      .catch(err => console.log('ERROR:cannot perform the request' + err));
    },
    asyncData({ req, redirect }) {
      console.log('hi from asyncData')

      if(process.server){
        console.log('server is on')
        const user = getUserFromCookie(req)
        console.log(user);
        if(!user){
          redirect("/")
        }
      } else {
        let user = firebase.auth().currentUser;
        console.log(user);
        if(!user){
          redirect("/")
        }
      }
    }
  }
</script>

<style scoped>
  .container {
    text-align: center;
  }
img{
  width: 100%;
  height: 100%;
}
</style>
