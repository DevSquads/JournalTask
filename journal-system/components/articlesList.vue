<template lang="html">
  <div class="container">
    <mdb-jumbotron v-for="(article,index) in articles" v-bind:key="index" class="text-left mt-5">
      <h1 class="display-5"> {{ article.data.title }} </h1>
      <p class="lead"> {{ article.data.disc }} </p>
      <div class="text-right">
        <p>Author: <span class="grey-text" > {{article.data.author}} </span></p>
      </div>
      <!-- <mdb-btn color="primary" size="sm">Read more</mdb-btn> -->
    </mdb-jumbotron>
  </div>
</template>

<script>
  import { mdbJumbotron, mdbBtn } from 'mdbvue';
  export default {
    name: 'articleslist',
    components: {
      mdbJumbotron,
      mdbBtn
    },
    props:{
      articles:{
        type: Array,
        required: true
      },
    }

  }
</script>

<style lang="css" scoped>
</style>
