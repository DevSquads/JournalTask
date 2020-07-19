<template>
  <div class="main-content">
    <mdb-navbar color="indigo" dark>
      <mdb-navbar-brand href="/">
       LEGENDARY News
      </mdb-navbar-brand>
      <mdb-navbar-toggler  v-if="loggedIn">
        <mdb-navbar-nav>
          <mdb-nav-item href="/articles" active>Articles</mdb-nav-item>
          <mdb-nav-item href="/dashboard">Dashboard</mdb-nav-item>
          <mdb-nav-item href="/addArticle">Add Article</mdb-nav-item>
        </mdb-navbar-nav>
        <form>
          <mdb-navbar-nav>
            <mdb-nav-item @click="logout" href="#">Logut</mdb-nav-item>
          </mdb-navbar-nav>
        </form>
      </mdb-navbar-toggler>
    </mdb-navbar>
    <div id="page-container">
     <div id="content-wrap">
      <nuxt/>
     </div>
    <appFooter/>
  </div>
</div>

</template>

<script>
import * as firebase from 'firebase/app'
import 'firebase/auth'
import Cookies from 'js-cookie'
import appFooter from '~/components/appFooter.vue'
import { mdbNavbar, mdbNavbarBrand, mdbNavbarToggler, mdbNavbarNav, mdbNavItem, mdbDropdown, mdbDropdownMenu, mdbDropdownToggle, mdbInput, mdbDropdownItem } from 'mdbvue';

export default {
  components: {
    appFooter,
    mdbNavbar,
    mdbNavbarBrand,
    mdbNavbarToggler,
    mdbNavbarNav,
    mdbNavItem,
    mdbDropdown,
    mdbDropdownMenu,
    mdbDropdownToggle,
    mdbDropdownItem,
    mdbInput
  },
  data() {
    return {
      loggedIn: false,
    };
  },
  methods:{
    logout(){
      console.log('logging out')
      firebase.auth().signOut().then(() =>{
        this.$router.push('/')
      })
      this.loggedIn = false;
    },
    setupFirebase(){
      console.log('checking auth')
      firebase.auth().onAuthStateChanged(user => {
        if(user){
          console.log('logged in');
          this.loggedIn = true;
          console.log(user)
          firebase.auth().currentUser.getIdToken(true).then(token =>{
            Cookies.set('access_token' , token);
            let usercookie = firebase.auth().currentUser;
            Cookies.set('usercookie' , usercookie.email);
          })
        } else {
          this.loggedIn = false;
          Cookies.remove('access_token');
          Cookies.remove('usercookie');
        }
      })
    }
  },
  mounted(){
    this.setupFirebase();
  }
}
</script>

<style>
html{
  margin: 0;
  padding: 0;
  font-family: 'Noto Serif', serif;
}

body{
  margin: 0;
  padding: 0;
}

.page-footer{
  margin: 0!important ;
  position: absolute;
  bottom: 0;
  width: 100%;
}

.row{
  margin-left: 0!important ;
  margin-right: 0!important ;
}

body { font-family: 'Montserrat'!important; font-weight:normal!important;  }
h1 { font-family: 'Montserrat'!important; font-weight:normal!important;  }
h2 { font-family: 'Montserrat'!important; font-weight:normal!important;  }
h3 { font-family: 'Montserrat'!important; font-weight:normal!important;  }
h4 { font-family: 'Montserrat'!important; font-weight:normal!important;  }
h5 { font-family: 'Montserrat'!important; font-weight:normal!important;  }
h6 { font-family: 'Montserrat'!important; font-weight:normal!important;  }
p { font-family: 'Montserrat'!important; font-weight:normal!important;  }
q { font-family: 'Montserrat'!important; font-weight:normal!important;  }
li { font-family: 'Montserrat'!important; font-weight:normal!important;  }
a { font-family: 'Montserrat'!important; font-weight:normal!important;  }

#page-container {
  position: relative;
  min-height: 100vh;
}

#content-wrap {
  padding-bottom: 10rem;
}

</style>
