<template>
  <div class="pageWraper">

  <mdb-container class="mt-5 mb-5">

    <mdb-row class="head">
      <mdb-col md="8" class="">
        <h3 class="text-left pb-4 pt-5 indigo-text">LEGENDARY News</h3>
        <h1 class="text-black text-left pb-4 pt-4" style="font-size: 40px;">
           Our Articles Keeps
          <br>You Updated To The World News
        </h1>
      </mdb-col>

      <mdb-col md="4">
          <img class="embed-responsive-item" src="~assets/img/header1.png" alt="">
      </mdb-col>
    </mdb-row>

    <h1 class="mt-2 text-left ml-3 grey-text" >Published Articles</h1>

     <articleslist
     :articles="articles"
     />

  </mdb-container>
</div>
</template>

<script>
    import { getUserFromCookie } from '~/helpers'
    import * as firebase from 'firebase/app'
    import 'firebase/auth'
    import articleslist from '~/components/articleslist.vue'
    import {
      mdbJumbotron,
      mdbContainer,
      mdbInput,
      mdbCard,
      mdbCardHeader,
      mdbCardTitle,
      mdbCardText,
      mdbCardBody,
      mdbIcon,
      mdbBtn,
      mdbRow,
      mdbCol,
    } from 'mdbvue';

    export default {
      components: {
        articleslist,
        mdbJumbotron,
        mdbContainer,
        mdbInput,
        mdbCard,
        mdbCardHeader,
        mdbCardTitle,
        mdbCardBody,
        mdbCardText,
        mdbIcon,
        mdbBtn,
        mdbRow,
        mdbCol,
      },
      data() {
        return {
          articles:[]
        }
      },
    mounted() {
      console.log('I am in mounted')
      this.$axios.get('http://localhost:8080/api/puplishedArticles')
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
};
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
