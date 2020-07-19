<template>
  <div class="container pt-5 pb-5 mb-2">

    <section class="form-elegant">
      <mdb-row>
        <mdb-col md="3">

        </mdb-col>
        <mdb-col md="6">
          <mdb-card class="pt-5 pb-3">
            <mdb-card-body class="mb-5 mx-4">
              <div class="text-center">
                <h3 class="dark-grey-text mb-5"><strong>Login</strong></h3>
              </div>
              <form @submit.prevent="login">

                <div v-for="error in errors" class="text-center">
                  <p style="color:red;" >{{error}}</p>
                </div>

                <mdb-input class="pb-4" v-model="email" label="Your email" type="email"/>
                <mdb-input v-model="password" label="Your password" type="password" containerClass="mb-0"/>
                <div class="text-center pt-1">
                  <mdb-btn type="submit" color="indigo" rounded class="btn-block mt-5 mb-1 z-depth-1a"> Login</mdb-btn>
                </div>

              </form>
            </mdb-card-body>
          </mdb-card>
        </mdb-col>
        <mdb-col md="3">

        </mdb-col>
      </mdb-row>
    </section>

  </div>
</template>

<script>
  import * as firebase from 'firebase/app'
  import 'firebase/auth'
  import { mdbRow, mdbCol, mdbCard, mdbCardBody, mdbInput, mdbBtn, mdbIcon, mdbModal, mdbModalBody, mdbModalFooter } from 'mdbvue';
  export default {
    name: 'FormsPage',
    components: {
      mdbRow,
      mdbCol,
      mdbCard,
      mdbCardBody,
      mdbInput,
      mdbBtn,
      mdbIcon,
      mdbModal,
      mdbModalBody,
      mdbModalFooter
    },
    data() {
      return {
        email: "",
        password: "",
        errors:[]
      };
    },
    methods: {
      login(){
        this.errors = [];
        let re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        let result = re.test(String(this.email).toLowerCase());
        console.log(result);

        if(!this.email){
          this.errors.push('Please Enter your Email Address')
        } else if (result != true) {
          this.errors.push('Email is Badly Formated')
        } else if (!this.password) {
          this.errors.push('Please Enter Your Passwor')
        }
        else{
          firebase.auth().signInWithEmailAndPassword(this.email, this.password)
          .then(data=>{
            console.log(data);
            console.log('in login method');
            this.$router.push('/articles')
          }).catch(error => this.errors.push('There is no user record corresponding to this identifier. The user may have been deleted.'))
        }
      }
    }
  }
</script>

<style scoped>

  .form-elegant .font-small {
    font-size: 0.8rem; }

  .form-elegant .z-depth-1a {
    -webkit-box-shadow: 0 2px 5px 0 rgba(55, 161, 255, 0.26), 0 4px 12px 0 rgba(121, 155, 254, 0.25);
    box-shadow: 0 2px 5px 0 rgba(55, 161, 255, 0.26), 0 4px 12px 0 rgba(121, 155, 254, 0.25); }

  .form-elegant .z-depth-1-half,
  .form-elegant .btn:hover {
    -webkit-box-shadow: 0 5px 11px 0 rgba(85, 182, 255, 0.28), 0 4px 15px 0 rgba(36, 133, 255, 0.15);
    box-shadow: 0 5px 11px 0 rgba(85, 182, 255, 0.28), 0 4px 15px 0 rgba(36, 133, 255, 0.15); }

</style>
