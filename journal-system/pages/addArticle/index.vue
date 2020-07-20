<template>
  <div class="pageWraper">
    <mdb-container class="mt-5 mb-5">

      <mdb-row class="head">
        <mdb-col md="8" class="pt-4">
          <h1 class="text-black text-left pt-5 mt-4" style="font-size: 40px;">
          All articles will be approved before
          <br>being published on
          </h1>
        </mdb-col>

        <mdb-col md="4">
            <img style="width:100%; height:100%"class="embed-responsive-item" src="~assets/img/contact-head.png" alt="">
        </mdb-col>
      </mdb-row>

      <mdb-card>
        <mdb-card-body>
           <h3 class="text-left pb-3 pt-3 indigo-text">Add Article</h3>

                <div v-for="error in errors" class="text-center">
                  <p style="color:red;" >{{error}}</p>
                </div>

                <mdb-row class="mt-3">
                  <mdb-col>
                    <mdb-input label="Article Title" v-model="title" required />
                  </mdb-col>
                </mdb-row>

                  <mdb-row class="mt-3">
                  <mdb-col>
                    <mdb-input type="textarea" label="Article Discription" v-model="disc" required />
                  </mdb-col>
                </mdb-row>

                <mdb-row class="mt-3">
                  <mdb-col class="mt-3" md="8">
                    <div class="text-left">
                      <label>Author Name</label>
                    </div>
                    <select v-model="author" class="browser-default custom-select">
                      <option v-for="(user,index) in users" v-bind:key="index"
                      :value="user.data.name">{{user.data.name}}</option>
                    </select>
                    <!-- <mdb-input label="Author Name" v-model="author" required /> -->
                  </mdb-col>
                  <mdb-col md="4" class="mt-5 pb-2">
                    <mdb-btn color="primary" @click="addArticle" class="float-right">Send</mdb-btn>
                  </mdb-col>
                </mdb-row>

        </mdb-card-body>
      </mdb-card>
  </mdb-container>
</div>
</template>


<script>
  import { getUserFromCookie } from '~/helpers'
  import * as firebase from 'firebase/app'
  import 'firebase/auth'
  import {
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
    mdbCol
  } from "mdbvue";

  export default {
    components: {
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
      mdbCol
    },
    data() {
      return {
          title: "",
          disc: "",
          author: "",
          users: [],
          errors:[]
      };
    },
    methods: {
      addArticle() {
        this.errors = [];
        if(!this.title){
          this.errors.push('Article Title is required, Please fill it properly')
        }else if (!this.disc) {
          this.errors.push('Article Description is required, Please fill it properly')

        } else if (!this.author) {
          this.errors.push('Select Author Name')
        } else {
        this.$axios.post('http://localhost:8080/api/addArticle', {"title":`${this.title}`,"disc":`${this.disc}`,"author":`${this.author}`,} )
        .then(res => {
          console.log('In Add Article')
        })
        .catch(err => {
          console.log('ERROR:cannot perform the request' + err)
        });
        location.reload();
      }
     }
    },
    mounted() {
      console.log('I am in mounted')
      this.$axios.get('http://localhost:8080/api/users')
      .then(res => {
        console.log(res.data)
        this.users = res.data.users;
        console.log(this.users)
      })
      .catch(err => console.log('ERROR:cannot perform the request' + err));
    },
    asyncData({req, redirect}) {
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
</style>
