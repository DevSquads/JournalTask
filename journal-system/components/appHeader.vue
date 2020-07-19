<template>
  <mdb-navbar color="indigo" dark>
    <mdb-navbar-brand href="/">
     LEGENDARY News
    </mdb-navbar-brand>
    <mdb-navbar-toggler v-if="loggedIn">
      <mdb-navbar-nav>
        <mdb-nav-item href="/articles" active>Articles</mdb-nav-item>
        <mdb-nav-item href="/addArticle">Add Article</mdb-nav-item>
        <mdb-nav-item href="/dashboard">Dashboard</mdb-nav-item>
      </mdb-navbar-nav>
      <form>
        <mdb-navbar-nav>
          <mdb-nav-item @click="logout" href="#">Logut</mdb-nav-item>
        </mdb-navbar-nav>
      </form>
    </mdb-navbar-toggler>
  </mdb-navbar>
</template>

<script>
  import { mdbNavbar, mdbNavbarBrand, mdbNavbarToggler, mdbNavbarNav, mdbNavItem, mdbDropdown, mdbDropdownMenu, mdbDropdownToggle, mdbInput, mdbDropdownItem } from 'mdbvue';
  export default {
    name: 'appHeader',
    components: {
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
        loggedIn: true,

      };
    },
    methods:{
      logout(){
        console.log('emiting event')
        this.$emit('logout')
        // this.loggedIn = false;
      }
    }
  }
</script>
