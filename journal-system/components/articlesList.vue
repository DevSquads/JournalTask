<template lang="html">
  <div class="container">
    <mdb-jumbotron v-for="(article,index) in articles" v-bind:key="index" class="text-left mt-5">
      <h1 class="display-5"> {{ article.data.title }} </h1>
      <p class="lead"> {{ article.data.disc }} </p>
      <div class="text-left">
        <p>Author: <span class="grey-text" > {{article.data.author}} </span></p>
      </div>
      <div class="text-right">
        <p>published At: <span class="grey-text" > {{new Date(article.data.publishedAt._seconds*1000).toUTCString()}} </span></p>
      </div>
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
